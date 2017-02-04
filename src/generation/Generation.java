/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generation;

import individual.TankIA;

/**
 * Class Handles the whole individual generation.
 * @author Yasin Radi
 */
public class Generation {
    
    public static int NUM_OF_INDIVIDUALS = 10;
    private TankIA[] generation;
    
    /**
     * Creates a generation of individuals and initializes them if needed.
     * @param initialize boolean
     */
    public Generation(boolean initialize)
    {
        generation = new TankIA[NUM_OF_INDIVIDUALS];
        
        if(initialize)
        {
            for(int i = 0; i < genSize(); i++)
            {
                TankIA individual   = new TankIA();
                individual.populate();
                this.generation[i]  = individual;
            }
        }
    }
    
    /**
     * Gets the fittest individual of the generation.
     * @return TankIA
     */
    public TankIA getFittest()
    {
        TankIA fittest = this.getGeneration()[0];
        
        for(TankIA tank : this.getGeneration())
        {
            if(tank.getFitness() >= fittest.getFitness())
            {
                fittest = tank;
            }
        }
        
        return fittest;
    }
    
    /**
     * Gets the second fittest individual of the generation.
     * @return TankIA
     */
    public TankIA getSecondFittest()
    {
        TankIA secondFittest = this.getGeneration()[0];
        
        for(TankIA tank : this.getGeneration())
        {
            if(tank.getFitness() >= secondFittest.getFitness() && tank != this.getFittest())
            {
                secondFittest = tank;
            }
        }
        
        return secondFittest;
    }

    /**
     * Gets the individual at the specified index.
     * @param index
     * @return TankIA
     */
    public TankIA getIndividual(int index)
    {
        return this.getGeneration()[index];
    }
    
    /**
     * Saves a individual in the specified index.
     * @param index
     * @param individual 
     */
    public void setIndividual(int index, TankIA individual)
    {
        this.getGeneration()[index] = individual;
    }
    
    public int genSize()
    {
        return this.getGeneration().length;
    }
    
    public TankIA[] getGeneration() {
        return generation;
    }

    public void setGeneration(TankIA[] generation) {
        this.generation = generation;
    }
    
}
