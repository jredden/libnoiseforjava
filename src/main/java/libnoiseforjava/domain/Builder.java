package libnoiseforjava.domain;

import libnoiseforjava.exception.ExceptionInvalidParam;
import libnoiseforjava.util.ImageCafe;
import libnoiseforjava.util.NoiseMap;
import libnoiseforjava.util.NoiseMapBuilderPlane;
import libnoiseforjava.util.RendererImage;


public class Builder {

	/**
	 * 
	 * @param width
	 * @param height
	 * @return NoiseMap
	 * @throws ExceptionInvalidParam
	 */
	public static NoiseMap createNoiseMap(Integer width, Integer height) throws ExceptionInvalidParam{
		return new NoiseMap(width, height);
	}
	
	/**
	 * 
	 * @param builderPlaneParameters
	 * @return NoiseMapBuilderPlane
	 * @throws ExceptionInvalidParam
	 */
	public static NoiseMapBuilderPlane builderPlane(
			BuilderPlaneParameters builderPlaneParameters)
			throws ExceptionInvalidParam {
		// create Builder object
		NoiseMapBuilderPlane noiseMapBuilderPlane = new NoiseMapBuilderPlane();
		noiseMapBuilderPlane
				.setSourceModule(builderPlaneParameters.getPerlin());
		noiseMapBuilderPlane.setDestNoiseMap(builderPlaneParameters
				.getNoiseMap());
		noiseMapBuilderPlane.setDestSize(
				builderPlaneParameters.getDestHeight(),
				builderPlaneParameters.getDestWidth());
		noiseMapBuilderPlane.setBounds(builderPlaneParameters.getLowerXBound(),
				builderPlaneParameters.getUpperXBound(),
				builderPlaneParameters.getLowerZBound(),
				builderPlaneParameters.getUpperZBound());
		noiseMapBuilderPlane.build();
		return noiseMapBuilderPlane;
	}
	
	/**
	 * 
	 * @param renderImageParameter
	 * @return RendereImage
	 * @throws ExceptionInvalidParam
	 */
	public static ImageCafe buildRendererImage(
			RenderImageParameter renderImageParameter)
			throws ExceptionInvalidParam {
		RendererImage renderer = new RendererImage();
		renderer.clearGradient();
		for (GradientPointParameter gradientPointParameter : renderImageParameter
				.getGradientPointList()) {
			renderer.addGradientPoint(
					gradientPointParameter.getGradientPosition(),
					gradientPointParameter.getColorCafe());
		}
		ImageCafe imageCafe = new ImageCafe(renderImageParameter.getNoiseMap()
				.getHeight(), renderImageParameter.getNoiseMap().getWidth());
		renderer.setSourceNoiseMap(renderImageParameter.getNoiseMap());

		renderer.setDestImage(imageCafe);
		renderer.enableLight(renderImageParameter.getLightEnable());
		renderer.setLightContrast(renderImageParameter.getLightContrast());
		renderer.setLightBrightness(renderImageParameter.getLightBrightness());
		renderer.render();

		return imageCafe;
	}

}
