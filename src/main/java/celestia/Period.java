package celestia;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zenred.util.GenRandomRolls;

/**
 * defines the stars period in the cluster around the barycentre in terran years
 * 
 * @author jredden
 *
 */

public class Period {
	/**
	 * instead of a continuous curve, use an integral of step increments
	 */
	private static Map<Double, Double> periodSteps = new HashMap<Double, Double>();
	static{
		periodSteps.put(new Double(0.0), new Double(1000.0));
		periodSteps.put(Math.pow(10, 6)*5.0, new Double(2000.0));
		periodSteps.put(Math.pow(10, 6)*7.5, new Double(3000.0));
		periodSteps.put(Math.pow(10, 7), new Double(5000.0));
		periodSteps.put(Math.pow(10, 7)*5.0, new Double(8000.0));
		periodSteps.put(Math.pow(10, 7)*7.5, new Double(13000.0));
		periodSteps.put(Math.pow(10, 8), new Double(21000.0));
		periodSteps.put(Double.MAX_VALUE, Double.MAX_VALUE);
	}

	/**
	 * 
	 * @param smallStep
	 * @param largeStep
	 * @return period
	 */
	private static Double genPeriod(Double smallStep, Double largeStep){
		
		Double period = smallStep  + (GenRandomRolls.Instance().draw_rand()*(largeStep-smallStep));
		return period;
	}
	
	/**
	 * 
	 * @param distanceToCentre
	 * @return stars period in years
	 */
	public static Double build(Double distanceToCentre){
		Set<Double> steps = periodSteps.keySet();
		
		Double nStep = 0.0;
		Double nStepPlusOne = 0.0;
		Double period = 0.0;
		
		Iterator<Double> iter = steps.iterator();
		nStep = iter.next();
		nStepPlusOne = iter.next();
		while(iter.hasNext()){
			if(distanceToCentre >= nStep && distanceToCentre <= nStepPlusOne){
				Double smallStep = periodSteps.get(nStep);
				Double largeStep = periodSteps.get(nStepPlusOne);
				period = genPeriod(smallStep, largeStep);
				break;
			}
			nStep = nStepPlusOne;
			nStepPlusOne = iter.next();
		}
		if(0.0 == period){
			throw new RuntimeException("period cannot be generated:" + distanceToCentre);
		}
		return period;
	}
}
