package GPWorkStation;

import java.util.Objects;
import snake.Snake;

public abstract class Node {

    static final Integer TERMINAL = 1;
    static final Integer FUNCTION = 2;
    static final Integer LOGICALTERMINAL = 3;
    static final Integer ARITHMETICTERMINAL = 4;
    static final Integer LOGICALOPERATOR = 5;
    static final Integer COMPARISONOPERATOR = 6;
    static final Integer ARITHMETICOPERATOR = 7;
    static final Integer CONSTANT = 7;

    public abstract Object f(Snake snake);

    public abstract String name();

    public abstract int arity();

    public int attachments() {
        return 0;
    }

    public Object num() {
        return 0;
    }

    public Node[] na;

    public Node att[];

    public Integer[] types;

    public Integer type;

    public double fitness;

    public int ID;

    public int visits;

    public Node() {
        att = new Node[attachments()];
        na = new Node[arity()];
        types = new Integer[arity()];
        setID();
        visits = 0;
    }

    //to generate the population
    public Node grow(NodeSource ns, int depth) {
        for (int i = 0; i < na.length; i++) {
            if (types[i].equals(LOGICALOPERATOR)) {
                na[i] = ns.getAnyLogical(depth - 1);
                if (na[i] != null) {
                    na[i].grow(ns, depth - 1);
                }
            } else if (types[i].equals(ARITHMETICOPERATOR)) {
                na[i] = ns.getAnyArithmetic(depth - 1);
                na[i].grow(ns, depth - 1);
            } else {
                na[i] = ns.getAny(depth - 1);
                na[i].grow(ns, depth - 1);
            }
        }
        // return it for convenient usage
        return this;
    }

    // no needed
    public Node full(NodeSource ns, int depth) {

        for (int i = 0; i < na.length; i++) {
            if (Objects.equals(types[i], LOGICALOPERATOR)) {
                na[i] = ns.getAnyLogical(depth - 1);
                na[i].grow(ns, depth - 1);
            } else if (Objects.equals(types[i], ARITHMETICOPERATOR)) {
                na[i] = ns.getAnyArithmetic(depth - 1);
                na[i].grow(ns, depth - 1);
            } else {
                na[i] = ns.getAnyFunctions(depth - 1);
                na[i].grow(ns, depth - 1);
            }
        }
        // return it for convenient usage
        return this;
    }

    public int size() {
        int s = 1;
        for (Node n : na) {
            s += n.size();
        }
        return s;
    }

    public int depth() {
        int d = 0;
        for (Node n : na) {
            d = Math.max(d, n.depth());
        }
        return d + 1;
    }

    public Node getNode(int[] a) {
        // a is used as a reference parameter
        // it gets decremented as we traverse the tree
        if (a[0] <= 0) {
            return this;
        } else {
            a[0]--;
            for (Node n : na) {
                Node ret = n.getNode(a);
                if (ret != null) {
                    return ret;
                }
            }
            return null;
        }
    }

    public Node getNode(int ID) {
        // a is used as a reference parameter
        // it gets decremented as we traverse the tree
        if (this.ID == ID) {
            return this;
        } else {
            for (Node n : na) {
                Node ret = n.getNode(ID);
                if (ret != null) {
                    return ret;
                }
            }
            return null;
        }
    }

    public Node copy() {
        // need to make a new copy of this
        // and then of it's children
        Node n = newNode();
        for (int i = 0; i < na.length; i++) {
            n.na[i] = na[i].copy();
        }
        return n;
    }

    public Node copyReplace(Node loc, Node sub) {
        if (this == loc) {
            // deliberate reference equality check
            return sub;
        } else {
            Node n = newNode();
            for (int i = 0; i < na.length; i++) {
                n.na[i] = na[i].copyReplace(loc, sub);
            }
            return n;
        }
    }

    @Override
    public String toString() {
        // simply return the size of this tree
        return String.format("Tree size: %d, depth: %d; %s", size(), depth(), name());
    }

    public Node newNode() {
        // make a new node of this type, but don't set anything up
        try {
            Node n = this.getClass().newInstance();
            // System.out.println("Made a new one: " + this.getClass().getName());
            return n;
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    abstract public String print(String tree);

    public boolean equals(Object o) {
        Node n = (Node) o;
        boolean e = false;
        if (this.name().equals(n.name())) {
            e = true;
        } else {
            return e;
        }

        for (int i = 0; i < this.arity(); i++) {
            Node a = this.na[i];
            Node b = n.na[i];
            e = a.equals(b);
            if (e == false) {
                return e;
            }
        }
        return e;
    }

    public void setID() {
        this.ID = ConsFigures.IDCounter++;
    }
}
