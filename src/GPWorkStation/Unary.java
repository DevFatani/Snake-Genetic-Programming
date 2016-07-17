package GPWorkStation;

public abstract class Unary extends Node {
    public int arity() {
        return 1;
    }

    @Override
    public String print(String tree) {
            tree = tree.concat(this.name()+"(");
            tree = na[0].print(tree);
            tree = tree.concat(")");
            return tree;
        }
}