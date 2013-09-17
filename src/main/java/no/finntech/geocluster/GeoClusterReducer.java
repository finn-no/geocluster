package no.finntech.geocluster;

import java.util.LinkedList;
import java.util.List;

public class GeoClusterReducer {

    private final GeoDistanceUnit unit = GeoDistanceUnit.KILOMETERS;
    private final double factor;

    public GeoClusterReducer(double factor) {
        this.factor = factor;
    }

    public List<GeoCluster> reduce(List<GeoCluster> clusters) {
        GeoBoundingBox bounds = getBounds(clusters);
        double maxDistance = bounds != null ? factor * bounds.size(unit) : 0.0;
        List<GeoCluster> reduced = new LinkedList<GeoCluster>();
        reduced.addAll(clusters);
        REDUCE:
        while (true) {
            for (int i = 0; i < reduced.size(); ++i) {
                for (int j = i + 1; j < reduced.size(); ++j) {
                    GeoCluster a = reduced.get(i);
                    GeoCluster b = reduced.get(j);
                    if (GeoPoints.distance(a.center(), b.center(), unit) <= maxDistance) {
                        reduced.remove(a);
                        reduced.remove(b);
                        reduced.add(a.merge(b));
                        continue REDUCE;
                    }
                }
            }
            break;
        }
        return reduced;
    }

    private static GeoBoundingBox getBounds(List<GeoCluster> clusters) {
        GeoBoundingBox bounds = null;
        for (GeoCluster cluster : clusters) {
            if (bounds != null) {
                bounds = bounds.extend(cluster.bounds());
            } else {
                bounds = cluster.bounds();
            }
        }
        return bounds;
    }
}
