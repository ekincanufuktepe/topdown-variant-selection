package tpvs.algorithm;

import org.opt4j.core.genotype.BooleanGenotype;
import org.opt4j.core.problem.Decoder;

public class TopDownVariantSelectionDecoder implements Decoder<BooleanGenotype, TopDownVariantSelectionSelector> {

	public TopDownVariantSelectionDecoder() {
	}
	
	public TopDownVariantSelectionSelector decode(BooleanGenotype genotype) {
		TopDownVariantSelectionSelector selection = new TopDownVariantSelectionSelector();
		for(Boolean i : genotype) {
			selection.add(i);
		}
		return selection;
	}
}
