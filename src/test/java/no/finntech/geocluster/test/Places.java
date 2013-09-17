package no.finntech.geocluster.test;

import no.finntech.geocluster.GeoBoundingBox;
import no.finntech.geocluster.GeoPoint;

public interface Places {

    GeoBoundingBox COLORADO = new GeoBoundingBox(GeoPoint.fromDegrees(41.00, -109.05), GeoPoint.fromDegrees(37.00, -102.04));
    GeoBoundingBox TROMS = new GeoBoundingBox(GeoPoint.fromDegrees(70.6, 16.0), GeoPoint.fromDegrees(68.4, 21.9));

    GeoPoint DENVER = GeoPoint.fromDegrees(39.75, -104.87);
    GeoPoint LAS_VEGAS = GeoPoint.fromDegrees(36.08, -115.17);
    GeoPoint SAN_DIEGO = GeoPoint.fromDegrees(32.82, -117.13);
    GeoPoint OSLO = GeoPoint.fromDegrees(59.91, 10.75);
    GeoPoint BARDU = GeoPoint.fromDegrees(68.86175, 18.33674);
    GeoPoint BARDU_5KM_SOUTH = GeoPoint.fromDegrees(68.81678, 18.33674);
}
