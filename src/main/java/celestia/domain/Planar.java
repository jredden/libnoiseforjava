package celestia.domain;

import com.zenred.cosmos.domain.Planetoid;
import com.zenred.cosmos.domain.Star;

public class Planar {
	
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
	
	public static Planar build(Star star, Planetoid planetoid, PlanarClass planarClass){
		Planar instance = new Planar();
		instance.planarClass = planarClass;
		instance.texture = planetoid.getPlanetoidName()+GRAPHIC_SUFFIX;
		return instance;
	}
}
