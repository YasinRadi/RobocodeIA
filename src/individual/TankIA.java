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
import robocode.BulletHitEvent;
import robocode.HitByBulletEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

/**
 * Class Handles each individual statistics and behavior.
 * @author Yasin Radi
 */
public class TankIA extends Robot{
    
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
    private List<String> auxPopulationIdx;
    
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
    private double distance;
    
    /**
     * Number of degrees that will be applied to the rotation.
     */
    private double rotationDegrees;
    
    /**
     * Number of degrees that will be applied to the radar rotation.
     */
    private double rotationRadar;
    
    /**
     * Number of degrees that will be applied to the gun rotation.
     */
    private double rotationGun;
    
    public TankIA()
    {
        /**
         * Attributes initialization.
         */
        this.fitness         = 0;
        this.rotationDegrees = 30;
        this.distance        = 20;
        this.genes = new String[GENETIC_LENGTH];
        
        /**
         * Template definition.
         */
        this.populationTemplate = new HashMap<>();
        this.populationTemplate.put("AHEAD",  (args) -> this.ahead(this.getDistance()));
        this.populationTemplate.put("BACK",   (args) -> this.back(this.getDistance()));
        this.populationTemplate.put("FIRE",   (args) -> this.fire(TankIA.BULLET_POWER));
        this.populationTemplate.put("TURN_R", (args) -> this.turnRight(this.getRotationDegrees()));
        this.populationTemplate.put("TURN_L", (args) -> this.turnLeft(this.getRotationDegrees()));
        this.populationTemplate.put("TURN_RADAR_R", (args) -> this.turnRadarRight(this.getRotationRadar()));
        this.populationTemplate.put("TURN_RADAR_L", (args) -> this.turnRadarLeft(this.getRotationRadar()));
        this.populationTemplate.put("TURN_GUN_R",   (args) -> this.turnGunRight(this.getRotationGun()));
        this.populationTemplate.put("TURN_GUN_L",   (args) -> this.turnGunLeft(this.getRotationGun()));
        this.populationTemplate.put("ON_ENEMY_HIT", (args) -> this.onBulletHit((BulletHitEvent) args[0]));
        this.populationTemplate.put("ON_HIT_BY_ENEMY", (args) -> this.onHitByBullet((HitByBulletEvent) args[0]));
        this.populationTemplate.put("ON_ENEMY_SCANED", (args) -> this.onScannedRobot((ScannedRobotEvent) args[0]));
        
        /**
         * Template index retrieving.
         */
        auxPopulationIdx = new ArrayList<>();
        this.populationTemplate.forEach((k, v) -> auxPopulationIdx.add(k));
    }

    public double getRotationRadar() {
        return rotationRadar;
    }

    public void setRotationRadar(double rotationRadar) {
        this.rotationRadar = rotationRadar;
    }

    public double getRotationGun() {
        return rotationGun;
    }

    public void setRotationGun(double rotationGun) {
        this.rotationGun = rotationGun;
    }

    public double getRotationDegrees() {
        return rotationDegrees;
    }

    public void setRotationDegrees(double rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }

    public Map<String, Method> getPopulationTemplate() {
        return populationTemplate;
    }

    public void setPopulationTemplate(Map<String, Method> populationTemplate) {
        this.populationTemplate = populationTemplate;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
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
     * Random Genetic code generation and population.
     */
    private void populate()
    {
        for(int i = 0; i < GENETIC_LENGTH; i++)
        {
            this.genes[i] = this.auxPopulationIdx.get(ThreadLocalRandom.current().nextInt(0, auxPopulationIdx.size()));
        }       
    }
    
    /**
     * Sets a gene at the specified index.
     * @param index
     * @param gene 
     */
    public void setGene(int index, String gene)
    {
        this.getGenes()[index] = gene;
    }
    
    /**
     * Gets the gene at the specified index.
     * @param index
     * @return 
     */
    public String getGene(int index)
    {
        return this.getGenes()[index];
    }
    
    /**
     * Increase individual Fitness whenever hits an enemy.
     * @param e 
     */
    @Override
    public void onBulletHit(BulletHitEvent e)
    {
        this.fitness += 0.1;
    }
    
    /**
     * Decrease individual Fitness whenever gets hit by an enemy.
     * @param e 
     */
    @Override
    public void onHitByBullet(HitByBulletEvent e)
    {
        this.fitness -= 0.1;
    }
    
    /**
     * Make individual fire whenever an enemy is detected.
     * @param e 
     */
    @Override
    public void onScannedRobot(ScannedRobotEvent e)
    {
        this.fire(TankIA.BULLET_POWER);
    }
}
