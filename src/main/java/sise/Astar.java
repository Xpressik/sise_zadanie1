/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author macki
 */
public class Astar {
    
    private final PriorityQueue<Jigsaw> open;
    private final ArrayList<Jigsaw> closed;

    public Astar(Comparator<Jigsaw> hueristics) {
        open = new PriorityQueue<Jigsaw>(hueristics);
        closed = new ArrayList<Jigsaw>();
    }
    
    public void AstarAlgorithm(Jigsaw jigsaw){
        open.add(jigsaw);
        while (open.size() > 0) {
            Jigsaw front = open.remove();
            String solution = front.getSolution();
            if (front.isSolution()) {
                return;
            }
            closed.add(front);
            ArrayList<Jigsaw> neighbours = front.getNeighbours("UDLR");
            for (Jigsaw neighbour : neighbours) {
                if (closed.contains(neighbour)) {
                    continue;
                }
                if (!open.contains(neighbour)) {
                    // statistics.incrementVisitedCount();
                    open.add(neighbour);
                } else {
                    boolean b = open.removeIf((Jigsaw j) -> {
                        if (!j.equals(neighbour)) {
                            return false;
                        }
                        return j.getSolution().length() > neighbour.getSolution().length();
                    });
                    if (b) {
                        open.add(neighbour);
                    }
                }
            }
        }
    }
}
