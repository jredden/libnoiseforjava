package components;

import java.util.List;

import libnoiseforjava.domain.GradientPointParameter;
import libnoiseforjava.module.Cached;
import libnoiseforjava.module.RidgedMulti;
import libnoiseforjava.module.ScaleBias;

public abstract class Planar {

	
	protected List<GradientPointParameter> gradientPointList;
	protected static Double planetCircumference;
	
	/*
	 * default constants
	 */
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
	private static Double METERS_PER_DEGREE = PLANET_CIRCUMFERENCE / 360.0;

	// Offset to apply to the terrain type definition. Low values (< 1.0) cause
	// the rough areas to appear only at high elevations. High values (> 2.0)
	// cause the rough areas to appear at any elevation. The percentage of
	// rough areas on the planet are independent of this value.
	private static Double TERRAIN_OFFSET = 1.0;


	protected static Double lightBrightness = new Double(2.0);
	protected static Double lightElevation = new Double(45.0);
	protected static Double lightAzumith = new Double(135.0);

	protected static Double degExtent = EAST_COORD - WEST_COORD;
	protected static Double gridExtent = new Double(GRID_WIDTH);
	
	// double resInMeters = (degExtent / gridExtent) * metersPerDegree;
	protected static Double resInMeters = (degExtent / gridExtent) * METERS_PER_DEGREE;
	protected static Double INVERSE_RES_IN_METERS = 1.0 / resInMeters;
	
	protected static Double lightContrast = new Double(1.0/resInMeters);

	// Specifies the level on the planet in which continental shelves appear.
	// This value must be between -1.0 (minimum planet elevation) and +1.0
	// (maximum planet elevation), and must be less than SEA_LEVEL.
	protected static Double SHELF_LEVEL = -0.375;

	// Lacunarity of the planet's mountains. Changing this value produces
	// slightly different mountains. For the best results, this value should
	// be random, but close to 2.0.
	protected static Double MOUNTAIN_LACUNARITY = 2.142578125;

	// Specifies the "twistiness" of the mountains.
	protected  static Double MOUNTAINS_TWIST = 1.0;
	// Specifies the amount of "glaciation" on the mountains. This value
	// should be close to 1.0 and greater than 1.0.
	protected static Double MOUNTAIN_GLACIATION = 1.375;
	// Lacunarity of the planet's hills. Changing this value produces slightly
	// different hills. For the best results, this value should be random, but
	// close to 2.0.
	protected static Double HILLS_LACUNARITY = 2.162109375;
	// Specifies the "twistiness" of the hills
	protected static Double HILLS_TWIST = 1.0;
	// Lacunarity of the planet's plains. Changing this value produces slightly
	// different plains. For the best results, this value should be random, but
	// close to 2.0.
	protected static Double PLAINS_LACUNARITY = 2.314453125;
	// Lacunarity of the planet's badlands. Changing this value produces
	// slightly different badlands. For the best results, this value should be
	// random, but close to 2.0.
	protected static Double BADLANDS_LACUNARITY = 2.212890625;
	// Specifies the "twistiness" of the badlands.
	protected static Double BADLANDS_TWIST = 1.0;
	// Scaling to apply to the base continent elevations, in planetary elevation
	// units.
	protected static Double CONTINENT_HEIGHT_SCALE = (1.0 - SEA_LEVEL) / 4.0;
	// Determines the amount of mountainous terrain that appears on the
	// planet. Values range from 0.0 (no mountains) to 1.0 (all terrain is
	// covered in mountains). Mountainous terrain will overlap hilly terrain.
	// Because the badlands terrain may overlap parts of the mountainous
	// terrain, setting MOUNTAINS_AMOUNT to 1.0 may not completely cover the
	// terrain in mountains.
	protected static Double MOUNTAINS_AMOUNT = 0.5;
	// Determines the amount of hilly terrain that appears on the planet.
	// Values range from 0.0 (no hills) to 1.0 (all terrain is covered in
	// hills). This value must be less than MOUNTAINS_AMOUNT. Because the
	// mountainous terrain will overlap parts of the hilly terrain, and
	// the badlands terrain may overlap parts of the hilly terrain, setting
	// HILLS_AMOUNT to 1.0 may not completely cover the terrain in hills.
	protected static Double HILLS_AMOUNT = (1.0 + MOUNTAINS_AMOUNT) / 2.0;
	// Determines the amount of badlands terrain that covers the planet.
	// Values range from 0.0 (no badlands) to 1.0 (all terrain is covered in
	// badlands.) Badlands terrain will overlap any other type of terrain.
	protected static Double BADLANDS_AMOUNT = 0.03125;
	// Maximum depth of the rivers, in planetary elevation units.
	protected static Double RIVER_DEPTH = 0.0234375;

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: continent definition (5 noise modules)
	//
	// This subgroup warps the output value from the the base-continent-
	// definition subgroup, producing more realistic terrain.
	//
	// Warping the base continent definition produces lumpier terrain with
	// cliffs and rifts.
	//
	// -1.0 represents the lowest elevations and +1.0 represents the highest
	// elevations.
	//

	// 1: [Coarse-turbulence module]: This turbulence module warps the output
	// value from the base-continent-definition subgroup, adding some coarse
	// detail to it.
	static private Double TU0_FREQUENCY = 15.25;
	// / The frequency of the turbulence determines how rapidly the
	// / displacement amount changes.
	static private Double TU0_POWER_SCALAR = 113.75;
	// / The power of the turbulence determines the scaling factor that is
	// / applied to the displacement amount.
	static private Integer TU0_ROUGHNESS = 13;
	// / The roughness of the turbulence determines the roughness of the
	// / changes to the displacement amount. Low values smoothly change
	// / the displacement amount. High values roughly change the
	// / displacement amount, which produces more "kinky" changes.
	// /
	// / Internally, there are three Perlin noise modules
	// / that displace the input value; one for the @a x, one for the @a y,
	// / and one for the @a z coordinate. The roughness value is equal to
	// / the number of octaves used by the noise::module::Perlin noise
	// / modules.
	// 2: [Intermediate-turbulence module]: This turbulence module warps the
	// output value from the coarse-turbulence module. This turbulence has
	// a higher frequency, but lower power, than the coarse-turbulence
	// module, adding some intermediate detail to it.

	static private Double TU1_FREQUENCY = 47.25;
	static private Double TU1_POWER_SCALAR = 433.75;
	static private Integer TU1_ROUGHNESS = 12;

	// 3: [Warped-base-continent-definition module]: This turbulence module
	// warps the output value from the intermediate-turbulence module. This
	// turbulence has a higher frequency, but lower power, than the
	// intermediate-turbulence module, adding some fine detail to it.

	static private Double TU2_FREQUENCY = 95.25;
	static private Double TU2_POWER_SCALAR = 1019.75;
	static private Integer TU2_ROUGHNESS = 11;

	// 4: [Select-turbulence module]: At this stage, the turbulence is applied
	// to the entire base-continent-definition subgroup, producing some very
	// rugged, unrealistic coastlines. This selector module selects the
	// output values from the (unwarped) base-continent-definition subgroup
	// and the warped-base-continent-definition module, based on the output
	// value from the (unwarped) base-continent-definition subgroup. The
	// selection boundary is near sea level and has a relatively smooth
	// transition. In effect, only the higher areas of the base-continent-
	// definition subgroup become warped; the underwater and coastal areas
	// remain unaffected.

	// / The control module (baseContinentDef) determines the output value to
	// select. If the
	// / output value from the control module is within a range of values
	// / known as the <i>selection range</i>, the getValue() method outputs
	// / the value from the source module with an index value of 1.
	// / Otherwise, this method outputs the value from the source module
	// / with an index value of 0.
	// /
	// / This method assigns the control module an index value of 2.
	// / Passing the control module to this method produces the same
	// / results as passing the control module to the setSourceModule()
	// / method while assigning that noise module an index value of 2.
	// /
	// / This control module must exist throughout the lifetime of this
	// / noise module unless another control module replaces that control
	// / module.

	private static Double CONTINENT_DEF_SE_LOWER_BOUNDS = SEA_LEVEL - 0.0375;
	private static Double CONTINENT_DEF_SE_UPPER_BOUNDS = SEA_LEVEL + 1000.0375;
	private static Double CONTINENT_DEF_SE_EDGE_FALLOFF = 0.0625;
	// / By default, there is an abrupt transition between the values from
	// / the two source modules at the boundaries of the selection range.
	// /
	// / For example, if the selection range is 0.5 to 0.8, and the edge
	// / falloff value is 0.1, then the getValue() method outputs:
	// / - the output value from the source module with an index value of 0
	// / if the output value from the control module is less than 0.4
	// / ( = 0.5 - 0.1).
	// / - a linear blend between the two output values from the two source
	// / modules if the output value from the control module is between
	// / 0.4 ( = 0.5 - 0.1) and 0.6 ( = 0.5 + 0.1).
	// / - the output value from the source module with an index value of 1
	// / if the output value from the control module is between 0.6
	// / ( = 0.5 + 0.1) and 0.7 ( = 0.8 - 0.1).
	// / - a linear blend between the output values from the two source
	// / modules if the output value from the control module is between
	// / 0.7 ( = 0.8 - 0.1 ) and 0.9 ( = 0.8 + 0.1).
	// / - the output value from the source module with an index value of 0
	// / if the output value from the control module is greater than 0.9
	// / ( = 0.8 + 0.1).

	// //////////////////////////////////////////////////////////////////////////
	// Module group: terrain type definition
	// //////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: terrain type definition (3 noise modules)
	//
	// This subgroup defines the positions of the terrain types on the planet.
	//
	// Terrain types include, in order of increasing roughness, plains, hills,
	// and mountains.
	//
	// This subgroup's output value is based on the output value from the
	// continent-definition group. Rougher terrain mainly appears at higher
	// elevations.
	//
	// -1.0 represents the smoothest terrain types (plains and underwater) and
	// +1.0 represents the roughest terrain types (mountains).
	//

	// 1: [Warped-continent module]: This turbulence module slightly warps the
	// output value from the continent-definition group. This prevents the
	// rougher terrain from appearing exclusively at higher elevations.
	// Rough areas may now appear in the the ocean, creating rocky islands
	// and fjords.

	static private Double TERRAIN_TYPE_TU_FREQUENCY = 18.125;
	static private Double TERRAIN_TYPE_TU_POWER = 20.59375;
	static private Integer TERRAIN_TYPE_TU_ROUGHNESS = 3;

	// 2: [Roughness-probability-shift module]: This terracing module sharpens
	// the edges of the warped-continent module near sea level and lowers
	// the slope towards the higher-elevation areas. This shrinks the areas
	// in which the rough terrain appears, increasing the "rarity" of rough
	// terrain.
	static private Double TERRAIN_TYPE_DEF_LOW_CONTROL_POINT = -1.00;
	static private Double TERRAIN_TYPE_DEF_MID_CONTROL_POINT_SCALAR = 2.00;
	static private Double TERRAIN_TYPE_DEF_HIGH_CONTROL_POINT = 1.00;

	// //////////////////////////////////////////////////////////////////////////
	// Module group: mountainous terrain
	// //////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: mountain base definition (9 noise modules)
	//
	// This subgroup generates the base-mountain elevations. Other subgroups
	// will add the ridges and low areas to the base elevations.
	//
	// -1.0 represents low mountainous terrain and +1.0 represents high
	// mountainous terrain.
	//

	// 1: [Mountain-ridge module]: This ridged-multifractal-noise module
	// generates the mountain ridges.
	static private Double MOUNTAIN_BASE_DEF_RM0_FREQUENCY = 1723.0;
	static private Integer MOUNTAIN_BASE_DEF_RM0_OCTAVE_COUNT = 4;

	// 2: [Scaled-mountain-ridge module]: Next, a scale/bias module scales the
	// output value from the mountain-ridge module so that its ridges are not
	// too high. The reason for this is that another subgroup adds actual
	// mountainous terrain to these ridges.
	static private Double MOUNTAIN_BASE_DEF_SB0_SCALE = 0.5;
	static private Double MOUNTAIN_BASE_DEF_SB0_BIAS = 0.375;

	// 3: [River-valley module]: This ridged-multifractal-noise module generates
	// the river valleys. It has a much lower frequency than the mountain-
	// ridge module so that more mountain ridges will appear outside of the
	// valleys. Note that this noise module generates ridged-multifractal
	// noise using only one octave; this information will be important in the
	// next step.
	static private Double MOUNTAIN_BASE_DEF_RM1_FREQUENCY = 367.0;
	static private Integer MOUNTAIN_BASE_DEF_RM1_OCTAVE_COUNT = 1;

	// 4: [Scaled-river-valley module]: Next, a scale/bias module applies a
	// scaling factor of -2.0 to the output value from the river-valley
	// module. This stretches the possible elevation values because one-
	// octave ridged-multifractal noise has a lower range of output values
	// than multiple-octave ridged-multifractal noise. The negative scaling
	// factor inverts the range of the output value, turning the ridges from
	// the river-valley module into valleys.
	static private Double MOUNTAIN_BASE_DEF_SB1_SCALE = -2.0;
	static private Double MOUNTAIN_BASE_DEF_SB1_BIAS = -0.5;

	// 7: [Coarse-turbulence module]: This turbulence module warps the output
	// value from the mountain-and-valleys module, adding some coarse detail
	// to it.
	static private Double MOUNTAIN_BASE_DEF_TU0_FREQUENCY = 1337.0;
	static private Double MOUNTAIN_BASE_DEF_TU0_POWER_SCALAR0 = 1.0;
	static private Double MOUNTAIN_BASE_DEF_TU0_POWER_SCALAR1 = 6730.0 * MOUNTAINS_TWIST;
	static private Integer MOUNTAIN_BASE_DEF_TU0_ROUGHNESS = 4;

	// 8: [Warped-mountains-and-valleys module]: This turbulence module warps
	// the output value from the coarse-turbulence module. This turbulence
	// has a higher frequency, but lower power, than the coarse-turbulence
	// module, adding some fine detail to it.
	static private Double MOUNTAIN_BASE_DEF_TU1_FREQUENCY = 21221.0;
	static private Double MOUNTAIN_BASE_DEF_TU1_POWER_SCALAR0 = 1.0;
	static private Double MOUNTAIN_BASE_DEF_TU1_POWER_SCALAR1 = 120157.0 * MOUNTAINS_TWIST;
	static private Integer MOUNTAIN_BASE_DEF_TU1_ROUGHNESS = 6;

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: high mountainous terrain (5 noise modules)
	//
	// This subgroup generates the mountainous terrain that appears at high
	// elevations within the mountain ridges.
	//
	// -1.0 represents the lowest elevations and +1.0 represents the highest
	// elevations.
	//

	// 1: [Mountain-basis-0 module]: This ridged-multifractal-noise module,
	// along with the mountain-basis-1 module, generates the individual
	// mountains.
	static private Double MOUNTAINOUS_HIGH_RM0_FREQUENCY = 2371.0;
	static private Double MOUNTAINOUS_HIGH_RM0_LACUNARITY = MOUNTAIN_LACUNARITY;
	static private Integer MOUNTAINOUS_HIGH_RM0_OCTAVE_COUNT = 3;
	static private RidgedMulti mountainousHigh_rm0 = new RidgedMulti();

	// 2: [Mountain-basis-1 module]: This ridged-multifractal-noise module,
	// along with the mountain-basis-0 module, generates the individual
	// mountains.
	static private Double MOUNTAINOUS_HIGH_RM1_FREQUENCY = 2341.0;
	static private Double MOUNTAINOUS_HIGH_RM1_LACUNARITY = MOUNTAIN_LACUNARITY;
	static private Integer MOUNTAINOUS_HIGH_RM1_OCTAVE_COUNT = 3;

	// 4: [Warped-high-mountains module]: This turbulence module warps the
	// output value from the high-mountains module, adding some detail to it.
	static private Double MOUNTAINOUS_HIGH_TU_FREQUENCY = 31511.0;
	static private Double MOUNTAINOUS_HIGH_TU_SCALAR1 = 1.0;
	static private Double MOUNTAINOUS_HIGH_TU_SCALAR2 = 180371.0;
	static private Double MOUNTAINOUS_HIGH_TU_POWER = MOUNTAINOUS_HIGH_TU_SCALAR1
			/ MOUNTAINOUS_HIGH_TU_SCALAR2 * MOUNTAINS_TWIST;
	static private Integer MOUNTAINOUS_HIGH_TU_ROUGHNESS = 4;

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: low mountainous terrain (4 noise modules)
	//
	// This subgroup generates the mountainous terrain that appears at low
	// elevations within the river valleys.
	//
	// -1.0 represents the lowest elevations and +1.0 represents the highest
	// elevations.
	//

	// 1: [Lowland-basis-0 module]: This ridged-multifractal-noise module,
	// along with the lowland-basis-1 module, produces the low mountainous
	// terrain.

	static private Double MOUNTAINOUS_LOW_RM0_FREQUENCY = 1381.0;
	static private Double MOUNTAINOUS_LOW_RM0_LACUNARITY = MOUNTAIN_LACUNARITY;
	static private Integer MOUNTAINOUS_LOW_RM0_OCTAVE_COUNT = 8;

	// 1: [Lowland-basis-1 module]: This ridged-multifractal-noise module,
	// along with the lowland-basis-0 module, produces the low mountainous
	// terrain.
	static private Double MOUNTAINOUS_LOW_RM1_FREQUENCY = 1427.0;
	static private Double MOUNTAINOUS_LOW_RM1_LACUNARITY = MOUNTAIN_LACUNARITY;
	static private Integer MOUNTAINOUS_LOW_RM1_OCTAVE_COUNT = 8;

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: mountainous terrain (7 noise modules)
	//
	// This subgroup generates the final mountainous terrain by combining the
	// high-mountainous-terrain subgroup with the low-mountainous-terrain
	// subgroup.
	//
	// -1.0 represents the lowest elevations and +1.0 represents the highest
	// elevations.
	//

	// 1: [Scaled-low-mountainous-terrain module]: First, this scale/bias module
	// scales the output value from the low-mountainous-terrain subgroup to a
	// very low value and biases it towards -1.0. This results in the low
	// mountainous areas becoming more-or-less flat with little variation.
	// This will also result in the low mountainous areas appearing at the
	// lowest elevations in this subgroup.
	static private Double MOUNTAINOUS_TERRAIN_SB0_SCALE = 0.03125;
	static private Double MOUNTAINOUS_TERRAIN_SB0_BIAS = -0.96875;

	// 2: [Scaled-high-mountainous-terrain module]: Next, this scale/bias module
	// scales the output value from the high-mountainous-terrain subgroup to
	// 1/4 of its initial value and biases it so that its output value is
	// usually positive.
	static private Double MOUNTAINOUS_TERRAIN_SB1_SCALE = 0.25;
	static private Double MOUNTAINOUS_TERRAIN_SB1_BIAS = 0.25;

	// 4: [Combined-mountainous-terrain module]: Note that at this point, the
	// entire terrain is covered in high mountainous terrain, even at the low
	// elevations. To make sure the mountains only appear at the higher
	// elevations, this selector module causes low mountainous terrain to
	// appear at the low elevations (within the valleys) and the high
	// mountainous terrain to appear at the high elevations (within the
	// ridges.) To do this, this noise module selects the output value from
	// the added-high-mountainous-terrain module if the output value from the
	// mountain-base-definition subgroup is higher than a set amount.
	// Otherwise, this noise module selects the output value from the scaled-
	// low-mountainous-terrain module.

	// / Sets the lower and upper bounds of the selection range.
	static private Double MOUNTAINOUS_TERRAIN_SE_BOUNDS_PARAM_0 = -0.5;
	static private Double MOUNTAINOUS_TERRAIN_SE_BOUNDS_PARAM_1 = 999.5;
	// / Sets the falloff value at the edge transition.
	static private Double MOUNTAINOUS_TERRAIN_SE_EDGE_FALLOFF = 0.5;

	// 5: [Scaled-mountainous-terrain-module]: This scale/bias module slightly
	// reduces the range of the output value from the combined-mountainous-
	// terrain module, decreasing the heights of the mountain peaks.
	static private Double MOUNTAINOUS_TERRAIN_SB2_SCALE = 0.8;
	static private Double MOUNTAINOUS_TERRAIN_SB2_BIAS = 0.0;

	private static Double HILLY_TERRAIN_BI_FREQUENCY = 1663.0;
	private static Double HILLY_TERRAIN_BI_PERSISTENCE = 0.5;
	private static Integer HILLY_TERRAIN_BI_OCTAVE_COUNT = 6;

	// 2: [Scaled-hills module]: Next, a scale/bias module scales the output
	// value from the hills module so that its hilltops are not too high.
	// The reason for this is that these hills are eventually added to the
	// river valleys (see below.)
	static private Double HILLY_TERRAIN_SB0_SCALE = 0.5;
	static private Double HILLY_TERRAIN_SB0_BIAS = 0.5;

	// 3: [River-valley module]: This ridged-multifractal-noise module generates
	// the river valleys. It has a much lower frequency so that more hills
	// will appear in between the valleys. Note that this noise module
	// generates ridged-multifractal noise using only one octave; this
	// information will be important in the next step.
	static private Double HILLY_TERRAIN_RM_FREQUENCY = 367.5;
	static private Integer HILLY_TERRAIN_RM_OCTAVE_COUNT = 1;

	// 4: [Scaled-river-valley module]: Next, a scale/bias module applies a
	// scaling factor of -2.0 to the output value from the river-valley
	// module. This stretches the possible elevation values because one-
	// octave ridged-multifractal noise has a lower range of output values
	// than multiple-octave ridged-multifractal noise. The negative scaling
	// factor inverts the range of the output value, turning the ridges from
	// the river-valley module into valleys.
	static private Double HILLY_TERRAIN_SB1_SCALE = -2.0;
	static private Double HILLY_TERRAIN_SB1_BIAS = -0.5;

	// 7: [Scaled-hills-and-valleys module]: This scale/bias module slightly
	// reduces the range of the output value from the hills-and-valleys
	// module, decreasing the heights of the hilltops.

	static private Double HILLY_TERRAIN_SB2_SCALE = 0.75;
	static private Double HILLY_TERRAIN_SB2_BIAS = -0.25;

	// 8: [Increased-slope-hilly-terrain module]: To increase the hill slopes at
	// higher elevations, this exponential-curve module applies an
	// exponential curve to the output value the scaled-hills-and-valleys
	// module. This exponential-curve module expects the input value to
	// range from -1.0 to 1.0.
	static private Double HILLY_TERRAIN_EX = 1.375;

	// 9: [Coarse-turbulence module]: This turbulence module warps the output
	// value from the increased-slope-hilly-terrain module, adding some
	// coarse detail to it.
	static private Double HILLY_TERRAIN_TU0_FREQUENCY = 1531.0;
	static private Double HILLY_TERRAIN_TU0_SCALAR0 = 1.0;
	static private Double HILLY_TERRAIN_TU0_SCALAR1 = 16921.0;
	static private Integer HILLY_TERRAIN_TU0_ROUGHNESS = 4;

	// 10: [Warped-hilly-terrain module]: This turbulence module warps the
	// output value from the coarse-turbulence module. This turbulence has
	// a higher frequency, but lower power, than the coarse-turbulence
	// module, adding some fine detail to it.

	static private Double HILLY_TERRAIN_TU1_FREQUENCY = 21617.0;
	static private Double HILLY_TERRAIN_TU1_SCALAR0 = 1.0;
	static private Double HILLY_TERRAIN_TU1_SCALAR1 = 117529.0;
	static private Integer HILLY_TERRAIN_TU1_ROUGHNESS = 6;

	// //////////////////////////////////////////////////////////////////////////
	// Module group: plains terrain
	// //////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: plains terrain (7 noise modules)
	//
	// This subgroup generates the plains terrain.
	//
	// Because this subgroup will eventually be flattened considerably, the
	// types and combinations of noise modules that generate the plains are not
	// really that important; they only need to "look" interesting.
	//
	// -1.0 represents the lowest elevations and +1.0 represents the highest
	// elevations.
	//

	// 1: [Plains-basis-0 module]: This billow-noise module, along with the
	// plains-basis-1 module, produces the plains.
	private static Double PLAINS_TERRAIN_BI0_FREQUENCY = 1097.0;
	private static Double PLAINS_TERRAIN_BI0_PERSISTENCE = 0.5;
	private static Integer PLAINS_TERRAIN_BI0_OCTAVE_COUNT = 8;

	// 2: [Positive-plains-basis-0 module]: This scale/bias module makes the
	// output value from the plains-basis-0 module positive since this output
	// value will be multiplied together with the positive-plains-basis-1
	// module.
	static private Double PLAINS_TERRAIN_SB0_SCALE = 0.5;
	static private Double PLAINS_TERRAIN_SB0_BIAS = 0.5;

	// 3: [Plains-basis-1 module]: This billow-noise module, along with the
	// plains-basis-2 module, produces the plains.

	private static Double PLAINS_TERRAIN_BI1_FREQUENCY = 1319.0;
	private static Double PLAINS_TERRAIN_BI1_PERSISTENCE = 0.5;
	private static Integer PLAINS_TERRAIN_BI1_OCTAVE_COUNT = 8;

	// 4: [Positive-plains-basis-1 module]: This scale/bias module makes the
	// output value from the plains-basis-1 module positive since this output
	// value will be multiplied together with the positive-plains-basis-0
	// module.
	static private Double PLAINS_TERRAIN_SB1_SCALE = 0.5;
	static private Double PLAINS_TERRAIN_SB1_BIAS = 0.5;

	// 6: [Rescaled-plains-basis module]: This scale/bias module maps the output
	// value that ranges from 0.0 to 1.0 back to a value that ranges from
	// -1.0 to +1.0.
	static private Double PLAINS_TERRAIN_SB2_SCALE = 2.0;
	static private Double PLAINS_TERRAIN_SB2_BIAS = -1.0;

	// //////////////////////////////////////////////////////////////////////////
	// Module group: badlands terrain
	// //////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: badlands sand (6 noise modules)
	//
	// This subgroup generates the sandy terrain for the badlands.
	//
	// -1.0 represents the lowest elevations and +1.0 represents the highest
	// elevations.
	//

	// 1: [Sand-dunes module]: This ridged-multifractal-noise module generates
	// sand dunes. This ridged-multifractal noise is generated with a single
	// octave, which makes very smooth dunes.
	static private Double BADLANDS_SAND_RM_FREQUENCY = 6163.5;
	static private Integer BADLANDS_SAND_RM_OCTAVE_COUNT = 1;

	// 2: [Scaled-sand-dunes module]: This scale/bias module shrinks the dune
	// heights by a small amount. This is necessary so that the subsequent
	// noise modules in this subgroup can add some detail to the dunes.

	static private Double BADLANDS_SAND_SB0_SCALE = 0.875;
	static private Double BADLANDS_SAND_SB0_BIAS = 0.0;

	// 3: [Dune-detail module]: This noise module uses Voronoi polygons to
	// generate the detail to add to the dunes. By enabling the distance
	// algorithm, small polygonal pits are generated; the edges of the pits
	// are joined to the edges of nearby pits.

	// / The frequency determines the size of the Voronoi cells and the
	// / distance between these cells.
	static private Double BADLANDS_SAND_VO_FREQUENCY = 16183.25;
	// / This noise module assigns each Voronoi cell with a random constant
	// / value from a coherent-noise function. The <i>displacement
	// / value</i> controls the range of random values to assign to each
	// / cell. The range of random values is +/- the displacement value.
	static private Double BADLANDS_SAND_VO_DISPLACEMWNT = 0.0;

	// 4: [Scaled-dune-detail module]: This scale/bias module shrinks the dune
	// details by a large amount. This is necessary so that the subsequent
	// noise modules in this subgroup can add this detail to the sand-dunes
	// module.
	static private Double BADLANDS_SAND_SB1_SCALE = 0.25;
	static private Double BADLANDS_SAND_SB1_BIAS = 0.25;

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: badlands cliffs (7 noise modules)
	//
	// This subgroup generates the cliffs for the badlands.
	//
	// -1.0 represents the lowest elevations and +1.0 represents the highest
	// elevations.
	//

	// 1: [Cliff-basis module]: This Perlin-noise module generates some coherent
	// noise that will be used to generate the cliffs.
	static private Double BADLANDS_CLIFFS_PE_FREQUENCY = CONTINENT_FREQUENCY * 839;
	static private Double BADLANDS_CLIFFS_PE_PERSISTENCE = 0.5;
	static private Integer BADLANDS_CLIFFS_PE_OCTAVE_COUNT = 6;

	// 5: [Coarse-turbulence module]: This turbulence module warps the output
	// value from the terraced-cliffs module, adding some coarse detail to
	// it.
	static private Double BADLANDS_CLIFFS_TU0_FREQUENCY = 16111.0;
	static private Double BADLANDS_CLIFFS_TU0_SCALAR0 = 1.0;
	static private Double BADLANDS_CLIFFS_TU0_SCALAR1 = 141539.0;
	static private Integer BADLANDS_CLIFFS_TU0_ROUGHNESS = 3;

	// 6: [Warped-cliffs module]: This turbulence module warps the output value
	// from the coarse-turbulence module. This turbulence has a higher
	// frequency, but lower power, than the coarse-turbulence module, adding
	// some fine detail to it.
	static private Double BADLANDS_CLIFFS_TU1_FREQUENCY = 36107.0;
	static private Double BADLANDS_CLIFFS_TU1_SCALAR0 = 1.0;
	static private Double BADLANDS_CLIFFS_TU1_SCALAR1 = 211543.0;
	static private Integer BADLANDS_CLIFFS_TU1_ROUGHNESS = 3;

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: badlands terrain (3 noise modules)
	//
	// Generates the final badlands terrain.
	//
	// Using a scale/bias module, the badlands sand is flattened considerably,
	// then the sand elevations are lowered to around -1.0. The maximum value
	// from the flattened sand module and the cliff module contributes to the
	// final elevation. This causes sand to appear at the low elevations since
	// the sand is slightly higher than the cliff base.
	//
	// -1.0 represents the lowest elevations and +1.0 represents the highest
	// elevations.
	//

	// 1: [Scaled-sand-dunes module]: This scale/bias module considerably
	// flattens the output value from the badlands-sands subgroup and lowers
	// this value to near -1.0.
	static private Double BADLANDS_TERRAIN_SB_SCALE = 0.25;
	static private Double BADLANDS_TERRAIN_SB_BIAS = -0.75;

	// //////////////////////////////////////////////////////////////////////////
	// Module group: river positions
	// //////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: river positions (7 noise modules)
	//
	// This subgroup generates the river positions.
	//
	// -1.0 represents the lowest elevations and +1.0 represents the highest
	// elevations.
	//

	// 1: [Large-river-basis module]: This ridged-multifractal-noise module
	// creates the large, deep rivers.
	static private Double RIVER_POSITIONS_RM0_FREQUENCY = 18.75;
	static private Integer RIVER_POSITIONS_RM0_OCTAVE_COUNT = 1;

	// / 3: [Small-river-basis module]: This ridged-multifractal-noise module
	// creates the small, shallow rivers.
	static private Double RIVER_POSITIONS_RM1_FREQUENCY = 43.25;
	static private Integer RIVER_POSITIONS_RM1_OCTAVE_COUNT = 1;

	// 6: [Warped-rivers module]: This turbulence module warps the output value
	// from the combined-rivers module, which twists the rivers. The high
	// roughness produces less-smooth rivers.
	static private Double RIVER_POSITIONS_TU_FREQUENCY = 9.25;
	static private Double RIVER_POSITIONS_TU_SCALAR0 = 1.0;
	static private Double RIVER_POSITIONS_TU_SCALAR1 = 57.75;
	static private Integer RIVER_POSITIONS_TU_ROUGHNESS = 6;

	// //////////////////////////////////////////////////////////////////////////
	// Module group: scaled mountainous terrain
	// //////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: scaled mountainous terrain (6 noise modules)
	//
	// This subgroup scales the output value from the mountainous-terrain group
	// so that it can be added to the elevation defined by the continent-
	// definition group.
	//
	// This subgroup scales the output value such that it is almost always
	// positive. This is done so that a negative elevation does not get applied
	// to the continent-definition group, preventing parts of that group from
	// having negative terrain features "stamped" into it.
	//
	// The output value from this module subgroup is measured in planetary
	// elevation units (-1.0 for the lowest underwater trenches and +1.0 for the
	// highest mountain peaks.)
	//

	// 1: [Base-scaled-mountainous-terrain module]: This scale/bias module
	// scales the output value from the mountainous-terrain group so that the
	// output value is measured in planetary elevation units.
	static private Double SCALED_MOUNTAINOUS_TERRAIN_SB0_SCALE = 0.125;
	static private Double SCALED_MOUNTAINOUS_TERRAIN_SB0_BIAS = 0.125;

	// 2: [Base-peak-modulation module]: At this stage, most mountain peaks have
	// roughly the same elevation. This Perlin-noise module generates some
	// random values that will be used by subsequent noise modules to
	// randomly change the elevations of the mountain peaks.
	static private Double SCALED_MOUNTAINOUS_TERRAIN_PE_FREQUENCY = 14.5;
	static private Double SCALED_MOUNTAINOUS_TERRAIN_PE_PERSISTENCE = 0.5;
	static private Integer SCALED_MOUNTAINOUS_TERRAIN_PE_OCTAVE_COUNT = 6;

	// 3: [Peak-modulation module]: This exponential-curve module applies an
	// exponential curve to the output value from the base-peak-modulation
	// module. This produces a small number of high values and a much larger
	// number of low values. This means there will be a few peaks with much
	// higher elevations than the majority of the peaks, making the terrain
	// features more varied.
	static private Double SCALED_MOUNTAINOUS_TERRAIN_EX_EXPONENT = 1.25;

	// 4: [Scaled-peak-modulation module]: This scale/bias module modifies the
	// range of the output value from the peak-modulation module so that it
	// can be used as the modulator for the peak-height-multiplier module.
	// It is important that this output value is not much lower than 1.0.
	static private Double SCALED_MOUNTAINOUS_TERRAIN_SB1_SCALE = 0.25;
	static private Double SCALED_MOUNTAINOUS_TERRAIN_SB1_BIAS = 1.0;

	// //////////////////////////////////////////////////////////////////////////
	// Module group: scaled hilly terrain
	// //////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: scaled hilly terrain (6 noise modules)
	//
	// This subgroup scales the output value from the hilly-terrain group so
	// that it can be added to the elevation defined by the continent-
	// definition group. The scaling amount applied to the hills is one half of
	// the scaling amount applied to the scaled-mountainous-terrain group.
	//
	// This subgroup scales the output value such that it is almost always
	// positive. This is done so that negative elevations are not applied to
	// the continent-definition group, preventing parts of the continent-
	// definition group from having negative terrain features "stamped" into it.
	//
	// The output value from this module subgroup is measured in planetary
	// elevation units (-1.0 for the lowest underwater trenches and +1.0 for the
	// highest mountain peaks.)
	//

	// 1: [Base-scaled-hilly-terrain module]: This scale/bias module scales the
	// output value from the hilly-terrain group so that this output value is
	// measured in planetary elevation units
	static private Double SCALED_HILLY_TERRAIN_SB0_SCALE = 0.0625;
	static private Double SCALED_HILLY_TERRAIN_SB0_BIAS = 0.0625;

	// 2: [Base-hilltop-modulation module]: At this stage, most hilltops have
	// roughly the same elevation. This Perlin-noise module generates some
	// random values that will be used by subsequent noise modules to
	// randomly change the elevations of the hilltops.
	static private Double SCALED_HILLY_TERRAIN_PE_FREQUENCY = 13.5;
	static private Double SCALED_HILLY_TERRAIN_PE_PERSISTENCE = 0.5;
	static private Integer SCALED_HILLY_TERRAIN_PE_OCTAVE_COUNT = 6;

	// 3: [Hilltop-modulation module]: This exponential-curve module applies an
	// exponential curve to the output value from the base-hilltop-modulation
	// module. This produces a small number of high values and a much larger
	// number of low values. This means there will be a few hilltops with
	// much higher elevations than the majority of the hilltops, making the
	// terrain features more varied.
	static private Double SCALED_HILLY_TERRAIN_EX_EXPONENT = 1.25;

	// 4: [Scaled-hilltop-modulation module]: This scale/bias module modifies
	// the range of the output value from the hilltop-modulation module so
	// that it can be used as the modulator for the hilltop-height-multiplier
	// module. It is important that this output value is not much lower than
	// 1.0.
	static private Double SCALED_HILLY_TERRAIN_SB1_SCALE = 0.5;
	static private Double SCALED_HILLY_TERRAIN_SB1_BIAS = 1.5;

	// //////////////////////////////////////////////////////////////////////////
	// Module group: scaled plains terrain
	// //////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: scaled plains terrain (2 noise modules)
	//
	// This subgroup scales the output value from the plains-terrain group so
	// that it can be added to the elevations defined by the continent-
	// definition group.
	//
	// This subgroup scales the output value such that it is almost always
	// positive. This is done so that negative elevations are not applied to
	// the continent-definition group, preventing parts of the continent-
	// definition group from having negative terrain features "stamped" into it.
	//
	// The output value from this module subgroup is measured in planetary
	// elevation units (-1.0 for the lowest underwater trenches and +1.0 for the
	// highest mountain peaks.)
	//

	// 1: [Scaled-plains-terrain module]: This scale/bias module greatly
	// flattens the output value from the plains terrain. This output value
	// is measured in planetary elevation units
	static private Double SCALED_PLAINS_TERRAIN_SB_SCALE = 0.00390625;
	static private Double SCALED_PLAINS_TERRAIN_SB_BIAS = 0.0078125;

	// //////////////////////////////////////////////////////////////////////////
	// Module group: scaled badlands terrain
	// //////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: scaled badlands terrain (2 noise modules)
	//
	// This subgroup scales the output value from the badlands-terrain group so
	// that it can be added to the elevations defined by the continent-
	// definition group.
	//
	// This subgroup scales the output value such that it is almost always
	// positive. This is done so that negative elevations are not applied to
	// the continent-definition group, preventing parts of the continent-
	// definition group from having negative terrain features "stamped" into it.
	//
	// The output value from this module subgroup is measured in planetary
	// elevation units (-1.0 for the lowest underwater trenches and +1.0 for the
	// highest mountain peaks.)
	//

	// 1: [Scaled-badlands-terrain module]: This scale/bias module scales the
	// output value from the badlands-terrain group so that it is measured
	// in planetary elevation units
	static private Double SCALED_BADLANDS_TERRAIN_SCALE = 0.0625;
	static private Double SCALED_BADLANDS_TERRAIN_BIAS = 0.0625;

	// //////////////////////////////////////////////////////////////////////////
	// Module group: final planet
	// //////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: continental shelf (6 noise modules)
	//
	// This module subgroup creates the continental shelves.
	//
	// The output value from this module subgroup are measured in planetary
	// elevation units (-1.0 for the lowest underwater trenches and +1.0 for the
	// highest mountain peaks.)
	//

	// 1: [Shelf-creator module]: This terracing module applies a terracing
	// curve to the continent-definition group at the specified shelf level.
	// This terrace becomes the continental shelf. Note that this terracing
	// module also places another terrace below the continental shelf near
	// -1.0. The bottom of this terrace is defined as the bottom of the
	// ocean; subsequent noise modules will later add oceanic trenches to the
	// bottom of the ocean.
	static private Double CONTINENTAL_SHELF_TE_LOWEST_CONTROL_POINT = -1.0;
	static private Double CONTINENTAL_SHELF_TE_LOW_CONTROL_POINT = -0.75;
	static private Double CONTINENTAL_SHELF_TE_HIGH_CONTROL_POINT = 1.0;

	// 2: [Oceanic-trench-basis module]: This ridged-multifractal-noise module
	// generates some coherent noise that will be used to generate the
	// oceanic trenches. The ridges represent the bottom of the trenches.
	static private Double CONTINENTAL_SHELF_FREQUENCY_SCALAR = 4.375;
	static private Integer CONTINENTAL_SHELF_FREQUENCY_OCTAVE_COUNT = 16;

	// 3: [Oceanic-trench module]: This scale/bias module inverts the ridges
	// from the oceanic-trench-basis-module so that the ridges become
	// trenches. This noise module also reduces the depth of the trenches so
	// that their depths are measured in planetary elevation units.
	static private Double CONTINENTAL_SHELF_SB_SCALE = -0.125;
	static private Double CONTINENTAL_SHELF_SB_BIAS = -0.125;

	// 4: [Clamped-sea-bottom module]: This clamping module clamps the output
	// value from the shelf-creator module so that its possible range is
	// from the bottom of the ocean to sea level. This is done because this
	// subgroup is only concerned about the oceans.
	static private Double CONTINENTAL_SHELF_CL_BOUNDS = -0.75;

	// //////////////////////////////////////////////////////////////////////////
	// Module group: base continent elevations (3 noise modules)
	//
	// This subgroup generates the base elevations for the continents, before
	// terrain features are added.
	//
	// The output value from this module subgroup is measured in planetary
	// elevation units (-1.0 for the lowest underwater trenches and +1.0 for the
	// highest mountain peaks.)
	//

	// 1: [Base-scaled-continent-elevations module]: This scale/bias module
	// scales the output value from the continent-definition group so that it
	// is measured in planetary elevation units
	static private Double BASE_CONTINENT_ELEV_SB_BIAS = 0.0;

	// 2: [Base-continent-with-oceans module]: This selector module applies the
	// elevations of the continental shelves to the base elevations of the
	// continent. It does this by selecting the output value from the
	// continental-shelf subgroup if the corresponding output value from the
	// continent-definition group is below the shelf level. Otherwise, it
	// selects the output value from the base-scaled-continent-elevations
	// module.
	static private Double BASE_CONTINENT_ELEV_SE_BOUND_SCALAR = 1000.0;
	static private Double BASE_CONTINENT_ELEV_SE_EDGE_FALLOFF = 0.03125;

	// 2: [Select-high-elevations module]: This selector module ensures that
	// the hills only appear at higher elevations. It does this by selecting
	// the output value from the continent-with-hills module if the
	// corresponding output value from the terrain-type-defintion group is
	// above a certain value. Otherwise, it selects the output value from the
	// continents-with-plains subgroup.
	static private Double CONTINENTS_WITH_HILLS_BOUNDS_SCALAR0 = 1.0;
	static private Double CONTINENTS_WITH_HILLS_BOUNDS_SCALAR1 = 1001.0;
	static private Double CONTINENTS_WITH_HILLS_EDGE_FALLOFF = 0.25;

	// 4: [Select-high-elevations module]: This selector module ensures that
	// mountains only appear at higher elevations. It does this by selecting
	// the output value from the continent-with-mountains module if the
	// corresponding output value from the terrain-type-defintion group is
	// above a certain value. Otherwise, it selects the output value from
	// the continents-with-hills subgroup. Note that the continents-with-
	// hills subgroup also contains the plains terrain.
	static private Double CONTINENT_WITH_MOUNTAINS_BOUNDS_SCALAR0 = 1.0;
	static private Double CONTINENT_WITH_MOUNTAINS_BOUNDS_SCALAR1 = 1001.0;
	static private Double CONTINENT_WITH_MOUNTAINS_EDGE_FALLOFF = 0.25;

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: continents with badlands (5 noise modules)
	//
	// This subgroup applies the scaled-badlands-terrain group to the
	// continents-with-mountains subgroup.
	//
	// The output value from this module subgroup is measured in planetary
	// elevation units (-1.0 for the lowest underwater trenches and +1.0 for the
	// highest mountain peaks.)
	//

	// 1: [Badlands-positions module]: This Perlin-noise module generates some
	// random noise, which is used by subsequent noise modules to specify the
	// locations of the badlands.
	static private Double CONTINENTS_WITH_BADLANDS_PE_FREQUENCY = 16.5;
	static private Double CONTINENTS_WITH_BADLANDS_PE_PERSISTENCE = 0.5;
	static private Integer CONTINENTS_WITH_BADLANDS_PE_OCTAVE_COUNT = 2;

	// 3: [Select-badlands-positions module]: This selector module places
	// badlands at random spots on the continents based on the Perlin noise
	// generated by the badlands-positions module. To do this, it selects
	// the output value from the continents-and-badlands module if the
	// corresponding output value from the badlands-position module is
	// greater than a specified value. Otherwise, this selector module
	// selects the output value from the continents-with-mountains subgroup.
	// There is also a wide transition between these two noise modules so
	// that the badlands can blend into the rest of the terrain on the
	// continents.
	static private Double CONTINENTS_WITH_BADLANDS_SE_BOUNDS_PARAM0 = 1.0;
	static private Double CONTINENTS_WITH_BADLANDS_SE_BOUNDS_PARAM1 = 1001.0;
	static private Double CONTINENTS_WITH_BADLANDS_SE_EDGE_FALLOFF = 0.25;

	// //////////////////////////////////////////////////////////////////////////
	// Module subgroup: continents with rivers (4 noise modules)
	//
	// This subgroup applies the river-positions group to the continents-with-
	// badlands subgroup.
	//
	// The output value from this module subgroup is measured in planetary
	// elevation units (-1.0 for the lowest underwater trenches and +1.0 for the
	// highest mountain peaks.)
	//

	// 1: [Scaled-rivers module]: This scale/bias module scales the output value
	// from the river-positions group so that it is measured in planetary
	// elevation units and is negative; this is required for step 2.
	static private Double CONTINENTS_WITH_RIVERS_SB_SCALAR0 = 2.0;
	static private Double CONTINENTS_WITH_RIVERS_SB_SCALAR1 = 2.0;

	
}
