package app;

import components.TerranPlanar;
import libnoiseforjava.persistence.Output;
import libnoiseforjava.util.ImageCafe;

public class TerranPlanarApp {
	
	public void basicTerran() {
		TerranPlanar terranPlanar = new TerranPlanar();
		ImageCafe  imageCafe = terranPlanar.build(Boolean.TRUE);
		String uri = "images/" + Math.random()
		+ "TerranPlanarApp0.png";
		Output.writer(imageCafe, uri);

		imageCafe = terranPlanar.build(Boolean.FALSE);
		uri = "images/" + Math.random()
		+ "TerranPlanarApp00.png";
		Output.writer(imageCafe, uri);

	}


	public static void main(String[] args) {
		TerranPlanarApp terranPlanarApp = new TerranPlanarApp();
		terranPlanarApp.basicTerran();

	}

}
