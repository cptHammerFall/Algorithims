/**
 * Author     :
 * Program    :
 * Date       :
 * Assignment :
 */
package randomqueues;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author TALONted
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    // construct an empty deque
    private Thing first;
    private Thing last;

    private int N;

    private class Thing {

        Item theThing;
        Thing next;
        Thing previous;

        Thing(Item item) {
            this.theThing = item;
            this.next = null;
            this.previous = null;
        }

        @Override
        public String toString() {
            String out = "Item: " + theThing;
            return out;
        }
    }

    public Deque() {
        first = null;
        last = null;
        //myDeque = new LinkedList();
    }

    //check deque for elements
    public boolean isEmpty() {

        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }
    // insert the item at the front

    public void addFirst(Item item) {
        checkItem(item);
        Thing newThing = new Thing(item);

        if (first == null) {
            first = newThing;
            last = newThing;
        } else {
            Thing oldFirstThing = first;
            first = newThing;
            first.next = oldFirstThing;
            oldFirstThing.previous = first;
        }
        N++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        checkItem(item);
        Thing newThing = new Thing(item);

        if (last == null) {
            first = newThing;
            last = newThing;
        } else {
            Thing oldLastThing = last;
            last = newThing;
            last.previous = oldLastThing;
            oldLastThing.next = first;
        }
        N++;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        checkDeque();
        Thing oldFirst = first;
        first = first.next;
        N--;
        return oldFirst.theThing;

    }

    // delete and return the item at the end
    public Item removeLast() {
        checkDeque();
        Thing oldLast = last;
        last = last.previous;
        N--;
        return oldLast.theThing;
    }

    private void checkItem(Item item) {
        if (item == null) {
            throw new NullPointerException("You cannot add a null item.");
        }
    }

    private void checkDeque() {
        if (isEmpty()) {
            throw new NoSuchElementException("You cannot remove nothing from nothing.");
        }
    }

    //return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {

        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Thing currentThing = first;

        @Override
        public boolean hasNext() {
            return currentThing == null;
        }

        @Override
        public Item next() {

            if (hasNext()) {
                return null;
            }
            Item item = currentThing.theThing;
            currentThing = currentThing.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public String toString() {
        return "First " + first + " ---- Last " + last + " ---- Elements: " + N;
    }

    public static void main(String[] args) throws IOException {

        Deque<String> mainDeque = new Deque<>();
        FileReader fs = new FileReader("/Users/TALONted/Desktop/SLCC_School/2017-Fall/Data&Algorithims/RandomQueuesDequeues/src/randomqueues/wordsforqueue");
        
        BufferedReader bs = new BufferedReader(fs);
        String currentLine;
        int i = 12;
        while ((currentLine = bs.readLine()) != null) {
            
            if(i%12 > 1)
            mainDeque.addFirst(currentLine);
            else
                mainDeque.addLast(currentLine);
            i++;
        }
        System.out.println(mainDeque);

        while (mainDeque.iterator().hasNext()) {
            System.out.print(mainDeque.iterator().next() + ", ");
        }
        
//
//        System.out.println("Add Green First");
//        mainDeque.addFirst("Green");
//        System.out.println(mainDeque);
//        System.out.println("Add Orange First");
//        mainDeque.addFirst("Orange");
//        System.out.println(mainDeque);
////        System.out.println("Add Null");
////        System.out.println("Null");
////        mainDeque.addFirst(null);
//        System.out.println("Add Blue Last");
//        mainDeque.addLast("Blue");
//        System.out.println(mainDeque);
//        System.out.println("Add Black First");
//        mainDeque.addFirst("Black");
//        System.out.println(mainDeque);
//        System.out.println("Add White Last");
//        mainDeque.addLast("White");
//        System.out.println(mainDeque);
//        System.out.println("Add Green First");
//        mainDeque.addFirst("Green");
//        System.out.println(mainDeque);
//        System.out.println("Add Yellow Last");
//        mainDeque.addLast("Yellow");
//        System.out.println(mainDeque);
//        System.out.println("Add Red Last");
//        mainDeque.addLast("Red");
//        System.out.println(mainDeque);
//
//        System.out.println("\n\n\n\n");
//        System.out.println("test removing items");
//        System.out.println(mainDeque.size());
//        System.out.println(mainDeque.removeFirst());
//        System.out.println(mainDeque.removeFirst());
//        System.out.println(mainDeque.removeLast());
//        System.out.println(mainDeque.removeLast());
//        System.out.println(mainDeque.size());
//
//        while (mainDeque.iterator().hasNext()) {
//            System.out.println(mainDeque.iterator().next());
//        }
    }

}
