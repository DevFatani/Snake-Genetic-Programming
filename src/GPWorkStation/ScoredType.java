package GPWorkStation;

import GPWorkStation.utilities.StatSummary;

import java.util.HashMap;

public class ScoredType<T> implements Comparable<ScoredType> {
    public T solution;
    public Double fitness;
    public Double avgScore;
    public int bestScore;
    public int noOfGens=0;
    public double noOfMazes=0;
    public int genNo;
    public Double ticks;
    public StatSummary ss = new StatSummary();

    public HashMap<Integer,Integer> lastFuncs = new HashMap<>();
    public HashMap<Integer,Integer> lastTerms = new HashMap<>();

    @Override
    public int compareTo(ScoredType o) {
        if (fitness.compareTo(o.fitness) > 0){
            return 1;
        }
        else if (fitness.compareTo(o.fitness) < 0){
            return -1;
        }
        else{
            return 0;
        }
    }

    public ScoredType(T solution, Double fitness) {
        this.solution = solution;
        this.fitness = fitness;
    }

    public ScoredType(T solution, Double fitness, int bestScore, int noOfGens) {
        this.solution = solution;
        this.fitness = fitness;
        this.bestScore = bestScore;
        this.noOfGens = noOfGens;
    }

    public ScoredType(ScoredType<T> st) {
        this.solution = st.solution;
        this.fitness = st.fitness;
        this.avgScore = st.avgScore;
        this.bestScore = st.bestScore;
        this.noOfGens = st.noOfGens;
        this.noOfMazes = st.noOfMazes;
        this.genNo = st.genNo;
        this.ticks = st.ticks;
        this.ss = st.ss;
        this.lastFuncs = st.lastFuncs;
        this.lastTerms = st.lastTerms;
    }

    public ScoredType(T solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return fitness+ " : "+solution + " : " + avgScore+ " : " + bestScore+ " : " + noOfMazes+ " mazes "+ " : " + ticks+ " Ticks ";
    }

    public ScoredType<T> copy (){
        return new ScoredType<>(this);
    }

    @Override
    public boolean equals(Object o){
        ScoredType<T> st = (ScoredType)o;
        return st.solution.equals(this.solution);
    }



    public void reset(){
        this.fitness = 0.0;
        this.avgScore = 0.0;
        this.bestScore = 0;
        this.noOfGens = 0;
        this.noOfMazes = 0;
        this.genNo = 0;
        this.ticks = 0.0;
        this.ss = new StatSummary();
    }
}