package utils.genetic;

import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

public class DistributionMutation implements EvolutionaryOperator<DistributionCromossome> {
    private final double MUTATION_RATE = 0.1F;
    
    @Override
    public List<DistributionCromossome> apply(
            List<DistributionCromossome> selectedCandidates, Random rng) {

        return null;
    }

}
