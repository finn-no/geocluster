package no.finntech.geocluster;

public class GeoPoints {

    private GeoPoints() {

    }

    public static double distance(GeoPoint from, GeoPoint to, GeoDistanceUnit unit) {
        return from.distanceTo(to, unit);
    }

    public static GeoPoint copy(GeoPoint point) {
        return GeoPoint.from(point);
    }

    public static boolean equals(GeoPoint left, GeoPoint right) {
        return toString(left).equals(toString(right));
    }

    public static String toString(GeoPoint point) {
        return String.format("%.4f,%.4f", point.getLat(), point.getLon());
    }
}
