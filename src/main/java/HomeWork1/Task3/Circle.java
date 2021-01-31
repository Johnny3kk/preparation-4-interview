package HomeWork1.Task3;

import java.awt.*;

public class Circle extends Shape {

    public int radius;

    public Circle(int x, int y, int radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public int getWidth() {
        return radius * 2;
    }

    @Override
    public int getHeight() {
        return radius * 2;
    }

    @Override
    public void paint() {
        System.out.println("I am a circle");
    }

}
