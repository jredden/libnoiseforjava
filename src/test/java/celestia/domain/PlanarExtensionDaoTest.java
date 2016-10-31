package celestia.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zenred.util.GenName;
import com.zenred.util.GenRandomRolls;

public class PlanarExtensionDaoTest {
	
	private static Integer PLANAR_ID = GenRandomRolls.Instance().getD100000();
	private static String PLANAR_NAME = GenName.generate(10);
	private static String TEXTURE = GenName.generate(12);
	private static String NIGHT_TEXTURE = GenName.generate(13);
	private static Double SEMI_MAJOR_AXIS = GenRandomRolls.Instance().draw_rand();
	private static Double ECCENTRICITY = GenRandomRolls.Instance().draw_rand(); 
	private static Double COLOR_R = GenRandomRolls.Instance().draw_rand();
	private static Double COLOR_G = GenRandomRolls.Instance().draw_rand();
	private static Double COLOR_B = GenRandomRolls.Instance().draw_rand();
	private static String SSPECULAR_TEXTURE = GenName.generate(20);
	private static Integer SPECULAR_POWER = GenRandomRolls.Instance().get_D10();
	private static Double HAZE_COLOR_R = GenRandomRolls.Instance().draw_rand();
	private static Double HAZE_COLOR_G = GenRandomRolls.Instance().draw_rand();
	private static Double HAZE_COLOR_B = GenRandomRolls.Instance().draw_rand();
	private static Integer HAZE_DENSITY = GenRandomRolls.Instance().getD100();
	private static Double OBLATENESS = GenRandomRolls.Instance().draw_rand();
	private static Integer ATMOSPHERE_HEIGHT = GenRandomRolls.Instance().getD100();
	private static Double LOWER_COLOR_R = GenRandomRolls.Instance().draw_rand();
	private static Double LOWER_COLOR_G = GenRandomRolls.Instance().draw_rand();
	private static Double LOWER_COLOR_B = GenRandomRolls.Instance().draw_rand();
	private static Double UPPER_COLOR_R = GenRandomRolls.Instance().draw_rand();
	private static Double UPPER_COLOR_G = GenRandomRolls.Instance().draw_rand();
	private static Double UPPER_COLOR_B = GenRandomRolls.Instance().draw_rand();
	private static Double SKY_COLOR_R = GenRandomRolls.Instance().draw_rand();
	private static Double SKY_COLOR_G = GenRandomRolls.Instance().draw_rand();
	private static Double SKY_COLOR_B = GenRandomRolls.Instance().draw_rand();
	private static Integer CLOUD_HEIGHT = GenRandomRolls.Instance().getD100();
	private static Integer CLOUD_SPEED = GenRandomRolls.Instance().getD100();
	private static String CLOUD_MAP = GenName.generate(18);
	private static Double ORBIT_PERIOD = GenRandomRolls.Instance().draw_rand();
	private static Double ORBIT_SEMI_MAJOR_AXIS  = GenRandomRolls.Instance().draw_rand();
	private static Double ORBIT_ECCENTRICITY = GenRandomRolls.Instance().draw_rand();
	private static Double ORBIT_INCLINATION = GenRandomRolls.Instance().draw_rand();
	private static Double ORBIT_LONG_OF_PERICENTRE = GenRandomRolls.Instance().draw_rand();
	private static Double ORBIT_OF_MEAN_LONGITUDE = GenRandomRolls.Instance().draw_rand();
	private static Double OBLIQUITY = GenRandomRolls.Instance().draw_rand();
	private static Double ROTATION_PERIOD = GenRandomRolls.Instance().draw_rand();
	private static Double ALBEDO = GenRandomRolls.Instance().draw_rand();
	
	
	@Test
	public void test() {
	}

}
