
package geneticRobocode.evolution;

import geneticRobocode.generation.Generation;
import geneticRobocode.individual.TankIA;
import java.util.Random;

/**
 * Handles the evolution itself of every individual and stage.
 * @author Yasin Radi
 */
public class Evolution {
    
    /**
     * Number of genes to be mutated.
     */
    private static final double MUTATION_RATE = 2;
    
    /**
     * Set if elitism will be applied.
     */
    private static final boolean ELITISM      = true;
    
    /**
     * Random number generator.
     */
    private static final Random RAND          = new Random();
    
    /**
     * Evolves a generation.
     * @param genToEvolve Generation
     * @return Generation
     */
    public static Generation evolve(Generation genToEvolve)
    {
        /**
         * Create a new generation but with no individual initialization.
         */
        Generation newGeneration = new Generation(false);
        
        /**
         * Get the best 2 individuals of the generation if elitism is expected
         * and establishes the iteration origin 2 numbers further.
         */
        int elitismOffset = 0;
        if(ELITISM)
        {
            newGeneration.setIndividual(0, genToEvolve.getFittest());
            newGeneration.getIndividual(0).setFitness(0);
            newGeneration.setIndividual(1, genToEvolve.getSecondFittest());
            newGeneration.getIndividual(1).setFitness(0);
            elitismOffset = 2;
        }
        
        /**
         * Fills the new Generation crossing over some of the best individuals.
         */
        for(int i = elitismOffset; i < Generation.NUM_OF_INDIVIDUALS; i++)
        {
            TankIA parent1 = tournamentSelection(genToEvolve);
            TankIA parent2 = tournamentSelection(genToEvolve);
            newGeneration.setIndividual(i, crossover(parent1, parent2));
        }
        
        /**
         * Applies mutation to all Generation individuals but the best two.
         */
        for(int i = elitismOffset; i < Generation.NUM_OF_INDIVIDUALS; i++)
        {
            mutation(newGeneration.getIndividual(i));
        }
        
        return newGeneration;
    }
    
    /**
     * Creates a new individual crossing 2 different individuals genes.
     * @param parent1 TankIA
     * @param parent2 TankIA
     * @return TankIA
     */
    private static TankIA crossover(TankIA parent1, TankIA parent2)
    {
        TankIA son = new TankIA();
        
        /**
         * Takes the first half of the parent 1 genes and the other half from the other parent.
         */            
        for(int i = 0; i < TankIA.GENETIC_LENGTH; i++)
        {
            if(i < TankIA.GENETIC_LENGTH / 2)
            {
                son.setGene(i, parent1.getGene(i));
            }
            else
            {
                son.setGene(i, parent2.getGene(i));
            }
        }
        
        return son;
    }
    
    /**
     * Mutates a number of the individual genes.
     * @param individualToMutate TankIA
     */
    private static void mutation(TankIA individualToMutate)
    {
        for(int i = 0; i < Evolution.MUTATION_RATE; i++)
        {
            individualToMutate.setGene(
                    RAND.nextInt(TankIA.GENETIC_LENGTH), 
                    TankIA.POPULATION_TEMPLATE.get(
                            RAND.nextInt(TankIA.POPULATION_TEMPLATE.size())
                    )
            );
        }
    }
    
    /**
     * Creates a random collection of individuals from the original Generation
     * and gets the best from it.
     * @param gen Generation
     * @return TankIA
     */
    private static TankIA tournamentSelection(Generation gen)
    {
        Generation tournamentGen = new Generation(false);
        
        for(int i = 0; i < Generation.NUM_OF_INDIVIDUALS; i++)
        {
            tournamentGen.setIndividual(i, 
                    gen.getGeneration()[RAND.nextInt(Generation.NUM_OF_INDIVIDUALS)]
            );
        }
        
        return tournamentGen.getFittest();
    }
}
