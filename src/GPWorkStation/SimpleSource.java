package GPWorkStation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Iterator;

public class SimpleSource implements NodeSource {

    static final Integer TERMINAL = 1;
    static final Integer FUNCTION = 2;
    static final Integer LOGICALTERMINAL = 3;
    static final Integer ARITHMETICTERMINAL = 4;
    static final Integer LOGICALOPERATOR = 5;
    static final Integer COMPARISONOPERATOR = 6;
    static final Integer ARITHMETICOPERATOR = 7;

    // the probability of selecting a terminal node when
    // depth is not zero
    int tourn = 5;
    double termProb = 0.2;
    ArrayList<Node> terminals;
    ArrayList<Node> dataTerminals;
    ArrayList<Node> all;
    ArrayList<Node> functions;
    int nInputs;
    // input array 'a'
    double[] a;
    static Random r = new Random();

    Node[] func = new Node[]{new Functions.Ifte()};

    Node[] terms = new Node[]{
        new ActionTerminals.TurnLeft(),
        new ActionTerminals.TurnRight(),
        new ActionTerminals.Ahead(),};

    Node[] dataterms;

    Node[] arthterms = new Node[]{};

    Node[] comparisonOperators = new Node[]{};

    Node[] logicalOperators = new Node[]{
        new LogicalOperators.AND(),
        new LogicalOperators.OR(),
        new LogicalOperators.NOT(),};

    Node[] arthOperators = new Node[]{};

    public void setInputs(double[] x) {
        System.arraycopy(x, 0, a, 0, x.length);
    }

    public SimpleSource(GPParameter p_gpp) {

        a = new double[nInputs];

        terminals = new ArrayList<>();

        dataTerminals = new ArrayList<>();

        functions = new ArrayList<>();

        all = new ArrayList<>();

        for (Node n : terms) {
            all.add(n);
            terminals.add(n);
        }

        if (p_gpp.NUM_OBSTACLES > 0) {
            System.out.println("with obstacles");

            this.dataterms = new Node[]{
                new LogicalTerminals.IsFoodInRow(),
                new LogicalTerminals.IsDangerWithObstcles2StepsAhead(p_gpp),
                new LogicalTerminals.IsDangerWithObstaclesAhead(p_gpp),
                new LogicalTerminals.IsFoodAhead(p_gpp),
                //                 new LogicalTerminals.IsFoodInRow(),
                new LogicalTerminals.IsFoodLeft(p_gpp),
                new LogicalTerminals.IsFoodRight(p_gpp),
                new LogicalTerminals.IsMovingUp(),
                new LogicalTerminals.IsMovingDown(),
                new LogicalTerminals.IsMovingLeft(),
                new LogicalTerminals.IsMovingRight(),
                new LogicalTerminals.IsWall2StepsAhead(p_gpp),
                new LogicalTerminals.IsWallAhead(p_gpp),
                new LogicalTerminals.IsWallLeft(p_gpp),
                new LogicalTerminals.IsWallRight(p_gpp),
                new LogicalTerminals.IsBodyAhead(),
                new LogicalTerminals.IsBodyRight(),
                new LogicalTerminals.IsBodyLeft(),
                new LogicalTerminals.IsObstacles(),
                new LogicalTerminals.IsObstacles2Step()
            };

            dataTerminals.addAll(Arrays.asList(dataterms));
        } else {
            System.out.println("without obstacles");

            this.dataterms = new Node[]{
                //                new LogicalTerminals.IsDanger2StepsAhead(p_gpp),
                new LogicalTerminals.IsDangerAhead(p_gpp),
                new LogicalTerminals.IsFoodAhead(p_gpp),
                new LogicalTerminals.IsFoodInRow(),
                new LogicalTerminals.IsFoodLeft(p_gpp),
                new LogicalTerminals.IsFoodRight(p_gpp),
                new LogicalTerminals.IsMovingUp(),
                new LogicalTerminals.IsMovingDown(),
                new LogicalTerminals.IsMovingLeft(),
                new LogicalTerminals.IsMovingRight(),
                new LogicalTerminals.IsWall2StepsAhead(p_gpp),
                new LogicalTerminals.IsWallAhead(p_gpp),
                new LogicalTerminals.IsWallLeft(p_gpp),
                new LogicalTerminals.IsWallRight(p_gpp),
                new LogicalTerminals.IsBodyAhead(),
                new LogicalTerminals.IsBodyRight(),
                new LogicalTerminals.IsBodyLeft()
            };

            dataTerminals.addAll(Arrays.asList(dataterms));

        }
        for (Node n : func) {
            all.add(n);
            functions.add(n);
        }
    }

    @Override
    public Node getAny(int depth) {
        if (depth <= 1 || r.nextDouble() < termProb) {
            if (terminals.size() > 0) {
                return randPick(terminals);
            }
        } else {
            if (functions.size() > 0) {
                return randPick(functions);
            }
        }
        return null;
    }

    @Override
    public Node getAnyFunctions(int depth) {
        if (depth <= 1) {
            if (terminals.size() > 0) {
                return randPick(terminals);
            }
        } else {
            if (functions.size() > 0) {
                return randPick(functions);
            }
        }
        return null;
    }

    @Override
    public Node getAnyLogical(int depth) {
        double d = r.nextDouble();
        if (depth <= 1 || d <= 0.33) {
            return randPick(dataTerminals);
        } else {
            if (d < 0.66) {
                if (logicalOperators.length > 0) {
                    return randPick(logicalOperators);
                } else if (comparisonOperators.length > 0) {
                    return randPick(comparisonOperators);
                }
            }
        }
        return randPick(dataTerminals);
    }

    @Override
    public Node getAnyArithmetic(int depth) {
        double d = r.nextDouble();
        if (depth <= 1 || d <= 0.5) {
            if (arthterms.length > 0) {
                return randPick(arthterms);
            }
        } else {
            if (arthOperators.length > 0) {
                return randPick(arthOperators);
            }
        }
        return null;
    }

    public Node randRoot(int depth) {
        // break this into two phases
        // 1: get a node
        // 2: grow it
        // System.out.println("Original: " + depth());
        Node node = getAny(depth);

        // System.out.println("Depth " + node.depth());
        // System.exit(0);
        node.grow(this, depth);
        // System.out.println(depth + " -> " + node.depth());
        return node;
    }

    public Node MutRandRoot(int depth, int type) {
        Node node;
        if (type == LOGICALOPERATOR || type == LOGICALTERMINAL) {
            node = getAnyLogical(depth);
        } else if (type == ARITHMETICOPERATOR || type == ARITHMETICTERMINAL) {
            node = getAnyArithmetic(depth);
        } else {
            node = getAnyFunctions(depth);
        }
        node.grow(this, depth);
        return node;
    }

    public Node randRoot2(int depth) {
        // break this into two phases
        // 1: get a node
        // 2: grow it
        // System.out.println("Original: " + depth());
        Node node = getAnyFunctions(depth);
        // System.out.println("Depth " + node.depth());
        // System.exit(0);
        node.full(this, depth);
        // System.out.println(depth + " -> " + node.depth());
        return node;
    }

    public Node randPick(ArrayList<Node> nodes) {
        return nodes.get(r.nextInt(nodes.size())).newNode();
    }

    public Node randPick(Node[] nodes) {
        return nodes[r.nextInt(nodes.length)].newNode();
    }

    public Node randNode(Node root) {
        // select a random node from the tree
        // find the size of the tree, choose
        // a random integer in that range, then iterate
        // over the tree to make the selection
        int selection = r.nextInt(root.size());
        // System.out.println("Selection = " + selection);
        return root.getNode(new int[]{selection});
    }

    public Node worstNode(ScoredType<Node> root) {

        int maxFunc = 0, lastFuncId = 0;

        // to find the FUNCTION that was the orst during the run
        Iterator itr = root.lastFuncs.keySet().iterator();
        while (itr.hasNext()) {
            int tempKey = (Integer) itr.next();
            int tempValue = root.lastFuncs.get(tempKey);

            if (maxFunc < tempValue) {
                maxFunc = tempValue;
                lastFuncId = tempKey;
            }
        }

        int maxTerm = 0, lastTermId = 0;

        itr = root.lastTerms.keySet().iterator();
        while (itr.hasNext()) {
            int tempKey = (Integer) itr.next();
            int tempValue = root.lastTerms.get(tempKey);

            if (maxTerm < tempValue) {
                maxTerm = tempValue;
                lastTermId = tempKey;
            }
        }

        if (maxTerm == maxFunc && lastFuncId != 0) {
            return root.solution.getNode(lastFuncId);
        } else {
            return root.solution.getNode(lastTermId);
        }

    }

    public Node randNode(Node root, int maxSize) {
        // iterate over all the nodes adding them to a collection of
        // suitably shallow nodes
        ArrayList<Node> nodes = new ArrayList<>();
        findNodes(root, maxSize, nodes);
        return nodes.get(r.nextInt(nodes.size()));
    }

    private void findNodes(Node root, int maxSize, ArrayList<Node> nodes) {
        if (root.size() <= maxSize) {
            nodes.add(root);
        }
        for (Node ch : root.na) {
            findNodes(ch, maxSize, nodes);
        }

    }

    public Node mutate(Node root) {
        Node mutNode = randNode(root);
        Node sub = MutRandRoot(mutNode.depth(), mutNode.type);
        return root.copyReplace(mutNode, sub);
    }

    public Node guidedMutate(ScoredType<Node> root) {
        // note the depth then make a copy

        Node mutNode = worstNode(root);
        Node sub = MutRandRoot(mutNode.depth(), mutNode.type);

        return root.solution.copyReplace(mutNode, sub);
        // now replace it
    }

    public Node crossover(Node p1, Node p2) {
        Node crossNode = randNode(p1);
        Node sub = null;
        int i;
        int t = 5;
        for (i = 0; i < t; i++) {
            sub = randNode(p2, crossNode.size());
            if (sub.type.equals(crossNode.type)) {
                break;
            } else if (((crossNode.type.equals(FUNCTION)) || (crossNode.type.equals(TERMINAL)))
                    && ((sub.type.equals(FUNCTION)) || (sub.type.equals(TERMINAL)))) {
                break;
            } else if (((crossNode.type.equals(COMPARISONOPERATOR)) || (crossNode.type.equals(LOGICALOPERATOR)) || (crossNode.type.equals(LOGICALTERMINAL)))
                    && ((sub.type.equals(COMPARISONOPERATOR)) || (sub.type.equals(LOGICALOPERATOR)) || (sub.type.equals(LOGICALTERMINAL)))) {
                break;
            } else if (((crossNode.type.equals(ARITHMETICOPERATOR)) || (crossNode.type.equals(ARITHMETICTERMINAL)))
                    && ((sub.type.equals(ARITHMETICOPERATOR)) || (sub.type.equals(ARITHMETICTERMINAL)))) {
                break;
            }
        }
        if (i >= t) {
            return null;
        }
        return p1.copyReplace(crossNode, sub);
    }

}
