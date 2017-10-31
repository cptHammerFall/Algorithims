package Percolation;

import edu.princeton.cs.algs4.UF;


/*
 * NAME         : Talon Dillman 
 * DATE         : 
 * CLASS        : 
 * ASSIGNMENT   : 
 */
/**
 *
 * @author TALONted
 */
public class Percolation {

    /**
     * create 2D array of booleans to check open/not all variables to utilize
     * throughout Percolation. size is for the utilization of a 1D array of the
     * Union Find
     */
    private final boolean[][] PercolationCheck;
    private final int size;
    private final UF unionfind;
    private final int topfloat;
    private final int bottomfloat;
    private int opensites = 0;
    /**
     * Creates a new percolation graph at size N both with booleans and int for
     * now
     * @param N
     */
    public Percolation(int N) {

        PercolationCheck = new boolean[N][N];

        /**
         * Initialize the UnionArray with the full size of the entire Make a
         * floating point that the first row will hit. Makes a floating point on
         * the bottom as well to check percolates().
         */
        size = N;
        topfloat = size * size;
        bottomfloat = topfloat + 1;
        unionfind = new UF(bottomfloat + 1);
        for (int i = 0; i < size; i++) {

            unionfind.union(i, topfloat);
        }
        /**
         * Initialize the Percolation Array with False values
         */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                PercolationCheck[i][j] = false;
            }
        }
    }
    /**
     * takes in a point to open it. Or turn it True.
     * @param i
     * @param j
     */
    public void open(int i, int j) {
        /**
         * Make sure the point isn't already open If it's not check for unions
         * to be made
         */
        if (!isOpen(i, j)) {
            PercolationCheck[i][j] = true;
            opensites++;
            checkSurrounding(i, j);
        }
    }
    /**
     * Check to see if the point selected is already open
     * @param i
     * @param j
     * @return a boolean saying its open
     */
    public boolean isOpen(int i, int j) {

        boolean open = false;
        if (PercolationCheck[i][j]) {
            open = true;
        }
        return open;
    }
    /**
     * Check to see if the point opened is also connected to the @int topfloat
     * @param i
     * @param j
     * @return
     */
    public boolean isFull(int i, int j) {
        boolean full = false;
        if (unionfind.connected(topfloat, PercolationArray1D(i, j)) && PercolationCheck[i][j]) {
            full = true;
        }
        return full;
    }
    /**
     * return a boolean that checks for @int topfloat connects to @int bottom
     * float. 
     * If the bottom site is open and connected topfloat it will union to
     * bottomfloat as well
     * @return
     */
    public boolean percolates() {
        boolean percos = false;
        for (int i = topfloat - 1; i >= topfloat - size; i--) {
            if (unionfind.connected(i, topfloat)) {
                unionfind.union(i, bottomfloat);
            }
        }
        if (unionfind.connected(topfloat, bottomfloat)) {
            percos = true;
            //System.out.println("Percolates with " + numberOfOpenSites() + " open sites");
        }

        return percos;
    }

    public int numberOfOpenSites() {
        return opensites;
    }
    /**
     * Checks the surrounding areas avoiding going out of bounds to create union
     * finds Makes sure that the check does not go out of bounds. Only allows a
     * UF to the bottomfloat if it connects to the top.
     * @param i
     * @param j
     */
    private void checkSurrounding(int i, int j) {
        if (!(i - 1 < 0)) {
            if (PercolationCheck[i - 1][j]) {
                unionfind.union(PercolationArray1D(i, j), PercolationArray1D(i - 1, j));
            }
        }
        if (!(i + 1 >= size)) {
            if ((PercolationCheck[i + 1][j])) {
                unionfind.union(PercolationArray1D(i + 1, j), PercolationArray1D(i, j));
            }
        }
        if (!(j - 1 < 0)) {
            if (PercolationCheck[i][j - 1]) {
                unionfind.union(PercolationArray1D(i, j), PercolationArray1D(i, j - 1));
            }
        }
        if (!(j + 1 >= size)) {
            if (PercolationCheck[i][j + 1]) {
                unionfind.union(PercolationArray1D(i, j), PercolationArray1D(i, j + 1));
            }
        }
        if (i == size - 1 && unionfind.connected(PercolationArray1D(i, j), topfloat)) {
            unionfind.union(PercolationArray1D(i, j), bottomfloat);
        }
    }
    /**
     * used to get the values of the 2D arrays position in a 1D array
     * @param x
     * @param y
     * @return
     */
    private int PercolationArray1D(int x, int y) {

        int position = (x * size) + y;
        //System.out.println("1D position = " + position);
        return position;
    }
}
