/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azaz1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kruskal's algorithm
 * @author Pavel Micka
 */
public class Kruskal {
    /**
     * Finds the minimum spanning tree of the given grapg with n vertices (the vertices are numbered
     * 0, 1, ..., n-1)
     * @param edges edges of the undirected graph
     * @param nodeCount number of vertices (n)
     * @return minimum spanning tree
     */
    public static List<Edge> kruskalAlgorithm(List<Edge> edges, int nodeCount) {
        DisjointSet ds = new DisjointSet(nodeCount);
        List<Edge> spanningTree = new ArrayList<Edge>();
        Collections.sort(edges);
        int i = 0;
        while (i != edges.size() && spanningTree.size() != nodeCount - 1) {
            Edge e = edges.get(i);
            if(ds.find(e.getFrom()) != ds.find(e.getTo())){
                spanningTree.add(e);
                ds.union(e.getFrom(), e.getTo());
            }
            i++;
        }
        return spanningTree;
    }
}
