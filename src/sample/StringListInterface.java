package sample;

public interface StringListInterface {
   // public boolean add(String newEntry);
    public void add(int index,String newEntry);
    public Object remove(int index);
    public void clear();
    public Object set(int index, String anEntry);// like replace
    public Object get(int index); // like getEntry
    public boolean contains(String anEntry);
    public int size(); // like getLength
    public boolean isEmpty();
}
