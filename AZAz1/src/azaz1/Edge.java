/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azaz1;

/**
 * Edge of a undirected graph
 * @author Pavel Micka
 */
class Edge implements Comparable<Edge> {
    private int from; //from
    private int to; //to
    private double weight; //weight
    public Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    /**
     * Compare to function (greater weight => is greater than)
     * @param o
     * @return
     */
    public int compareTo(Edge o) {
        if (this.getWeight() > o.getWeight()) {
            return 1;
        } else if (this.getWeight() < o.getWeight()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * @return the from
     */
    public int getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(int from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public int getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(int to) {
        this.to = to;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}

