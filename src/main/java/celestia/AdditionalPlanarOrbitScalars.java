package celestia;

import com.zenred.util.GenRandomRolls;

/**
 * could be fun making these more meaningful later ...
 * 
 * @author jredden
 *
 */

public class AdditionalPlanarOrbitScalars {
	
	private static Double aNumberOutOfTheEther = 0.2;
	private static Double anotherNumberOutOfTheEther = 40.0;
	private static Double yetAnotherNumberOutOfTheEther = 360.0;
	
	public static Double genEccentricity(){
		Double eccentricity = GenRandomRolls.Instance().draw_rand() * aNumberOutOfTheEther;
		return eccentricity;
	}

	public static Double genInclination(){
		Double inclination = GenRandomRolls.Instance().draw_rand() * anotherNumberOutOfTheEther;
		return inclination;
	}
	
	public static Double genLongOfPericentre(){
		Double longOfPericentre = GenRandomRolls.Instance().draw_rand() * yetAnotherNumberOutOfTheEther;
		return longOfPericentre;
	}
}
