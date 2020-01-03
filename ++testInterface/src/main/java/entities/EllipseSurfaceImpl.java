package entities;

import interfaces.Surface;

public abstract class EllipseSurfaceImpl implements Surface {
    protected double radius1;
    protected double radius2;

    protected EllipseSurfaceImpl(double radius1, double radius2) {
        this.radius1 = radius1;
        this.radius2 = radius2;
    }

    public double calculateArea() {
        return Math.PI * this.radius1 * this.radius2;
    }
}
