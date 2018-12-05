
/*
 *  @author Aashutosh Mallik
 *
 *
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class DFS {
    /**
     * This method creates a color map and a pred map (see example Graph.java
     * under Week 13 of Lecture notes) and an empty stack of type Stack<V>. It
     * colors each vertex "white" and sets the value of each vertex to null in the
     * pred map (see Graph.java). Then as long as there is a "white" vertex w
     * left, the method calls visitDFS(...) on the vertex w along with other
     * parameters. If visitDFS(...) returns false, then this method returns null.
     * Otherwise, it returns the stack containing the list of all vertices in a
     * topological order, which is produced by the visitDFS(...) method. If aGraph
     * is null, then it throws IllegalArgumentException.
     */
    public static <V> Stack<V> depthFirstSearch(DiGraph<V> graph) {
        if (graph == null) {
            throw new IllegalArgumentException();
        }

        HashMap<V, String> color = new HashMap<>();
        HashMap<V, V> pred = new HashMap<>();
        Stack<V> topoOrder = new Stack<>();

        for (V vertex : graph.vertices()) {
            color.put(vertex, "white");
            pred.put(vertex, null);
        }

        for (V vertex : graph.vertices()) {
            if (color.get(vertex).equals("white")) {
                boolean asyclic = visitDFS(graph, vertex, color, pred, topoOrder);
                if (asyclic == false) {
                    return null;
                }
            }
        }

        return topoOrder;
    }

    /**
     * This method implements an iterative depth-first search algorithm for
     * checking if the given graph is acyclic (has no cycles) and if so, generates
     * a stack (named topoOrder) of all vertices in a topological order and
     * returns true. Otherwise, it returns false. An iterative depth-first search
     * algorithm is given in under lecture notes for an undirected graph (Week
     * 13 of Lecture Notes). Here,
     * you need to modify the algorithm to make it work for a directed graph. Note
     * that the edge iterator citer (inside Graph.java under Lecture Notes) should
     * be changed from type Iterator<V> to type Iterator<Edge<V, Integer>>, and
     * that citer.next().getVertex(), instead of citer.next(), gives the vertex w.
     * If the vertex w is "gray", then the graph has a cycle. So the method
     * returns false. Whenever a vertex is colored "black", the vertex is pushed
     * onto the stack topoOrder. If the graph has no cycles (the execution reaches
     * the end of the method), then the method returns true.
     */
    protected static <V> boolean visitDFS(DiGraph<V> graph, V s, HashMap<V, String> color, HashMap<V, V> pred,
                                          Stack<V> topoOrder) {

        color.put(s, "gray");
        Stack<V> nodeStack = new Stack<>();
        Stack<Iterator<Edge<V, Integer>>> edgeStack = new Stack<>();
        Iterator<Edge<V, Integer>> siter = graph.adjacentTo(s).iterator();
        nodeStack.push(s);
        edgeStack.push(siter);

        while (!nodeStack.empty()) {
            V c = nodeStack.peek();
            Iterator<Edge<V, Integer>> citer = edgeStack.peek();

            if (citer.hasNext()) {
                Edge<V, Integer> other = citer.next();
                V w = other.getVertex();

                if (color.get(w).equals("white")) {
                    color.put(w, "gray"); // reached but not processed
                    pred.put(w, c);
                    Iterator<Edge<V, Integer>> witer = graph.adjacentTo(w).iterator();
                    nodeStack.push(w);
                    edgeStack.push(witer);
                } else if (color.get(w).equals("gray")) {
                    return false;
                }
            } else {
                color.put(c, "black"); // processed
                nodeStack.pop();
                edgeStack.pop();
                topoOrder.add(c);
            }
        }

        return true;
    }
}
