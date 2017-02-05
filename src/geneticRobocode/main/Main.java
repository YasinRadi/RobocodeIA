
package geneticRobocode.main;

import geneticRobocode.evolution.Evolution;
import geneticRobocode.fitness.Fitness;
import geneticRobocode.generation.Generation;

/**
 * 
 * @author Yasin Radi
 */
public class Main {
    
    public static void main(String[] args)
    {
        Generation gen = new Generation(true);
        
        int genCount = 0;
        
        while(gen.getFittest().getFitness() < Fitness.MAX_FITNESS)
        {
            genCount++;
            System.out.println("Generation num: " + genCount + " Fittest : " + gen.getFittest().getFitness());
            gen = Evolution.evolve(gen);
        }
        
        System.out.println("Solution found.");
        System.out.println("Generation num: " + genCount);
        System.out.println("Fitness: " + gen.getFittest().getFitness());
    }
}
