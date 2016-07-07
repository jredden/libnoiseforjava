package components;

import java.util.List;

import com.zenred.util.GenRandomRolls;

import libnoiseforjava.NoiseGen.NoiseQuality;
import libnoiseforjava.domain.ControlPoint;
import libnoiseforjava.domain.CurveBuilder;
import libnoiseforjava.domain.PerlinBuilder;
import libnoiseforjava.module.Cached;
import libnoiseforjava.module.Curve;
import libnoiseforjava.module.Perlin;

public class PlanarBaseContinent implements CachedIF {

	private Double continent_frequency;
	private Double base_continent_persistence_0;
	private Double base_continent_persistence_1;
	private Double continent_lacunarity;
	private Integer base_continent_octive_count0;
	private Integer base_continent_octive_count1;
	private List<ControlPoint> controlPoints;
	private Integer spheres_scalar;
	private Boolean genSpheres = Boolean.FALSE;
	private NoiseQuality noiseQuality;
	private Double p_level = new Double(0);
	private Cached cache;

	// no arg CTOR
	public PlanarBaseContinent() {
	}

	/**
	 * 
	 * @param continent_frequency
	 * @param base_continent_persistence_0
	 * @param continent_lacunarity
	 * @param base_continent_octive_count0
	 * @param controlPoints
	 * @param base_continent_persistence_1
	 * @param base_continent_octive_count1
	 */
	public PlanarBaseContinent(Double continent_frequency, Double base_continent_persistence_0,
			Double continent_lacunarity, Integer base_continent_octive_count0, List<ControlPoint> controlPoints,
			Double base_continent_persistence_1, Integer base_continent_octive_count1, NoiseQuality noiseQuality) {
		super();
		this.continent_frequency = continent_frequency;
		this.base_continent_persistence_0 = base_continent_persistence_0;
		this.continent_lacunarity = continent_lacunarity;
		this.base_continent_octive_count0 = base_continent_octive_count0;
		this.controlPoints = controlPoints;
		this.base_continent_persistence_1 = base_continent_persistence_1;
		this.base_continent_octive_count0 = base_continent_octive_count1;
		this.noiseQuality = noiseQuality;

	}

	public void setContinent_frequency(Double continent_frequency) {
		this.continent_frequency = continent_frequency;
	}

	public void setBase_continent_persistence_0(Double base_continent_persistence_0) {
		this.base_continent_persistence_0 = base_continent_persistence_0;
	}

	public void setBase_continent_persistence_1(Double base_continent_persistence_1) {
		this.base_continent_persistence_1 = base_continent_persistence_1;
	}

	public void setContinent_lacunarity(Double continent_lacunarity) {
		this.continent_lacunarity = continent_lacunarity;
	}

	public void setBase_continent_octive_count0(Integer base_continent_octive_count0) {
		this.base_continent_octive_count0 = base_continent_octive_count0;
	}

	public void setControlPoints(List<ControlPoint> controlPoints) {
		this.controlPoints = controlPoints;
	}

	public void setSpheres_scalar(Integer spheres_scalar) {
		this.spheres_scalar = spheres_scalar;
	}

	public void setGenSpheres(Boolean genSpheres) {
		this.genSpheres = genSpheres;
	}

	public Double getContinent_frequency() {
		return continent_frequency;
	}

	public Double getBase_continent_persistence_0() {
		return base_continent_persistence_0;
	}

	public Double getContinent_lacunarity() {
		return continent_lacunarity;
	}

	public Integer getBase_continent_octive_count0() {
		return base_continent_octive_count0;
	}

	public List<ControlPoint> getControlPoints() {
		return controlPoints;
	}

	public Integer getSpheres_scalar() {
		return spheres_scalar;
	}

	public Boolean getGenSpheres() {
		return genSpheres;
	}

	public Cached getCache() {
		return cache;
	}

	public Integer getBase_continent_octive_count1() {
		return base_continent_octive_count1;
	}

	public void setBase_continent_octive_count1(Integer base_continent_octive_count1) {
		this.base_continent_octive_count1 = base_continent_octive_count1;
	}

	public NoiseQuality getNoiseQuality() {
		return noiseQuality;
	}

	public void setNoiseQuality(NoiseQuality noiseQuality) {
		this.noiseQuality = noiseQuality;
	}

	public Double getBase_continent_persistence_1() {
		return base_continent_persistence_1;
	}
	
	public Double getP_Level() {
		return p_level;
	}

	public void setP_Level(Double p_Level) {
		p_level = p_Level;
	}

	public Cached build() {
		Integer currSeed = GenRandomRolls.Instance().getD1000();
		Perlin baseContinentDef_pe0 = new PerlinBuilder().biuld(currSeed, continent_frequency,
				base_continent_persistence_0, continent_lacunarity, base_continent_octive_count0, noiseQuality);
		Curve baseContinentDef_cu = new CurveBuilder().builder(baseContinentDef_pe0, p_level, controlPoints);
		Perlin baseContinentDef_pe1 = new PerlinBuilder().biuld(currSeed, continent_frequency,
				base_continent_persistence_1, continent_lacunarity, base_continent_octive_count1, noiseQuality);
		
		return null;
	}

	@Override
	public String toString() {
		return "PlanarBaseContinent [continent_frequency=" + continent_frequency + ", base_continent_persistence_0="
				+ base_continent_persistence_0 + ", base_continent_persistence_1=" + base_continent_persistence_1
				+ ", continent_lacunarity=" + continent_lacunarity + ", base_continent_octive_count0="
				+ base_continent_octive_count0 + ", base_continent_octive_count1=" + base_continent_octive_count1
				+ ", controlPoints=" + controlPoints + ", spheres_scalar=" + spheres_scalar + ", genSpheres="
				+ genSpheres + ", noiseQuality=" + noiseQuality + ",P_Level=" + p_level + "]";
	}

}
