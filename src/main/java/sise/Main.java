package sise;


import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String args[]){
        final String strategyAcronym = args[0];
        final String strategyProperties = args[1];
        final String inputFilename = args[2];
        final String solutionFilename = args[3];
        final String additionalInformationFilename = args[4];

        Jigsaw jigsaw = null;
        try {
            jigsaw = new Jigsaw(inputFilename, strategyProperties);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Stats stats = new Stats();
        BFS bfs = new BFS(stats);
        bfs.run(jigsaw);

        try {
            stats.saveStatsToFile(additionalInformationFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
