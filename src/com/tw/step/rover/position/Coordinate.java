package com.tw.step.rover.position;

import java.util.Objects;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean isWithin(Coordinate bottomLeft, Coordinate topRight) {
        return this.x >= bottomLeft.x && this.x <= topRight.x && this.y >= bottomLeft.y && this.y <= topRight.y;
    }

    public Coordinate add(Coordinate other) {
        return new Coordinate(this.x + other.x, this.y + other.y);
    }
}
