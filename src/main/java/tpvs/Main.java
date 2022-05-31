package tpvs;

import java.util.List;

import tpvs.parser.VariantParser;
import tpvs.variants.Feature;
import tpvs.variants.FeatureModel;
import tpvs.variants.Variant;

public class Main {

	public static void main(String[] args) {
		VariantParser vp = new VariantParser();
		// collect the full feature list in the feature model 
		FeatureModel fm = vp.parseFeatureModel("featureModels/smartHome/model.xml");
		
		// Collect the feature lists for each variant
		List<Variant> variants = vp.parseVariants("featureModels/smartHome/variants");
		
		
		
		// A sanity check for listing every variant's feature list
		for(Variant v : variants) {
			System.out.println("Variant #" + v.getVariantID());
			for(Feature f : v.getFeatureList()) {
				System.out.println("\t" + f);
			}
		}
	}

}
