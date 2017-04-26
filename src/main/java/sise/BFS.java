package sise;

import java.util.*;

public class BFS {

    private final LinkedList<Jigsaw> queue;
    private final ArrayList<Jigsaw> visited;

    public BFS() {
        this.queue = new LinkedList<Jigsaw>();
        this.visited = new ArrayList<Jigsaw>();
    }

    public void run(){
        while (!queue.isEmpty()) {
            Jigsaw jigsaw = queue.poll();

//            for (Jigsaw j : jigsaw.getNeighboursList()) {
//
//                if (j.isSolution()) {
//                    System.out.println("SOLVED");
//                    return;
//                } else if (!queue.contains(j) && !visited.contains(j)) {
//                    queue.add(j);
//                }
//
//            }
        }
    }
}
