package celestia;

import java.util.HashMap;
import java.util.Map;

import celestia.domain.ColorRGB;

public class StarColorMapping {

	public static Map<String, ColorRGB> colorMap = new HashMap<String, ColorRGB>();
	static{
		colorMap.put("blue", new ColorRGB(0.0, 0.0, 1.0));
		colorMap.put("lightblue", new ColorRGB(0.6784313725, 0.8470588235, 0.9019607843));
		colorMap.put("white", new ColorRGB(1.0, 1.0, 1.0));
		colorMap.put("yellow", new ColorRGB(1.0, 1.0, 0.0));
		colorMap.put("lightyellow", new ColorRGB(1.0, 1.0, 0.8784313725));
		colorMap.put("orange", new ColorRGB(1.0, 0.6470588235, 0.0));
		colorMap.put("red", new ColorRGB(1.0, 0.0, 0.0));
		colorMap.put("purple", new ColorRGB(0.5, 0.0, 0.5));
		colorMap.put("brown", new ColorRGB(0.6470588235, 0.1647058823, 0.1647058823));
		colorMap.put("#7E354D", new ColorRGB(0.4941176470, 0.1372549019, 0.3019607843));
	}
}
