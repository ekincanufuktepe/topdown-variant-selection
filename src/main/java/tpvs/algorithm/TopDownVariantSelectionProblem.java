package tpvs.algorithm;

import java.util.ArrayList;
import tpvs.Main;

public class TopDownVariantSelectionProblem {
	protected ArrayList<Boolean> variants = new ArrayList<Boolean>();
	public ArrayList<Boolean> getVariants() {
		return variants;
	}

	public void setVariants(ArrayList<Boolean> variants) {
		this.variants = variants;
	}

	public TopDownVariantSelectionProblem() {
		for(int i=0; i<Main.variantList.size(); i++) {
			variants.add(false);
		}
	}
}
