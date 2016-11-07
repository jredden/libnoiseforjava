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
import celestia.domain.PlanarExtension;
import celestia.domain.PlanarExtension.OGL_Color;
import celestia.persistence.BasicFileWriter;

/**
 * generates a celestia ssc file for each star system
 * 
 * @author jredden
 *
 */

public class GenSSCWithPersistence implements SSC_Entry_formatsI {
	
	/**
	 * generic planar builder for SSC 
	 * 
	 * @param unifiedPlanetoidI
	 * @param image
	 * @return processed string image
	 */
	private static PlanarExtension buildPlanar(Star star, UnifiedPlanetoidI unifiedPlanetoidI, PlanarExtension planarExtension){
		planarExtension.setTexture(unifiedPlanetoidI.getPlanetoid().getPlanetoidName());
 		planarExtension.setEmmisive(Boolean.TRUE);;   // light source from primary
		planarExtension.setNightTexture(unifiedPlanetoidI.getPlanetoid().getPlanetoidName());
		planarExtension.setBumpMap(bumpMap.format(new Object[]{unifiedPlanetoidI.getPlanetoid().getPlanetoidName(), bumpMapType}));
		planarExtension.setBumpHeight(BumpHeight.build(unifiedPlanetoidI));
		ColorRGB colorRGB = StarColorMapping.mapStarColor(star.getStar_color());
		OGL_Color oglColor = planarExtension.new OGL_Color();
		oglColor.rOfRGB = colorRGB.getColorR();
		oglColor.gOfRGB = colorRGB.getColorG();
		oglColor.bOfRGB = colorRGB.getColorB();
		planarExtension.setColor(oglColor);
		planarExtension.setSpecularTexture(unifiedPlanetoidI.getPlanetoid().getPlanetoidName());
		colorRGB = PlanarSpecularColor.build(star, unifiedPlanetoidI);
		oglColor = planarExtension.new OGL_Color();
		oglColor.rOfRGB = colorRGB.getColorR();
		oglColor.gOfRGB = colorRGB.getColorG();
		oglColor.bOfRGB = colorRGB.getColorB();
 		planarExtension.setSpecularColor(oglColor);	
		String planarSpecular = PlanarSpecular.build(star, unifiedPlanetoidI).toString();
		planarExtension.setSpecularPower(new Integer(planarSpecular));
		Haze haze = PlanarHaze.build(unifiedPlanetoidI);
		oglColor = planarExtension.new OGL_Color();
		oglColor.rOfRGB = haze.getHazeColor().getColorR();
		oglColor.gOfRGB = haze.getHazeColor().getColorG();
		oglColor.bOfRGB = haze.getHazeColor().getColorB();
		planarExtension.setHazeColor(oglColor);
		planarExtension.setHazeDensity(haze.getHazeDensity());
		planarExtension.setRadius(unifiedPlanetoidI.getPlanetoid().getRadius());
		planarExtension.setOblateness(Oblateness.build(unifiedPlanetoidI));

		CelestAtmosphere celestAtmosphere = PlanarAtmosphere.build(star, unifiedPlanetoidI);
		oglColor = planarExtension.new OGL_Color();
		oglColor.rOfRGB = celestAtmosphere.getLower().getColorR();
		oglColor.gOfRGB = celestAtmosphere.getLower().getColorG();
		oglColor.bOfRGB = celestAtmosphere.getLower().getColorB();
		planarExtension.setLower(oglColor);
		
		oglColor = planarExtension.new OGL_Color();
		oglColor.rOfRGB = celestAtmosphere.getUpper().getColorR();
		oglColor.gOfRGB = celestAtmosphere.getUpper().getColorG();
		oglColor.bOfRGB = celestAtmosphere.getUpper().getColorB();
		planarExtension.setUpper(oglColor);

		oglColor = planarExtension.new OGL_Color();
		oglColor.rOfRGB = celestAtmosphere.getSky().getColorR();
		oglColor.gOfRGB = celestAtmosphere.getSky().getColorG();
		oglColor.bOfRGB = celestAtmosphere.getSky().getColorB();
		planarExtension.setSky(oglColor);

		
		planarExtension.setAlbedo(PlanarAlbedo.genAlbedoPlanar(unifiedPlanetoidI));		
		return null;
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
	private static String buildPlanet(Star star, UnifiedPlanetoidI unifiedPlanetoidI, PlanarExtension planarExtension){
		StringBuilder planetImage = new StringBuilder("");
		planetImage.append(planarClass.format(new Object[]{planetClass.toString()}));
		planetImage.append(buildPlanar(star, unifiedPlanetoidI, planarExtension));
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
	private static String buildMoon(Star star, UnifiedPlanetoidI unifiedPlanetoidI, UnifiedPlanetoidI unifiedMoonI, PlanarExtension planarExtension){
		StringBuilder moonImage = new StringBuilder("");
		moonImage.append(planarClass.format(new Object[]{moonClass.toString()}));
		moonImage.append(buildPlanar(star, unifiedPlanetoidI, planarExtension));
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
		PlanarExtension planarExtension = new PlanarExtension();
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
						String planetImage = buildPlanet(star, unifiedPlanetoidI, planarExtension);
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
								String moonImage = buildMoon(star, unifiedPlanetoidI, unifiedMoonI, planarExtension);
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
