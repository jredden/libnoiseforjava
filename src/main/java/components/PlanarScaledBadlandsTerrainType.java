package components;

import libnoiseforjava.domain.ScaleBiasBuilder;
import libnoiseforjava.module.Cached;
import libnoiseforjava.module.ScaleBias;

public class PlanarScaledBadlandsTerrainType implements CachedIF {
	
	Cached badlandsTerrain;
	Double scaled_badlands_terrain_scale;
	Double scaled_badlands_terrain_bias;
	Cached scaled_badlands_terrain;
	
	

	public PlanarScaledBadlandsTerrainType(Cached badlandsTerrain, Double scaled_badlands_terrain_scale,
			Double scaled_badlands_terrain_bias) {
		super();
		this.badlandsTerrain = badlandsTerrain;
		this.scaled_badlands_terrain_scale = scaled_badlands_terrain_scale;
		this.scaled_badlands_terrain_bias = scaled_badlands_terrain_bias;
	}

	@Override
	public Cached build() {
		ScaleBias scaleBias = new ScaleBiasBuilder().build(scaled_badlands_terrain_scale, scaled_badlands_terrain_scale, badlandsTerrain);
		this.scaled_badlands_terrain = new Cached(scaleBias);
		return this.scaled_badlands_terrain;
	}

	public Cached getBadlandsTerrain() {
		return badlandsTerrain;
	}

	public void setBadlandsTerrain(Cached badlandsTerrain) {
		this.badlandsTerrain = badlandsTerrain;
	}

	public Double getScaled_badlands_terrain_bias() {
		return scaled_badlands_terrain_bias;
	}

	public void setScaled_badlands_terrain_bias(Double scaled_badlands_terrain_bias) {
		this.scaled_badlands_terrain_bias = scaled_badlands_terrain_bias;
	}

	public Cached getScaled_badlands_terrain() {
		return scaled_badlands_terrain;
	}

	public void setScaled_badlands_terrain(Cached scaled_badlands_terrain) {
		this.scaled_badlands_terrain = scaled_badlands_terrain;
	}

	@Override
	public String toString() {
		return "PlanarScaledBadlandsTerrain [scaled_badlands_terrain_scale=" + scaled_badlands_terrain_scale
				+ ", scaled_badlands_terrain_bias=" + scaled_badlands_terrain_bias + "]";
	}

	
}
