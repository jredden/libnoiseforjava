package celestia;

import java.util.List;

import com.zenred.cosmos.domain.ClusterRep;
import com.zenred.cosmos.domain.ClusterRepDao;
import com.zenred.cosmos.domain.Star;
import com.zenred.cosmos.domain.StarDao;
import com.zenred.cosmos.domain.SystemClusterSubSet;
import com.zenred.cosmos.domain.SystemDao;

/**
 * generates an STC file for celestia
 * 
 * @author jredden
 *
 */

public class GenSTC {
	
	public static void build() {
		SystemDao systemDao = new SystemDao();
		ClusterRepDao clusterRepDao = new ClusterRepDao();
		StarDao starDao = new StarDao();
		List<SystemClusterSubSet> systemsWithCluster = systemDao.readSystemsWithClusters();
		for (SystemClusterSubSet systemClusterSubSet : systemsWithCluster) {
			Double rightAscension = RightAscension.buildRightAcension(systemClusterSubSet.getUcoordinate(),
					systemClusterSubSet.getVcoordinate());
			Double declension = Declension.determineQuadrant(rightAscension);
			Double distance = Math.sqrt(systemClusterSubSet.getUcoordinate() * systemClusterSubSet.getUcoordinate()
					+ systemClusterSubSet.getVcoordinate() * systemClusterSubSet.getVcoordinate());
			String name = systemClusterSubSet.getClustername();
			ClusterRep clusterRep = clusterRepDao.readClusterRepById(systemClusterSubSet.getClusterRepId());
			List<Star> starList = starDao.readStarsInCluster(clusterRep);
			for(Star star : starList){
				Double period = Period.build(star.getDistance_clust_virt_centre());
				Double semiMajorAxis = star.getDistance_clust_virt_centre();
				Double eccentricity = Eccentriity.build();
			}
		}
	}

}
