package tpvs.variants;

public class Feature {
	private String featureName;
	private Integer featureID;
	
	public Feature(String featureName) {
		this.featureName = featureName;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	
	public Integer getFeatureID() {
		return featureID;
	}

	public void setFeatureID(Integer featureID) {
		this.featureID = featureID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.featureName.equals(((Feature)obj).getFeatureName())) {
			return true;
		}
		return false;
	}
	

	@Override
	public String toString() {
		return "Feature [featureName=" + featureName + ", featureID=" + featureID + "]";
	}

	
}
