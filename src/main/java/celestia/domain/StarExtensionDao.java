package celestia.domain;

import java.util.Map;

import com.zenred.johntredden.domain.AbstractJDBCDao;

public class StarExtensionDao extends AbstractJDBCDao {
	
	public static final String STAR_EXTENSION = "StarExtension";
	
	public static final String STAREXTENSIONID = "starExtensionId";
	public static final String STARID = "starId";
	public static final String STARNAME = "starName";
	public static final String PERIOD = "period";
	public static final String STAR_SEMIMAJORAXIS = "semiMajorAxis";
	public static final String STAR_ECCENTRICITY = "eccentricity";
	public static final String ASCENDINGNODE = "ascendingNode";
	public static final String INCLINATION = "inclination";
	public static final String APPARANTMAGNITUDE = "apparantMagnitude";
	public static final String ECCENTRICITY = "eccentricity";
	public static final String SEMIMAJORAXIS = "semiMajorAxis";


	public StarExtension addStarExtension(StarExtension starExtension){
		Map<String, Object> starExtensionMap = StarExtension.getStarExtensionMap(starExtension);
		return null;
	}
}
