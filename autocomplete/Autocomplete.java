/*
 * NAME         : Talon Dillman 
 * DATE         : 
 * CLASS        : 
 * ASSIGNMENT   : 
 */
package autocomplete;

import edu.princeton.cs.introcs.In;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

/**
 *
 * @author TALONted
 */
public class Autocomplete {
    
    private Comparator<Term> row = Term.byReverseWeight();
     private Comparator<Term> qur = Term.byQuerryOrder();
    private int matches;
    private Term[] terms;
    /**
     * initialize a private array terms with the given array but sorted
     * @param terms 
     */
    public Autocomplete(Term[] terms) {
        
        
        this.terms = terms.clone();
        printTerms(this.terms, 25);
        Arrays.sort(this.terms,qur);
        System.out.println("\n\n\n");
        printTerms(this.terms, 25);
        
    }
    /**
     * gives all the terms that's prefix match the given prefix
     * @param prefix
     * @return 
     */
    public Term[] allTerms(String prefix) {
        /**
         * Make sure prefix is not null
         */
        if(prefix == null) {
            throw new NullPointerException("Cannot find null values");
        }
        //make a new Term of weight 0;
        Term search = new Term(prefix, 0);
        
        /**
         * Find all the words that share the prefix. 
         * Find first instance
         * and Last instance
         */
        System.out.println(terms[1] + " compare to " + search);
        
        int first = BinarySearchDeluxe.FirstIndexOf(terms, search, Term.byPrefixOrder(prefix.length()-1));
        int last = BinarySearchDeluxe.LastIndexOf(terms, search, Term.byPrefixOrder(prefix.length()-1));
        /**
         * If no matches return an empty Term[]
         */
        if (first == -1) {
            return new Term[0];
        }
        matches = last - first;
        //System.out.println("Number of matches = " + numberOfMatches());
        
        /**
         * Create a new Terms[] length of matches
         * Used Arrays copy of Range method to create a new array;
         */
        //Term[] temp = new Term[numberOfMatches()];
        Term[] temp = Arrays.copyOfRange(terms, first, last);
        Arrays.sort(temp, row);
        return temp;
    }
    
    public int numberOfMatches(String prefix) {
        Term[] terms = allTerms(prefix).clone();
        matches = terms.length;
        return matches;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * Read in Cities
         */
        Term[] terms = makeArray("/Users/TALONted/Desktop/SLCC_School/2017-Fall/Data&Algorithims/Autocomplete/src/autocomplete/wiktionary.txt");
        terms = makeArray("/Users/TALONted/Desktop/SLCC_School/2017-Fall/Data&Algorithims/Autocomplete/src/autocomplete/cities.txt");;
        Autocomplete auto = new Autocomplete(terms);
        
        String pretest = "Gun";
        Term[] prefixtest = auto.allTerms(pretest).clone();
        System.out.println("***************************\nIN ORDER BY WEIGHT\n******************************* ");
        printTerms(prefixtest, prefixtest.length);
        
//        System.out.println("***************************\nOUT OF ORDER\n******************************* ");
//        printTerms(terms, 100);
//        
//        System.out.println("***************************\nIN ORDER BY WEIGHT\n******************************* ");
        //Arrays.sort(terms, row);
        //printTerms(terms, 100);
//
        //System.out.println("***************************\nCOMPARE PREFIX\n******************************* ");
        //System.out.println("Difference between the prefixes of length 10 are = " + pre.compare(terms[3], terms[7]));
        
        
        /**
         * Read in Wiktionary stuff
         */
        terms = makeArray("/Users/TALONted/Desktop/SLCC_School/2017-Fall/Data&Algorithims/Autocomplete/src/autocomplete/cities.txt");;

    }

    private static void printTerms(Term[] terms, int length) {
        for (int i = 0; i < length; i++) {
            System.out.println(terms[i]);
        }
    }

    private static Term[] makeArray(String file) {
        
        In in = new In(file);
        int N = in.readInt();
        Term[] terms = new Term[N];
        
        for (int i = 0; i < N; i++) {
            double weight = 0;
            try {
                weight = in.readDouble();
            } catch (InputMismatchException e) {
                System.out.print(e.getMessage()); //try to find out specific reason.
            }
            in.readChar();
            String querry = in.readLine();
            terms[i] = new Term(querry, weight);
        }
        return terms;
    }

}
