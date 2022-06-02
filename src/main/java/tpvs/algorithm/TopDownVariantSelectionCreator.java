package tpvs.algorithm;

import org.opt4j.core.genotype.BooleanGenotype;
import org.opt4j.core.problem.Creator;

import com.google.inject.Inject;

public class TopDownVariantSelectionCreator implements Creator<BooleanGenotype>{

	protected final TopDownVariantSelectionProblem problem;
	
	@Inject
	public TopDownVariantSelectionCreator(TopDownVariantSelectionProblem problem) {
		this.problem = problem;
	}
	
	public BooleanGenotype create() {
		BooleanGenotype genotype = new BooleanGenotype();
		for(Boolean variantSelection : problem.variants) {
			genotype.add(variantSelection);
		}
		return genotype;
	}

}
