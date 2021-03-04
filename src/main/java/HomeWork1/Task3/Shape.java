package HomeWork1.Task3;

import java.awt.*;

abstract class Shape {

    public int x;
    public int y;
    public Color color;

    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public void paint() {
        System.out.println("What a shape!");
    }
}
