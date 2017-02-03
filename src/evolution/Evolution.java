/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution;

import generation.Generation;

/**
 * Class Handles the evolution itself of every individual and stage.
 * @author Yasin Radi
 */
public class Evolution {
    
    /**
     * Genetic Algorithm Parameters.
     */
    private static final double mutationRate = 2;
    private static final boolean elitism     = true;
    private static final double uniformRate  = 0.5;
    private static final int tournamentSize  = 5;
    
    public static Generation evolve()
    {
        /**
         * Create a new generation but with no individual initialization.
         */
        Generation newGeneration = new Generation(false);
        
        return null;
    }
}
