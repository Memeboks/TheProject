package com.bot;

public class TheA {
    int length;
    double  points_x [] = new double[length];
    double  points_y [] = new double[length];

    public void setPoints_y(double [] points_y,int length) {
        for (int i = 0; i < length; i++) {
            this.points_y[i] = points_y[i];
        }
    }
    public void setPoints_x(double [] points_x,int length) {
        for (int i = 0; i < length; i++) {
            this.points_x[i] = points_x[i];
        }
    }

    public double [] getPoints_x() {
            return points_x;
    }

    public double[] getPoints_y() {
            return points_y;
        }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public TheA (int length, double  points_x [], double  points_y []) {
        this.length = length;
        this.points_x = points_x;
        this.points_y = points_y;
    }
}
