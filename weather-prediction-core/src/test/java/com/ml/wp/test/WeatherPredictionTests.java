package com.ml.wp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.ml.wp.Application;
import com.ml.wp.model.Coordinates;
import com.ml.wp.model.Galaxy;
import com.ml.wp.model.PredictionResult;
import com.ml.wp.service.GalaxyService;
import com.ml.wp.util.Coordinates2DHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class WeatherPredictionTests {

	@Autowired
	private GalaxyService galaxyService;

	@Autowired
	private Galaxy defaultGalaxy;

	@Test
	public void contextLoads() throws Exception {
		assertNotNull(defaultGalaxy);
		assertNotNull(galaxyService);
	}

	@Test
	public void isCoordinateInsideTriangle() {
		try {
			Coordinates t1 = new Coordinates(0.00, 100.0);
			Coordinates t2 = new Coordinates(-50.00, -100.0);
			Coordinates t3 = new Coordinates(50.00, -100.0);
			Coordinates x = new Coordinates(0.00, 0.00);
			assertTrue(Coordinates2DHelper.isCoordinateInsideTriangle(t1, t2, t3, x));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void calculateTrianglePerimeter() {
		try {
			Coordinates t1 = new Coordinates(7.0, 7.0);
			Coordinates t2 = new Coordinates(-3.0, -5.0);
			Coordinates t3 = new Coordinates(-8.0, 7.0);
			assertEquals(new Double(43.6205), Coordinates2DHelper.getTrianglePerimeter(t1, t2, t3));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void areAlignButNotWithTheSun() {
		try {
			Coordinates t1 = new Coordinates(1.0, -2.0);
			Coordinates t2 = new Coordinates(-2.0, 1.0);
			Coordinates t3 = new Coordinates(2.0, -3.0);
			Coordinates sun = new Coordinates(0.0, 0.0);
			if (Coordinates2DHelper.areAligned(t1, t2, t3)) {
				assertFalse(Coordinates2DHelper.areAligned(t1, t2, sun));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void areAlignWithTheSun() {
		try {
			Coordinates t1 = new Coordinates(1.0, 1.0);
			Coordinates t2 = new Coordinates(10.0, 10.0);
			Coordinates t3 = new Coordinates(20.0, 20.0);
			Coordinates sun = new Coordinates(0.0, 0.0);
			if (Coordinates2DHelper.areAligned(t1, t2, t3)) {
				assertTrue(Coordinates2DHelper.areAligned(t1, t2, sun));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void calculateWeatherPredictionIn10Years() {
		PredictionResult pr = galaxyService.calculateWeatherPredictionInYears(defaultGalaxy, 10L);
		System.out.println(pr.toString());
	}

	@Test
	public void persistWeatherPredictionIn10Years() {
		PredictionResult pr = galaxyService.calculateAndSaveWeatherPredictionInYears(defaultGalaxy, 10L, true);
		System.out.println(pr.toString());
	}

}
