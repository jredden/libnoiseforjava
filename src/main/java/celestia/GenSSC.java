package celestia;

import java.text.MessageFormat;
import java.util.List;

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

public class GenSSC {
	
	
	
	public static String planetClass = "planet";
	public static String moonClass = "moon";
	public static String imageType = ".png";
	public static String nightImageType = ".night.png";
	
	// 0 -> planet  1-> star 2-> rest of planet ... or ... 0 -> moon  1 -> planet 2-> rest of moon
	private static MessageFormat planar = new MessageFormat("\"{0}\" \"{1}\" '{' \n {2} \n '}' \n\n");
	// 0 -> moon or planet or other in system object
	private static MessageFormat planarClass = new MessageFormat("Class \"{0}\" \n");
	// png image
	private static MessageFormat texture = new MessageFormat("Texture \"{0}{1}\" ");
	// night png image
	private static MessageFormat nightTexture = new MessageFormat("NightTexture \"{0}{1}\" ");
	
	private static Double bumpHeight(UnifiedPlanetoidI unifiedPlanetoidI){
		Double height = null;
		Double radius = unifiedPlanetoidI.getPlanetoid().getRadius();
		String planarSizeType = GenAtmosphere.sizeType(radius);
		String temperatureType = GenAtmosphere.temperatureType(unifiedPlanetoidI.getPlanetoid().getTemperature());
		return height;
	}
	
	private static String buildPlanar(UnifiedPlanetoidI unifiedPlanetoidI, StringBuilder image){
		image.append(texture.format(new Object[]{unifiedPlanetoidI.getPlanetoid().getPlanetoidName(), imageType}));
		image.append("Emissive true \n");  // light source from primary
		image.append(nightTexture.format(new Object[]{unifiedPlanetoidI.getPlanetoid().getPlanetoidName(), nightImageType}));
		return image.toString();
	}
	
	private static String buildPlanet(UnifiedPlanetoidI unifiedPlanetoidI){
		StringBuilder planetImage = new StringBuilder("");
		planetImage.append(planarClass.format(new Object[]{planetClass}));
		return planetImage.toString();
	}
	
	private static String buildMoon(UnifiedPlanetoidI unifiedPlanetoidI){
		StringBuilder moonImage = new StringBuilder("");
		moonImage.append(planarClass.format(new Object[]{moonClass}));
		
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
						String planarClass = 
					}
				}

			}
		}
		
	}

	public static void main(String[] args) {
		build();

	}

}
