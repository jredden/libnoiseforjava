package celestia;

public class RATest {
	
	private static Double[] virtEarth = new Double[2];
	static{
		virtEarth[0] = new Double(5000);	// u
		virtEarth[1] = new Double(5000);	// v
	}
	
	private static Double[] someDimensions = new Double[6];
	static{
		someDimensions[0] = new Double(3040);	// u = 33040
		someDimensions[1] = new Double(71899);	// v = 71899
		someDimensions[2] = new Double(75772);	// u = 75772
		someDimensions[3] = new Double(89143);	// v = 89143
		someDimensions[4] = new Double(28987);	// u = 28987
		someDimensions[5] = new Double(3414);	// v = 3414
	}
	
	private static Double[] someVectors = new Double[6];
	static{
		someVectors[0] = someDimensions[0] - virtEarth[0];	// u
		someVectors[1] = someDimensions[1] - virtEarth[1];	// v
		someVectors[2] = someDimensions[2] - virtEarth[0];	// u
		someVectors[3] = someDimensions[3] - virtEarth[1];	// v
		someVectors[4] = someDimensions[4] - virtEarth[0];	// u
		someVectors[5] = someDimensions[5] - virtEarth[1];	// v
		
	}
	
	private static Double checkForNegativeDegree(Double degree){
		if(degree < 0.0){
			degree += 360.0;
		}
		return degree;
	}

	public static void main(String[] args) {
		Double tn0 = Math.atan2(someVectors[1], someVectors[0]);
		Double tn1 = Math.atan2(someVectors[3], someVectors[2]);
		Double tn2 = Math.atan2(someVectors[5], someVectors[4]);
		
		Double d0 = Math.toDegrees(tn0);
		Double d1 = Math.toDegrees(tn1);
		Double d2 = Math.toDegrees(tn2);
		
		// d2 += 360.0;   negative degrees
		d0 = checkForNegativeDegree(d0);
		d1 = checkForNegativeDegree(d1);
		d2 = checkForNegativeDegree(d2);
		
		
		System.out.println("vectU:" + someVectors[0] + ":VectV:" + someVectors[1] + ":tn0:" + tn0 + ":d0:" + d0 + ":");
		System.out.println("vectU:" + someVectors[2] + ":VectV:" + someVectors[3] + ":tn1:" + tn1 + ":d1:" + d1 + ":");
		System.out.println("vectU:" + someVectors[4] + ":VectV:" + someVectors[5] + ":tn2:" + tn2 + ":d2:" + d2 + ":");
		
		Double declination = Declension.determineQuadrant(d0);
		System.out.println("d0:"+d0+" declenation:" + declination);
		
		declination = Declension.determineQuadrant(d1);
		System.out.println("d1:"+d1+" declenation:" + declination);
		
		declination = Declension.determineQuadrant(d2);
		System.out.println("d2:"+d2+" declenation:" + declination);

		
	}

}
