package sample;

public interface ListInterface <T>{

    public boolean add( T newEntry);
    public void add(int index, T newEntry);
    public Object remove(int index);
    public Object set(int index, T editEntry);
    public Object get(int index);
    public boolean contains(T anEntry);
    public int size();
    public boolean isEmpty();


}
