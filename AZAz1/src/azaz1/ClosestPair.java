/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azaz1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Simona
 */
public class ClosestPair {
    public static Vrchol[] closestPairCalc(Vrchol[] points) {
        Vrchol[] closestPair = new Vrchol[2];

        //When we start the min distance is the infinity
        double crtMinDist = Double.POSITIVE_INFINITY;

        //Get the points and sort them
        Vrchol[] sorted = Arrays.copyOf(points, points.length);
        Arrays.sort(sorted, HORIZONTAL_COMPARATOR);

        //When we start the left most candidate is the first one
        int leftMostCandidateIndex = 0;

        //Vertically sorted set of candidates
        SortedSet<Vrchol> candidates = new TreeSet<Vrchol>(VERTICAL_COMPARATOR);

        //For each point from left to right
        for (Vrchol current : sorted) {
            //Shrink the candidates
            while (current.x - sorted[leftMostCandidateIndex].x > crtMinDist) {
                candidates.remove(sorted[leftMostCandidateIndex]);
                leftMostCandidateIndex++;
            }

            //Compute the y head and the y tail of the candidates set
            Vrchol head = new Vrchol(current.x, (int) (current.y - crtMinDist));
            Vrchol tail = new Vrchol(current.x, (int) (current.y + crtMinDist));

            //We take only the interesting candidates in the y axis
            for (Vrchol point : candidates.subSet(head, tail)) {
                //podmienka nech sa nepridaju body z rovnakeho rootu
                if(current.getRootNum() == point.getRootNum())
                    continue;
                
                double distance = current.distance(point);

                //Simple min computation
                if (distance < crtMinDist) {
                    crtMinDist = distance;

                    closestPair[0] = current;
                    closestPair[1] = point;
                }

            }

            //The current point is now a candidate
            candidates.add(current);
        }

        return closestPair;
    }

    private static final Comparator<Vrchol> VERTICAL_COMPARATOR = new Comparator<Vrchol>() {
        @Override
        public int compare(Vrchol a, Vrchol b) {
            if (a.y < b.y) {
                return -1;
            }
            if (a.y > b.y) {
                return 1;
            }
            if (a.x < b.x) {
                return -1;
            }
            if (a.x > b.x) {
                return 1;
            }
            return 0;
        }
    };

    private static final Comparator<Vrchol> HORIZONTAL_COMPARATOR = new Comparator<Vrchol>() {
        @Override
        public int compare(Vrchol a, Vrchol b) {
            if (a.x < b.x) {
                return -1;
            }
            if (a.x > b.x) {
                return 1;
            }
            if (a.y < b.y) {
                return -1;
            }
            if (a.y > b.y) {
                return 1;
            }
            return 0;
        }
    };
}
