/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azaz1;

/**
 * Implementation of the disjoint set structure
 * @author Pavel Micka
 */
class DisjointSet {

    private Node[] nodes;

    public DisjointSet(int nodeCount) {
        nodes = new Node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new Node();
            nodes[i].id = i;
        }
    }

    /**
     * Unions sets, which contain vertices "a" and "b"
     * Union is always performed as merging "B" into "A"
     * @param a number of a node "a"
     * @param b number of a node "b"
     * @return number of a representative of the merged component
     */
    public int union(int a, int b) {
        Node repA = nodes[find(a)];
        Node repB = nodes[find(b)];

        repB.parent = repA;
        return repA.id;
    }

    /**
     * Returns representative of the compoment which contains the given node
     * @param a number of a node, whose representative we are looking for
     * @return number of the representative
     */
    public int find(int a) {
        Node n = nodes[a];
        int jumps = 0;
        while (n.parent != null) {
            n = n.parent;
            jumps++;
        }
        if (jumps > 1) repair(a, n.id);
        return n.id;
    }

    /**
     * Performs compression of the path
     * @param a
     * @param rootId
     */
    private void repair(int a, int rootId) {
        Node curr = nodes[a];
        while (curr.id != rootId) {
            Node tmp = curr.parent;
            curr.parent = nodes[rootId];
            curr = tmp;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nodes.length; i++) {
            builder.append(find(i) + " ");
        }
        return builder.toString();
    }

    /**
     * Vertex of an n-ary tree
     */
    private static class Node {
        /**
         * Parent
         */
        Node parent;
        /**
         * Node id
         */
        int id;
    }
}
