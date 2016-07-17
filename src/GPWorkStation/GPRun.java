package GPWorkStation;

import GPWorkStation.utilities.ElapsedTimer;
import GPWorkStation.view.GraphWriter;
import GPWorkStation.view.TreeView;
import snake.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class GPRun {

    static PrintWriter out;
    static PrintWriter out2;
    static PrintWriter out3;
    static PrintWriter out4;
    static String imageName;
    static String reevaluationfilename;
    static String readingfilename;
    static ArrayList<ScoredType<Node>> bestNodes;
    static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH-mm-ss";

    //population
    public ArrayList<ScoredType<Node>> pop;
    public ArrayList<ScoredType<Node>> newpop;
    public ArrayList<ScoredType<Node>> superPop;

    int genNo = 1;
    int err;
    double avgFitness = 0;

    SimpleSource ss;
    GraphWriter gw;
    ScoredType<Node> best = null;
    ScoredType<Node> avg = null;
    GPParameter gpp;

    public GPRun(GPParameter p_gpp) {
        gpp = p_gpp;
    }

    public void main() {
        
        
        bestNodes = new ArrayList<>(gpp.bestNodesSize);
        ElapsedTimer timer = new ElapsedTimer();

        ConsFigures.IDCounter = 0;

        System.out.println("***********************************************************");
        System.out.println("                     RUN NUMBER " + (1));
        System.out.println("***********************************************************");

        this.ss = new SimpleSource(gpp);

        gw = new GraphWriter("Generations", "Fitness", "Best Ind Fitness", "Gen Avg Fitness", "Evolution chart");

        createfiles();

        printData();

        ElapsedTimer t = new ElapsedTimer();
        //start hereh
        run();

        t.elapsed();

        // print out the best soulation
        ScoredType<Node> st = pop.get(pop.size() - 1);
        Node best = st.solution;
        System.out.println("<><><><><><><><><><><><>FINISHED<><><><><><><><><><><><>");
        System.out.println("Best: " + best.fitness);
        System.out.println(t);

        gw.printImage(imageName);

        System.out.println("Alltimes best score = " + this.best.bestScore);
        System.out.println("Alltimes best average score = " + this.avg.fitness);

        String tree = "";
        tree = best.print(tree);
        System.out.println(tree);

        out.println("<><><><><><><><><><><><>FINISHED<><><><><><><><><><><><>");
        out.println("Best: " + best.fitness);
        out.println("The elapsed Time = " + t);
        out.println("Best indvidual : " + tree);

        out.println("Alltimes best score = " + this.best.bestScore);

        tree = this.best.solution.print(tree);
        out.println(tree);

        tree = "";

        out.println("Alltimes best average score = " + this.avg.fitness);
        tree = this.avg.solution.print(tree);

        new GPTest(new StringBuffer(tree), gpp);
        out.println(tree);
        this.display(st);

        for (ScoredType<Node> temp : bestNodes) {
            tree = "";
            tree = temp.solution.print(tree);
            out3.print("Best Average : ");
            out3.println(st);
            out3.println(tree);
            out3.flush();
        }

        out.close();
        out2.close();
        out3.close();
        pop.clear();
        newpop.clear();

        bestNodes.clear();

        out4.close();

        timer.elapsed();
        System.out.println("Elapsed time : " + timer);

    }

    //create the initial population and then the test cases
    public void init() {
        pop = new ArrayList<>();
//        superPop = new ArrayList<ScoredType<Node>>(popSize);
        //create the initial population
        for (int i = 0; i < gpp.popSize; i++) {
            ScoredType<Node> indv = new ScoredType<>(ss.randRoot(gpp.depth));
//            if(pop.contains(indv))
            pop.add(indv);
        }
    }

    public void initRHAH() {
        pop = new ArrayList<>();
        ScoredType<Node> indv;
        // divided into tow full and grow
        int groupSize = gpp.popSize / (gpp.depth - 1);
        for (int i = 2; i <= gpp.depth; i++) {
            for (int j = 0; j <= groupSize; j++) {
                //to clear the constant and make sure that new constant will be created
//               ArithmeticTerminals.Constant c = new ArithmeticTerminals.Constant(-10);
                if (j < groupSize / 2) {
                    // this grow take 50%
                    indv = new ScoredType<>(ss.randRoot(i));
                } else {
                    // this full take the remaine
                    indv = new ScoredType<>(ss.randRoot2(i));
                }
                pop.add(indv);
            }
        }
    }

    public void run() {

        System.out.println("---------------- generatrion 1 ----------------");
        initRHAH();
        gw.paint();
        evalSort();

        for (genNo = 2; genNo <= gpp.nGens; genNo++) {
            breed2();
            System.out.println("---------------- generatrion " + genNo + " ----------------");
            evalSort();
            if (pop.get(pop.size() - 1).fitness == 211) {
                return;
            }
        }
    }

    public void display(int gen) {
        if (gen % gpp.repfreq == 0) {
            ScoredType<Node> best = pop.get(gpp.popSize - 1);
            String title = "Gen: " + gen + " best: " + best.fitness;
            TreeView tv = new TreeView(best.solution);
            tv.showTree(title);
        }
    }

    public void display(ScoredType<Node> ind) {
        String title = " fitness: " + ind.fitness;
        TreeView tv = new TreeView(ind.solution);
        tv.showTree(title);
    }

    /**
     * this method will select the pop and calc the fintess for each indivaiaul
     */
    public void evalSort() {
        if (newpop != null && newpop.size() > 0) {
            pop.addAll(newpop);
            newpop.clear();
        }
        for (ScoredType<Node> st : pop) {

            evalSort(st);

            avgFitness += st.fitness;
//            avgTicks += st.ticks;

            if (bestNodes.size() < gpp.bestNodesSize && st.fitness != null && st.fitness > 0) {
                bestNodes.add(st.copy());
            } else if (bestNodes.size() > 0 && bestNodes.get(bestNodes.size() - 1).compareTo(st) <= 0) {
                bestNodes.set(bestNodes.size() - 1, st.copy());
            }
            Collections.sort(bestNodes);

            if ((best == null) || (best.bestScore < st.bestScore)) {
                best = null;
                best = st.copy();//new ScoredType(st.solution.copy(),st.fitness,st.bestScore,st.noOfGens);
            }

            if ((avg == null) || (avg.fitness < st.fitness)) {
                avg = null;
                avg = st.copy();//new ScoredType(st.solution.copy(),st.fitness,st.bestScore,st.noOfGens);
            }
        }

        if (newpop != null && newpop.size() > 0) {
            pop.addAll(newpop);
            newpop.clear();
        }

        Collections.sort(pop);

//        for(ScoredType<Node> st:pop){
//            System.out.println(st);
//        }
        int dif = pop.size() - gpp.popSize;
        for (int i = 0; i < dif; i++) {
            pop.remove(0);
        }
        ScoredType<Node> st = pop.get(gpp.popSize - 1);
//        if(err > 0){
//            double per = ((double)err/(double)pop.size())*100 ;
//            System.out.println("number of faulty individuals : "+err+" - "+per+"%");
//        }
        System.out.println("Best :" + st);

        System.out.println(st.solution.print(""));
        System.out.println("Generation average Fitness ; " + avgFitness / gpp.popSize);
//        System.out.println("Generation average Ticks ; "+avgTicks/popSize);
        System.out.println(err + "(" + err / pop.size() + "%) faulty individuals");
        err = 0;
//        System.out.println(treesSize);
//        System.out.println("Best Single run :"+maxTreeGen+" - "+maxGen);
//        out.println(genNo+"\t\t"+pop.get(popSize-1).fitness+"\t\t"+maxGen);

        gw.addData1(pop.get(pop.size() - 1).fitness);
        gw.addData2(avgFitness / gpp.popSize);
        gw.repaint();

        out.println(genNo + "\t" + pop.get(gpp.popSize - 1).fitness + "\t" + pop.get(gpp.popSize - 1).bestScore + "\t" + pop.get(gpp.popSize - 1).noOfMazes + "\t" + avgFitness / gpp.popSize);
        out.flush();
    }

    public void evalSort(ScoredType<Node> st) {
        try {
            for (int i = 0; i < gpp.NRuns; i++) {
                SnakeGPRun snake = new SnakeGPRun(/*!true, !true, true, */170, false, false, false, st, gpp.NRuns, gpp);
//                Snake2 snake = new Snake2(st, NRuns);
                Thread thread = new Thread(snake);
                thread.start();

                thread.join();
            }

            // to calc the avareage of player after 5 run
            st.fitness = st.ss.mean();
            st.bestScore = (int) st.ss.max();
            st.ticks = st.ss.ticks();
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
//            ex.printStackTrace();
            System.out.println(st.solution.print(""));
            st.fitness = 0.0;
            err++;
        }
    }

    public void breed() {
        // assumes that the population has already been sorted
        // with the best ones first in the list
        // first extract the parents
        ArrayList<ScoredType<Node>> parents = new ArrayList<>();
//        System.out.println("pop size : "+pop.size());
        for (int i = 1; i <= gpp.nParents; i++) {
            parents.add(pop.get(pop.size() - i));
        }
//        System.out.println("parents size : "+parents.size());
        // now empty the population
        pop.clear();
        // now add best one back in
        for (int i = 0; i < gpp.reproductionRate; i++) {
            pop.add(parents.get(i));
        }
        // and now add the rest
        for (int i = 0; i < gpp.popSize - gpp.reproductionRate; i++) {
            pop.add(new ScoredType<>(makeChild(parents)));
        }
    }

    public void breed2() {

        newpop = new ArrayList<>();
        avgFitness = 0;
        for (int i = 0; i < gpp.popSize; i++) {
            ScoredType st;
            do {
                st = new ScoredType<>(makeChild(pop));
                evalSort(st);
            } while (st.fitness == null);
            if (st.fitness > pop.get(0).fitness) {
                newpop.add(st);
                pop.remove(0);
                avgFitness += st.fitness;
            }
        }
    }

    //create the new generation by choosing a random parent and then change it using
    // Mutation or Crossover depending on a randomly chosen mutation prpality.
    public Node makeChild(ArrayList<ScoredType<Node>> parents) {
        ArrayList<ScoredType<Node>> tournNodes = new ArrayList<>();
        Node child;
        for (int i = 0; i < gpp.tournRate; i++) {
            tournNodes.add(parents.get(gpp.r.nextInt(parents.size())));
        }
        Collections.sort(tournNodes);
        double t = gpp.r.nextDouble();
        if (t < gpp.mutProb) {
//                System.out.println("mutation");
            // select a single parent and mutate it
            //            Node parent = parents.get(r.nextInt(parents.size())).solution;
            child = ss.mutate(tournNodes.get(tournNodes.size() - 1).solution);
        } else if (t < gpp.mutProb + gpp.gMutProb) {
            child = ss.guidedMutate(tournNodes.get(tournNodes.size() - 1));
        } else {
            Node p1 = tournNodes.get(tournNodes.size() - 1).solution;

            do {
//                    System.out.println("crossover");
                tournNodes.clear();
                for (int i = 0; i < gpp.tournRate; i++) {
                    tournNodes.add(parents.get(gpp.r.nextInt(parents.size())));
                }
                Collections.sort(tournNodes);
                Node p2 = tournNodes.get(tournNodes.size() - 1).solution;
                child = ss.crossover(p1, p2);
            } while (child == null);
        }
        return child;
    }

    void printData() {
        out.println("Population       : " + gpp.popSize);
        out.println("Generations      : " + gpp.nGens);
        out.println("No of runs       : " + gpp.NRuns);
        out.println("Mut prob         : " + gpp.mutProb);
        out.println("G-Mut prob       : " + gpp.gMutProb);
        out.println("Max depth        : " + gpp.depth);
        if (gpp.scenario) {
            out.println("Test Time        :" + gpp.testTime);
            out.println("No Of Scenarios  :" + gpp.noOfScenarios);
            out.println("Escape           :" + gpp.escape);
            out.println("Clear Pills      :" + gpp.clearPills);
            out.println("Clear Powers     :" + gpp.clearPowers);
            out.println("Set Edible Score :" + gpp.setEdibleScore);
            out.println("Key Scenario     :" + gpp.keyScenario);
        }
        SimpleSource ss = new SimpleSource(gpp);
        for (Node n : ss.functions) {
            out.print(n.name() + " - ");
        }
        out.println();

        for (Node n : ss.logicalOperators) {
            out.print(n.name() + " - ");
        }
        out.println();

        for (Node n : ss.arthOperators) {
            out.print(n.name() + " - ");
        }
        out.println();

        for (Node n : ss.dataterms) {
            out.print(n.name() + " - ");
        }
        out.println();
        for (Node n : ss.terms) {
            out.print(n.name() + " - ");
        }
        out.println();
        for (Node n : ss.logicalOperators) {
            out.print(n.name() + " - ");
        }
        out.println();

        out.println("\n\nGen\t\tBest");

    }

    static void createfiles() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

        try {
            out = new PrintWriter(new FileWriter("data\\" + sdf.format(cal.getTime()) + ".txt"));
            out2 = new PrintWriter(new FileWriter("data\\" + sdf.format(cal.getTime()) + "-max.txt"));
            readingfilename = "data\\" + sdf.format(cal.getTime()) + "-avg.txt";
            out3 = new PrintWriter(new FileWriter(readingfilename));
            reevaluationfilename = "data\\" + sdf.format(cal.getTime()) + "-top.txt";
            out4 = new PrintWriter(new FileWriter(reevaluationfilename));
            imageName = "data\\" + sdf.format(cal.getTime()) + ".png";
        } catch (Exception ex) {
            System.out.println("FILE OUTPUT ERROR : " + ex.getMessage());
        }
    }
}
