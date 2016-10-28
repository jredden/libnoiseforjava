package celestia.domain;

import java.util.Map;

import com.zenred.johntredden.domain.AbstractJDBCDao;

public class PlanarExtensionDao extends AbstractJDBCDao {
	
	public static final String PLANET_EXTENSION = "PlanetExtension";

	public static final String PLANETOIDEXTENSIONID = "planetoidExtensionId";
	public static final String PLANETOIDID = "planetoidId";
	public static final String PLANETOIDNAME = "planetoidName";
	public static final String TEXTURE = "texture";
	public static final String NIGHTTEXTURE = "nightTexture";
	public static final String SEMIMAJORAXIS = "semiMajorAxis";
	public static final String ECCENTRICITY = "eccentricity";
	public static final String COLORR = "colorR";
	public static final String COLORG = "colorG";
	public static final String COLORB = "colorB";
	public static final String SPECULARTEXTURE = "specularTexture";
	public static final String SPECULARPOWER = "specularPower";
	public static final String HAZECOLORR = "hazeColorR";
	public static final String HAZECOLORG = "hazeColorG";
	public static final String HAZECOLORB = "hazeColorB";
	public static final String HAZEDENSITY = "hazeDensity";
	public static final String OBLATENESS = "oblateness";
	public static final String ATMOSPHEREHEIGHT = "atmosphereHeight";
	public static final String ATMOSPHERELOWERR = "atmosphereLowerR";
	public static final String ATMOSPHERELOWERG = "atmosphereLowerG";
	public static final String ATMOSPHERELOWERB = "atmosphereLowerB";
	public static final String ATMOSPHEREUPPERR = "atmosphereUpperR";
	public static final String ATMOSPHEREUPPERG = "atmosphereUpperG";
	public static final String ATMOSPHEREUPPERB = "atmosphereUpperB";
	public static final String ATMOSPHERESKYR = "atmosphereSkyR";
	public static final String ATMOSPHERESKYG = "atmosphereSkyG";
	public static final String ATMOSPHERESKYB = "atmosphereSkyB";
	public static final String CLOUDHEIGHT = "cloudHeight";
	public static final String CLOUDSPEED = "cloudSpeed";
	public static final String CLOUDMAP = "cloudMap";
	public static final String ORBITPERIOD = "orbitPeriod";
	public static final String ORBITSEMIMAJORAXIS = "orbitSemiMajorAxis";
	public static final String ORBITECCENTRICITY = "orbitEccentricity";
	public static final String ORBITINCLINATION = "orbitInclination";
	public static final String ORBITLONGOFPERICENTRE = "orbitLongOfPeriCentre";
	public static final String ORBITMEANLONGITUDE = "orbitMeanLongitude";
	public static final String OBLIQUITY = "obliquity";
	public static final String ROTATIONPERIOD = "rotationPeriod";
	public static final String ALBEDO = "albedo";
	public static final String DATESTAMP = "datestamp";
	
	private static String lastPlanarExtensionInsertSql = "SELECT MAX("+PLANETOIDEXTENSIONID+") FROM " + PLANET_EXTENSION;
	
	// + " plex." + XXX + " "
	
	private static String readPlanarExtensionByIdSql = "SELECT "
			+ " plex." + PLANETOIDEXTENSIONID + " "
			+ " plex." + PLANETOIDID + " "
			+ " plex." + PLANETOIDNAME + " "
			+ " plex." + TEXTURE + " "
			+ " plex." + NIGHTTEXTURE + " "			
			+ " plex." + SEMIMAJORAXIS + " "
			+ " plex." + ECCENTRICITY + " "
			+ " plex." + COLORR + " "
			+ " plex." + COLORG + " "
			+ " plex." + COLORB + " "
			+ " plex." + SPECULARTEXTURE + " "
			+ " plex." + SPECULARPOWER + " "
			+ " plex." + HAZECOLORR + " "
			+ " plex." + HAZECOLORG + " "
			+ " plex." + HAZECOLORB + " "
			+ " plex." + HAZEDENSITY + " "
			+ " plex." + OBLATENESS + " "
			+ " plex." + ATMOSPHEREHEIGHT + " "
			+ " plex." + ATMOSPHERELOWERR + " "
			+ " plex." + ATMOSPHERELOWERG + " "
			+ " plex." + ATMOSPHERELOWERB + " "
			+ " plex." + ATMOSPHEREUPPERR + " "
			+ " plex." + ATMOSPHEREUPPERG + " "
			+ " plex." + ATMOSPHEREUPPERB + " "
			+ " plex." + ATMOSPHERESKYR + " "
			+ " plex." + ATMOSPHERESKYG + " "
			+ " plex." + ATMOSPHERESKYB + " "
			+ " plex." + CLOUDHEIGHT + " "
			+ " plex." + CLOUDSPEED + " "
			+ " plex." + CLOUDMAP + " "
			+ " plex." + ORBITPERIOD + " "
			+ " plex." + ORBITSEMIMAJORAXIS + " "
			+ " plex." + ORBITECCENTRICITY + " "
			+ " plex." + ORBITINCLINATION + " "
			+ " plex." + ORBITLONGOFPERICENTRE + " "
			+ " plex." + ORBITMEANLONGITUDE + " "
			+ " plex." + OBLIQUITY + " "
			+ " plex." + ROTATIONPERIOD + " "
			+ " plex." + ALBEDO + " "
			+ " plex." + DATESTAMP + " "
			+ " FROM " + PLANET_EXTENSION + " plex "
			+ " WHERE plex." + PLANETOIDEXTENSIONID + " = ? "
			;

	private static String readPlanarExtensionByPlanarNameSql = "SELECT "
			+ " plex." + PLANETOIDEXTENSIONID + " "
			+ " plex." + PLANETOIDID + " "
			+ " plex." + PLANETOIDNAME + " "
			+ " plex." + TEXTURE + " "
			+ " plex." + NIGHTTEXTURE + " "			
			+ " plex." + SEMIMAJORAXIS + " "
			+ " plex." + ECCENTRICITY + " "
			+ " plex." + COLORR + " "
			+ " plex." + COLORG + " "
			+ " plex." + COLORB + " "
			+ " plex." + SPECULARTEXTURE + " "
			+ " plex." + SPECULARPOWER + " "
			+ " plex." + HAZECOLORR + " "
			+ " plex." + HAZECOLORG + " "
			+ " plex." + HAZECOLORB + " "
			+ " plex." + HAZEDENSITY + " "
			+ " plex." + OBLATENESS + " "
			+ " plex." + ATMOSPHEREHEIGHT + " "
			+ " plex." + ATMOSPHERELOWERR + " "
			+ " plex." + ATMOSPHERELOWERG + " "
			+ " plex." + ATMOSPHERELOWERB + " "
			+ " plex." + ATMOSPHEREUPPERR + " "
			+ " plex." + ATMOSPHEREUPPERG + " "
			+ " plex." + ATMOSPHEREUPPERB + " "
			+ " plex." + ATMOSPHERESKYR + " "
			+ " plex." + ATMOSPHERESKYG + " "
			+ " plex." + ATMOSPHERESKYB + " "
			+ " plex." + CLOUDHEIGHT + " "
			+ " plex." + CLOUDSPEED + " "
			+ " plex." + CLOUDMAP + " "
			+ " plex." + ORBITPERIOD + " "
			+ " plex." + ORBITSEMIMAJORAXIS + " "
			+ " plex." + ORBITECCENTRICITY + " "
			+ " plex." + ORBITINCLINATION + " "
			+ " plex." + ORBITLONGOFPERICENTRE + " "
			+ " plex." + ORBITMEANLONGITUDE + " "
			+ " plex." + OBLIQUITY + " "
			+ " plex." + ROTATIONPERIOD + " "
			+ " plex." + ALBEDO + " "
			+ " plex." + DATESTAMP + " "
			+ " FROM " + PLANET_EXTENSION + " plex "
			+ " WHERE plex." + PLANETOIDNAME + " = ? "
			;
	
	private static String readPlanarByNameCountSql  = "SELECT COUNT(*) "
			+ " FROM " + PLANET_EXTENSION + " plex " + " WHERE plex." + PLANETOIDNAME
			+ " = ? "
			;
	
	private static String deletePlanarExtensionSql = "DELETE FROM " + PLANET_EXTENSION + " WHERE " + PLANETOIDEXTENSIONID
			+ " = ? ";	
	
	private static String updatePlanarExtensionSql = "UPDATE " + PLANET_EXTENSION + " plex SET "
			+ " plex." + PLANETOIDEXTENSIONID + " "
			+ " plex." + PLANETOIDID + " "
			+ " plex." + PLANETOIDNAME + " "
			+ " plex." + TEXTURE + " "
			+ " plex." + NIGHTTEXTURE + " "			
			+ " plex." + SEMIMAJORAXIS + " "
			+ " plex." + ECCENTRICITY + " "
			+ " plex." + COLORR + " "
			+ " plex." + COLORG + " "
			+ " plex." + COLORB + " "
			+ " plex." + SPECULARTEXTURE + " "
			+ " plex." + SPECULARPOWER + " "
			+ " plex." + HAZECOLORR + " "
			+ " plex." + HAZECOLORG + " "
			+ " plex." + HAZECOLORB + " "
			+ " plex." + HAZEDENSITY + " "
			+ " plex." + OBLATENESS + " "
			+ " plex." + ATMOSPHEREHEIGHT + " "
			+ " plex." + ATMOSPHERELOWERR + " "
			+ " plex." + ATMOSPHERELOWERG + " "
			+ " plex." + ATMOSPHERELOWERB + " "
			+ " plex." + ATMOSPHEREUPPERR + " "
			+ " plex." + ATMOSPHEREUPPERG + " "
			+ " plex." + ATMOSPHEREUPPERB + " "
			+ " plex." + ATMOSPHERESKYR + " "
			+ " plex." + ATMOSPHERESKYG + " "
			+ " plex." + ATMOSPHERESKYB + " "
			+ " plex." + CLOUDHEIGHT + " "
			+ " plex." + CLOUDSPEED + " "
			+ " plex." + CLOUDMAP + " "
			+ " plex." + ORBITPERIOD + " "
			+ " plex." + ORBITSEMIMAJORAXIS + " "
			+ " plex." + ORBITECCENTRICITY + " "
			+ " plex." + ORBITINCLINATION + " "
			+ " plex." + ORBITLONGOFPERICENTRE + " "
			+ " plex." + ORBITMEANLONGITUDE + " "
			+ " plex." + OBLIQUITY + " "
			+ " plex." + ROTATIONPERIOD + " "
			+ " plex." + ALBEDO + " "
			+ " plex." + DATESTAMP + " "
			+ " FROM " + PLANET_EXTENSION + " plex "
			+ " WHERE plex." + PLANETOIDNAME + " = ?"
		;
	
	
}
