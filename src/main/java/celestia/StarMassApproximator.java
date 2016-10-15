package celestia;

import java.util.HashMap;
import java.util.Map;

public class StarMassApproximator {
	

	
	interface StarMass{
		Double genStarMass(String starType);
	}

	private static Map<String, StarMass> starMassMap = new HashMap<String, StarMass>();
	static{
		starMassMap.put("sg2o",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg2b",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg2a",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg2f",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg2g",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg2k",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg2m",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg1o",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg1b",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg1a",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg1f",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg1g",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg1k",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sg1m",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g2o",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g2b",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g2a",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g2f",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g2g",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g2k",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g2m",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g1o",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g1b",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g1a",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g1f",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g1g",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g1k",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g1m",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sbgo",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sbgb",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sbga",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sbgf",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sbgg",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sbgk",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sbgm",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("o",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("b",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("a",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("f",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("k",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("m",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sdo",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sdb",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sda",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sdf",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sdg",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sdk",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("o",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("b",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("a",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("f",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("g",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("k",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("m",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sdo",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sdb",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sda",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sdf",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sdg",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sdk",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("sdm",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("do",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("dwo",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("db",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("da",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("df",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("dg",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("dk",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("dm",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("pmd",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("bs",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});
		starMassMap.put("dbs",new StarMass() {
			@Override
			public Double genStarMass(String starType) {
				return null;
			}
		});

	}
}
