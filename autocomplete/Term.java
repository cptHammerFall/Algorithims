/**
 * Author     :
 * Program    :
 * Date       :
 * Assignment :
 */
package autocomplete;

import java.util.Comparator;

/**
 *
 * @author TALONted
 */
public class Term implements Comparable<Term> {

    private String querry;
    private double weight;

    // Initialize a term with the given query string and weight.
    public Term(String querry, double weight) {
        this.querry = querry;
        this.weight = weight;
    }

    public static Comparator<Term> byQuerryOrder() {
        
        return new QuerryOrder();
    }
    
    private static class QuerryOrder implements Comparator<Term> {
        
        @Override
        public int compare (Term a, Term b) {
            //System.out.println("Hey you made it here!");
            return a.querry.compareTo(b.querry);
        }
    }
    // Compare the terms in descending order by weight.
    public static Comparator<Term> byReverseWeight() {
        return new ReverseWeightOrder();
    }

    private static class ReverseWeightOrder implements Comparator<Term> {

        @Override
        public int compare(Term a, Term b) {
            return (int) (((int) a.weight - b.weight)) * (-1);
        }
    }

    /**
     * Creates a new PrefixOrder comparison search
     *
     * @param r
     * @return
     */
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException("Can't compare at a negative length");
        }
        return new PrefixOrder(r);
    }
    
    // Compare the terms in lexicographic order but using only the first r characters of each query. 
    private static class PrefixOrder implements Comparator<Term> {

        private int length;

        public PrefixOrder(int r) {
            length = r;
        }

        @Override
        public int compare(Term a, Term b) {

            String termA = compareLetters(a.querry, length);
            String termB = compareLetters(b.querry, length);

            //System.out.println(termA + " to " + termB  + " compare= " + termA.compareTo(termB));
            return termA.compareTo(termB);
        }
    }

    /**
     * Pull a subsrting from p of length r
     *
     * @param q
     * @param r
     * @return
     */
    private static String compareLetters(String q, int r) {

        /**
         * if r is longer than the length of the word just return the word
         */
        if (r <= q.length()) {
            String sq = q.substring(0, r + 1);
            return sq;
        }
        return q;
    }

    // Compare the terms in lexicographic order by query.
    @Override
    public int compareTo(Term that) {
        return Integer.parseInt(this.querry) - Integer.parseInt(that.querry);
    }

    // Return a string representation of the term in the following format:
    // the weight, followed by a tab, followed by the query.
    @Override
    public String toString() {
        return querry + "\t weight = " + weight;
    }
}
