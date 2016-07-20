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
	protected static Double meters_per_degree = planet_circumference / 360.0;
	protected static Double resinmeters = (degextent / gridextent) * meters_per_degree;
	protected static Double inverse_res_in_meters = 1.0 / resinmeters;

	protected static Double lightcontrast = new Double(1.0 / resinmeters);

	// surface map
	static List<GradientPointParameter> gradientPointList = new ArrayList<GradientPointParameter>();
	static {
		GradientPointParameter gradientPointParameter = new GradientPointParameter(-16384.0 + seaLevelInMeters,
				new ColorCafe(3, 29, 63, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(deep_sea_level + seaLevelInMeters,
				new ColorCafe(3, 29, 63, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(-1.0 + seaLevelInMeters, new ColorCafe(7, 106, 127, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(0.0 + seaLevelInMeters, new ColorCafe(62, 86, 30, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(1024.0 + seaLevelInMeters, new ColorCafe(84, 96, 50, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(2048.0 + seaLevelInMeters,
				new ColorCafe(130, 127, 97, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(3072.0 + seaLevelInMeters,
				new ColorCafe(184, 163, 141, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(4096.0 + seaLevelInMeters,
				new ColorCafe(255, 255, 255, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(6144.0 + seaLevelInMeters,
				new ColorCafe(128, 255, 255, 255));
		gradientPointList.add(gradientPointParameter);
		gradientPointParameter = new GradientPointParameter(16384.0 + seaLevelInMeters, new ColorCafe(0, 0, 255, 255));
		gradientPointList.add(gradientPointParameter);

	}

	static List<ControlPoint> controlPoints = new ArrayList<ControlPoint>();
	static {
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
	
	
	static List<ControlPoint> badLandCLiffsControlPoints = new ArrayList<ControlPoint>();
	static{
		ControlPoint controlPoint = new ControlPoint();
		controlPoint.inputValue = -2.0000;
		controlPoint.outputValue = -2.0000;
		badLandCLiffsControlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = -1.0000;
		controlPoint.outputValue = -1.2500;
		badLandCLiffsControlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = -0.0000;
		controlPoint.outputValue = -0.7500;
		badLandCLiffsControlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 0.5000;
		controlPoint.outputValue = -0.2500;
		badLandCLiffsControlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 0.6250;
		controlPoint.outputValue = 0.8750;
		badLandCLiffsControlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 0.7500;
		controlPoint.outputValue = 1.0000;
		badLandCLiffsControlPoints.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 2.0000;
		controlPoint.outputValue = 1.2500;
		badLandCLiffsControlPoints.add(controlPoint);
	}
	
	static List<Double> badLandsTerraceControlPoints = new ArrayList<Double>();
	static{
		badLandsTerraceControlPoints.add(-1.0000);
		badLandsTerraceControlPoints.add(-0.8750);
		badLandsTerraceControlPoints.add(-0.7500);
		badLandsTerraceControlPoints.add(-0.5000);
		badLandsTerraceControlPoints.add(1.0000);
	}
	
	
	static List<ControlPoint> riverPositionControlPoints0 = new ArrayList<ControlPoint>();
	static{
		ControlPoint controlPoint = new ControlPoint();
		controlPoint.inputValue = -2.0000;
		controlPoint.outputValue = 2.0000;
		riverPositionControlPoints0.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = -1.0000;
		controlPoint.outputValue = 1.0000;
		riverPositionControlPoints0.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = -0.1250;
		controlPoint.outputValue = 0.785;
		riverPositionControlPoints0.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 0.0000;
		controlPoint.outputValue = 1.0000;
		riverPositionControlPoints0.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 1.0000;
		controlPoint.outputValue = -1.5000;
		riverPositionControlPoints0.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 2.0000;
		controlPoint.outputValue = -2.0000;
		riverPositionControlPoints0.add(controlPoint);
	}
	
	static List<ControlPoint> riverPositionControlPoints1 = new ArrayList<ControlPoint>();
	static{
		ControlPoint controlPoint = new ControlPoint();
		controlPoint.inputValue = -2.0000;
		controlPoint.outputValue = 2.0000;
		riverPositionControlPoints1.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = -1.0000;
		controlPoint.outputValue = 1.5000;
		riverPositionControlPoints1.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = -0.1250;
		controlPoint.outputValue = 0.4375;
		riverPositionControlPoints1.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 0.0000;
		controlPoint.outputValue = 0.5000;
		riverPositionControlPoints1.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 1.0000;
		controlPoint.outputValue = 0.2500;
		riverPositionControlPoints1.add(controlPoint);
		controlPoint = new ControlPoint();
		controlPoint.inputValue = 2.0000;
		controlPoint.outputValue = 0.0000;
		riverPositionControlPoints1.add(controlPoint);
	}
	
	
	
	/**
	 * 
	 * @param renderParameter
	 * @return
	 */

	public ImageCafe build(Boolean renderParameter) {

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

		PlanarTerrainType planarTerrainType = new PlanarTerrainType(continent, terrain_type_tu_frequency,
				terrain_type_tu_power, terrain_type_tu_roughness, terrain_type_def_low_control_point,
				terrain_type_def_mid_control_point_scalar, terrain_type_def_high_control_point, shelf_level, sea_level);
		Cached terrainType = planarTerrainType.build();
		
		PlanarMountanType planarMountanType = new PlanarMountanType(mountain_base_def_tu0_frequency,
				mountain_base_def_rm0_octave_count, NoiseQuality.QUALITY_STD, inverse_res_in_meters,
				mountain_base_def_sb0_scale, mountain_base_def_sb0_bias, mountain_base_def_sb1_scale,
				mountain_base_def_sb1_bias, mountain_base_def_rm1_frequency, mountain_base_def_rm1_octave_count,
				NoiseQuality.QUALITY_BEST, mountain_base_def_tu0_frequency, mountain_base_def_tu1_frequency,
				mountain_base_def_tu0_power_scalar0, mountain_base_def_tu1_power_scalar1,
				mountain_base_def_tu0_roughness, mountain_base_def_tu1_roughness, -1.0);
		
		Cached baseMountain = planarMountanType.build();
		
		PlanarHighMountainType planarHighMountainType = new PlanarHighMountainType(mountainous_high_rm0_frequency,
				mountainous_high_rm0_octave_count, mountainous_high_rm1_frequency, mountainous_high_rm1_octave_count,
				NoiseQuality.QUALITY_BEST, mountainous_high_rm0_lacunarity, mountainous_high_tu_frequency, mountainous_high_tu_scalar1,
				mountainous_high_tu_scalar2, mountainous_high_tu_roughness, mountains_twist);
		
		Cached highMountain = planarHighMountainType.build();
		
		PlanarLowMountainType planarLowMountainType = new PlanarLowMountainType(mountainous_low_rm0_frequency,
				mountainous_low_rm0_octave_count, mountainous_low_rm1_frequency, mountainous_low_rm1_octave_count,
				mountainous_low_rm0_lacunarity, NoiseQuality.QUALITY_BEST);
		
		Cached lowMountain = planarLowMountainType.build();
		
		PlanarMountainTerrainType mountainTerrainType = new PlanarMountainTerrainType(mountainous_terrain_sb0_scale,
				mountainous_terrain_sb0_bias, mountainous_terrain_sb1_scale, mountainous_terrain_sb1_bias,
				mountainous_terrain_sb2_scale, mountainous_terrain_sb2_bias, lowMountain, highMountain, baseMountain,
				mountainous_terrain_se_bounds_param_0, mountainous_terrain_se_bounds_param_1,
				mountainous_terrain_se_edge_falloff, mountain_glaciation);
		
		Cached mountainTerrain = mountainTerrainType.build();
		
		PlanarHillType planarHillType = new PlanarHillType(hilly_terrain_bi_frequency, inverse_res_in_meters,
				inverse_res_in_meters, hilly_terrain_bi_octave_count, inverse_res_in_meters, inverse_res_in_meters,
				inverse_res_in_meters, hilly_terrain_rm_octave_count, NoiseQuality.QUALITY_BEST, inverse_res_in_meters,
				inverse_res_in_meters, -1.0, inverse_res_in_meters, inverse_res_in_meters,
				inverse_res_in_meters, inverse_res_in_meters, inverse_res_in_meters, resinmeters,
				hilly_terrain_tu0_roughness, planet_circumference, meters_per_degree, lightcontrast,
				inverse_res_in_meters, hilly_terrain_tu1_roughness);
		
		Cached hillTerrain = planarHillType.build();
		
		PlanarPlainsType planarPlainsType = new PlanarPlainsType(plains_lacunarity, plains_terrain_bi0_frequency,
				plains_terrain_bi0_persistence, plains_terrain_bi0_octave_count, NoiseQuality.QUALITY_BEST,
				plains_terrain_sb0_bias, plains_terrain_sb0_scale, plains_terrain_bi1_frequency,
				plains_terrain_bi1_persistence, plains_terrain_bi1_octave_count, plains_terrain_sb1_bias,
				plains_terrain_sb1_scale, plains_terrain_sb2_bias, plains_terrain_sb2_scale);
		
		Cached plainsTerrain = planarPlainsType.build();
		
		PlanarBadlandSandType planarBadlandSandType = new PlanarBadlandSandType(badlands_lacunarity,
				NoiseQuality.QUALITY_BEST, badlands_sand_rm_frequency, badlands_sand_rm_octave_count,
				badlands_sand_sb0_scale, badlands_sand_sb0_bias, badlands_sand_vo_frequency,
				badlands_sand_vo_displacemwnt, Boolean.TRUE, badlands_sand_sb1_scale, badlands_sand_sb1_bias);

		Cached badlandSand = planarBadlandSandType.build();
		
		PlanarBadlandCliffType planarBadlandCliffType = new PlanarBadlandCliffType(badlands_lacunarity, badlands_twist,
				badlands_cliffs_pe_frequency, continent_frequency, badlands_cliffs_pe_persistence,
				badlands_cliffs_pe_octave_count, badlands_cliffs_cl_lower_bound, badlands_cliffs_cl_upper_bound,
				badlands_cliffs_tu0_frequency, badlands_cliffs_tu0_scalar0, badlands_cliffs_tu0_scalar1,
				badlands_cliffs_tu0_roughness, badlands_cliffs_tu1_frequency, badlands_cliffs_tu1_scalar0,
				badlands_cliffs_tu1_scalar1, badlands_cliffs_tu1_roughness, badLandCLiffsControlPoints,
				badLandsTerraceControlPoints, NoiseQuality.QUALITY_BEST);
		
		Cached badlandCliffs = planarBadlandSandType.build();
		
		PlanarBadlandsDunesType planarBadlandsDunesType = new PlanarBadlandsDunesType(badlands_terrain_sb_scale,
				badlands_terrain_sb_bias, badlandSand);

		Cached badlandDunes = planarBadlandsDunesType.build();

		PlanarRiverPositionsType planarRiverPositionsType = new PlanarRiverPositionsType(river_positions_rm0_frequency,
				river_positions_rm0_octave_count, river_positions_rm1_frequency, river_positions_rm1_octave_count,
				river_positions_tu_frequency, river_positions_tu_scalar0, river_positions_tu_scalar1,
				river_positions_tu_roughness, continent_lacunarity, NoiseQuality.QUALITY_BEST,
				riverPositionControlPoints0, riverPositionControlPoints1);

		Cached riverPositions = planarRiverPositionsType.build();
		
		PlanarScaledMountainTerrain planarScaledMountainTerrain = new PlanarScaledMountainTerrain(
				scaled_mountainous_terrain_sb0_scale, scaled_mountainous_terrain_sb0_bias,
				scaled_mountainous_terrain_sb1_scale, scaled_mountainous_terrain_sb1_bias, mountainTerrain,
				scaled_mountainous_terrain_pe_frequency, scaled_mountainous_terrain_pe_persistence, mountain_lacunarity,
				scaled_mountainous_terrain_pe_octave_count, NoiseQuality.QUALITY_STD,
				scaled_mountainous_terrain_ex_exponent);
		
		Cached scaledMountainTerrain = planarScaledMountainTerrain.build();

		/**
		 * build the planet
		 */

		NoiseMapBuilderSphere planet = new NoiseMapBuilderSphere();
		NoiseMap elevGrid = new NoiseMap(grid_width, grid_height);

		planet.setBounds(south_coord, north_coord, west_coord, east_coord);
		planet.setDestSize(grid_width, grid_height);

		planet.setSourceModule(terrainType);
		planet.setDestNoiseMap(elevGrid);
		planet.build();
		RenderImageParameter renderImageParameter = new RenderImageParameter(gradientPointList, elevGrid, renderParameter,
				lightcontrast, lightcontrast);
		ImageCafe imageCafe = Builder.buildRendererImage(renderImageParameter);

		return imageCafe;
	}
}
