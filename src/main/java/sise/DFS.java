package sise;

import java.util.*;

/**
 * Created by Xpress on 27.04.2017.
 */
public class DFS {

    private final static int MAX_RECURSION_DEPTH = 20;
    private final Stack<Jigsaw> S;
    private final ArrayList<Jigsaw> visitedArrayList;
    private Stats stats;

    public DFS(Stats stats) {
        this.stats = stats;
        this.S = new Stack<>();
        this.visitedArrayList = new ArrayList<>();
    }





    public void dfs(Jigsaw jigsaw){
        stats.startTimer();
        S.push(jigsaw);
        while (!S.isEmpty()) {
            final Jigsaw v = S.pop();

            if (v.isSolution()){
                stats.stopTimer();
                stats.setSolution(v.getSolution());
                return;
            }
            if (v.getSolution().length() >= MAX_RECURSION_DEPTH) {
                continue;
            }

            visitedArrayList.add(v);
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
                else if (visitedArrayList.contains(neighbour) || S.contains(neighbour)) {
                    continue;
                }
                S.push(neighbour);
                visitedArrayList.add(neighbour);
                stats.setVisitedStates(stats.getVisitedStates() + 1);
            }
        }
    }
}
