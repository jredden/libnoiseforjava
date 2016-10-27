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
import celestia.domain.CelestAtmosphere;
import celestia.domain.ColorRGB;
import celestia.domain.Haze;
import celestia.persistence.BasicFileWriter;

/**
 * generates a celestia ssc file for each star system
 * 
 * @author jredden
 *
 */

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
	private static MessageFormat hazePower = new MessageFormat("HazeDensity {0} \n");
	// radius
	private static MessageFormat radius = new MessageFormat("Radius {0} \n");
	// oblateness
	private static MessageFormat oblateness = new MessageFormat("Oblateness {0}\n");
	// atmosphere
	private static MessageFormat planarAtmosphere = new MessageFormat("Atmosphere '{' \n Height {0} \n"
			+ " Lower [ {1} {2} {3} ] \n"
			+ " Upper [ {4} {5} {6} ] \n"
			+ " Sky [ {7} {8} {9} ] \n"
			+ " CloudHeight {10} \n"
			+ " CloudSpeed {11} \n"
			+ " CloudMap \"{12}\" \n"
			+ "'}' \n\n"
			);
	private static MessageFormat ellipticalOrbit = new MessageFormat("ElipticalOrbit '{'\n"
			+ " Period {0} \n"
			+ " SemiMajorAxis {1} \n"
			+ " Eccentricity {2} \n"
			+ " Inclination {3} \n"
			+ " LongOfPericenter {4} \n"
			+ " MeanLongitude {5} \n"
			+ "'}' \n\n"
			);
	private static MessageFormat planarAlbedo = new MessageFormat("Albedo {0} \n");
	
	/**
	 * generic planar builder for SSC 
	 * 
	 * @param unifiedPlanetoidI
	 * @param image
	 * @return processed string image
	 */
	private static String buildPlanar(Star star, UnifiedPlanetoidI unifiedPlanetoidI){
		StringBuilder image = new StringBuilder();
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
		image.append(planarAlbedo.format(new Object[]{PlanarAlbedo.genAlbedoPlanar(unifiedPlanetoidI)}));
		return image.toString();
	}
	/**
	 * 
	 * @param unifiedPlanetoidI
	 * @param period
	 * @param axis
	 * @return orbit component
	 */
	private static String buildOrbit(UnifiedPlanetoidI unifiedPlanetoidI, Double period, Double axis){
		StringBuilder image = new StringBuilder();
		Double eccentricity = AdditionalPlanarOrbitScalars.genEccentricity();
		Double inclinaton = AdditionalPlanarOrbitScalars.genInclination();
		Double longOfPeriCentre = AdditionalPlanarOrbitScalars.genLongOfPericentre();
		Double meanLongitude = AdditionalPlanarOrbitScalars.genMeanLongitude();
		image.append(ellipticalOrbit.format(new Object[]{
		period // {0}
		,axis	// {1}
		,eccentricity // {2}
		,inclinaton 	// {3}
		,longOfPeriCentre // {4}
		,meanLongitude	// {5}
		}));
		return image.toString();
	}
	/**
	 * 
	 * @param star
	 * @param unifiedPlanetoidI
	 * @return planet image
	 */
	private static String buildPlanet(Star star, UnifiedPlanetoidI unifiedPlanetoidI){
		StringBuilder planetImage = new StringBuilder("");
		planetImage.append(planarClass.format(new Object[]{planetClass.toString()}));
		planetImage.append(buildPlanar(star, unifiedPlanetoidI));
		Double planarPeriod = PlanarPeriod.build(star, unifiedPlanetoidI);
		Double semiMajorAxis = unifiedPlanetoidI.getPlanetoid().getDistanceToPrimary();
		planetImage.append(buildOrbit(unifiedPlanetoidI, planarPeriod, semiMajorAxis));
		return planetImage.toString();
	}
	/**
	 * 
	 * @param star
	 * @param unifiedPlanetoidI
	 * @param unifiedMoonI
	 * @return moon image
	 */
	private static String buildMoon(Star star, UnifiedPlanetoidI unifiedPlanetoidI, UnifiedPlanetoidI unifiedMoonI){
		StringBuilder moonImage = new StringBuilder("");
		moonImage.append(planarClass.format(new Object[]{moonClass.toString()}));
		moonImage.append(buildPlanar(star, unifiedPlanetoidI));
		Double moonPeriod = PlanarPeriod.build(unifiedMoonI);
		Double semiMajorAxis = unifiedMoonI.getPlanetoid().getDistanceToPrimary() * AstronomicalUnits.MOON_UNIT;
		moonImage.append(buildOrbit(unifiedPlanetoidI, moonPeriod, semiMajorAxis));
		return moonImage.toString();
	}
	
	/**
	 * primary procedure
	 */
	public static void build() {
		SystemDao systemDao = new SystemDao();
		ClusterRepDao clusterRepDao = new ClusterRepDao();
		StarDao starDao = new StarDao();
		PlanetoidDao planetoidDdao = new PlanetoidDao();
		List<SystemClusterSubSet> systemsWithCluster = systemDao.readSystemsWithClusters();
		for (SystemClusterSubSet systemClusterSubSet : systemsWithCluster) {
			ClusterRep clusterRep = clusterRepDao.readClusterRepById(systemClusterSubSet.getClusterRepId());
			StringBuilder masterFileImage = new StringBuilder("");
			List<Star> starList = starDao.readStarsInCluster(clusterRep);
			for(Star star : starList){
				List<UnifiedPlanetoidI> unifiedPlanetoidIs = ExistingSystemWithStars
						.readPlanetsAroundStar(star);
				if (unifiedPlanetoidIs.isEmpty()) {
					continue; // no planars
				}
				else{
					StringBuilder fileImage = new StringBuilder("");
					for (UnifiedPlanetoidI unifiedPlanetoidI : unifiedPlanetoidIs){
						String planetnoidName = unifiedPlanetoidI.getPlanetoid().getPlanetoidName();
						String planetImage = buildPlanet(star, unifiedPlanetoidI);
						fileImage.append(planetImage);
						StringBuilder container = new StringBuilder().append(planar.format(new Object[]{planetnoidName,
								star.getName(), fileImage}));
						masterFileImage.append(container);
						
						List<UnifiedPlanetoidI> unifiedMoonsIs = ExistingSystemWithStars
								.readMoonsAroundPlanet(unifiedPlanetoidI.getPlanetoid());
						if(unifiedMoonsIs.isEmpty()){
							continue; // no moons
						}
						else{
							fileImage = new StringBuilder("");
							for (UnifiedPlanetoidI unifiedMoonI : unifiedMoonsIs) {
								String moonName = unifiedMoonI.getPlanetoid().getPlanetoidName();
								String moonImage = buildMoon(star, unifiedPlanetoidI, unifiedMoonI);
								fileImage.append(moonImage);
								container = new StringBuilder().append(planar.format(new Object[]{moonName, star.getName() + '/' + planetnoidName,
										fileImage}));
								masterFileImage.append(container);
							}
						}
					}
				}
				String uri = "celestia/cosmos/" + Math.random() + star.getName() + "_cosmos.ssc";
				BasicFileWriter.writeIt(masterFileImage, uri);
				masterFileImage = new StringBuilder("");
			}
	
		}
		
	}

	public static void main(String[] args) {
		build();

	}

}
