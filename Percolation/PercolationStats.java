/**
 * Author     :
 * Program    :
 * Date       :
 * Assignment :
 */
package Percolation;

import edu.princeton.cs.introcs.StdStats;
import java.util.Random;

/**
 * Automated Percolation runner
 * @author TALONted
 */
public class PercolationStats {
    /**
     * percArray is to hold the open sites / total sites 
     * Random r to create random numbers
     * size initialized to N for ease of use
     */
    private double percArray[];
    private Random r;
    private int size;
    /**
     * N used for size 
     * t to run x times
     * creates a percolation check T times
     * prints out the mean, standard deviation, confidence high/low
     * @param N
     * @param T 
     */
    public PercolationStats(int N, int T) {
        percArray = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);
            r = new Random();
            size = N;
            while (!percolation.percolates()) {
                int check1 = r.nextInt(size);
                int check2 = r.nextInt(size);
                if (!percolation.isOpen(check1, check2)) {
                    percolation.open(check1, check2);
                }
            }
            double dn = N * N;
            percArray[i] = percolation.numberOfOpenSites() / dn;
        }
        System.out.println("Mean = " + mean());
        System.out.println("Standard Deviation = " +stddev());
        System.out.println("Confidence Low = "+confidenceLow());
        System.out.println("Confidence High = " + confidenceHigh());
    }
/**
 * returns the mean of a double array
 * @return 
 */
    public double mean() {
        return StdStats.mean(percArray);
    }
/**
 * returns the standard deviation of a double array
 * @return 
 */
    public double stddev() {
        return StdStats.stddev(percArray);
    }
/**
 * returns the lowest point of percolation
 * @return 
 */
    public double confidenceLow() {
        double low = 1;
        for (double i : percArray) {
            if (i < low) {
                low = i;
            }
        }
        return low;
    }
/**
 * return the highest point of percolation
 * @return 
 */
    public double confidenceHigh() {
        double high = 0;
        for (double i : percArray) {
            if (i > high) {
                high = i;
            }
        }
        return high;
    }
}
