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
public class Astar {
    
    private final PriorityQueue<Jigsaw> open;
    private final ArrayList<Jigsaw> closed;
    private final Stats stats;

    public Astar(Comparator<Jigsaw> hueristics, Stats stats) {
        open = new PriorityQueue<>(hueristics);
        closed = new ArrayList<>();
        this.stats = stats;
    }
    
    public void AstarAlgorithm(Jigsaw jigsaw){
        jigsaw.setDirections("UDLR");
        stats.startTimer();
        stats.setVisitedStates(stats.getVisitedStates() + 1);
        open.add(jigsaw);
        while (open.size() > 0) {
            Jigsaw front = open.remove();
            stats.setProcessedStates(stats.getProcessedStates() + 1);
            String solution = front.getSolution();
            if (solution.length() > stats.getMaxRecursionDepth()) {
                stats.setMaxRecursionDepth(solution.length());
            }

            if (front.isSolution()) {
                stats.stopTimer();
                stats.setSolution(solution);
                return;
            }
            closed.add(front);
            List<Jigsaw> neighbours = front.getNeighbours();
            for (Jigsaw neighbour : neighbours) {
                stats.setVisitedStates(stats.getVisitedStates() + 1);
                if (closed.contains(neighbour)) {
                    continue;
                }
                if (!open.contains(neighbour)) {
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
