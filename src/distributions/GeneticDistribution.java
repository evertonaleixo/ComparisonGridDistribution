package distributions;

import java.util.LinkedList;
import java.util.List;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import utils.Grid;
import utils.genetic.DistributionCandidateFactory;
import utils.genetic.DistributionCromossome;
import utils.genetic.DistributionCrossover;
import utils.genetic.DistributionEvaluator;
import utils.genetic.DistributionMutation;

public class GeneticDistribution extends Distribution {

    public GeneticDistribution(Grid g) {
        super(g);
    }

    @Override
    public void fillGrid(Grid g) {
        CandidateFactory<DistributionCromossome> candidateFactory;
        candidateFactory = new DistributionCandidateFactory();
        
        // Evolutionary Operator
        List<EvolutionaryOperator<DistributionCromossome>> operators = null;
        operators = new LinkedList<EvolutionaryOperator<DistributionCromossome>>();
        operators.add(new DistributionCrossover(1));
        operators.add(new DistributionMutation());
    
        EvolutionaryOperator<DistributionCromossome> pipeline = null;
        pipeline = new EvolutionPipeline<DistributionCromossome>(operators);
        
        // Fitness
        FitnessEvaluator fitnessEvaluator = new DistributionEvaluator();

        // Strategy selection
        SelectionStrategy selectionStrategy = new RouletteWheelSelection();
        
        // Engine
        EvolutionEngine<DistributionCromossome> engine = null;
        engine = new GenerationalEvolutionEngine<DistributionCromossome>(candidateFactory,
                                                  pipeline,
                                                  fitnessEvaluator,
                                                  selectionStrategy,
                                                  new MersenneTwisterRNG());

        // Execute
        int popSize = 100;
        int eliteCount = 1;
        DistributionCromossome evolve = engine.evolve(popSize, eliteCount, new GenerationCount(1000));
        
        // Collect the result
        
    }

}
