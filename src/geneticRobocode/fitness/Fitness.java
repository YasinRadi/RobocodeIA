
package geneticRobocode.fitness;

import geneticRobocode.individual.TankIA;

/**
 * Class Handles the calculation of the total Generation fitness and
 * the target fitness to achieve.
 * @author Yasin Radi
 */
public class Fitness {
    
    /**
     * Goal fitness to achieve by the Generation individuals.
     */
    public static final double MAX_FITNESS = 80;
    
    /**
     * Calculates the fitness of an individual.
     * @param ia TankIA
     * @return double
     */
    public static double getFitness(TankIA ia)
    {
        return ia.getFitness();
    }
}
