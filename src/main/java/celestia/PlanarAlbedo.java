package celestia;

import com.zenred.cosmos.domain.Star;
import com.zenred.cosmos.domain.UnifiedPlanetoidI;

public class PlanarAlbedo {
	
	interface PlanarTemperature {
		public Double modAlbedo(Double albedo);
	}
	
	interface PlanarAtmosphere{
		public Double modAlbedo(Double albedo);
	}
	
	interface PlanarSize{
		public Double modAlbedo(Double albedo);
	}

	
	public static Double typicalAlbedo = 30.0;
	
	/**
	 * -Assume full planar phase, geometric albedo, 100% potential flux
	 * -The closer to the star, the greater albedo
	 * -Hotter the star, the greater the albedo
	 * -Atmospheres, with water, will reduce albedo
	 * -dwarf planar size, will reduce albedo
	 * -ice planars will increase albedo
	 * 
	 * @param unifiedPlanetoidI
	 * @return generated albedo
	 */
	public static Double genAlbedoPlanar(UnifiedPlanetoidI unifiedPlanetoidI){
		return null;
	}
	
	/**
	 * 
	 * @param planarPlanetoidI
	 * @param moonPlanetoidI
	 * @return generated albedo
	 */
	public static Double genAlbedoMoon(UnifiedPlanetoidI planarPlanetoidI, UnifiedPlanetoidI moonPlanetoidI){
		return null;
	}

}
