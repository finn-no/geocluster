package no.finntech.geocluster;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GeoCluster {

    private int size;
    private GeoPoint center;
    private GeoBoundingBox bounds;
    private List<GeoPoint> points = new LinkedList<GeoPoint>();

    public GeoCluster(GeoPoint point) {
        this(1, point, new GeoBoundingBox(point));
        points.add(point);
    }

    public GeoCluster(int size, GeoPoint center, GeoBoundingBox bounds) {
        this.size = size;
        this.center = center;
        this.bounds = bounds;
    }

    public void add(GeoPoint point) {
        ++size;
        center = mean(center, size - 1, point, 1);
        bounds = bounds.extend(point);
        points.add(point);
    }

    public GeoCluster merge(GeoCluster that) {
        int size = this.size + that.size();
        GeoPoint center = mean(this.center, size - that.size(), that.center(), that.size());
        GeoBoundingBox bounds = this.bounds.extend(that.bounds());
        GeoCluster cluster = new GeoCluster(size, center, bounds);
        cluster.points.addAll(points);
        cluster.points.addAll(that.points);
        return cluster;
    }

    private static GeoPoint mean(GeoPoint left, int leftWeight, GeoPoint right, int rightWeight) {
        double lat = (left.getLat() * leftWeight + right.getLat() * rightWeight) / (leftWeight + rightWeight);
        double lon = (left.getLon() * leftWeight + right.getLon() * rightWeight) / (leftWeight + rightWeight);
        return GeoPoint.fromDegrees(lat, lon);
    }

    public List<GeoPoint> getPoints() {
        return points;
    }

    public int size() {
        return size;
    }

    public GeoPoint center() {
        return center;
    }

    public GeoBoundingBox bounds() {
        return bounds;
    }

    @Override
    public boolean equals(Object that) {
        return that instanceof GeoCluster &&
                equals((GeoCluster) that);
    }

    private boolean equals(GeoCluster that) {
        return size == that.size() &&
                GeoPoints.equals(center, that.center()) &&
                bounds.equals(that.bounds());
    }

    @Override
    public int hashCode() {
        return hashCode(size, center.toString(), bounds);
    }

    private static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", GeoPoints.toString(center), size);
    }
}
