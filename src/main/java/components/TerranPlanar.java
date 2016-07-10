package components;

import java.util.ArrayList;
import java.util.List;

import libnoiseforjava.NoiseGen.NoiseQuality;
import libnoiseforjava.domain.Builder;
import libnoiseforjava.domain.ControlPoint;
import libnoiseforjava.domain.GradientPointParameter;
import libnoiseforjava.domain.RenderImageParameter;
import libnoiseforjava.module.Cached;
import libnoiseforjava.util.ColorCafe;
import libnoiseforjava.util.ImageCafe;
import libnoiseforjava.util.NoiseMap;
import libnoiseforjava.util.NoiseMapBuilderSphere;

public class TerranPlanar extends Planar {
	
	protected static Double planet_circumference = 44236800.0;
	
	// surface map
	static List<GradientPointParameter> gradientPointList = new ArrayList<GradientPointParameter>();
	static {
		GradientPointParameter gradientPointParameter = new GradientPointParameter(
				-16384.0 + seaLevelInMeters, new ColorCafe(3, 29, 63, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(deep_sea_level
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

	static List<ControlPoint> controlPoints = new ArrayList<ControlPoint>();
	static{
		ControlPoint controlPoint = new ControlPoint();
		controlPoint.inputValue = -2.0000;
		controlPoint.outputValue = -1.625;
		controlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = -1.0000;
		controlPoint.outputValue = -1.375;
		controlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 0.0000;
		controlPoint.outputValue = -0.375;
		controlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 0.0625;
		controlPoint.outputValue = 0.125;
		controlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 0.1250;
		controlPoint.outputValue = 0.250;
		controlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 0.2500;
		controlPoint.outputValue = 1.000;
		controlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 0.5000;
		controlPoint.outputValue = 0.2500;
		controlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 0.7500;
		controlPoint.outputValue = 0.2500;
		controlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 1.0000;
		controlPoint.outputValue = 0.500;
		controlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 2.0000;
		controlPoint.outputValue = 0.500;
		controlPoints.add(controlPoint);

	}
	
	public ImageCafe build() {

		PlanarBaseContinent planarBaseContinent = new PlanarBaseContinent(continent_frequency,
				base_continent_def_persistence_0, continent_lacunarity, base_continent_def_octave_count_pe0,
				controlPoints, base_continent_def_persistence_1, base_continent_def_octave_count_pe1,
				base_continent_def_scale, base_continent_def_bias, continent_def_se_lower_bounds,
				continent_def_se_upper_bounds, NoiseQuality.QUALITY_STD);
		Cached baseContinent = planarBaseContinent.build();
		PlanarContinent planarContinent = new PlanarContinent(null, Boolean.FALSE, tu0_frequency, tu1_frequency,
				tu2_frequency, tu0_power_scalar, tu1_power_scalar, tu2_power_scalar, tu0_roughness, tu1_roughness,
				tu2_roughness, continent_def_se_lower_bounds, continent_def_se_upper_bounds,
				continent_def_se_edge_falloff, baseContinent);
		Cached continent = planarContinent.build();
		
		NoiseMapBuilderSphere planet = new NoiseMapBuilderSphere();
		NoiseMap elevGrid = new NoiseMap(grid_width, grid_height);

		planet.setBounds(south_coord, north_coord, west_coord, east_coord);
		planet.setDestSize(grid_width, grid_height);

		planet.setSourceModule(continent);
		planet.setDestNoiseMap(elevGrid);
		planet.build();
		RenderImageParameter renderImageParameter = new RenderImageParameter(gradientPointList, elevGrid, Boolean.FALSE,
				lightcontrast, lightcontrast);
		ImageCafe imageCafe = Builder.buildRendererImage(renderImageParameter);

		return imageCafe;
	}
}
