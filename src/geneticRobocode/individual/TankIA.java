
package geneticRobocode.individual;

import geneticRobocode.fitness.Fitness;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class Handles each individual statistics and behavior.
 * @author Yasin Radi
 */
public class TankIA {
    
    /**
     * Bullet power.
     */
    private static final double BULLET_POWER = 2;
    
    /**
     * Number of genes that a genetic code has.
     */
    public static final int GENETIC_LENGTH = 64;
   
    /**
     * Template from which the action will be retrieved when populating.
     */
    public static final List<String> POPULATION_TEMPLATE = new ArrayList<>();
    static {
        POPULATION_TEMPLATE.add("AHEAD");
        POPULATION_TEMPLATE.add("BACK");
        POPULATION_TEMPLATE.add("FIRE");
        POPULATION_TEMPLATE.add("STOP");
        POPULATION_TEMPLATE.add("TURN_R");
        POPULATION_TEMPLATE.add("TURN_L");
        POPULATION_TEMPLATE.add("TURN_GUN_R");
        POPULATION_TEMPLATE.add("TURN_GUN_L");
        POPULATION_TEMPLATE.add("ON_BULLET_HIT");
        POPULATION_TEMPLATE.add("ON_HIT_BY_ROBOT");
        POPULATION_TEMPLATE.add("ON_SCANNED_ROBOT");
    }
        
    
    /**
     * Individual fitness value.
     */
    private double fitness;
    
    /**
     * Genetic code of the individual.
     */
    private String[] genes;
    
    /**
     * Distance value that will be ran on each moving action.
     */
    private static final double DISTANCE         = 100;
    
    /**
     * Number of degrees that will be applied to the rotation.
     */
    private static final double ROTATION_DEGREES = 30;
    
    /**
     * Number of degrees that will be applied to the radar rotation.
     */
    private static final double ROTATION_RADAR   = 30;
    
    /**
     * Number of degrees that will be applied to the gun rotation.
     */
    private static final double ROTATION_GUN     = 30;
    
    /**
     * Random number generator.
     */
    private static final Random RAND             = new Random();
    
    /**
     * Constructor.
     */
    public TankIA()
    {
        /**
         * Attributes initialization.
         */
        this.fitness = 0;
        this.genes   = new String[GENETIC_LENGTH];
        
    }
    
    /**
     * Runs the TankIA behavior.
     */
    public void run()
    {
        for(String action : this.getGenes())
        {
            switch (action) {
                case "AHEAD":
                    this.ahead();
                    break;
                case "BACK":
                    this.back();
                    break;
                case "FIRE":
                    this.fire();
                    break;
                case "TURN_R":
                    this.turnRight();
                    break;
                case "TURN_L":
                    this.turnLeft();
                    break;
                case "TURN_GUN_R":
                    this.turnGunRight();
                    break;
                case "TURN_GUN_L":
                    this.turnGunLeft();
                    break;
                case "ON_BULLET_HIT":
                    this.onBulletHit();
                    break;
                case "ON_HIT_BY_BULLET":
                    this.onHitByBullet();
                    break;
                case "ON_SCANNED_ROBOT":
                    this.onScannedRobot();
                    break;
                default:
                    break;
            }
        }
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public String[] getGenes() {
        return genes;
    }

    public void setGenes(String[] genes) {
        this.genes = genes;
    }

    public double getFitness() {
        return fitness;
    }
        
    /**
     * Calculates individual's fitness.
     * @return double
     */
    public double calcFitness() 
    {
        if(this.fitness == 0)
        {
            this.fitness = Fitness.getFitness(this);
        }
        return this.fitness;
    }
    
    /**
     * Goes forward.
     */
    private void ahead()
    {
        this.fitness += 0.1;
    }
    
    /**
     * Goes backwards.
     */
    private void back()
    {
        this.fitness -= 0.5;
    }
    
    /**
     * Turn Right method.
     */
    private void turnRight()
    {
        this.fitness += 0.1;
    }
    
    /**
     * Turn Left method.
     */
    private void turnLeft()
    {
        this.fitness -= 0.5;
    }
    
    /**
     * Turn Turret Right Method.
     */
    private void turnGunRight()
    {
        this.fitness += 0.1;
    }
    
    /**
     * Turn Turret Left Method.
     */
    private void turnGunLeft()
    {
        this.fitness -= 0.5;
    }
    
    /**
     * Random Genetic code generation and population.
     */
    public void populate()
    {
        for(int i = 0; i < GENETIC_LENGTH; i++)
        {
            this.genes[i] = this.POPULATION_TEMPLATE.get(RAND.nextInt(POPULATION_TEMPLATE.size()));
        }       
    }
    
    /**
     * Sets a gene at the specified index.
     * @param index int
     * @param gene String
     */
    public void setGene(int index, String gene)
    {
        this.getGenes()[index] = gene;
    }
    
    /**
     * Gets the gene at the specified index.
     * @param index int
     * @return String
     */
    public String getGene(int index)
    {
        return this.getGenes()[index];
    }
    
    /**
     * Generates a random between 0 and 1, if it's 1 the shoot lands.
     */
    public void fire()
    {   
        this.fitness = RAND.nextInt(2) == 1 
                ? this.getFitness() + 0.1
                : this.getFitness() - 0.5;
    }
    
    /**
     * Increase individual Fitness whenever hits an enemy.
     */
    public void onBulletHit()
    {
        this.fitness += 0.1;
    }
    
    /**
     * Decrease individual Fitness whenever gets hit by an enemy.
     */
    public void onHitByBullet()
    {
        this.fitness -= 0.5;
    }
    
    /**
     * Make individual fire whenever an enemy is detected.
     */
    public void onScannedRobot()
    {
        this.fire();
    }
}
