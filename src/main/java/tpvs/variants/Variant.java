package tpvs.variants;

public class Variant extends FeatureModel {

	private Integer variantID;
	
	public Variant() {
		super();
	}
	
	

	@Override
	public String toString() {
		return "Variant [variantID=" + variantID + ", featureList=" + featureList + "]";
	}

	public Integer getVariantID() {
		return variantID;
	}

	public void setVariantID(Integer variantID) {
		this.variantID = variantID;
	}
}
