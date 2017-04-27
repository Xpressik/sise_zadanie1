/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sise;

/**
 *
 * @author macki
 */
public abstract class Algorithm {
    protected final Stats statistics;

    public Algorithm() {
        this.statistics = new Stats();
    }
    
    public abstract void run(Jigsaw jigsaw);

    public Stats getStatistics() {
        return statistics;
    }
}
