package sample;

import sample.ListInterface;

import java.util.ArrayList;

public class ListClass<T> implements ListInterface<T>
//{
// implements Iterable <Node>
// implements Iterable <Node> implements ListInterface<T>
{

    private Node<T> firstNode;
    private int length;
    private Object ListProperty;

    /**
     *
     */
    public ListClass() {
        firstNode = null;
        length = 0;
    }

//    public static <E> E[] toArray(E[] a, Class<E> clazz) {
//        Node<E> root = null;
//        Node<E> prev = null;
//        Node<E> curr = null;
//
//        for (E e : a) {
//            curr = new Node<E>(e);
//            if (prev != null) {
//                prev.setNext(curr);
//            }
//            else {
//                root = curr;
//            }
//            prev = curr;
//        }
//
//        curr =  root;
//
//        // Cast is unsafe. I don't know a better way to do this.
//        E[] newArray = (E[]) Array.newInstance(clazz, a.length);
//        int i = 0;
//        while (curr != null) {
//            newArray[i] = curr.value();
//            curr = curr.next();
//            i++;
//        }
//
//        return newArray;
//    }

//    public ArrayList arrayListProperty() {
//        Node previous = firstNode;
//        Node current = firstNode;
//
//        ArrayList<Object> temp = new ArrayList<>();
//
//        int positionInList = 0; //first node in list
//        current = current.next; //current moved on to next item
//
//        while (current != null) {
//            positionInList++;
//            current = current.next;
//            previous = previous.next;
//            current.Data = ListProperty ;
//            temp.add(ListProperty);
//        }
//
//        {
////            Node temp = new Node((T) newEntry, current);
// //           previous.next = temp;
//            length++;
// //           return;
//            /////
//            Node i = firstNode;
//            while (i.next != null) {
//                temp.add((Property) i.Data);
//                i = i.next;
//            }
//            return temp;
//        }
//    }

    // create arraylist loop to copy all items from our linked list to arraylist
    // arraylist <temp> = new arrayList<>()
    //Node<i> = startofourlinkedlist
    //while (i.next != null) {
    //      item.add (i.data)
//
//    if(index < 0 || index > length){   //invalid index
//    return;// false;
//}
//        if(index == 0){
//    add(newEntry);
//    return;
//}
//    int positionInList = 1; //second node in list
//    current=current.next; //current moved on to next item
//
//        while (current != null && positionInList < index) {
//    positionInList++;
//    current = current.next;
//    previous = previous.next;
//}
//
//        if (positionInList == index) {
//    Node temp = new Node((T) newEntry, current);
//    previous.next = temp;
//    length++;
//    return;
//}
//        return;
    //   i=i.next;

    public boolean addToLast(T newEntry) {
        Node current = firstNode;
        Node previous = firstNode;

        current = current.next;


        current = current.next; //current moved on to next item

        while (current != null) {
            current = current.next;
            previous = previous.next;
            if (current.next == null) {
                Node temp = new Node((T) newEntry, current);
                previous.next = temp;

            }
            return true;
        } return false;
    }

    public boolean add(T newEntry) {
        Node temp = firstNode;
        firstNode = new Node((T)newEntry, temp);
        length++;
        return true;
    }

    public void add1(T newEntry) {
        Node temp = firstNode;
        firstNode = new Node((T)newEntry, temp);
        length++;
    }

    public void add(int index, T newEntry) {
        Node previous = firstNode;
        Node current = firstNode;

        if(index < 0 || index > length){   //invalid index
            return;// false;
        }
        if(index == 0){
            add(newEntry);
            return;
        }
        int positionInList = 1; //second node in list
        current=current.next; //current moved on to next item

        while (current != null && positionInList < index) {
            positionInList++;
            current = current.next;
            previous = previous.next;
        }

        if (positionInList == index) {
            Node temp = new Node((T) newEntry, current);
            previous.next = temp;
            length++;
            return;
        }
        return;
    }

    public T remove(int index) {
        Node previous = firstNode;
        Node current = firstNode;

        if(index < 0 || index >= length){   //invalid index
            return null;
        }
        if(index == 0){   //remove startOfList item
            Node temp = firstNode;
            firstNode = firstNode.next;
            length--;
            return (T)(temp.Data);  //Note: need to cast
        }
        int positionInList = 1; //second node in list
        current=current.next; //current moved on to next item

        while (previous != null && positionInList < index) {
            positionInList++;
            current = current.next;
            previous = previous.next;
        }

        if (positionInList == index) {
            T temp =  (T)(current.Data);  //Note: need to cast
            previous.next = current.next;
            length--;
            return temp;
        }
        return null;
    }
    /**

     */

    public void clear() {
        firstNode = null;
        length = 0;
    }

    public T set(int index, Object anEntry) { // like replace
        return null;
    }

    public T get(int index) // like getEntry
    {
        if (index < 0 || index >= length) //invalid index
            return null;
        int positionInList = 0;
        Node temp = firstNode;
        while (positionInList < index) {
            temp = temp.next;
            positionInList++;
        }
        return (T) temp.Data;
    }

    public boolean contains(T anEntry) {
        int positionInList = 0;
        Node temp = firstNode;
        while (positionInList < length) {
            if (temp.Data.equals(anEntry)) {
                return true;
            }
        }
        return false;
    }
//    public Property containsString(String string) {
//        int positionInList = 0;
//        Node temp = firstNode;
//        while (positionInList < length) {
//            if (temp.Data.equals(string)) {
//               return (Property) temp.Data;
//            }
//        }
//        return null;
//    }

    public int size() // like getLength
    {
        return length;
    }

    public boolean isEmpty() {
        if (length == 0)
            return true;
        return false;
    }

    public String toString() {
        int positionInList = 0;
        Node temp = firstNode;
        String out = "";
        while (positionInList < length) {
            out += ""+positionInList+": "+ temp.Data+"\n";
            positionInList++;
            temp=temp.next;
        }
        return out;
    }

//    public String getCounties(){
//        int positionInList = 0;
//        Node temp = firstNode;
//        String out = "";
//        while (positionInList < length) {
//            for (Property property : prop)
//        }
//
//    }

//    @Override
//    public Iterator<javafx.scene.Node> iterator() {
//        return null;
//    }
//
//    @Override
//    public void forEach(Consumer<? super javafx.scene.Node> action) {
//
//    }
//
//    @Override
//    public Spliterator<javafx.scene.Node> spliterator() {
//        return null;
//    }


    /*******************************************************************************/
    class Node<T1> {  //inner class
        private T1 Data; // this is a piece of primitive data as a textbook type example
        private Node next;

        private Node(T1 newData, Node newNext) {
            Data = newData;
            next = newNext;
        }
    }
    /*******************************************************************************/

}
