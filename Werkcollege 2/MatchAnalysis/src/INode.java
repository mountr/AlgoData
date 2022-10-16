public interface INode {
    public String getName();
    @Override
    public int hashCode();
    @Override
    public boolean equals(Object object);
    @Override
    public String toString();
}