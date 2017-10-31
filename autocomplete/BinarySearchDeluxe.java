/**
 * Author     : Talon Dillman
 * Program    : Autocomplete.java
 * Date       : 10-30-2017
 * Assignment : Autocomplete
 */
package autocomplete;

import java.util.Comparator;

/**
 *
 * @author TALONted
 */
public class BinarySearchDeluxe {

    /**
     * private variables for use through code
     */
    private static int start;
    private static int end;
    private static int mid;

    /**
     * Find the first instance of the key your are looking for.
     *
     * @param <Key>
     * @param a --> array to look through
     * @param key --> to look for
     * @param comparator
     * @return
     */
    public static <Key> int FirstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        /**
         * if passed a null search value for key through exception.
         */
        if (key == null || a == null || comparator == null) {
            throw new NullPointerException("Can not find null values.");
        }
        /**
         * if Array doesn't exist return -1
         */
        if (a.length == 0) {
            System.out.println("lenght 0");
            return -1;
        }
        /**
         * initialize a start and end point.
         */
        end = a.length - 1;
        start = 0;
        /**
         * loop through array looking for the first instance of Key in a[]
         */
        while (end - start > 1) {

            mid = (end + start) / 2;
            //System.out.println("\nend = " + end + "---" + a[end] + " \nstart = " + start + "---" + a[start] + " \nmid = " + mid + "---" + a[mid]);
            if (comparator.compare(key, a[mid]) < 0) {
                end = mid - 1;
            } else if (comparator.compare(key, a[mid]) > 0){
                start = mid + 1;
            } else {
                end = mid ;
            }

        }
        /**
         * return start first if possible.
         */
        if (comparator.compare(key, a[start]) == 0) {
            return start;
        }
        /**
         * otherwise return the last element of a[] that equals key
         */
        if (comparator.compare(key, a[end]) == 0) {
            return end;
        }
        /**
         * if nothing equals key from a[] return -1
         */
        return -1;
    }

    /**
     * Does the same as FirstIndexOf but finds the last element of a[] that
     * equals key
     *
     * @param <Key>
     * @param a
     * @param key
     * @param comparator
     * @return
     */
    public static <Key> int LastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        /**
         * if passed a null search value for key through exception.
         */
        if (key == null || a == null || comparator == null) {
            throw new NullPointerException("Can not find null values.");
        }
        /**
         * if Array doesn't exist return -1
         */
        if (a.length == 0) {
            return -1;
        }
        /**
         * initialize a start and end point.
         */
        end = a.length - 1;
        start = 0;

        /**
         * loop through array looking for the first instance of Key in a[]
         */
        while (end - start > 1) {
            mid = (end + start) / 2;
            System.out.println("\nend = " + end + "---" + a[end] + " \nstart = " + start + "---" + a[start] + " \nmid = " + mid + "---" + a[mid]);
            if (comparator.compare(key, a[mid]) < 0) {
                end = mid - 1;
            } else if (comparator.compare(key, a[mid]) > 0){
                start = mid + 1;
            } else {
                start = mid ;
            }
        }
        /**
         * return end first if possible.
         */
        if (comparator.compare(key, a[end]) == 0) {
            return end;
        }
        /**
         * otherwise return the fist element of a[] that equals key
         */
        if (comparator.compare(key, a[start]) == 0) {
            return start;
        }
        /**
         * if nothing equals key from a[] return -1
         */
        return -1;
    }

}
