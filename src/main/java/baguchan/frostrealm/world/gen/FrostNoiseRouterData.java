package baguchan.frostrealm.world.gen;

import baguchan.frostrealm.data.resource.FrostDensityFunctions;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.synth.NormalNoise;


public class FrostNoiseRouterData {

	private static final ResourceKey<DensityFunction> Y = createKey("y");
	private static final ResourceKey<DensityFunction> SHIFT_X = createKey("shift_x");
	private static final ResourceKey<DensityFunction> SHIFT_Z = createKey("shift_z");
	public static final ResourceKey<DensityFunction> RIDGES = createKey("overworld/ridges");
	private static final ResourceKey<DensityFunction> SPAGHETTI_ROUGHNESS_FUNCTION = createKey("overworld/caves/spaghetti_roughness_function");
	private static final ResourceKey<DensityFunction> ENTRANCES = createKey("overworld/caves/entrances");
	private static final ResourceKey<DensityFunction> NOODLE = createKey("overworld/caves/noodle");
	private static final ResourceKey<DensityFunction> PILLARS = createKey("overworld/caves/pillars");
	private static final ResourceKey<DensityFunction> SPAGHETTI_2D_THICKNESS_MODULATOR = createKey("overworld/caves/spaghetti_2d_thickness_modulator");
	private static final ResourceKey<DensityFunction> SPAGHETTI_2D = createKey("overworld/caves/spaghetti_2d");


	private static ResourceKey<DensityFunction> createKey(String p_209537_) {
		return ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(p_209537_));
	}

	private static ResourceKey<DensityFunction> createKey(String modid, String p_209537_) {
		return ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(modid, p_209537_));
	}

	private static DensityFunction underground(HolderGetter<DensityFunction> p_256548_, HolderGetter<NormalNoise.NoiseParameters> p_256236_, DensityFunction p_256658_) {
		DensityFunction densityfunction = getFunction(p_256548_, SPAGHETTI_2D);
		DensityFunction densityfunction1 = getFunction(p_256548_, SPAGHETTI_ROUGHNESS_FUNCTION);
		DensityFunction densityfunction2 = DensityFunctions.noise(p_256236_.getOrThrow(Noises.CAVE_LAYER), 8.0D);
		DensityFunction densityfunction3 = DensityFunctions.mul(DensityFunctions.constant(4.0D), densityfunction2.square());
		DensityFunction densityfunction4 = DensityFunctions.noise(p_256236_.getOrThrow(Noises.CAVE_CHEESE), 0.6666666666666666D);
		//Start Cheese
		DensityFunction densityfunction5 = DensityFunctions.add(DensityFunctions.add(DensityFunctions.constant(0.27D), densityfunction4).clamp(-1.0D, 1.0D), DensityFunctions.add(DensityFunctions.constant(1.5D), DensityFunctions.mul(DensityFunctions.constant(-0.64D), p_256658_)).clamp(0.0D, 0.5D));
		//Stop Cheese
		DensityFunction densityfunction6 = DensityFunctions.add(densityfunction3, densityfunction5);
		DensityFunction densityfunction7 = DensityFunctions.min(DensityFunctions.min(densityfunction6, getFunction(p_256548_, ENTRANCES)), DensityFunctions.add(densityfunction, densityfunction1));
		DensityFunction densityfunction8 = getFunction(p_256548_, PILLARS);
		DensityFunction densityfunction9 = DensityFunctions.rangeChoice(densityfunction8, -1000000.0D, 0.03D, DensityFunctions.constant(-1000000.0D), densityfunction8);
		return DensityFunctions.max(densityfunction7, densityfunction9);
	}

	private static DensityFunction postProcess(DensityFunction p_224493_) {
		DensityFunction densityfunction = DensityFunctions.blendDensity(p_224493_);
		return DensityFunctions.mul(DensityFunctions.interpolated(densityfunction), DensityFunctions.constant(0.64D)).squeeze();
	}

	public static NoiseRouter frostrealm(HolderGetter<DensityFunction> p_224486_, HolderGetter<NormalNoise.NoiseParameters> p_256236_) {
		DensityFunction densityfunction = DensityFunctions.noise(p_256236_.getOrThrow(Noises.AQUIFER_BARRIER), 0.5D);
		DensityFunction densityfunction1 = DensityFunctions.noise(p_256236_.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67D);
		DensityFunction densityfunction2 = DensityFunctions.noise(p_256236_.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143D);
		DensityFunction densityfunction3 = DensityFunctions.noise(p_256236_.getOrThrow(Noises.AQUIFER_LAVA));
		DensityFunction densityfunction4 = getFunction(p_224486_, SHIFT_X);
		DensityFunction densityfunction5 = getFunction(p_224486_, SHIFT_Z);
		DensityFunction densityfunction6 = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D, p_256236_.getOrThrow(Noises.TEMPERATURE_LARGE));
		DensityFunction densityfunction7 = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D, p_256236_.getOrThrow(Noises.VEGETATION_LARGE));
		DensityFunction densityfunction8 = getFunction(p_224486_, FrostDensityFunctions.FACTOR);
		DensityFunction densityfunction9 = getFunction(p_224486_, FrostDensityFunctions.DEPTH);
		DensityFunction densityfunction10 = noiseGradientDensity(DensityFunctions.cache2d(densityfunction8), densityfunction9);
		DensityFunction densityfunction11 = getFunction(p_224486_, FrostDensityFunctions.SLOPED_CHEESE);
		DensityFunction densityfunction12 = DensityFunctions.min(densityfunction11, DensityFunctions.mul(DensityFunctions.constant(5.0D), getFunction(p_224486_, ENTRANCES)));
		DensityFunction densityfunction13 = DensityFunctions.rangeChoice(densityfunction11, -1000000.0D, 1.5625D, densityfunction12, underground(p_224486_, p_256236_, densityfunction11));
		DensityFunction densityfunction14 = DensityFunctions.min(postProcess(DensityFunctions.add(slideCave(densityfunction13), slideOverworld(densityfunction13))), getFunction(p_224486_, NOODLE));
		DensityFunction densityfunction15 = getFunction(p_224486_, Y);
		return new NoiseRouter(densityfunction, densityfunction1, densityfunction2, densityfunction3, densityfunction6, densityfunction7, getFunction(p_224486_, FrostDensityFunctions.CONTINENTS), getFunction(p_224486_, FrostDensityFunctions.EROSION), densityfunction9, getFunction(p_224486_, RIDGES), slideOverworld(DensityFunctions.add(densityfunction10, DensityFunctions.constant(-0.703125D)).clamp(-80.0D, 64.0D)), densityfunction14, DensityFunctions.constant(0.0F), DensityFunctions.constant(0.0F), DensityFunctions.constant(0.0F));
	}

	private static DensityFunction slideOverworld(DensityFunction p_224491_) {
		return slide(p_224491_, -80, 400, 32, 16, -0.078125D, 0, 32, 0.5D);
	}

	private static DensityFunction slideCave(DensityFunction p_224491_) {
		return slide(p_224491_, -80, -32, 32, 16, -0.078125D, 0, 32, 0.5D);
	}

	private static DensityFunction noiseGradientDensity(DensityFunction p_212272_, DensityFunction p_212273_) {
		DensityFunction densityfunction = DensityFunctions.mul(p_212273_, p_212272_);
		return DensityFunctions.mul(DensityFunctions.constant(4.0D), densityfunction.quarterNegative());
	}

	private static DensityFunction yLimitedInterpolatable(DensityFunction p_209472_, DensityFunction p_209473_, int p_209474_, int p_209475_, int p_209476_) {
		return DensityFunctions.interpolated(DensityFunctions.rangeChoice(p_209472_, (double) p_209474_, (double) (p_209475_ + 1), p_209473_, DensityFunctions.constant((double) p_209476_)));
	}

	private static DensityFunction slide(DensityFunction density, int minY, int maxY, int fromYTop, int toYTop, double offset1, int fromYBottom, int toYBottom, double offset2) {
		DensityFunction topSlide = DensityFunctions.yClampedGradient(minY + maxY - fromYTop, minY + maxY - toYTop, 1, 0);
		density = DensityFunctions.lerp(topSlide, offset1, density);
		DensityFunction bottomSlide = DensityFunctions.yClampedGradient(minY + fromYBottom, minY + toYBottom, 0, 1);
		return DensityFunctions.lerp(bottomSlide, offset2, density);
	}


	private static DensityFunction getFunction(HolderGetter<DensityFunction> p_224465_, ResourceKey<DensityFunction> p_224466_) {
		return new DensityFunctions.HolderHolder(p_224465_.getOrThrow(p_224466_));
	}
}