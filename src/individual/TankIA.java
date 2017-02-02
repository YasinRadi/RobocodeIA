/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package individual;

import individual.methods.Method;
import java.util.HashMap;
import java.util.Map;
import robocode.HitRobotEvent;
import robocode.Robot;

/**
 *
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
     * Individual fitness value.
     */
    private double fitness;
    
    /**
     * Genetic code of the individual.
     */
    private HashMap<String, Method> genes;
    
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
        this.fitness         = 0;
        this.rotationDegrees = 30;
        
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

    public HashMap<String, Method> getGenes() {
        return genes;
    }

    public void setGenes(HashMap<String, Method> genes) {
        this.genes = genes;
    }
    
    
    private void populate()
    {
        this.setPopulationTemplate( new HashMap<>());
        
        this.getPopulationTemplate().put("AHEAD",  () -> this.ahead(this.getDistance()));
        this.getPopulationTemplate().put("BACK",   () -> this.back(this.getDistance()));
        this.getPopulationTemplate().put("FIRE",   () -> this.fire(TankIA.BULLET_POWER));
        this.getPopulationTemplate().put("TURN_R", () -> this.turnRight(this.getRotationDegrees()));
        this.getPopulationTemplate().put("TURN_L", () -> this.turnLeft(this.getRotationDegrees()));
        this.getPopulationTemplate().put("TURN_RADAR_R", () -> this.turnRadarRight(this.getRotationRadar()));
        this.getPopulationTemplate().put("TURN_RADAR_L", () -> this.turnRadarLeft(this.getRotationRadar()));
        this.getPopulationTemplate().put("TURN_GUN_R",   () -> this.turnGunRight(this.getRotationGun()));
        this.getPopulationTemplate().put("TURN_GUN_L",   () -> this.turnGunLeft(this.getRotationGun()));
//        this.getPopulationTemplate().put("ON_ENEMY_HIT", () -> this.onHitRobot();
        
    }
}
