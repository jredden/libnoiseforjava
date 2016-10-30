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
	
	private Integer planarExtensionId;
	private Integer planarId;
	private String planarName;
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
	private Double orbitPeriod;
	private Double orbitSemiMajorAxis;
	private Double orbitEccentricity;
	private Double orbitInclination;
	private Double orbitLongOfPeriCentre;
	private Double orbitMeanLongitude;
	private String dateStamp;
	
	
	public static PlanarExtension build(Star star, Planetoid planetoid, PlanarClass planarClass){
		PlanarExtension instance = new PlanarExtension();
		instance.planarClass = planarClass;
		instance.texture = planetoid.getPlanetoidName()+GRAPHIC_SUFFIX;
		return instance;
	}

	public String getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(String dateStamp) {
		this.dateStamp = dateStamp;
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

	public Integer getPlanarExtensionId() {
		return planarExtensionId;
	}

	public Integer getPlanarId() {
		return planarId;
	}

	public void setPlanarId(Integer planarId) {
		this.planarId = planarId;
	}

	public void setPlanarExtensionId(Integer planarExtensionId) {
		this.planarExtensionId = planarExtensionId;
	}

	public String getPlanarName() {
		return planarName;
	}

	public void setPlanarName(String planarName) {
		this.planarName = planarName;
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

	
	public Double getOrbitPeriod() {
		return orbitPeriod;
	}

	public void setOrbitPeriod(Double orbitPeriod) {
		this.orbitPeriod = orbitPeriod;
	}

	public Double getOrbitSemiMajorAxis() {
		return orbitSemiMajorAxis;
	}

	public void setOrbitSemiMajorAxis(Double orbitSemiMajorAxis) {
		this.orbitSemiMajorAxis = orbitSemiMajorAxis;
	}

	public Double getOrbitEccentricity() {
		return orbitEccentricity;
	}

	public void setOrbitEccentricity(Double orbitEccentricity) {
		this.orbitEccentricity = orbitEccentricity;
	}

	public Double getOrbitInclination() {
		return orbitInclination;
	}

	public void setOrbitInclination(Double orbitInclination) {
		this.orbitInclination = orbitInclination;
	}

	public Double getOrbitLongOfPeriCentre() {
		return orbitLongOfPeriCentre;
	}

	public void setOrbitLongOfPeriCentre(Double orbitLongOfPeriCentre) {
		this.orbitLongOfPeriCentre = orbitLongOfPeriCentre;
	}

	public Double getOrbitMeanLongitude() {
		return orbitMeanLongitude;
	}

	public void setOrbitMeanLongitude(Double orbitMeanLongitude) {
		this.orbitMeanLongitude = orbitMeanLongitude;
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
		result = prime * result + ((orbitEccentricity == null) ? 0 : orbitEccentricity.hashCode());
		result = prime * result + ((orbitInclination == null) ? 0 : orbitInclination.hashCode());
		result = prime * result + ((orbitLongOfPeriCentre == null) ? 0 : orbitLongOfPeriCentre.hashCode());
		result = prime * result + ((orbitMeanLongitude == null) ? 0 : orbitMeanLongitude.hashCode());
		result = prime * result + ((orbitPeriod == null) ? 0 : orbitPeriod.hashCode());
		result = prime * result + ((orbitSemiMajorAxis == null) ? 0 : orbitSemiMajorAxis.hashCode());
		result = prime * result + ((period == null) ? 0 : period.hashCode());
		result = prime * result + ((planarClass == null) ? 0 : planarClass.hashCode());
		result = prime * result + ((planarExtensionId == null) ? 0 : planarExtensionId.hashCode());
		result = prime * result + ((planarId == null) ? 0 : planarId.hashCode());
		result = prime * result + ((planarName == null) ? 0 : planarName.hashCode());
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
		if (orbitEccentricity == null) {
			if (other.orbitEccentricity != null)
				return false;
		} else if (!orbitEccentricity.equals(other.orbitEccentricity))
			return false;
		if (orbitInclination == null) {
			if (other.orbitInclination != null)
				return false;
		} else if (!orbitInclination.equals(other.orbitInclination))
			return false;
		if (orbitLongOfPeriCentre == null) {
			if (other.orbitLongOfPeriCentre != null)
				return false;
		} else if (!orbitLongOfPeriCentre.equals(other.orbitLongOfPeriCentre))
			return false;
		if (orbitMeanLongitude == null) {
			if (other.orbitMeanLongitude != null)
				return false;
		} else if (!orbitMeanLongitude.equals(other.orbitMeanLongitude))
			return false;
		if (orbitPeriod == null) {
			if (other.orbitPeriod != null)
				return false;
		} else if (!orbitPeriod.equals(other.orbitPeriod))
			return false;
		if (orbitSemiMajorAxis == null) {
			if (other.orbitSemiMajorAxis != null)
				return false;
		} else if (!orbitSemiMajorAxis.equals(other.orbitSemiMajorAxis))
			return false;
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		if (planarClass != other.planarClass)
			return false;
		if (planarExtensionId == null) {
			if (other.planarExtensionId != null)
				return false;
		} else if (!planarExtensionId.equals(other.planarExtensionId))
			return false;
		if (planarId == null) {
			if (other.planarId != null)
				return false;
		} else if (!planarId.equals(other.planarId))
			return false;
		if (planarName == null) {
			if (other.planarName != null)
				return false;
		} else if (!planarName.equals(other.planarName))
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
		map.put(PlanarExtensionDao.NIGHTTEXTURE, planarExtension.getNightTexture());
		map.put(PlanarExtensionDao.OBLATENESS, planarExtension.getOblateness());
		map.put(PlanarExtensionDao.OBLIQUITY, planarExtension.getObliquity());
		map.put(PlanarExtensionDao.ORBITPERIOD, planarExtension.getOrbitPeriod());
		map.put(PlanarExtensionDao.ORBITECCENTRICITY, planarExtension.getOrbitEccentricity());
		map.put(PlanarExtensionDao.ORBITINCLINATION, planarExtension.getOrbitInclination());
		map.put(PlanarExtensionDao.ORBITLONGOFPERICENTRE, planarExtension.getOrbitLongOfPeriCentre());
		map.put(PlanarExtensionDao.ORBITMEANLONGITUDE, planarExtension.getOrbitMeanLongitude());
		map.put(PlanarExtensionDao.ORBITSEMIMAJORAXIS, planarExtension.getOrbitSemiMajorAxis());
		map.put(PlanarExtensionDao.ROTATIONPERIOD, planarExtension.getRotationPeriod());
		map.put(PlanarExtensionDao.SEMIMAJORAXIS, planarExtension.getSemiMajorAxis());
		map.put(PlanarExtensionDao.ATMOSPHERESKYR, planarExtension.getSky().rOfRGB);
		map.put(PlanarExtensionDao.ATMOSPHERESKYG, planarExtension.getSky().gOfRGB);
		map.put(PlanarExtensionDao.ATMOSPHERESKYB, planarExtension.getSky().bOfRGB);
		map.put(PlanarExtensionDao.SPECULARPOWER, planarExtension.getSpecularPower());
		map.put(PlanarExtensionDao.SPECULARTEXTURE, planarExtension.getSpecularTexture());
		map.put(PlanarExtensionDao.TEXTURE, planarExtension.getTexture());
		map.put(PlanarExtensionDao.ATMOSPHEREUPPERR, planarExtension.getUpper().rOfRGB);
		map.put(PlanarExtensionDao.ATMOSPHEREUPPERG, planarExtension.getUpper().gOfRGB);
		map.put(PlanarExtensionDao.ATMOSPHEREUPPERB, planarExtension.getUpper().bOfRGB);
		map.put(PlanarExtensionDao.PLANETOIDEXTENSIONID, planarExtension.getPlanarExtensionId());
		map.put(PlanarExtensionDao.PLANETOIDID, planarExtension.getPlanarId());
		map.put(PlanarExtensionDao.PLANETOIDNAME, planarExtension.getPlanarName());
		map.put(PlanarExtensionDao.DATESTAMP, planarExtension.getDateStamp());
		return map;
	}
	
	public static String[] csvPlanarExtension() {
		return new String[] { PlanarExtensionDao.ALBEDO, PlanarExtensionDao.ATMOSPHEREHEIGHT,
				PlanarExtensionDao.CLOUDHEIGHT, PlanarExtensionDao.CLOUDMAP, PlanarExtensionDao.CLOUDSPEED,
				PlanarExtensionDao.COLORR, PlanarExtensionDao.COLORG, PlanarExtensionDao.COLORB,
				PlanarExtensionDao.ECCENTRICITY, PlanarExtensionDao.HAZECOLORR, PlanarExtensionDao.HAZECOLORG,
				PlanarExtensionDao.HAZECOLORB, PlanarExtensionDao.ORBITLONGOFPERICENTRE,
				PlanarExtensionDao.ATMOSPHERELOWERR, PlanarExtensionDao.ATMOSPHERELOWERG,
				PlanarExtensionDao.ATMOSPHERELOWERB, PlanarExtensionDao.ORBITMEANLONGITUDE,
				PlanarExtensionDao.NIGHTTEXTURE, PlanarExtensionDao.OBLATENESS, PlanarExtensionDao.OBLIQUITY,
				PlanarExtensionDao.ORBITPERIOD, PlanarExtensionDao.ROTATIONPERIOD, PlanarExtensionDao.SEMIMAJORAXIS,
				PlanarExtensionDao.ATMOSPHERESKYR, PlanarExtensionDao.ATMOSPHERESKYG, PlanarExtensionDao.ATMOSPHERESKYB,
				PlanarExtensionDao.SPECULARPOWER, PlanarExtensionDao.SPECULARTEXTURE, PlanarExtensionDao.TEXTURE,
				PlanarExtensionDao.ATMOSPHEREUPPERR, PlanarExtensionDao.ATMOSPHEREUPPERG,
				PlanarExtensionDao.ATMOSPHEREUPPERB, PlanarExtensionDao.PLANETOIDEXTENSIONID, PlanarExtensionDao.PLANETOIDID,
				PlanarExtensionDao.ORBITECCENTRICITY, PlanarExtensionDao.ORBITINCLINATION, PlanarExtensionDao.ORBITLONGOFPERICENTRE,
				PlanarExtensionDao.ORBITMEANLONGITUDE, PlanarExtensionDao.ORBITSEMIMAJORAXIS, PlanarExtensionDao.DATESTAMP
			};
	}

	@Override
	public String toString() {
		return "PlanarExtension [planarExtensionId=" + planarExtensionId + ", planarId=" + planarId + ", planarName="
				+ planarName + ", planarClass=" + planarClass + ", texture=" + texture + ", nightTexture="
				+ nightTexture + ", color=" + color + ", specularTexture=" + specularTexture + ", specularPower="
				+ specularPower + ", hazeColor=" + hazeColor + ", hazeDensity=" + hazeDensity + ", radius=" + radius
				+ ", oblateness=" + oblateness + ", atmosphereHeight=" + atmosphereHeight + ", lower=" + lower
				+ ", upper=" + upper + ", sky=" + sky + ", cloudHeight=" + cloudHeight + ", cloudMap=" + cloudMap
				+ ", cloudSpeed=" + cloudSpeed + ", period=" + period + ", semiMajorAxis=" + semiMajorAxis
				+ ", eccentricity=" + eccentricity + ", longOfPericenter=" + longOfPericenter + ", meanLongitude="
				+ meanLongitude + ", rotationPeriod=" + rotationPeriod + ", obliquity=" + obliquity + ", albedo="
				+ albedo + ", orbitPeriod=" + orbitPeriod + ", orbitSemiMajorAxis=" + orbitSemiMajorAxis
				+ ", orbitEccentricity=" + orbitEccentricity + ", orbitInclination=" + orbitInclination
				+ ", orbitLongOfPeriCentre=" + orbitLongOfPeriCentre + ", orbitMeanLongitude=" + orbitMeanLongitude
				+ ", dateStamp=" + dateStamp + "]";
	}
	
	
}
