package components;
import libnoiseforjava.domain.ScaleBiasBuilder;
import libnoiseforjava.module.Cached;
import libnoiseforjava.module.Max;
import libnoiseforjava.module.ModuleBase;
import libnoiseforjava.module.ScaleBias;

public class PlanarBadlandsDunesType implements CachedIF {
	
	Double badlands_terrain_sb_scale;
	Double badlands_terrain_sb_bias;
	Cached badlands_sand;
	Cached badland_cliffs;

	

	public PlanarBadlandsDunesType(Double badlands_terrain_sb_scale, Double badlands_terrain_sb_bias,
			Cached badlands_sand) {
		super();
		this.badlands_terrain_sb_scale = badlands_terrain_sb_scale;
		this.badlands_terrain_sb_bias = badlands_terrain_sb_bias;
		this.badlands_sand = badlands_sand;
	}



	@Override
	public Cached build() {
		ScaleBias scaleBias = new ScaleBiasBuilder().build(badlands_terrain_sb_scale, badlands_terrain_sb_bias, badlands_sand);
		Max badlandsTerrain_ma = new Max(badland_cliffs, scaleBias);
		this.badland_cliffs = new Cached(badlandsTerrain_ma);
		return this.badland_cliffs;
	}



	public Double getBadlands_terrain_sb_scale() {
		return badlands_terrain_sb_scale;
	}



	public void setBadlands_terrain_sb_scale(Double badlands_terrain_sb_scale) {
		this.badlands_terrain_sb_scale = badlands_terrain_sb_scale;
	}



	public Double getBadlands_terrain_sb_bias() {
		return badlands_terrain_sb_bias;
	}



	public void setBadlands_terrain_sb_bias(Double badlands_terrain_sb_bias) {
		this.badlands_terrain_sb_bias = badlands_terrain_sb_bias;
	}



	@Override
	public String toString() {
		return "PlanarBadlandsDunes [badlands_terrain_sb_scale=" + badlands_terrain_sb_scale
				+ ", badlands_terrain_sb_bias=" + badlands_terrain_sb_bias + "]";
	}

}
