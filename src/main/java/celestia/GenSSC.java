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
	public static String bumpMapType = ".bump.png";
	
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
	
	/**
	 * generic planar builder for SSC 
	 * 
	 * @param unifiedPlanetoidI
	 * @param image
	 * @return processed string image
	 */
	private static String buildPlanar(UnifiedPlanetoidI unifiedPlanetoidI, StringBuilder image){
		image.append(texture.format(new Object[]{unifiedPlanetoidI.getPlanetoid().getPlanetoidName(), imageType}));
		image.append("Emissive true \n");  // light source from primary
		image.append(nightTexture.format(new Object[]{unifiedPlanetoidI.getPlanetoid().getPlanetoidName(), nightImageType}));
		image.append(bumpMap.format(new Object[]{unifiedPlanetoidI.getPlanetoid().getPlanetoidName(), bumpMapType}));
		image.append(bumpHeight.format(new Object[]{BumpHeight.build(unifiedPlanetoidI)}));
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
					}
				}

			}
		}
		
	}

	public static void main(String[] args) {
		build();

	}

}
