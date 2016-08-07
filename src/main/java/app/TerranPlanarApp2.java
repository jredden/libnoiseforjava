package app;

import components.TerranPlanar;
import libnoiseforjava.persistence.Output;
import libnoiseforjava.util.ImageCafe;

public class TerranPlanarApp2 {
	
	public void basicTerran() {
		TerranPlanar terranPlanar = new TerranPlanar();

		ImageCafe imageCafe = terranPlanar.build(Boolean.TRUE);
		String uri = "images/" + Math.random()
		+ "TerranPlanarApp2.png";
		Output.writer(imageCafe, uri);

	}


	public static void main(String[] args) {
		TerranPlanarApp2 terranPlanarApp = new TerranPlanarApp2();
		terranPlanarApp.basicTerran();

	}

}
