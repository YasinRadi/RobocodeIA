/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package individual;

import individual.methods.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import robocode.AdvancedRobot;
import robocode.BulletHitEvent;
import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import robocode.TurnCompleteCondition;

/**
 * Class Handles each individual statistics and behavior.
 * @author Yasin Radi
 */
public class TankIA extends AdvancedRobot{
    
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
    private Map<String, Method> populationTemplate;
    
    /**
     * Aux population template indexer.
     */
    public List<String> auxPopulationIdx;
    
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
    private static final double DISTANCE         = 40000;
    
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
    
    public TankIA()
    {
        /**
         * Attributes initialization.
         */
        this.fitness = 0;
        this.genes   = new String[GENETIC_LENGTH];
        
        /**
         * Template definition.
         */
        this.populationTemplate = new HashMap<>();
        this.populationTemplate.put("AHEAD",  (args) -> this.ahead(TankIA.DISTANCE));
        this.populationTemplate.put("BACK",   (args) -> this.back(TankIA.DISTANCE));
        this.populationTemplate.put("FIRE",   (args) -> this.fire(TankIA.BULLET_POWER));
        this.populationTemplate.put("STOP", (args) -> this.stop());
        this.populationTemplate.put("TURN_R", (args) -> this.turningRight());
        this.populationTemplate.put("TURN_L", (args) -> this.turningLeft());
        this.populationTemplate.put("TURN_RADAR_R", (args) -> this.turnRadarRight(TankIA.ROTATION_RADAR));
        this.populationTemplate.put("TURN_RADAR_L", (args) -> this.turnRadarLeft(TankIA.ROTATION_RADAR));
        this.populationTemplate.put("TURN_GUN_R",   (args) -> this.turnGunRight(TankIA.ROTATION_GUN));
        this.populationTemplate.put("TURN_GUN_L",   (args) -> this.turnGunLeft(TankIA.ROTATION_GUN));
        this.populationTemplate.put("ON_ENEMY_HIT", (args) -> this.onBulletHit((BulletHitEvent) args[0]));
        this.populationTemplate.put("ON_HIT_BY_ENEMY", (args) -> this.onHitByBullet((HitByBulletEvent) args[0]));
        this.populationTemplate.put("ON_ENEMY_SCANED", (args) -> this.onScannedRobot((ScannedRobotEvent) args[0]));
        
        /**
         * Template index retrieving.
         */
        auxPopulationIdx = new ArrayList<>();
        this.populationTemplate.forEach((k, v) -> auxPopulationIdx.add(k));
    }
    
    /**
     * Runs the TankIA behavior.
     */
    public void run()
    {
        while(true)
        {            
            for(String action : this.getGenes())
            {
                this.getPopulationTemplate().get(action);
            }
        }
    }

    public List<String> getAuxPopulationIdx() {
        return auxPopulationIdx;
    }

    public void setAuxPopulationIdx(List<String> auxPopulationIdx) {
        this.auxPopulationIdx = auxPopulationIdx;
    }

    public Map<String, Method> getPopulationTemplate() {
        return populationTemplate;
    }

    public void setPopulationTemplate(Map<String, Method> populationTemplate) {
        this.populationTemplate = populationTemplate;
    }
    
    public double getFitness() {
        return fitness;
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
    
    /**
     * Turn Right method unofficial override to be able to wait for the previous
     * turning action to be finished before starting a new turning action.
     */
    private void turningRight()
    {
        this.waitFor( new TurnCompleteCondition(this));
        this.turnRight(TankIA.ROTATION_DEGREES);
    }
    
    /**
     * Turn Left method unofficial override to be able to wait for the previous
     * turning action to be finished before starting a new turning action.
     */
    private void turningLeft()
    {
        this.waitFor( new TurnCompleteCondition(this));
        this.turnLeft(TankIA.ROTATION_DEGREES);
    }
    
    /**
     * Random Genetic code generation and population.
     */
    public void populate()
    {
        for(int i = 0; i < GENETIC_LENGTH; i++)
        {
            this.genes[i] = this.auxPopulationIdx.get(ThreadLocalRandom.current().nextInt(0, auxPopulationIdx.size()));
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
     * Increase individual Fitness whenever hits an enemy.
     * @param e BulletHitEvent
     */
    @Override
    public void onBulletHit(BulletHitEvent e)
    {
        this.fitness += 0.1;
    }
    
    /**
     * Decrease individual Fitness whenever gets hit by an enemy.
     * @param e HitByBulletEvent
     */
    @Override
    public void onHitByBullet(HitByBulletEvent e)
    {
        this.fitness -= 0.1;
    }
    
    /**
     * Make individual fire whenever an enemy is detected.
     * @param e ScannedRobotEvent
     */
    @Override 
    public void onScannedRobot(ScannedRobotEvent e)
    {
        this.fire(TankIA.BULLET_POWER);
    }
}
