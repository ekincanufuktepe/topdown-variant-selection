package tpvs.algorithm;

import java.util.List;

import org.opt4j.core.problem.ProblemModule;

import tpvs.Main;
import tpvs.variants.Variant;

public class TopDownVariantSelectionModule extends ProblemModule {

	protected List<Variant> variants;
	
	public List<Variant> getVariants() {
		return variants;
	}

	public void setVariants(List<Variant> variants) {
		this.variants = variants;
	}

	public TopDownVariantSelectionModule() {
		this.variants = Main.variantList;
	}
	
	@Override
	protected void config() {
		bindProblem(TopDownVariantSelectionCreator.class, TopDownVariantSelectionDecoder.class, TopDownVariantSelectionEvaluator.class);
	}

}
