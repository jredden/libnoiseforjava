package example;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import libnoiseforjava.NoiseGen.NoiseQuality;
import libnoiseforjava.domain.Builder;
import libnoiseforjava.domain.GradientPointParameter;
import libnoiseforjava.domain.RenderImageParameter;
import libnoiseforjava.domain.RenderImageSphereParameter;
import libnoiseforjava.module.Cached;
import libnoiseforjava.module.Clamp;
import libnoiseforjava.module.Curve;
import libnoiseforjava.module.Min;
import libnoiseforjava.module.Perlin;
import libnoiseforjava.module.ScaleBias;
import libnoiseforjava.persistence.Output;
import libnoiseforjava.util.ColorCafe;
import libnoiseforjava.util.ImageCafe;
import libnoiseforjava.util.NoiseMap;
import libnoiseforjava.util.NoiseMapBuilder;
import libnoiseforjava.util.NoiseMapBuilderSphere;
import libnoiseforjava.util.RendererImage;

import org.junit.Test;

public class ComplexPlanetTest {
	/**
	 * these values should either be generated by an AI engine and (possibly
	 * persisted in the store)
	 */

	// //////////////////////////////////////////////////////////////////////////
	// Constants
	//
	// Modify these constants to change the terrain of the planet and to change
	// the boundaries and size of the elevation grid.
	//
	// Note: "Planetary elevation units" range from -1.0 (for the lowest
	// underwater trenches) to +1.0 (for the highest mountain peaks.)
	//

	// Planet seed. Change this to generate a different planet
	private static Integer CUR_SEED = 0;
	// Frequency of the planet's continents. Higher frequency produces smaller,
	// more numerous continents. This value is measured in radians.
	private static Double CONTINENT_FREQUENCY = 1.0;
	// The persistence value controls the roughness of the Perlin noise,
	// normally between 0.0 and 1.0
	private static Double BASE_CONTINENT_DEF_PERSISTENCE_0 = 0.5;
	private static Double BASE_CONTINENT_DEF_PERSISTENCE_1 = 0.5;
	// Lacunarity. from Latin meaning gap, or fill space
	// Lacunarity of the planet's continents. Changing this value produces
	// slightly different continents. For the best results, this value should
	// be random, but close to 2.0.
	private static Double CONTINENT_LACUNARITY = 2.208984375;
	// Octave count determines the amount of Perlin noise, the higher the count,
	// the more detail
	private static Integer BASE_CONTINENT_DEF_OCTAVE_COUNT_PE0 = 14;
	private static Integer BASE_CONTINENT_DEF_OCTAVE_COUNT_PE1 = 11;
	// Specifies the planet's sea level. This value must be between -1.0
	// (minimum planet elevation) and +1.0 (maximum planet elevation.)
	private static Double SEA_LEVEL = 0.0;
	// / Sets the bias to apply to the scaled output value from the source
	// / module.
	private static Double DEEP_SEA_LEVEL = -256.0;
	private static Double BASE_CONTINENT_DEF_BIAS = 0.625;
	// / Sets the scaling factor to apply to the output value from the
	// / source module.
	private static Double BASE_CONTINENT_DEF_SCALE = 0.375;
	// noise clamping bounds
	private static Double BASE_CONTINENT_DEF_CLAMP_LOWER_BOUND = -1.0;
	private static Double BASE_CONTINENT_DEF_CLAMP_UPPER_BOUND = 1.0;
	// Minimum elevation on the planet, in meters. This value is approximate.
	private static Double MIN_ELEV = -8192.0;
	// Maximum elevation on the planet, in meters. This value is approximate.
	private static Double MAX_ELEV = 8192.0;
	// sea level calculation parameters
	private static Double PARAMETER0 = 1.0;
	private static Double PARAMETER1 = 2.0;
	// Calculate the sea level, in meters
	private static Double seaLevelInMeters = (((SEA_LEVEL + PARAMETER0) / PARAMETER1) * (MAX_ELEV - MIN_ELEV))
			+ MIN_ELEV;
	// Southernmost coordinate of elevation grid.
	private static Double SOUTH_COORD = -90.0;
	// Northernmost coordinate of elevation grid.
	private static Double NORTH_COORD = 90.0;
	// Westernmost coordinate of elevation grid.
	private static Double WEST_COORD = -180.0;
	// Easternmost coordinate of elevation grid.
	private static Double EAST_COORD = 180.0;
	// Width of elevation grid, in points
	private static Integer GRID_WIDTH = 4096;
	// Height of elevation grid, in points
	private static Integer GRID_HEIGHT = 2048;
	
	private static Double PLANET_CIRCUMFERENCE = 44236800.0;
	private static Double METERS_PER_DEGREE = PLANET_CIRCUMFERENCE/360.0;
	// 

	// create a spherical-noise-map builder
	private static NoiseMapBuilderSphere planet = new NoiseMapBuilderSphere();
	private static NoiseMap elevGrid = new NoiseMap(GRID_HEIGHT, GRID_WIDTH);
	static {
		planet.setBounds(SOUTH_COORD, NORTH_COORD, WEST_COORD, EAST_COORD);
		planet.setDestSize(100, 100);

	}

	static List<GradientPointParameter> gradientPointList = new ArrayList<GradientPointParameter>();
	static {
		GradientPointParameter gradientPointParameter = new GradientPointParameter(
				-16384.0 + seaLevelInMeters, new ColorCafe(3, 29, 63, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(DEEP_SEA_LEVEL
				+ seaLevelInMeters, new ColorCafe(3, 29, 63, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(-1.0
				+ seaLevelInMeters, new ColorCafe(7, 106, 127, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(
				0.0 + seaLevelInMeters, new ColorCafe(62, 86, 30, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(
				1024.0 + seaLevelInMeters, new ColorCafe(84, 96, 50, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(
				2048.0 + seaLevelInMeters, new ColorCafe(130, 127, 97, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(
				3072.0 + seaLevelInMeters, new ColorCafe(184, 163, 141, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(
				4096.0 + seaLevelInMeters, new ColorCafe(255, 255, 255, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(
				6144.0 + seaLevelInMeters, new ColorCafe(128, 255, 255, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(
				16384.0 + seaLevelInMeters, new ColorCafe(0, 0, 255, 255));
		gradientPointList.add(gradientPointParameter);

	}

	static Double lightContrast = new Double(3.0);
	static Double lightBrightness = new Double(2.0);
	static Double lightElevation = new Double(45.0);
	static Double lightAzumith = new Double(135.0);
	
	static Double  degExtent = EAST_COORD - WEST_COORD;
	static Double gridExtent = new Double(GRID_WIDTH);
	// double resInMeters = (degExtent / gridExtent) * metersPerDegree;
	static Double resInMeters = (degExtent / gridExtent) * METERS_PER_DEGREE;
	static Double INVERSE_RES_IN_METERS = 1.0/resInMeters;

	// 1: [Continent module]: This Perlin-noise module generates the continents.
	// This noise module has a high number of octaves so that detail is
	// visible at high zoom levels.

	static private Perlin baseContinentDef_pe0 = new Perlin();
	static {
		baseContinentDef_pe0.setSeed(CUR_SEED);
		baseContinentDef_pe0.setFrequency(CONTINENT_FREQUENCY);
		baseContinentDef_pe0.setPersistence(BASE_CONTINENT_DEF_PERSISTENCE_0);
		baseContinentDef_pe0.setLacunarity(CONTINENT_LACUNARITY);
		baseContinentDef_pe0
				.setOctaveCount(BASE_CONTINENT_DEF_OCTAVE_COUNT_PE0);
		baseContinentDef_pe0.setNoiseQuality(NoiseQuality.QUALITY_STD);
	}

	// 2: [Continent-with-ranges module]: Next, a curve module modifies the
	// output value from the continent module so that very high values appear
	// near sea level. This defines the positions of the mountain ranges.
	static private Curve baseContinentDef_cu = new Curve(baseContinentDef_pe0);
	static {
		baseContinentDef_cu.addControlPoint(-2.0000 + SEA_LEVEL, -1.625
				+ SEA_LEVEL);
		baseContinentDef_cu.addControlPoint(-1.0000 + SEA_LEVEL, -1.375
				+ SEA_LEVEL);
		baseContinentDef_cu.addControlPoint(0.0000 + SEA_LEVEL, -0.375
				+ SEA_LEVEL);
		baseContinentDef_cu.addControlPoint(0.0625 + SEA_LEVEL,
				0.125 + SEA_LEVEL);
		baseContinentDef_cu.addControlPoint(0.1250 + SEA_LEVEL,
				0.250 + SEA_LEVEL);
		baseContinentDef_cu.addControlPoint(0.2500 + SEA_LEVEL,
				1.000 + SEA_LEVEL);
		baseContinentDef_cu.addControlPoint(0.5000 + SEA_LEVEL,
				0.250 + SEA_LEVEL);
		baseContinentDef_cu.addControlPoint(0.7500 + SEA_LEVEL,
				0.250 + SEA_LEVEL);
		baseContinentDef_cu.addControlPoint(1.0000 + SEA_LEVEL,
				0.500 + SEA_LEVEL);
		baseContinentDef_cu.addControlPoint(2.0000 + SEA_LEVEL,
				0.500 + SEA_LEVEL);

	}
	// 3: [Carver module]: This higher-frequency Perlin-noise module will be
	// used by subsequent noise modules to carve out chunks from the mountain
	// ranges within the continent-with-ranges module so that the mountain
	// ranges will not be complely impassible.

	static private Perlin baseContinentDef_pe1 = new Perlin();
	static {
		baseContinentDef_pe1.setSeed(CUR_SEED + 1);
		baseContinentDef_pe1.setFrequency(CONTINENT_FREQUENCY);
		baseContinentDef_pe1.setPersistence(BASE_CONTINENT_DEF_PERSISTENCE_1);
		baseContinentDef_pe1.setLacunarity(CONTINENT_LACUNARITY);
		baseContinentDef_pe1
				.setOctaveCount(BASE_CONTINENT_DEF_OCTAVE_COUNT_PE1);
		baseContinentDef_pe1.setNoiseQuality(NoiseQuality.QUALITY_STD);
	}

	// 4: [Scaled-carver module]: This scale/bias module scales the output
	// value from the carver module such that it is usually near 1.0. This
	// is required for step 5.

	static private ScaleBias baseContinentDef_sb = new ScaleBias(
			baseContinentDef_pe1);
	static {
		baseContinentDef_sb.setScale(BASE_CONTINENT_DEF_SCALE);
		baseContinentDef_sb.setBias(BASE_CONTINENT_DEF_BIAS);
	}

	// 5: [Carved-continent module]: This minimum-value module carves out chunks
	// from the continent-with-ranges module. It does this by ensuring that
	// only the minimum of the output values from the scaled-carver module
	// and the continent-with-ranges module contributes to the output value
	// of this subgroup. Most of the time, the minimum-value module will
	// select the output value from the continents-with-ranges module since
	// the output value from the scaled-carver module is usually near 1.0.
	// Occasionally, the output value from the scaled-carver module will be
	// less than the output value from the continent-with-ranges module, so
	// in this case, the output value from the scaled-carver module is
	// selected.
	static private Min baseContinentDef_mi = new Min(baseContinentDef_sb,
			baseContinentDef_cu);

	// 6: [Clamped-continent module]: Finally, a clamp module modifies the
	// carved-continent module to ensure that the output value of this
	// subgroup is between -1.0 and 1.0.

	static private Clamp baseContinentDef_cl = new Clamp(baseContinentDef_mi);
	static {
		baseContinentDef_cl.setBounds(BASE_CONTINENT_DEF_CLAMP_LOWER_BOUND,
				BASE_CONTINENT_DEF_CLAMP_UPPER_BOUND);
	}

	static private Cached baseContinentDef = new Cached(baseContinentDef_cl);

	@Test
	public void test() {
		// Build the elevation grid with the output values from the final-planet
		// group.
		planet.setSourceModule(baseContinentDef);
		planet.setDestNoiseMap(elevGrid);
		planet.build();
		RendererImage surfaceRenderer = new RendererImage();
		ImageCafe destImage = new ImageCafe(elevGrid.getWidth(), elevGrid.getHeight());
		surfaceRenderer.setDestImage(destImage);
		surfaceRenderer.setSourceNoiseMap(elevGrid);
		surfaceRenderer.clearGradient();
		for (GradientPointParameter gradientPointParameter : gradientPointList ){
			surfaceRenderer.addGradientPoint(gradientPointParameter.getGradientPosition(), gradientPointParameter.getColorCafe());
		}
		surfaceRenderer.enableLight(Boolean.TRUE);
		surfaceRenderer.setLightContrast(lightContrast);
		surfaceRenderer.setLightIntensity(lightBrightness);
		surfaceRenderer.setLightElev(lightElevation);
		surfaceRenderer.setLightAzimuth(lightAzumith);
		surfaceRenderer.render();
		String uri = "images/"+Math.random()+"complextPlanet0_test.png";
		Output.writer(destImage, uri);

	}

	@Test
	public void test0() {
		// Build the elevation grid with the output values from the final-planet
		// group.
		planet.setSourceModule(baseContinentDef);
		planet.setDestNoiseMap(elevGrid);
		planet.build();
		RenderImageParameter renderImageParameter = new RenderImageParameter(gradientPointList, elevGrid, Boolean.TRUE, lightContrast, lightBrightness);
		ImageCafe imageCafe = Builder.buildRendererImage(renderImageParameter);
		String uri = "images/"+Math.random()+"complextPlanet1_test.png";
		Output.writer(imageCafe, uri);

	}

}
