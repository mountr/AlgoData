
public interface IEdge {
    public int getWeight();
    public INode getStart();
    public INode getEnd();
    @Override
    public int hashCode();
    @Override
    public boolean equals(Object object);
    @Override
    public String toString();
}