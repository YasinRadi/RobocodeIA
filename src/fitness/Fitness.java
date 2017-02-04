/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitness;

/**
 * Class Handles the calculation of the total Generation fitness and
 * the target fitness to achieve.
 * @author Yasin Radi
 */
public class Fitness {
    
    /**
     * Goal fitness to achieve by the Generation individuals.
     */
    private static double maxFitness;
    
    /**
     * Enables the setting of a custom max fitness.
     * @param newFitness double
     */
    public static void setMaxFitness(double newFitness)
    {
        maxFitness = newFitness;
    }
    
    /**
     * Enables the getting of the current max fitness.
     * @return double
     */
    public static double getMaxFitness()
    {
        return maxFitness;
    }
}
