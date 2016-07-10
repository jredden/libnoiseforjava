package example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.TerranPlanar;
import libnoiseforjava.persistence.Output;
import libnoiseforjava.util.ImageCafe;

public class TerranPlanetTest {

	@Test
	public void basicTerranTest() {
		TerranPlanar terranPlanar = new TerranPlanar();
		ImageCafe  imageCafe = terranPlanar.build();
		String uri = "images/" + Math.random()
		+ "TerranPlanarTest0.png";
		Output.writer(imageCafe, uri);

	}

}
