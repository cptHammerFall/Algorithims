/**
 * Author     :
 * Program    :
 * Date       :
 * Assignment :
 */
package djikstrastest;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.StdOut;
import java.io.File;

/**
 *
 * @author TALONted
 */
public class SymbolEdgeWeightedDigraph {

    private final int V;
    private int E;
    private ST<String, Integer> Cities;

    public SymbolEdgeWeightedDigraph(In in) {

        Out out = new Out("/Users/TALONted/Desktop/SLCC_School/2017-Fall/Data&Algorithims/DjikstrasTest/src/djikstrastest/Airports.txt");
        int Vcount = 0;
        int Ecount = 0;
        Cities = new ST<>();
        String connections = "";
        while (in.hasNextLine()) {

            Ecount++;
            String string = in.readLine();
            String[] line = string.split(",");
            String v = line[0];
            String w = line[1];

            if (!Cities.contains(v)) {
                Cities.put(v, Vcount);
                Vcount++;
            }
            if (!Cities.contains(w)) {
                Cities.put(w, Vcount);
                Vcount++;
            }
            Double weight = Double.parseDouble(line[2]);

            connections += Cities.get(v) + " " + Cities.get(w) + " " + weight + "\n";
        }

        this.E = Ecount;
        this.V = Vcount;

        out.println(V);
        out.println(E);
        out.println(connections);
    }

    public ST getST() {
        return Cities;
    }

    public static void main(String[] args) {

        File csv = new File("/Users/TALONted/Desktop/SLCC_School/2017-Fall/Data&Algorithims/DjikstrasTest/src/djikstrastest/Airports.csv");
        In in = new In(csv);

        SymbolEdgeWeightedDigraph newFile = new SymbolEdgeWeightedDigraph(in);
        in = new In("/Users/TALONted/Desktop/SLCC_School/2017-Fall/Data&Algorithims/DjikstrasTest/src/djikstrastest/Airports.txt");
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        System.out.println("The Graph");
        System.out.println(G);
        System.out.println("Shortes Path");
        int s = 0;
        String S = "";

        DijkstraSP sp = new DijkstraSP(G, s);
        ST<String, Integer> st = newFile.getST();
        for (String x : st.keys()) {
            if (s == st.get(x)) {
                S = x;
            }
        }
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                String TO = "";
                for (String x : st.keys()) {
                    if (t == st.get(x)) {
                        TO = x;
                    }
                }
                StdOut.printf("%s to %s (%.2f)  ", S, TO, sp.distTo(t));
                if (sp.hasPathTo(t)) {
                    for (DirectedEdge e : sp.pathTo(t)) {
                        String v1 = "";
                        String v2 = "";
                        for (String x : st.keys()) {
                            if (e.from() == st.get(x)) {
                                v1 = x;
                            }
                            if (e.to() == st.get(x)) {
                                v2 = x;
                            }
                        }
                        StdOut.print(v1 + " -> " + v2 + " : " + e.weight() + "\t");
                    }
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
    }
}
