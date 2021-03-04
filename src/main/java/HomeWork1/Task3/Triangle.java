package HomeWork1.Task3;

import java.awt.*;

public class Triangle extends Shape {

    public int a;
    public int b;
    public int c;
    public int corAB;
    public int corAC;
    public int corBC;

    public Triangle(int x, int y, int a, int b, int c, int corAB, int corAC, int corBC, Color color) {
        super(x, y, color);
        this.a = a;
        this.b = b;
        this.c = c;
        this.corAB = corAB;
        this.corAC = corAC;
        this.corBC = corBC;
    }

    @Override
    public int getWidth() {
        int width = a;
        if (width < b) width = b;
        if (width < c) width = c;
        return width;
    }

    @Override
    public int getHeight() {
        int height;
        int min = a;
        height = b * (int)Math.sin(Math.toRadians(corAB));

        if (min > b) {
            min = b;
            height = a * (int)Math.sin(Math.toRadians(corAB));
        }

        if (min > c) {
            min = c;
            height = a * (int)Math.sin(Math.toRadians(corAC));
        }

        return height;
    }

    @Override
    public void paint() {
        System.out.println("I am triangle");
    }
}
