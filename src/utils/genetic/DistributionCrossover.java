package utils.genetic;

import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

public class DistributionCrossover extends AbstractCrossover<DistributionCromossome> {

    public DistributionCrossover(int crossoverPoints) {
        super(crossoverPoints);
    }

    @Override
    protected List<DistributionCromossome> mate(DistributionCromossome parent1,
            DistributionCromossome parent2, int numberOfCrossoverPoints,
            Random rng) {

        return null;
    }

}
