package GPWorkStation;

public interface NodeSource {
    public Node getAny(int depth);
    public Node getAnyFunctions(int depth);
    public Node getAnyLogical(int depth);
    public Node getAnyArithmetic(int depth);
}
