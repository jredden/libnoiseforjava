package celestia;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zenred.cosmos.domain.Star;
import com.zenred.cosmos.domain.UnifiedPlanetoidI;
import com.zenred.util.GenRandomRolls;

/**
 * 
 * generates specular scalar for celestia.  The complexity of clouds and atmosphere are not
 * accounted for
 * 
 * @author jredden
 *
 */

public class PlanarSpecular {
	
	interface Luminosity{
		Integer genSpecularLumenosityScalar();
	}
	
	interface Distance{
		Integer genSPecualrDistanceScalar();
	}

	private static Map<Double, Luminosity> luminosityMap = new HashMap<Double, PlanarSpecular.Luminosity>();
	static{
		luminosityMap.put(new Double(0.0), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 1+GenRandomRolls.Instance().get_D2();
			}
		});
		luminosityMap.put(new Double(0.00005), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 1+GenRandomRolls.Instance().get_D4();
			}
		});
		luminosityMap.put(new Double(0.005), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 5+GenRandomRolls.Instance().get_D6();
			}
		});
		luminosityMap.put(new Double(0.05), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 10+GenRandomRolls.Instance().get_D8();
			}
		});
		luminosityMap.put(new Double(0.5), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 10+GenRandomRolls.Instance().get_D10();
			}
		});
		luminosityMap.put(new Double(1.0), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 15+GenRandomRolls.Instance().get_D18();
			}
		});
		luminosityMap.put(new Double(5.0), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 20+GenRandomRolls.Instance().getDX(20);
			}
		});
		luminosityMap.put(new Double(10.0), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 25+GenRandomRolls.Instance().getDX(20);
			}
		});
		luminosityMap.put(new Double(25.0), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 30+GenRandomRolls.Instance().getDX(20);
			}
		});
		luminosityMap.put(new Double(100.0), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 40+GenRandomRolls.Instance().getDX(20);
			}
		});
		luminosityMap.put(new Double(500.0), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 50+GenRandomRolls.Instance().getDX(20);
			}
		});
		luminosityMap.put(new Double(1000.0), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 60+GenRandomRolls.Instance().getDX(20);
			}
		});
		luminosityMap.put(new Double(10000.0), new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 70+GenRandomRolls.Instance().getDX(30);
			}
		});
		luminosityMap.put(Double.MAX_VALUE, new Luminosity() {
			@Override
			public Integer genSpecularLumenosityScalar() {
				return 100;
			}
		});
	}
	
	/**
	 * 
	 * @param star
	 * @param unifiedPlanetoidI
	 * @return adjusted specular power of the planet
	 */
	public static Integer build(Star star, UnifiedPlanetoidI unifiedPlanetoidI){
		
		Set<Double> luminosityKeys = luminosityMap.keySet();
		Integer luminosityScalar = null;
		Iterator<Double> luminosityIterator = luminosityKeys.iterator();
		Double currentLuminosityKey = luminosityIterator.next();
		while(luminosityIterator.hasNext()){
			Double nextLuminosityKey = luminosityIterator.next();
			if(star.getLuminosity() > currentLuminosityKey && nextLuminosityKey <= nextLuminosityKey){
				luminosityScalar = luminosityMap.get(nextLuminosityKey).genSpecularLumenosityScalar();
				break;
			}
			currentLuminosityKey = nextLuminosityKey;
		}
		return null;
	}
}
