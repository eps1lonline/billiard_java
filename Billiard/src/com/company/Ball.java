package com.company;

import java.awt.*;
import java.util.Random;

public class Ball {
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;
    private Color color;
    private boolean stopped;
    private boolean inHole;

    public Ball() {
        this.x = new Random().nextInt(850 - 150) + 150; // [150, 850)
        this.y = new Random().nextInt(650 - 150) + 150; // [150, 650)
        this.xVelocity = new Random().nextInt(20 - 10) + 10; // [10, 20)
        this.yVelocity = this.xVelocity;
        this.color = setColor();
        this.stopped = false;
        this.inHole = false;
    }

    public Color setColor() {
        int rand = new Random().nextInt(10);
        switch(rand) {
            case 0: return Color.BLACK;
            case 1: return Color.BLUE;
            case 2: return Color.CYAN;
            case 3: return Color.DARK_GRAY;
            case 4: return Color.GRAY;
            case 5: return Color.LIGHT_GRAY;
            case 6: return Color.MAGENTA;
            case 7: return Color.ORANGE;
            case 8: return Color.PINK;
            case 9: return Color.RED;
            case 10: return Color.WHITE;
            default: return Color.YELLOW;
        }
    }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void set_xVelocity(int xVelocity) { this.xVelocity = xVelocity; }
    public void set_yVelocity(int yVelocity) { this.yVelocity = yVelocity; }
    public void setStopped(boolean stopped) { this.stopped = stopped; }
    public void setInHole(boolean inHole) { this.inHole = inHole; }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public int get_xVelocity() { return this.xVelocity; }
    public int get_yVelocity() { return this.yVelocity; }
    public Color getColor() { return this.color; }
    public boolean getStopped() { return this.stopped; }
    public boolean getInHole() { return inHole; }
}
