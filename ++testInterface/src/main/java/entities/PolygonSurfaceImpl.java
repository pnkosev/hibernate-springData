package entities;

import interfaces.Surface;

public abstract class PolygonSurfaceImpl implements Surface {
    protected double sideA;
    protected double sideB;

    protected PolygonSurfaceImpl(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public double calculateArea() {
        return this.sideA * this.sideB;
    }
}
