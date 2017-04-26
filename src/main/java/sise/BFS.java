package sise;

import java.util.ArrayList;
import java.util.LinkedList;

public class BFS {

    private final LinkedList<Jigsaw> queue;
    private final ArrayList<Jigsaw> visited;
    private final Stats stats;

    public BFS(Stats stats) {
        this.queue = new LinkedList<>();
        this.visited = new ArrayList<>();
        this.stats = stats;
    }

    public void run(Jigsaw jig){
        stats.startTimer();
        queue.add(jig);

        while (!queue.isEmpty()) {
            Jigsaw jigsaw = queue.poll();
            visited.add(jigsaw);
            stats.setProcessedStates(stats.getProcessedStates() + 1);

            for (Jigsaw neighbour : jigsaw.getNeighbours()) {
                if (neighbour.getSolution().length() >= stats.getMaxRecursionDepth()) {
                    stats.setMaxRecursionDepth(neighbour.getSolution().length());
                }
                if (neighbour.isSolution()) {
                    stats.stopTimer();
                    stats.setSolution(neighbour.getSolution());
                    stats.setVisitedStates(stats.getVisitedStates() + 1);
                    return;
                } else if (!queue.contains(neighbour) && !visited.contains(neighbour)) {
                    queue.add(neighbour);
                    stats.setVisitedStates(stats.getVisitedStates() + 1);
                }
            }
        }
    }
}
