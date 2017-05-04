package sise;


import sun.awt.Symbol;

import java.io.FileNotFoundException;
import java.io.FileWriter;
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
        switch (strategyAcronym){
            case "bfs":
                BFS bfs = new BFS(stats);
                bfs.run(jigsaw);
                break;
            case "dfs":
                DFS dfs = new DFS(stats);
                dfs.dfs(jigsaw);
                break;
            case "astr":
                Astar astar;
                switch (strategyProperties){
                    case "hamm":
                        astar = new Astar(new HammingHeuristics(), stats);
                        astar.AstarAlgorithm(jigsaw);
                        break;
                    case "manh":
                        astar = new Astar(new ManhattanHeuristics(), stats);
                        astar.AstarAlgorithm(jigsaw);
                        break;
                }
                break;
        }

        try {
            stats.saveStatsToFile(additionalInformationFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(solutionFilename);
            try {
                fileWriter.write(stats.getSolution().length() + "\n" + stats.getSolution());
            } catch(NullPointerException e){
                fileWriter.write("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
