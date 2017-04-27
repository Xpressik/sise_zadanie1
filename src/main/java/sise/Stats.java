package sise;

import java.io.FileWriter;
import java.io.IOException;

class Stats {

    private long startTime;
    private long stopTime;
    private long solutionLength;
    private long processedStates;
    private long visitedStates;
    private long maxRecursionDepth;
    private String solution;

    public void startTimer() {
        startTime = System.currentTimeMillis();
    }

    public void stopTimer() {
        stopTime = System.currentTimeMillis();
        getProcessingTime();
    }

    public long getProcessingTime() {
        return stopTime - startTime;
    }

    public long getProcessedStates() {
        return processedStates;
    }

    public void setProcessedStates(long processedStates) {
        this.processedStates = processedStates;
    }
    
    public void IncrementProcessedStates() {
        this.processedStates++;
    }

    public long getVisitedStates() {
        return visitedStates;
    }

    public void setVisitedStates(long visitedStates) {
        this.visitedStates = visitedStates;
    }
    
    public void IncrementVisitedStates() {
        this.visitedStates++;
    }

    public long getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public void setMaxRecursionDepth(long maxRecursionDepth) {
        this.maxRecursionDepth = maxRecursionDepth;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
        this.solutionLength = solution.length();
    }

    public void saveStatsToFile(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        fw.write(Long.toString(solutionLength) + "\n");
        fw.write(Long.toString(getVisitedStates()) + "\n");
        fw.write(Long.toString(getProcessedStates()) + "\n");
        fw.write(Long.toString(maxRecursionDepth) + "\n");
        fw.write(Long.toString(getProcessingTime()) + "\n");
        fw.close();
    }
}
