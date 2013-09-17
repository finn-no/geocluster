package no.finntech.geocluster;

public enum GeoDistanceUnit {
    METERS(1000.0),
    MILES(1 / 1.60934),
    KILOMETERS(1.0);

    private final double factor;

    GeoDistanceUnit(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}
