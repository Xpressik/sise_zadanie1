/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author macki
 */
public class Astar extends Algorithm{
    
    private final PriorityQueue<Jigsaw> open;
    private final List<Jigsaw> closed;

    public Astar(Comparator<Jigsaw> hueristics) {
        open = new PriorityQueue<Jigsaw>(hueristics);
        closed = new ArrayList<Jigsaw>();
    }
    
    /**
     *
     * @param jigsaw
     */
    public void run(Jigsaw jigsaw) {
        statistics.startTimer();
        statistics.IncrementVisitedStates();
        open.add(jigsaw);
        while (open.size() > 0) {
            Jigsaw front = open.remove();
            statistics.IncrementProcessedStates();
            String solution = front.getSolution();
            if (solution.length() > statistics.getMaxRecursionDepth()) {
                statistics.setMaxRecursionDepth(solution.length());
            }
            if (front.isSolution()) {
                statistics.stopTimer();
                statistics.setSolution(solution);
                return;
            }
            closed.add(front);
            List<Jigsaw> neighbours = front.getNeighbours();
            for (Jigsaw neighbour : neighbours) {
                statistics.IncrementVisitedStates();
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
