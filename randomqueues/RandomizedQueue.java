/**
 * Author     :
 * Program    :
 * Date       :
 * Assignment :
 */
package randomqueues;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author TALONted
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Random r = new Random();
    private Thing aThing;
    private int N;
    private ArrayList<Thing> allThings = new ArrayList<>();

    /**
     * Make Thing
     * initializes theThing
     */
    private class Thing {

        Item theThing;

        Thing(Item item) {
            this.theThing = item;
        }

        @Override
        public String toString() {
            return "Item :" + theThing;
        }

    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        aThing = null;
    }
    
    // return if queue is Empty 
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * return the amount of Items in the queue
     *
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * Add an item to a random place in the queue
     *
     * @param item
     */
    public void enqueue(Item item) {

        aThing = new Thing(item);
        if (allThings.isEmpty()) {
            allThings.add(aThing);
        } else {
            allThings.add(r.nextInt(allThings.size() + 1), aThing);
            //System.out.println("Index is = " + allThings.indexOf(aThing));
        }
        N++;
    }

    /**
     * remove an item
     *
     * @return the removed item
     */
    public Item dequeue() {

        checkQueue();
        int x = r.nextInt(N);
        Thing removed = allThings.get(x);
        Item rm = removed.theThing;
        allThings.remove(removed);
        N--;
        return rm;

    }

    /**
     * return an item without removing it
     *
     * @return
     */
    public Item Sample() {
        int x = r.nextInt(N);
        Thing removed = allThings.get(x);
        Item rm = removed.theThing;
        return rm;
    }

    @Override
    public Iterator<Item> iterator() {
        
        return new QueueIterator();

    }

    private class QueueIterator implements Iterator<Item> {

        int size = 0;
        Item currentThing = allThings.get(size).theThing;

        @Override
        public boolean hasNext() {
            return currentThing == null;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                System.out.println("has next is true " + currentThing  +" should equal Null");
                return null;
            }
            Item item = currentThing;
            Item hold = allThings.get(size + 1).theThing;
            currentThing = hold;
            System.out.print(item + " , ");
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private void checkQueue() {
        if (allThings.isEmpty()) {
            throw new NoSuchElementException("You can't remove from nothing.");
        }
    }

    @Override
    public String toString() {

        return "Things --> " + N + " Last thing added " + aThing;
    }
    /**
     * Commented out for final purposes
     */
//    public static void main(String[] args) throws IOException  {
//        
//        RandomizedQueue<String> myQueue = new RandomizedQueue<>();
//       
//        /**
//         * test remove null 
//         */
//        //System.out.println(myQueue.dequeue());
//        FileReader fs = new FileReader("/Users/TALONted/Desktop/SLCC_School/2017-Fall/Data&Algorithims/RandomQueuesDequeues/src/randomqueues/wordsforqueue");
//        BufferedReader bs = new BufferedReader(fs);
//        String currentLine;
//        while((currentLine = bs.readLine()) != null) {
//            myQueue.enqueue(currentLine);
//        }
//        System.out.println(myQueue);
//        
//        while(myQueue.iterator().hasNext()) {
//            System.out.print(myQueue.iterator().next() + ", ");
//        }
//        
//        System.out.println(myQueue.Sample());
//        System.out.println(myQueue.Sample());
//        System.out.println(myQueue.Sample());
//        System.out.println(myQueue.Sample());
//        System.out.println(myQueue.Sample());
//        System.out.println(myQueue.Sample());
//        System.out.println(myQueue.Sample());
//        System.out.println(myQueue.Sample());
//        System.out.println(myQueue.Sample());
//        System.out.println(myQueue.Sample());
//        System.out.println(myQueue.Sample());
//        System.out.println(myQueue.Sample());
//        
//        System.out.println(myQueue.dequeue());
//        System.out.println(myQueue.dequeue());
//        System.out.println(myQueue.dequeue());
//        System.out.println(myQueue.dequeue());
//        System.out.println(myQueue.dequeue());
//        System.out.println(myQueue.dequeue());
//        System.out.println(myQueue.dequeue());
//        System.out.println(myQueue.dequeue());
//        System.out.println(myQueue.dequeue());
//        System.out.println(myQueue.dequeue());
//        System.out.println(myQueue.dequeue());
//        System.out.println(myQueue.dequeue());
//        
//        System.out.println(myQueue);
//        while(myQueue.iterator().hasNext()) {
//            System.out.print(myQueue.iterator().next() + ", ");
//        }
//    }

}
