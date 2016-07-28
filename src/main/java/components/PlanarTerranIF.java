package components;

import java.util.ArrayList;
import java.util.List;

import libnoiseforjava.domain.ControlPoint;
import libnoiseforjava.domain.GradientPointParameter;
import libnoiseforjava.util.ColorCafe;

public interface PlanarTerranIF {

	// surface map
	static List<GradientPointParameter> gradientPointList = new ArrayList<GradientPointParameter>();
	static List<ControlPoint> controlPoints = new ArrayList<ControlPoint>();
	static List<ControlPoint> badLandCLiffsControlPoints = new ArrayList<ControlPoint>();
	static List<Double> badLandsTerraceControlPoints = new ArrayList<Double>();
	static List<ControlPoint> riverPositionControlPoints0 = new ArrayList<ControlPoint>();
	static List<ControlPoint> riverPositionControlPoints1 = new ArrayList<ControlPoint>();
	static List<ControlPoint> continentsMountainsControlPoints = new ArrayList<ControlPoint>();

	abstract class AbstractPlanarTerran {

		static protected Double deep_sea_level;
		static protected Double seaLevelInMeters;
		static protected Double mountains_amount;
		static {
			GradientPointParameter gradientPointParameter = new GradientPointParameter(-16384.0 + seaLevelInMeters,
					new ColorCafe(3, 29, 63, 255));
			gradientPointList.add(gradientPointParameter);
			gradientPointParameter = new GradientPointParameter(deep_sea_level + seaLevelInMeters,
					new ColorCafe(3, 29, 63, 255));
			gradientPointList.add(gradientPointParameter);
			gradientPointParameter = new GradientPointParameter(-1.0 + seaLevelInMeters,
					new ColorCafe(7, 106, 127, 255));
			gradientPointList.add(gradientPointParameter);
			gradientPointParameter = new GradientPointParameter(0.0 + seaLevelInMeters, new ColorCafe(62, 86, 30, 255));
			gradientPointList.add(gradientPointParameter);
			gradientPointParameter = new GradientPointParameter(1024.0 + seaLevelInMeters,
					new ColorCafe(84, 96, 50, 255));
			gradientPointList.add(gradientPointParameter);
			gradientPointParameter = new GradientPointParameter(2048.0 + seaLevelInMeters,
					new ColorCafe(130, 127, 97, 255));
			gradientPointList.add(gradientPointParameter);
			gradientPointParameter = new GradientPointParameter(3072.0 + seaLevelInMeters,
					new ColorCafe(184, 163, 141, 255));
			gradientPointList.add(gradientPointParameter);
			gradientPointParameter = new GradientPointParameter(4096.0 + seaLevelInMeters,
					new ColorCafe(255, 255, 255, 255));
			gradientPointList.add(gradientPointParameter);
			gradientPointParameter = new GradientPointParameter(6144.0 + seaLevelInMeters,
					new ColorCafe(128, 255, 255, 255));
			gradientPointList.add(gradientPointParameter);
			gradientPointParameter = new GradientPointParameter(16384.0 + seaLevelInMeters,
					new ColorCafe(0, 0, 255, 255));
			gradientPointList.add(gradientPointParameter);

		}

		static {
			ControlPoint controlPoint = new ControlPoint();
			controlPoint.inputValue = -2.0000;
			controlPoint.outputValue = -1.625;
			controlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = -1.0000;
			controlPoint.outputValue = -1.375;
			controlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 0.0000;
			controlPoint.outputValue = -0.375;
			controlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 0.0625;
			controlPoint.outputValue = 0.125;
			controlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 0.1250;
			controlPoint.outputValue = 0.250;
			controlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 0.2500;
			controlPoint.outputValue = 1.000;
			controlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 0.5000;
			controlPoint.outputValue = 0.2500;
			controlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 0.7500;
			controlPoint.outputValue = 0.2500;
			controlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 1.0000;
			controlPoint.outputValue = 0.500;
			controlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 2.0000;
			controlPoint.outputValue = 0.500;
			controlPoints.add(controlPoint);

		}

		static {
			ControlPoint controlPoint = new ControlPoint();
			controlPoint.inputValue = -2.0000;
			controlPoint.outputValue = -2.0000;
			badLandCLiffsControlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = -1.0000;
			controlPoint.outputValue = -1.2500;
			badLandCLiffsControlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = -0.0000;
			controlPoint.outputValue = -0.7500;
			badLandCLiffsControlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 0.5000;
			controlPoint.outputValue = -0.2500;
			badLandCLiffsControlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 0.6250;
			controlPoint.outputValue = 0.8750;
			badLandCLiffsControlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 0.7500;
			controlPoint.outputValue = 1.0000;
			badLandCLiffsControlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 2.0000;
			controlPoint.outputValue = 1.2500;
			badLandCLiffsControlPoints.add(controlPoint);
		}

		static {
			badLandsTerraceControlPoints.add(-1.0000);
			badLandsTerraceControlPoints.add(-0.8750);
			badLandsTerraceControlPoints.add(-0.7500);
			badLandsTerraceControlPoints.add(-0.5000);
			badLandsTerraceControlPoints.add(1.0000);
		}

		static {
			ControlPoint controlPoint = new ControlPoint();
			controlPoint.inputValue = -2.0000;
			controlPoint.outputValue = 2.0000;
			riverPositionControlPoints0.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = -1.0000;
			controlPoint.outputValue = 1.0000;
			riverPositionControlPoints0.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = -0.1250;
			controlPoint.outputValue = 0.785;
			riverPositionControlPoints0.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 0.0000;
			controlPoint.outputValue = 1.0000;
			riverPositionControlPoints0.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 1.0000;
			controlPoint.outputValue = -1.5000;
			riverPositionControlPoints0.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 2.0000;
			controlPoint.outputValue = -2.0000;
			riverPositionControlPoints0.add(controlPoint);
		}

		static {
			ControlPoint controlPoint = new ControlPoint();
			controlPoint.inputValue = -2.0000;
			controlPoint.outputValue = 2.0000;
			riverPositionControlPoints1.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = -1.0000;
			controlPoint.outputValue = 1.5000;
			riverPositionControlPoints1.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = -0.1250;
			controlPoint.outputValue = 0.4375;
			riverPositionControlPoints1.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 0.0000;
			controlPoint.outputValue = 0.5000;
			riverPositionControlPoints1.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 1.0000;
			controlPoint.outputValue = 0.2500;
			riverPositionControlPoints1.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 2.0000;
			controlPoint.outputValue = 0.0000;
			riverPositionControlPoints1.add(controlPoint);
		}

		static {
			ControlPoint controlPoint = new ControlPoint();
			controlPoint.inputValue = -1.0000;
			controlPoint.outputValue = -0.0625;
			continentsMountainsControlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 0.0000;
			controlPoint.outputValue = 0.0000;
			continentsMountainsControlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 1.0 - mountains_amount;
			controlPoint.outputValue = 0.0625;
			continentsMountainsControlPoints.add(controlPoint);
			controlPoint = new ControlPoint();
			controlPoint.inputValue = 1.0000;
			controlPoint.outputValue = 0.2500;
			continentsMountainsControlPoints.add(controlPoint);
		}

	}

}
