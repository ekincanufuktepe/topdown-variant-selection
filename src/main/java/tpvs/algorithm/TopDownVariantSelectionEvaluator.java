package tpvs.algorithm;

import java.util.ArrayList;

import org.opt4j.core.Objective;
import org.opt4j.core.Objectives;
import org.opt4j.core.Objective.Sign;
import org.opt4j.core.problem.Evaluator;

import tpvs.Main;
import tpvs.variants.Feature;

public class TopDownVariantSelectionEvaluator implements Evaluator<TopDownVariantSelectionSelector> {

	Objective featureCoverageAndMinimalVariantSelection = new Objective("fcamvs", Sign.MAX);
	
	public Objectives evaluate(TopDownVariantSelectionSelector variantSelections) {
		Objectives objectives = new Objectives();
		objectives.add(featureCoverageAndMinimalVariantSelection, FCAMVS(variantSelections));
		return objectives;
	}
	
	public static Double FCAMVS(ArrayList<Boolean> selectedVariants) {
		Double selectionCount = 0.0;
		for(Boolean variantSelection : selectedVariants) {
			if(variantSelection) {
				selectionCount += 1.0;
			}
		}
		if(selectionCount == 0) {
			return 0.0;
		}
		Double sc = 1.0/selectionCount;
		Double fc = featureCoverage(selectedVariants);
		if(fc != 1.0) {
			return 0.0;
		}
		System.out.println("Feature Cov: "+ fc + ", Selection Count: " + selectionCount);
		Double fcmavsScore = sc * fc;
		return fcmavsScore;  
	}
	
	public static Double featureCoverage(ArrayList<Boolean> variantSelections) {
		ArrayList<Feature> coveredFeatures = new ArrayList<Feature>();
		for(int i=0; i<variantSelections.size(); i++) {
			if(variantSelections.get(i)) {
				for(Feature feature : Main.variantList.get(i).getFeatureList()) {
					if(!coveredFeatures.contains(feature)) {
						coveredFeatures.add(feature);
					}
				}
			}
		}
		Double totalFeatures = (double) Main.featureModel.getFeatureList().size();
		Double totalCoveredFeatures = (double) coveredFeatures.size();
		return totalCoveredFeatures/totalFeatures;
	}

}
