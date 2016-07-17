package components;

import java.util.ArrayList;
import java.util.List;

import libnoiseforjava.NoiseGen.NoiseQuality;
import libnoiseforjava.domain.ControlPoint;
import libnoiseforjava.module.Cached;

public class PlanarBadlandCliffType implements CachedIF {
	
	Double badlands_lucanarity;
	Double badlands_twist;
	
	Double badlands_cliffs_pe_frequency;
	Double continent_frequency;
	Double badlands_cliffs_pe_persistence;
	Integer badlands_cliffs_pe_octave_count;
	
	Double badlands_cliffs_cl_lower_bound;
	Double badlands_cliffs_cl_upper_bound;
	
	Double badlands_cliffs_tu0_frequency;
	Double badlands_cliffs_tu0_scalar0;
	Double badlands_cliffs_tu0_scalar1;
	Integer badlands_cliffs_tu0_roughness;

	Double badlands_cliffs_tu1_frequency;
	Double badlands_cliffs_tu1_scalar0;
	Double badlands_cliffs_tu1_scalar1;
	Integer badlands_cliffs_tu1_roughness;
	

	
	List<ControlPoint> badlands_cliffs_control_points;
	List<ControlPoint> badlands_terrace_control_points;
	NoiseQuality noiseQuality;
	
	Cached badlandCliffs;

	@Override
	public Cached build() {
		// TODO Auto-generated method stub
		return null;
	}

}
