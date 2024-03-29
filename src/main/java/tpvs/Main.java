package tpvs;

import java.util.ArrayList;
import java.util.List;

import org.opt4j.core.Genotype;
import org.opt4j.core.Individual;
import org.opt4j.core.common.completer.IndividualCompleterModule;
import org.opt4j.core.genotype.BooleanGenotype;
import org.opt4j.core.optimizer.Archive;
import org.opt4j.core.start.Opt4JTask;
import org.opt4j.operators.crossover.BasicCrossoverModule;
import org.opt4j.operators.mutate.BasicMutateModule;
import org.opt4j.operators.mutate.BasicMutateModule.MutationRateType;
import org.opt4j.optimizers.ea.EvolutionaryAlgorithmModule;
import org.opt4j.viewer.ViewerModule;
import org.opt4j.operators.mutate.BasicMutateModule.PermutationType;

import tpvs.algorithm.TopDownVariantSelectionModule;
import tpvs.algorithm.TopDownVariantSelectionSelector;
import tpvs.algorithm.TopDownVariantSelectionDecoder;
import tpvs.parser.VariantParser;
import tpvs.variants.FeatureModel;
import tpvs.variants.Variant;

public class Main {

	public static List<Variant> variantList = new ArrayList<Variant>();
	public static FeatureModel featureModel = new FeatureModel();
//	private static final String featureModelData = "smartHome";
//	private static final String featureModelData = "bankAccount";
//	private static final String featureModelData = "elevator";
//	private static final String featureModelData = "email";
//	private static final String featureModelData = "sodaVendingMachine";
	private static final String featureModelData = "studentAttendanceSystem";

	public static void main(String[] args) {
		VariantParser vp = new VariantParser();
		// collect the full feature list in the feature model 
//		FeatureModel fm = vp.parseFeatureModel("featureModels/smartHome/model.xml");
		FeatureModel fm = vp.parseFeatureModel("featureModels/"+featureModelData+"/model.xml");
		featureModel = fm;

		// Collect the feature lists for each variant
//		List<Variant> variants = vp.parseVariants("featureModels/smartHome/variants");
		List<Variant> variants = vp.parseVariants("featureModels/"+featureModelData+"/variants");
		variantList.addAll(variants);

		// A sanity check for listing every variant's feature list
//		for(Variant v : variants) {
//			System.out.println("Variant #" + v.getVariantID());
//			for(Feature f : v.getFeatureList()) {
//				System.out.println("\t" + f);
//			}
//		}

		EvolutionaryAlgorithmModule ea = new EvolutionaryAlgorithmModule();
		ea.setGenerations(100);
		ea.setCrossoverRate(0.75);
		TopDownVariantSelectionModule dtlz = new TopDownVariantSelectionModule();

		BasicMutateModule mm = new BasicMutateModule();
		mm.setMutationRate(0.3);
		MutationRateType mtrMutationRateType = MutationRateType.ADAPTIVE;
		mm.setMutationRateType(mtrMutationRateType);

		PermutationType pt = PermutationType.INSERT;
		mm.setPermutationType(pt);

		BasicCrossoverModule bcm = new BasicCrossoverModule();
		bcm.setPermutationType(org.opt4j.operators.crossover.BasicCrossoverModule.PermutationType.BUCKET);

		IndividualCompleterModule icm = new IndividualCompleterModule();
		icm.setThreads(12);
		icm.setType(IndividualCompleterModule.Type.PARALLEL);

		ViewerModule viewer = new ViewerModule();
		viewer.setCloseOnStop(false);
		
		Opt4JTask task = new Opt4JTask(false);

//					task.init(ea, dtlz, mm, icm, bcm, viewer);
		task.init(ea, dtlz, mm, icm, bcm);

		try {
			task.execute();
			Archive archive = task.getInstance(Archive.class);
			for (Individual individual : archive) {
//				System.out.println(individual.getObjectives().getValues());
				TopDownVariantSelectionDecoder decoder = new TopDownVariantSelectionDecoder();
				printSelectedVariants(decoder.decode((BooleanGenotype) individual.getGenotype()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			task.close();
		}
	}
	
	public static void printSelectedVariants(TopDownVariantSelectionSelector genotype) {
		System.out.print("Selected Variants: ");
		int selectedVariantCount = 0;
		for(int i=0; i<genotype.size(); i++) {
			if(genotype.get(i)) {
				System.out.print(i + ", ");
				selectedVariantCount++;
			}
		}
		System.out.println("\nSelected Variant Count: " + selectedVariantCount);
	}

}
