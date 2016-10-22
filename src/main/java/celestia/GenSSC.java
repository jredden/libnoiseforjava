package celestia;

import java.text.MessageFormat;
import java.util.List;

import com.zenred.cosmos.domain.AstronomicalUnits;
import com.zenred.cosmos.domain.ClusterRep;
import com.zenred.cosmos.domain.ClusterRepDao;
import com.zenred.cosmos.domain.ExistingSystemWithStars;
import com.zenred.cosmos.domain.PlanetoidDao;
import com.zenred.cosmos.domain.Star;
import com.zenred.cosmos.domain.StarDao;
import com.zenred.cosmos.domain.SystemClusterSubSet;
import com.zenred.cosmos.domain.SystemDao;
import com.zenred.cosmos.domain.UnifiedPlanetoidI;
import com.zenred.cosmos.service_rules_and_infrastructure.GenAtmosphere;

import celestia.domain.CelestAtmosphere;
import celestia.domain.ColorRGB;
import celestia.domain.Haze;

public class GenSSC {
	
	
	
	public static String planetClass = "planet";
	public static String moonClass = "moon";
	public static String imageType = ".png";
	public static String nightImageType = ".night.png";
	public static String bumpMapType = ".bump.png";
	public static String specularTextureType = ".specular.png";
	public static String cloudMapType = ".cloud.png";
	
	// 0 -> planet  1-> star 2-> rest of planet ... or ... 0 -> moon  1 -> planet 2-> rest of moon
	private static MessageFormat planar = new MessageFormat("\"{0}\" \"{1}\" '{' \n {2} \n '}' \n\n");
	// 0 -> moon or planet or other in system object
	private static MessageFormat planarClass = new MessageFormat("Class \"{0}\" \n");
	// png image
	private static MessageFormat texture = new MessageFormat("Texture \"{0}{1}\" \n ");
	// night png image
	private static MessageFormat nightTexture = new MessageFormat("NightTexture \"{0}{1}\" \n ");
	// bump map png image
	private static MessageFormat bumpMap = new MessageFormat("BumpMap \"{0}{1}\" \n ");
	// bump height
	private static MessageFormat bumpHeight = new MessageFormat("BumpHeight {0} \n");
	// generic color of planet from stars color
	private static MessageFormat planarBaseColor = new MessageFormat("Color [ {0} {1} {2} ] \n");
	// specular png image
	private static MessageFormat specularTexture = new MessageFormat("SpecularTexture \"{0}{1}\" \n ");
	// specular power
	private static MessageFormat specularPower = new MessageFormat("SpecularPower {0} \n");
	// specular color
	private static MessageFormat specularColor = new MessageFormat("SpecularColor [ {0} {1} {2} \n ]");
	// haze color
	private static MessageFormat hazeColor = new MessageFormat("HazeColor [ {0} {1} {2} \n ]");
	// haze density
	private static MessageFormat hazePower = new MessageFormat("HazeDensity [0] \n");
	// radius
	private static MessageFormat radius = new MessageFormat("Radius [0] \n");
	// oblateness
	private static MessageFormat oblateness = new MessageFormat("Oblateness [0]\n");
	// atmosphere
	private static MessageFormat planarAtmosphere = new MessageFormat("Atmosphere \n { Height {0} \n"
			+ " Lower [ {1} {2} {3} ] \n"
			+ " Upper [ {4} {5} {6} ] \n"
			+ " Sky [ {7} {8} {9} ] \n"
			+ " CloudHeight {10} \n"
			+ " CloudSpeed {11} \n"
			+ " CloudMap \"{12}\" \n"
			+ "} \n\n"
			);
	
	
	
	/**
	 * generic planar builder for SSC 
	 * 
	 * @param unifiedPlanetoidI
	 * @param image
	 * @return processed string image
	 */
	private static String buildPlanar(Star star, UnifiedPlanetoidI unifiedPlanetoidI, StringBuilder image){
		image.append(texture.format(new Object[]{unifiedPlanetoidI.getPlanetoid().getPlanetoidName(), imageType}));
		image.append("Emissive true \n");  // light source from primary
		image.append(nightTexture.format(new Object[]{unifiedPlanetoidI.getPlanetoid().getPlanetoidName(), nightImageType}));
		image.append(bumpMap.format(new Object[]{unifiedPlanetoidI.getPlanetoid().getPlanetoidName(), bumpMapType}));
		image.append(bumpHeight.format(new Object[]{BumpHeight.build(unifiedPlanetoidI)}));
		ColorRGB colorRGB = StarColorMapping.mapStarColor(star.getStar_color());
		image.append(planarBaseColor.format(new Object[]{colorRGB.getColorR(), colorRGB.getColorG(), colorRGB.getColorB()}));
		image.append(specularTexture.format(new Object[]{unifiedPlanetoidI.getPlanetoid().getPlanetoidName(), specularTextureType}));
		colorRGB = PlanarSpecularColor.build(star, unifiedPlanetoidI);
		image.append(specularColor.format(new Object[]{colorRGB.getColorR(), colorRGB.getColorG(), colorRGB.getColorB()}));
		image.append(specularPower.format(new Object[]{PlanarSpecular.build(star, unifiedPlanetoidI)}));
		Haze haze = PlanarHaze.build(unifiedPlanetoidI);
		image.append(hazeColor.format(new Object[] { haze.getHazeColor().getColorR(), haze.getHazeColor().getColorG(),
				haze.getHazeColor().getColorB() }));
		image.append(hazePower.format(new Object[]{haze.getHazeDensity()}));
		image.append(radius.format(new Object[]{unifiedPlanetoidI.getPlanetoid().getRadius()}));
		image.append(oblateness.format(new Object[]{Oblateness.build(unifiedPlanetoidI)}));
		CelestAtmosphere celestAtmosphere = PlanarAtmosphere.build(star, unifiedPlanetoidI);
		image.append(planarAtmosphere.format(new Object[]{
				celestAtmosphere.getHeight() // {0}
				, celestAtmosphere.getLower().getColorR() // {1}
				, celestAtmosphere.getLower().getColorG() // {2}
				, celestAtmosphere.getLower().getColorB() // {3}
				, celestAtmosphere.getUpper().getColorR() // {4}
				, celestAtmosphere.getUpper().getColorG() // {5}
				, celestAtmosphere.getUpper().getColorB() // {6}
				, celestAtmosphere.getSky().getColorR() // {7}
				, celestAtmosphere.getSky().getColorG() // {8}
				, celestAtmosphere.getSky().getColorB() // {9}
				, celestAtmosphere.getCloudHeight() // {10}
				, celestAtmosphere.getCloudSpeed() // {11}
				, celestAtmosphere.getCloudMap() // {12}
		}));
		return image.toString();
	}
	
	private static String buildPlanet(Star star, UnifiedPlanetoidI unifiedPlanetoidI, StringBuilder image){
		StringBuilder planetImage = new StringBuilder("");
		planetImage.append(planarClass.format(new Object[]{planetClass}));
		planetImage.append(buildPlanar(star, unifiedPlanetoidI, planetImage));
		Double planarPeriod = PlanarPeriod.build(star, unifiedPlanetoidI);
		Double semiMajorAxis = unifiedPlanetoidI.getPlanetoid().getDistanceToPrimary();
		return planetImage.toString();
	}
	
	private static String buildMoon(Star star, UnifiedPlanetoidI unifiedPlanetoidI, UnifiedPlanetoidI unifiedMoonI, StringBuilder image){
		StringBuilder moonImage = new StringBuilder("");
		moonImage.append(planarClass.format(new Object[]{moonClass}));
		moonImage.append(buildPlanar(star, unifiedPlanetoidI, moonImage));
		Double moonPeriod = PlanarPeriod.build(unifiedMoonI);
		Double semiMajorAxis = unifiedMoonI.getPlanetoid().getDistanceToPrimary() * AstronomicalUnits.MOON_UNIT;
		return moonImage.toString();
	}
	public static void build() {
		StringBuilder fileImage = new StringBuilder("");
		SystemDao systemDao = new SystemDao();
		ClusterRepDao clusterRepDao = new ClusterRepDao();
		StarDao starDao = new StarDao();
		PlanetoidDao planetoidDdao = new PlanetoidDao();
		List<SystemClusterSubSet> systemsWithCluster = systemDao.readSystemsWithClusters();
		for (SystemClusterSubSet systemClusterSubSet : systemsWithCluster) {
			ClusterRep clusterRep = clusterRepDao.readClusterRepById(systemClusterSubSet.getClusterRepId());
			List<Star> starList = starDao.readStarsInCluster(clusterRep);
			for(Star star : starList){
				List<UnifiedPlanetoidI> unifiedPlanetoidIs = ExistingSystemWithStars
						.readPlanetsAroundStar(star);
				if (unifiedPlanetoidIs.isEmpty()) {
					continue; // no planars
				}
				else{
					for (UnifiedPlanetoidI unifiedPlanetoidI : unifiedPlanetoidIs){
						String planetnoidName = unifiedPlanetoidI.getPlanetoid().getPlanetoidName();
						fileImage.append(buildPlanet(star, unifiedPlanetoidI, fileImage));
						List<UnifiedPlanetoidI> unifiedMoonsIs = ExistingSystemWithStars
								.readMoonsAroundPlanet(unifiedPlanetoidI.getPlanetoid());
						if(unifiedMoonsIs.isEmpty()){
							continue; // no moons
						}
						else{
							for (UnifiedPlanetoidI unifiedMoonI : unifiedMoonsIs) {
								fileImage.append(buildMoon(star, unifiedPlanetoidI, unifiedMoonI, fileImage));
							}
						}
					}
				}

			}
		}
		
	}

	public static void main(String[] args) {
		build();

	}

}
