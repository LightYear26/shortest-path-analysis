package edu.princeton.cs.algs4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class GraphGenerator {

    // Graph class to represent a simple undirected graph
    public static class Graph {
        private final int V;   // Number of vertices
        private int E;         // Number of edges
        private List<Integer>[] adj;  // Adjacency list
        private double[][] weights;   // Edge weights

        // Constructor
        public Graph(int V) {
            this.V = V;
            this.E = 0;
            adj = (List<Integer>[]) new List[V];
            weights = new double[V][V];  // Matrix to store edge weights

            for (int v = 0; v < V; v++) {
                adj[v] = new ArrayList<>();
            }
        }

        public int V() {
            return V;
        }

        public int E() {
            return E;
        }

        // Add edge between v and w with a specified weight
        public void addEdge(int v, int w, double weight) {
            adj[v].add(w);
            adj[w].add(v);
            weights[v][w] = weight;
            weights[w][v] = weight;
            E++;
        }

        // Get the adjacency list for vertex v
        public Iterable<Integer> adj(int v) {
            return adj[v];
        }

        // Get the weight of the edge between v and w
        public double getWeight(int v, int w) {
            return weights[v][w];
        }
    }

    private static final class Edge implements Comparable<Edge> {
        private int v;
        private int w;
        private double weight; // Added weight for each edge

        private Edge(int v, int w, double weight) {
            if (v < w) {
                this.v = v;
                this.w = w;
            } else {
                this.v = w;
                this.w = v;
            }
            this.weight = weight;
        }

        public int compareTo(Edge that) {
            if (this.v < that.v) return -1;
            if (this.v > that.v) return +1;
            if (this.w < that.w) return -1;
            if (this.w > that.w) return +1;
            return Double.compare(this.weight, that.weight);
        }
    }

    // This class cannot be instantiated
    private GraphGenerator() { }

    /**
     * Writes the graph to a text file with weighted edges.
     * The file is overwritten each time the program is run.
     * @param G the graph to be written to file
     */
    private static void writeGraphToFile(Graph G) {
        // Specify the full path to the graph.txt file
        String filePath = "C:\\Users\\fernandez\\IdeaProjects\\Java_Introduction\\graph.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the number of vertices and edges
            writer.write(G.V() + " " + G.E() + " \n");

            // Write each edge with its weight
            for (int v = 0; v < G.V(); v++) {
                for (int w : G.adj(v)) {
                    if (v < w) { // Avoid duplicate edges (v,w) and (w,v)
                        // Assuming getWeight(v, w) fetches the weight of the edge (v, w)
                        double weight = G.getWeight(v, w);
                        writer.write(v + " " + w + " " + weight + "\n");
                    }
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a random simple graph containing {@code V} vertices and {@code E} edges with random weights.
     * @param V the number of vertices
     * @param E the number of edges
     * @return a random simple graph on {@code V} vertices, containing a total of {@code E} edges
     * @throws IllegalArgumentException if no such simple graph exists
     */
    public static Graph simple(int V, int E) {
        if (E > (long) V * (V - 1) / 2) throw new IllegalArgumentException("Too many edges");
        if (E < 0) throw new IllegalArgumentException("Too few edges");
        Graph G = new Graph(V);
        Set<Edge> set = new HashSet<Edge>();
        while (G.E() < E) {
            int v = StdRandom.uniformInt(V);
            int w = StdRandom.uniformInt(V);
            Edge e = new Edge(v, w, StdRandom.uniform(1.0, 10.0)); // Random weight between 1.0 and 10.0
            if ((v != w) && !set.contains(e)) {
                set.add(e);
                G.addEdge(v, w, e.weight); // Assuming addEdge now takes weight as a parameter
            }
        }
        // Write the graph to the file after generating it
        writeGraphToFile(G);
        return G;
    }

    /**
     * Returns a random simple graph on {@code V} vertices, with an edge between any two vertices with probability {@code p}.
     * This is sometimes referred to as the Erdos-Renyi random graph model.
     * @param V the number of vertices
     * @param p the probability of choosing an edge
     * @return a random simple graph on {@code V} vertices, with an edge between any two vertices with probability {@code p}
     * @throws IllegalArgumentException if probability is not between 0 and 1
     */
    public static Graph simple(int V, double p) {
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        Graph G = new Graph(V);
        for (int v = 0; v < V; v++) {
            for (int w = v + 1; w < V; w++) {
                if (StdRandom.bernoulli(p)) {
                    double weight = StdRandom.uniform(1.0, 10.0); // Random weight between 1.0 and 10.0
                    G.addEdge(v, w, weight); // Add edge with random weight
                }
            }
        }
        // Write the graph to the file after generating it
        writeGraphToFile(G);
        return G;
    }

    /**
     * Returns the complete graph on {@code V} vertices.
     * @param V the number of vertices
     * @return the complete graph on {@code V} vertices
     */
    public static Graph complete(int V) {
        return simple(V, 1.0);
    }

    // Add other graph generation methods as needed (bipartite, path, binaryTree, etc.)

    // Main method for testing purposes
    public static void main(String[] args) {
        int V = 10000; // Number of vertices
        int E = 15000000; // Number of edges
        Graph G = simple(V, E); // Generate a random simple graph with random weights and write to file
    }
}
