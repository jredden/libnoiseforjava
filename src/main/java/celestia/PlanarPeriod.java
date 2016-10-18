package celestia;

import org.apache.log4j.Logger;

import com.zenred.cosmos.domain.AstronomicalUnits;
import com.zenred.cosmos.domain.Star;
import com.zenred.cosmos.domain.UnifiedPlanetoidI;

public class PlanarPeriod {
	
	static private Logger logger = Logger.getLogger(PlanarPeriod.class);
	
	static private final Double MAGIC_SCALAR = 320.0;	// derived by gosh and by golly
	
	/**
	 * 2.0 and 3.0 are powers using grav equations
	 * 
	 * @param star
	 * @param unifiedPlanetoidI
	 * @return period of the planar
	 */
	public static Double build(Star star, UnifiedPlanetoidI unifiedPlanetoidI){
		
		Double starMass = StarMassApproximator.genStarMass(star.getStar_type());
		Double period = (Math.PI * 2.0
				* Math.sqrt((Math.pow(starMass * AstronomicalUnits.AstronomicalUnit, 3.0)
						/ (unifiedPlanetoidI.getPlanetoid().getDistanceToPrimary() * AstronomicalUnits.SOLAR_MASS))))
				/ MAGIC_SCALAR;
		logger.info("period:"+period);

		return period;
	}

}
