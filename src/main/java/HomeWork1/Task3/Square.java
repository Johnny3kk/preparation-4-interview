package HomeWork1.Task3;

import java.awt.*;

public class Square extends Shape {

    public int side;

    public Square(int x, int y, int side, Color color) {
        super(x, y, color);
        this.side = side;
    }

    @Override
    public int getWidth() {
        return side;
    }

    @Override
    public int getHeight() {
        return side;
    }

    @Override
    public void paint() {
        System.out.println("I am square");
    }

}
