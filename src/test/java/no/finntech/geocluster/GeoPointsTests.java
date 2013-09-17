package no.finntech.geocluster;

import java.io.IOException;

//import org.testng.annotations.Test;
import org.junit.Test;

import static no.finntech.geocluster.test.Places.LAS_VEGAS;
import static no.finntech.geocluster.test.Places.SAN_DIEGO;
import static no.finntech.geocluster.test.Places.BARDU;
import static no.finntech.geocluster.test.Places.BARDU_5KM_SOUTH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;

public class GeoPointsTests {

    @Test
    public void testDistanceMiles() throws IOException {
        assertThat("Distance (mi)", GeoPoints.distance(LAS_VEGAS, SAN_DIEGO, GeoDistanceUnit.MILES), closeTo(250.0, 2.0));
    }

    @Test
    public void testDistanceKilometers() throws IOException {
        assertThat("Distance (km)", GeoPoints.distance(LAS_VEGAS, SAN_DIEGO, GeoDistanceUnit.KILOMETERS), closeTo(405, 3.0));
    }

    @Test
    public void testDistanceMeters() throws IOException {
        assertThat("Distance (m)", GeoPoints.distance(LAS_VEGAS, SAN_DIEGO, GeoDistanceUnit.METERS), closeTo(405000, 3000.0));
    }

    @Test
    public void testDistanceSame() throws IOException {
        assertThat("Distance zero", GeoPoints.distance(BARDU, BARDU, GeoDistanceUnit.KILOMETERS), equalTo(0.0));
    }

    @Test
    public void testLocationOffsetBy() throws IOException {
        assertThat("Location offsetBy", GeoPoints.distance(BARDU.offsetBy(5.0, 180.0, GeoDistanceUnit.KILOMETERS), BARDU_5KM_SOUTH, GeoDistanceUnit.KILOMETERS), closeTo(0.0, 0.01));
    }
}
