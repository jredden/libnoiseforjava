package celestia;

import java.text.MessageFormat;
import java.util.List;

import com.zenred.cosmos.domain.ClusterRep;
import com.zenred.cosmos.domain.ClusterRepDao;
import com.zenred.cosmos.domain.ExistingSystemWithStars;
import com.zenred.cosmos.domain.Planetoid;
import com.zenred.cosmos.domain.PlanetoidDao;
import com.zenred.cosmos.domain.Star;
import com.zenred.cosmos.domain.StarDao;
import com.zenred.cosmos.domain.SystemClusterSubSet;
import com.zenred.cosmos.domain.SystemDao;
import com.zenred.cosmos.domain.UnifiedPlanetoidI;

import celestia.domain.PlanarExtension;
import celestia.domain.PlanarExtensionDao;

public interface SSC_Entry_formatsI {
	public static String planetClass = "planet";
	public static String moonClass = "moon";
	public static String imageType = ".png";
	public static String nightImageType = ".night.png";
	public static String bumpMapType = ".bump.png";
	public static String specularTextureType = ".specular.png";
	public static String cloudMapType = ".cloud.png";

	// 0 -> planet 1-> star 2-> rest of planet ... or ... 0 -> moon 1 -> planet
	// 2-> rest of moon
	public static MessageFormat planar = new MessageFormat("\"{0}\" \"{1}\" '{' \n {2} \n '}' \n\n");
	// 0 -> moon or planet or other in system object
	public static MessageFormat planarClass = new MessageFormat("Class \"{0}\" \n");
	// png image
	public static MessageFormat texture = new MessageFormat("Texture \"{0}{1}\" \n ");
	// night png image
	public static MessageFormat nightTexture = new MessageFormat("NightTexture \"{0}{1}\" \n ");
	// bump map png image
	public static MessageFormat bumpMap = new MessageFormat("BumpMap \"{0}{1}\" \n ");
	// bump height
	public static MessageFormat bumpHeight = new MessageFormat("BumpHeight {0} \n");
	// generic color of planet from stars color
	public static MessageFormat planarBaseColor = new MessageFormat("Color [ {0} {1} {2} ] \n");
	// specular png image
	public static MessageFormat specularTexture = new MessageFormat("SpecularTexture \"{0}{1}\" \n ");
	// specular power
	public static MessageFormat specularPower = new MessageFormat("SpecularPower {0} \n");
	// specular color
	public static MessageFormat specularColor = new MessageFormat("SpecularColor [ {0} {1} {2} \n ]");
	// haze color
	public static MessageFormat hazeColor = new MessageFormat("HazeColor [ {0} {1} {2} \n ]");
	// haze density
	public static MessageFormat hazePower = new MessageFormat("HazeDensity {0} \n");
	// radius
	public static MessageFormat radius = new MessageFormat("Radius {0} \n");
	// oblateness
	public static MessageFormat oblateness = new MessageFormat("Oblateness {0}\n");
	// atmosphere
	public static MessageFormat planarAtmosphere = new MessageFormat("Atmosphere '{' \n Height {0} \n"
			+ " Lower [ {1} {2} {3} ] \n" + " Upper [ {4} {5} {6} ] \n" + " Sky [ {7} {8} {9} ] \n"
			+ " CloudHeight {10} \n" + " CloudSpeed {11} \n" + " CloudMap \"{12}\" \n" + "'}' \n\n");
	public static MessageFormat ellipticalOrbit = new MessageFormat(
			"ElipticalOrbit '{'\n" + " Period {0} \n" + " SemiMajorAxis {1} \n" + " Eccentricity {2} \n"
					+ " Inclination {3} \n" + " LongOfPericenter {4} \n" + " MeanLongitude {5} \n" + "'}' \n\n");
	public static MessageFormat planarAlbedo = new MessageFormat("Albedo {0} \n");

	/**
	 * uses the formats
	 * 
	 * @author jredden
	 *
	 */
	public abstract class BuildSSCFlatFile {

		public static PlanarExtension readPlanet(Star star, UnifiedPlanetoidI unifiedPlanetoidI,
				PlanarExtension planarExtension) {
			
			return planarExtension;
		}

		public static PlanarExtension readMoon(Star star, UnifiedPlanetoidI unifiedPlanetoidI, UnifiedPlanetoidI unifiedMoonI,
				PlanarExtension planarExtension) {
			
			return planarExtension;
		}
		
		public static StringBuffer common() {
			StringBuffer fileImage = new StringBuffer();
			SystemDao systemDao = new SystemDao();
			ClusterRepDao clusterRepDao = new ClusterRepDao();
			StarDao starDao = new StarDao();
			PlanetoidDao planetoidDao = new PlanetoidDao();
			PlanarExtension planarExtension = new PlanarExtension();
			PlanarExtensionDao planarExtensionDao = null;
			List<SystemClusterSubSet> systemsWithCluster = systemDao.readSystemsWithClusters();
			for (SystemClusterSubSet systemClusterSubSet : systemsWithCluster) {
				ClusterRep clusterRep = clusterRepDao.readClusterRepById(systemClusterSubSet.getClusterRepId());
				List<Star> starList = starDao.readStarsInCluster(clusterRep);
				for (Star star : starList) {
					List<UnifiedPlanetoidI> unifiedPlanetoidIs = ExistingSystemWithStars.readPlanetsAroundStar(star);
					if (unifiedPlanetoidIs.isEmpty()) {
						continue; // no planars
					} else {
						planarExtensionDao = new PlanarExtensionDao();
						for (UnifiedPlanetoidI unifiedPlanetoidI : unifiedPlanetoidIs) {
							String planetnoidName = unifiedPlanetoidI.getPlanetoid().getPlanetoidName();
							Planetoid planetoid = planetoidDao.readPlanetoidByName(planetnoidName);
							planarExtension = readPlanet(star, unifiedPlanetoidI, planarExtension);
							List<UnifiedPlanetoidI> unifiedMoonsIs = ExistingSystemWithStars
									.readMoonsAroundPlanet(unifiedPlanetoidI.getPlanetoid());
							if (unifiedMoonsIs.isEmpty()) {
								continue; // no moons
							} else {
								planarExtension = new PlanarExtension();
								for (UnifiedPlanetoidI unifiedMoonI : unifiedMoonsIs) {
									String moonName = unifiedMoonI.getPlanetoid().getPlanetoidName();
									planetoid = planetoidDao.readPlanetoidByName(moonName);
									planarExtension = readMoon(star, unifiedPlanetoidI, unifiedMoonI, planarExtension);
								}
							}
						}
					}
				}
			}
			return fileImage;
		}

	}

}
