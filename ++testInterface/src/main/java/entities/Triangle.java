package entities;

public class Triangle extends PolygonSurfaceImpl {
    public Triangle(double sideA, double sideB) {
        super(sideA, sideB / 2);
    }
}
