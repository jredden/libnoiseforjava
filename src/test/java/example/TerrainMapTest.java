package example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import libnoiseforjava.domain.Builder;
import libnoiseforjava.domain.BuilderPlaneParameters;
import libnoiseforjava.domain.GradientPointParameter;
import libnoiseforjava.exception.ExceptionInvalidParam;
import libnoiseforjava.module.Perlin;
import libnoiseforjava.util.ColorCafe;
import libnoiseforjava.util.NoiseMap;
import libnoiseforjava.util.NoiseMapBuilderPlane;

public class TerrainMapTest {

	static Integer noiseMapWidth = new Integer(256);
	static Integer noiseMapHeight = new Integer(256);
	static Integer destinationWidth = new Integer(256);
	static Integer destinationHeight = new Integer(256);
	static Double lowerXBound = new Double(2.0);
	static Double upperXBound = new Double(6.0);
	static Double lowerZBound = new Double(1.0);
	static Double upperZBound = new Double(5.0);
	
	static{
		List<GradientPointParameter> gradientPointList = new ArrayList<GradientPointParameter>();
		GradientPointParameter gradientPointParameter = new GradientPointParameter(-1.0000, new ColorCafe(0, 0, 128, 255));  //deeps
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(-0.2500, new ColorCafe(0, 0, 255, 255));  // shallow
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(0.0000, new ColorCafe(0, 128, 255, 255));  // shore
		gradientPointList.add(gradientPointParameter);
	}
	
	/*
	 *  renderer.addGradientPoint(-1.0000, new ColorCafe(0, 0, 128, 255)); // deeps
		renderer.addGradientPoint(-0.2500, new ColorCafe(0, 0, 255, 255)); // shallow
		renderer.addGradientPoint(0.0000, new ColorCafe(0, 128, 255, 255)); // shore
		renderer.addGradientPoint(0.0625, new ColorCafe(240, 240, 64, 255)); // sand
		renderer.addGradientPoint(0.1250, new ColorCafe(32, 160, 0, 255)); // grass
		renderer.addGradientPoint(0.3750, new ColorCafe(224, 224, 0, 255)); // dirt
		renderer.addGradientPoint(0.7500, new ColorCafe(128, 128, 128, 255)); // rock
		renderer.addGradientPoint(1.0000, new ColorCafe(255, 255, 255, 255)); // snow

	 */
	
	/*
	 * 
	 */

	@Test
	public void test() {
		NoiseMap noiseMap = Builder.createNoiseMap(noiseMapWidth,
				noiseMapHeight);
		Perlin perlin = new Perlin();
		BuilderPlaneParameters builderPlaneParameters = new BuilderPlaneParameters(
				perlin, noiseMap, destinationWidth, destinationHeight,
				lowerXBound, upperXBound, lowerZBound, upperZBound);
		NoiseMapBuilderPlane noiseMapBuilderPlane = Builder.builderPlane(builderPlaneParameters);
	}

}
