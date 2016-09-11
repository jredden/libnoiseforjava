package celestia.domain;

import java.util.HashMap;
import java.util.Map;

import com.zenred.cosmos.domain.Planetoid;
import com.zenred.cosmos.domain.Star;

public class PlanarExtension {
	
	private static String GRAPHIC_SUFFIX = ".png";
	
	public enum PlanarClass {PLANET("planet"){}, MOON("moon"){};
		
		private String type;
		
		PlanarClass(String type){
			this.setType(type);
		}
		
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	};
	
	/*
	 * Open GL colors ... take HTML colors and divide by 255
	 */
	public class OGL_Color{
		Float rOfRGB;
		Float gOfRGB;
		Float bOfRGB;
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((bOfRGB == null) ? 0 : bOfRGB.hashCode());
			result = prime * result + ((gOfRGB == null) ? 0 : gOfRGB.hashCode());
			result = prime * result + ((rOfRGB == null) ? 0 : rOfRGB.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			OGL_Color other = (OGL_Color) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (bOfRGB == null) {
				if (other.bOfRGB != null)
					return false;
			} else if (!bOfRGB.equals(other.bOfRGB))
				return false;
			if (gOfRGB == null) {
				if (other.gOfRGB != null)
					return false;
			} else if (!gOfRGB.equals(other.gOfRGB))
				return false;
			if (rOfRGB == null) {
				if (other.rOfRGB != null)
					return false;
			} else if (!rOfRGB.equals(other.rOfRGB))
				return false;
			return true;
		}
		private PlanarExtension getOuterType() {
			return PlanarExtension.this;
		}
	}
	
	private PlanarClass planarClass;
	private String texture;
	private String nightTexture;
	private OGL_Color color;
	private String specularTexture;
	private Integer specularPower;
	private OGL_Color hazeColor;
	private Float hazeDensity;
	private Double radius;
	private Double oblateness;
	private Integer atmosphereHeight;
	private OGL_Color lower;
	private OGL_Color upper;
	private OGL_Color sky;
	private Integer cloudHeight;
	private String cloudMap;
	private Integer cloudSpeed;
	private Double period;
	private Double semiMajorAxis;
	private Double eccentricity;
	private Double longOfPericenter;
	private Double meanLongitude;
	private Double rotationPeriod;
	private Double obliquity;
	private Double albedo;
	
	public static PlanarExtension build(Star star, Planetoid planetoid, PlanarClass planarClass){
		PlanarExtension instance = new PlanarExtension();
		instance.planarClass = planarClass;
		instance.texture = planetoid.getPlanetoidName()+GRAPHIC_SUFFIX;
		return instance;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public String getNightTexture() {
		return nightTexture;
	}

	public void setNightTexture(String nightTexture) {
		this.nightTexture = nightTexture;
	}

	public OGL_Color getColor() {
		return color;
	}

	public void setColor(OGL_Color color) {
		this.color = color;
	}

	public String getSpecularTexture() {
		return specularTexture;
	}

	public void setSpecularTexture(String specularTexture) {
		this.specularTexture = specularTexture;
	}

	public Integer getSpecularPower() {
		return specularPower;
	}

	public void setSpecularPower(Integer specularPower) {
		this.specularPower = specularPower;
	}

	public OGL_Color getHazeColor() {
		return hazeColor;
	}

	public void setHazeColor(OGL_Color hazeColor) {
		this.hazeColor = hazeColor;
	}

	public Float getHazeDensity() {
		return hazeDensity;
	}

	public void setHazeDensity(Float hazeDensity) {
		this.hazeDensity = hazeDensity;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public Double getOblateness() {
		return oblateness;
	}

	public void setOblateness(Double oblateness) {
		this.oblateness = oblateness;
	}

	public Integer getAtmosphereHeight() {
		return atmosphereHeight;
	}

	public void setAtmosphereHeight(Integer atmosphereHeight) {
		this.atmosphereHeight = atmosphereHeight;
	}

	public OGL_Color getLower() {
		return lower;
	}

	public void setLower(OGL_Color lower) {
		this.lower = lower;
	}

	public OGL_Color getUpper() {
		return upper;
	}

	public void setUpper(OGL_Color upper) {
		this.upper = upper;
	}

	public OGL_Color getSky() {
		return sky;
	}

	public void setSky(OGL_Color sky) {
		this.sky = sky;
	}

	public Integer getCloudHeight() {
		return cloudHeight;
	}

	public void setCloudHeight(Integer cloudHeight) {
		this.cloudHeight = cloudHeight;
	}

	public String getCloudMap() {
		return cloudMap;
	}

	public void setCloudMap(String cloudMap) {
		this.cloudMap = cloudMap;
	}

	public Integer getCloudSpeed() {
		return cloudSpeed;
	}

	public void setCloudSpeed(Integer cloudSpeed) {
		this.cloudSpeed = cloudSpeed;
	}

	public Double getPeriod() {
		return period;
	}

	public void setPeriod(Double period) {
		this.period = period;
	}

	public Double getSemiMajorAxis() {
		return semiMajorAxis;
	}

	public void setSemiMajorAxis(Double semiMajorAxis) {
		this.semiMajorAxis = semiMajorAxis;
	}

	public Double getEccentricity() {
		return eccentricity;
	}

	public void setEccentricity(Double eccentricity) {
		this.eccentricity = eccentricity;
	}

	public Double getLongOfPericenter() {
		return longOfPericenter;
	}

	public void setLongOfPericenter(Double longOfPericenter) {
		this.longOfPericenter = longOfPericenter;
	}

	public Double getMeanLongitude() {
		return meanLongitude;
	}

	public void setMeanLongitude(Double meanLongitude) {
		this.meanLongitude = meanLongitude;
	}

	public Double getRotationPeriod() {
		return rotationPeriod;
	}

	public void setRotationPeriod(Double rotationPeriod) {
		this.rotationPeriod = rotationPeriod;
	}

	public Double getObliquity() {
		return obliquity;
	}

	public void setObliquity(Double obliquity) {
		this.obliquity = obliquity;
	}

	public Double getAlbedo() {
		return albedo;
	}

	public void setAlbedo(Double albedo) {
		this.albedo = albedo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albedo == null) ? 0 : albedo.hashCode());
		result = prime * result + ((atmosphereHeight == null) ? 0 : atmosphereHeight.hashCode());
		result = prime * result + ((cloudHeight == null) ? 0 : cloudHeight.hashCode());
		result = prime * result + ((cloudMap == null) ? 0 : cloudMap.hashCode());
		result = prime * result + ((cloudSpeed == null) ? 0 : cloudSpeed.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((eccentricity == null) ? 0 : eccentricity.hashCode());
		result = prime * result + ((hazeColor == null) ? 0 : hazeColor.hashCode());
		result = prime * result + ((hazeDensity == null) ? 0 : hazeDensity.hashCode());
		result = prime * result + ((longOfPericenter == null) ? 0 : longOfPericenter.hashCode());
		result = prime * result + ((lower == null) ? 0 : lower.hashCode());
		result = prime * result + ((meanLongitude == null) ? 0 : meanLongitude.hashCode());
		result = prime * result + ((nightTexture == null) ? 0 : nightTexture.hashCode());
		result = prime * result + ((oblateness == null) ? 0 : oblateness.hashCode());
		result = prime * result + ((obliquity == null) ? 0 : obliquity.hashCode());
		result = prime * result + ((period == null) ? 0 : period.hashCode());
		result = prime * result + ((radius == null) ? 0 : radius.hashCode());
		result = prime * result + ((rotationPeriod == null) ? 0 : rotationPeriod.hashCode());
		result = prime * result + ((semiMajorAxis == null) ? 0 : semiMajorAxis.hashCode());
		result = prime * result + ((sky == null) ? 0 : sky.hashCode());
		result = prime * result + ((specularPower == null) ? 0 : specularPower.hashCode());
		result = prime * result + ((specularTexture == null) ? 0 : specularTexture.hashCode());
		result = prime * result + ((texture == null) ? 0 : texture.hashCode());
		result = prime * result + ((upper == null) ? 0 : upper.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanarExtension other = (PlanarExtension) obj;
		if (albedo == null) {
			if (other.albedo != null)
				return false;
		} else if (!albedo.equals(other.albedo))
			return false;
		if (atmosphereHeight == null) {
			if (other.atmosphereHeight != null)
				return false;
		} else if (!atmosphereHeight.equals(other.atmosphereHeight))
			return false;
		if (cloudHeight == null) {
			if (other.cloudHeight != null)
				return false;
		} else if (!cloudHeight.equals(other.cloudHeight))
			return false;
		if (cloudMap == null) {
			if (other.cloudMap != null)
				return false;
		} else if (!cloudMap.equals(other.cloudMap))
			return false;
		if (cloudSpeed == null) {
			if (other.cloudSpeed != null)
				return false;
		} else if (!cloudSpeed.equals(other.cloudSpeed))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (eccentricity == null) {
			if (other.eccentricity != null)
				return false;
		} else if (!eccentricity.equals(other.eccentricity))
			return false;
		if (hazeColor == null) {
			if (other.hazeColor != null)
				return false;
		} else if (!hazeColor.equals(other.hazeColor))
			return false;
		if (hazeDensity == null) {
			if (other.hazeDensity != null)
				return false;
		} else if (!hazeDensity.equals(other.hazeDensity))
			return false;
		if (longOfPericenter == null) {
			if (other.longOfPericenter != null)
				return false;
		} else if (!longOfPericenter.equals(other.longOfPericenter))
			return false;
		if (lower == null) {
			if (other.lower != null)
				return false;
		} else if (!lower.equals(other.lower))
			return false;
		if (meanLongitude == null) {
			if (other.meanLongitude != null)
				return false;
		} else if (!meanLongitude.equals(other.meanLongitude))
			return false;
		if (nightTexture == null) {
			if (other.nightTexture != null)
				return false;
		} else if (!nightTexture.equals(other.nightTexture))
			return false;
		if (oblateness == null) {
			if (other.oblateness != null)
				return false;
		} else if (!oblateness.equals(other.oblateness))
			return false;
		if (obliquity == null) {
			if (other.obliquity != null)
				return false;
		} else if (!obliquity.equals(other.obliquity))
			return false;
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		if (radius == null) {
			if (other.radius != null)
				return false;
		} else if (!radius.equals(other.radius))
			return false;
		if (rotationPeriod == null) {
			if (other.rotationPeriod != null)
				return false;
		} else if (!rotationPeriod.equals(other.rotationPeriod))
			return false;
		if (semiMajorAxis == null) {
			if (other.semiMajorAxis != null)
				return false;
		} else if (!semiMajorAxis.equals(other.semiMajorAxis))
			return false;
		if (sky == null) {
			if (other.sky != null)
				return false;
		} else if (!sky.equals(other.sky))
			return false;
		if (specularPower == null) {
			if (other.specularPower != null)
				return false;
		} else if (!specularPower.equals(other.specularPower))
			return false;
		if (specularTexture == null) {
			if (other.specularTexture != null)
				return false;
		} else if (!specularTexture.equals(other.specularTexture))
			return false;
		if (texture == null) {
			if (other.texture != null)
				return false;
		} else if (!texture.equals(other.texture))
			return false;
		if (upper == null) {
			if (other.upper != null)
				return false;
		} else if (!upper.equals(other.upper))
			return false;
		return true;
	}
	
	public static Map<String, Object> getPlanarExtensionMap(PlanarExtension planarExtension){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(PlanarExtensionDao.ALBEDO, planarExtension.getAlbedo());
		map.put(PlanarExtensionDao.ATMOSPHEREHEIGHT, planarExtension.getAtmosphereHeight());
		map.put(PlanarExtensionDao.CLOUDHEIGHT, planarExtension.getCloudHeight());
		map.put(PlanarExtensionDao.CLOUDMAP, planarExtension.getCloudMap());
		map.put(PlanarExtensionDao.CLOUDSPEED, planarExtension.getCloudSpeed());
		map.put(PlanarExtensionDao.COLORR, planarExtension.getColor().rOfRGB);
		map.put(PlanarExtensionDao.COLORG, planarExtension.getColor().gOfRGB);
		map.put(PlanarExtensionDao.COLORB, planarExtension.getColor().bOfRGB);
		map.put(PlanarExtensionDao.ECCENTRICITY, planarExtension.getEccentricity());
		map.put(PlanarExtensionDao.HAZECOLORR, planarExtension.getHazeColor().rOfRGB);		
		map.put(PlanarExtensionDao.HAZECOLORG, planarExtension.getHazeColor().gOfRGB);		
		map.put(PlanarExtensionDao.HAZECOLORB, planarExtension.getHazeColor().bOfRGB);
		map.put(PlanarExtensionDao.ORBITLONGOFPERICENTRE, planarExtension.getLongOfPericenter());
		map.put(PlanarExtensionDao.ATMOSPHERELOWERR, planarExtension.getLower().rOfRGB);		
		map.put(PlanarExtensionDao.ATMOSPHERELOWERG, planarExtension.getLower().gOfRGB);		
		map.put(PlanarExtensionDao.ATMOSPHERELOWERB, planarExtension.getLower().bOfRGB);		
		map.put(PlanarExtensionDao.ORBITMEANLONGITUDE, planarExtension.getMeanLongitude());
		return map;
	}

	@Override
	public String toString() {
		return "PlanarExtension [planarClass=" + planarClass + ", texture=" + texture + ", nightTexture=" + nightTexture
				+ ", color=" + color + ", specularTexture=" + specularTexture + ", specularPower=" + specularPower
				+ ", hazeColor=" + hazeColor + ", hazeDensity=" + hazeDensity + ", radius=" + radius + ", oblateness="
				+ oblateness + ", atmosphereHeight=" + atmosphereHeight + ", lower=" + lower + ", upper=" + upper
				+ ", sky=" + sky + ", cloudHeight=" + cloudHeight + ", cloudMap=" + cloudMap + ", cloudSpeed="
				+ cloudSpeed + ", period=" + period + ", semiMajorAxis=" + semiMajorAxis + ", eccentricity="
				+ eccentricity + ", longOfPericenter=" + longOfPericenter + ", meanLongitude=" + meanLongitude
				+ ", rotationPeriod=" + rotationPeriod + ", obliquity=" + obliquity + ", albedo=" + albedo + "]";
	}
	
	
}
