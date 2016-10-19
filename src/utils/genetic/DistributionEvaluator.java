package utils.genetic;

import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

public class DistributionEvaluator implements FitnessEvaluator<DistributionCromossome> {

    @Override
    public double getFitness(DistributionCromossome ind,
            List<? extends DistributionCromossome> indList) {

        return 0;
    }

    @Override
    public boolean isNatural() {
        // false -> find the individual with less fitness value
        return false;
    }

}
