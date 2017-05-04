package sise;

import java.util.*;

/**
 * Created by Xpress on 27.04.2017.
 */
public class DFS {

    private final static int MAX_RECURSION_DEPTH = 20;
    private final LinkedHashSet<Jigsaw> visited;
    private final LinkedHashSet<Jigsaw> processed;
    private Stats stats;

    public DFS(Stats stats) {
        this.stats = stats;
        this.visited = new LinkedHashSet<>();
        this.processed = new LinkedHashSet<>();
    }


    public void dfs(Jigsaw jigsaw){
        stats.startTimer();
        visited.add(jigsaw);
        stats.setVisitedStates(stats.getVisitedStates() + 1);

        while (!visited.isEmpty()) {

            final Jigsaw v = removeLastElement(visited);
//            if (v.isSolution()){
//                stats.stopTimer();
//                stats.setSolution(v.getSolution());
//                return;
//            }
            if (v.getSolution().length() >= MAX_RECURSION_DEPTH) {
                continue;
            }

            processed.add(v);
            stats.setProcessedStates(stats.getProcessedStates() + 1);

            List<Jigsaw> neighbours = v.getNeighbours();
            Collections.reverse(neighbours);

            for (Jigsaw neighbour : neighbours) {
                if (neighbour.getSolution().length() >= stats.getMaxRecursionDepth()) {
                    stats.setMaxRecursionDepth(neighbour.getSolution().length());
                }
                if (neighbour.isSolution()) {
                    stats.stopTimer();
                    stats.setVisitedStates(stats.getVisitedStates() + 1);
                    stats.setSolution(neighbour.getSolution());
                    return;
                }
                else if (processed.contains(neighbour) || visited.contains(neighbour)) {
                    continue;
                }
                visited.add(neighbour);
                stats.setVisitedStates(stats.getVisitedStates() + 1);
            }
        }
    }

    private Jigsaw removeLastElement(LinkedHashSet<Jigsaw> set) {
        final Jigsaw top = (Jigsaw) set.toArray()[set.size() - 1];
        set.remove(set.toArray()[set.size() - 1]);
        return top;
    }

}
