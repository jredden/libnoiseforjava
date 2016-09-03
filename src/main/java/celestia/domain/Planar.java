package celestia.domain;

public class Planar {
	/*
	 * Open GL colors ... take HTML colors and divide by 255
	 */
	public class OGL_Color{
		Float rOfRGB;
		Float gOfRGB;
		Float bOfRGB;
	}
	
	private String planarClass;
	private String texture;
	private String nightTexture;
	private OGL_Color color;
	private String specularTexture;
	private Integer specularPower;
	private OGL_Color hazeColor;
	private Float hazeDensity;
	private Double radius;

}
