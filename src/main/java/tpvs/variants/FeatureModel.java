package tpvs.variants;

import java.util.ArrayList;
import java.util.List;

public class FeatureModel {
	protected List<Feature> featureList;

	public FeatureModel() {
		featureList = new ArrayList<Feature>();
	}

	public List<Feature> getFeatureList() {
		return featureList;
	}

	public void setFeatureList(List<Feature> featureList) {
		this.featureList = featureList;
	}

	@Override
	public String toString() {
		return "FeatureModel [featureList=" + featureList + "]";
	}
}
