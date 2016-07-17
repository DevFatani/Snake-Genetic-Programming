package GPWorkStation;

public abstract class Binary extends Node {
    @Override
    public int arity() {
        return 2;
    }

    @Override
    public String print(String tree) {
        tree = tree.concat(this.name()+"(");
        tree = na[0].print(tree);
        tree = tree.concat(",");
        tree = na[1].print(tree);
        tree = tree.concat(")");    
        return tree;
    }
}