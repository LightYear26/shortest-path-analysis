package edu.princeton.cs.algs4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Ensure correct arguments are passed (graph file and source vertex)
        if (args.length < 2) {
            System.out.println("Usage: java Main <graph_file> <source_vertex>");
            return;
        }

        // Use the path of the graph.txt file generated earlier
        String graphFile = "graph.txt"; // Path to graph.txt from args
        int source = Integer.parseInt(args[1]); // Source vertex from arguments

        // Read the graph from the input file
        EdgeWeightedDigraph G = readGraphFromFile(graphFile);

        // Check if the graph was read successfully
        if (G == null) {
            System.out.println("Error reading the graph.");
            return;
        }

        // Ensure the source vertex is within bounds
        if (source < 0 || source >= G.V()) {
            System.out.println("Invalid source vertex.");
            return;
        }

        // Running Dijkstra Algorithm
        DijkstraSP dijkstraSP = new DijkstraSP(G, source);
        System.out.println("Dijkstra Algorithm:");
        for (int t = 0; t < G.V(); t++) {
            if (dijkstraSP.hasPathTo(t)) {
                System.out.printf("%d to %d (%.2f)  ", source, t, dijkstraSP.distTo(t));
                for (DirectedEdge e : dijkstraSP.pathTo(t)) {
                    System.out.print(e + "   ");
                }
                System.out.println();
            } else {
                System.out.printf("%d to %d         no path\n", source, t);
            }
        }

        // Running Bellman-Ford Algorithm
        BellmanFordSP bellmanFordSP = new BellmanFordSP(G, source);
        System.out.println("Bellman-Ford Algorithm:");
        for (int t = 0; t < G.V(); t++) {
            if (bellmanFordSP.distTo(t) < Double.POSITIVE_INFINITY) {
                System.out.printf("%d to %d (%.2f)  ", source, t, bellmanFordSP.distTo(t));
                for (DirectedEdge e : bellmanFordSP.pathTo(t)) {
                    System.out.print(e + "   ");
                }
                System.out.println();
            } else {
                System.out.printf("%d to %d         no path\n", source, t);
            }
        }
    }

    // Helper function to read the graph from a file using BufferedReader
    private static EdgeWeightedDigraph readGraphFromFile(String graphFile) {
        EdgeWeightedDigraph G = null;
        File file = new File(graphFile);

        if (!file.exists()) {
            System.out.println("Error: Graph file does not exist.");
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("graph.txt"))) {
            // Read number of vertices and edges
            String line = br.readLine();
            if (line != null) {
                String[] firstLine = line.split("\\s+");
                int V = Integer.parseInt(firstLine[0]);
                int E = Integer.parseInt(firstLine[1]);

                System.out.println("Reading graph with " + V + " vertices and " + E + " edges.");

                G = new EdgeWeightedDigraph(V);

                // Reading edges
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\s+");
                    if (parts.length != 3) {
                        System.out.println("Error: Invalid edge format in the file.");
                        continue;
                    }
                    int v = Integer.parseInt(parts[0]);
                    int w = Integer.parseInt(parts[1]);
                    double weight = Double.parseDouble(parts[2]);
                    System.out.println("Edge: " + v + " -> " + w + " with weight " + weight);
                    DirectedEdge e = new DirectedEdge(v, w, weight);
                    G.addEdge(e);
                }
            } else {
                System.out.println("Error: File format is incorrect.");
                return null;
            }
        } catch (IOException e) {
            System.out.println("Error reading the graph file: " + graphFile);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        return G;
    }
}
