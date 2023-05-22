package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JPanel implements ActionListener {
    private Font font = new Font("Arial", Font.BOLD, 30);
    private int w = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int h = Toolkit.getDefaultToolkit().getScreenSize().height;
    private boolean pause = false;
    private int countBall;
    private Ball[] ballMass;
    private int time = 0;

    public MainFrame(int countBall) {
        setLayout(null);

        setCountBall(countBall);

        ballMass = new Ball[getCountBall()];
        setBallMass(ballMass);

        Timer animation = new Timer(10, this);
        animation.start();
    }

    public void setPause(boolean pause) { this.pause = pause; }
    public void setCountBall(int countBall) { this.countBall = countBall; }
    public void setBallMass(Ball[] ballMass) {
        for (int i = 0; i < getCountBall(); i++) {
            Ball b = new Ball();
            ballMass[i] = b;
        }
    }

    public boolean getPause() { return this.pause; }
    public int getCountBall() { return this.countBall; }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // clear window
        g.clearRect(0, 0, w, h);

        // background color
        g.setColor(Color.gray);
        g.fillRect(0, 0, w, h);

        // while field
        g.setColor(Color.WHITE);
        g.fillRect(1100, 100, 350, 700);

        // green field
        g.setColor(Color.green);
        g.fillRect(100, 100, 900, 700);

        // holes
        g.setColor(Color.ORANGE);
        g.fillOval(100, 100, 60, 60); // left-up
        g.fillOval(525, 100, 60, 60); // up-center
        g.fillOval(940, 100, 60, 60); // right-up
        g.fillOval(100, 740, 60, 60); // left-down
        g.fillOval(525, 740, 60, 60); // down-center
        g.fillOval(940, 740, 60, 60); // right-down

        // txt
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString("Game Field:", 460, 90);
        g.drawString("Information:", 1180, 90);
        g.drawString("Bounds:", 1640, 90);

        // buttons
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.fillRect(1550, 100, 300, 100);
        g.fillRect(1550, 210, 300, 100);
        g.setColor(Color.BLACK);
        g.drawString("Pause", 1650, 160);
        g.drawString("Stop", 1660, 270);

        for (int i = 0; i < getCountBall(); i++) {
            g.setColor(ballMass[i].getColor());

            if (!ballMass[i].getInHole())
                g.fillOval(ballMass[i].getX(), ballMass[i].getY(), 40, 40);

            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf("№" + i + " x: " + ballMass[i].getX()), 1100, 120 + i * 20);
            g.drawString(String.valueOf("y: " + ballMass[i].getX()), 1180, 120 + i * 20);
            g.drawString(String.valueOf("xV: " + ballMass[i].get_xVelocity()), 1230, 120 + i * 20);
            g.drawString(String.valueOf("yV: " + ballMass[i].get_yVelocity()), 1280, 120 + i * 20);

            if (ballMass[i].getStopped() && !ballMass[i].getInHole()) {
                g.setColor(Color.red);
                g.drawString("STOPPED", 1330, 120 + i * 20);
            }
            if (ballMass[i].getInHole()) {
                g.setColor(Color.green);
                g.drawString("IN HOLE", 1330, 120 + i * 20);
            }
        }

        g.setColor(Color.BLACK);
        g.drawString("**********************************************************", 1100, 120 + getCountBall() * 20);
        g.drawString("Time: " + time, 1100, 130 + getCountBall() * 20);

        if (allStopped() == getCountBall()) {
            g.setColor(Color.black);
            g.drawString("Msg: ", 1100, 150 + getCountBall() * 20);
            g.setColor(Color.red);
            g.drawString("All balls stopped", 1140, 150 + getCountBall() * 20);
        }
        if (allInHole() == getCountBall()) {
            g.setColor(Color.black);
            g.drawString("Msg: ", 1100, 150 + getCountBall() * 20);
            g.setColor(Color.GREEN);
            g.drawString("All balls in hole", 1140, 150 + getCountBall() * 20);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (!pause) {
            time++;

            for (int i = 0; i < getCountBall(); i++) {
                if (!ballMass[i].getStopped() && !ballMass[i].getInHole()) {
                    ballMass[i].setX(ballMass[i].getX() + ballMass[i].get_xVelocity());
                    ballMass[i].setY(ballMass[i].getY() + ballMass[i].get_yVelocity());

                    if (ballMass[i].getX() < 110 || ballMass[i].getX() > 960)
                        ballMass[i].set_xVelocity(ballMass[i].get_xVelocity() * -1);
                    if (ballMass[i].getY() < 100 || ballMass[i].getY() > 750)
                        ballMass[i].set_yVelocity(ballMass[i].get_yVelocity() * -1);

                    if (ballMass[i].get_xVelocity() == 0)
                        ballMass[i].setStopped(true);

                    if (time % 20 == 1) {
                        if (ballMass[i].get_xVelocity() < 0)
                            ballMass[i].set_xVelocity(ballMass[i].get_xVelocity() + 1);
                        else
                            ballMass[i].set_xVelocity(ballMass[i].get_xVelocity() - 1);

                        if (ballMass[i].get_yVelocity() < 0)
                            ballMass[i].set_yVelocity(ballMass[i].get_yVelocity() + 1);
                        else
                            ballMass[i].set_yVelocity(ballMass[i].get_yVelocity() - 1);
                    }
                }
            }

            for (int i = 0; i < getCountBall(); i++) {
                if (ballMass[i].getX() <= 150 && ballMass[i].getY() <= 150) // up-left
                    ballMass[i].setInHole(true);
                if (ballMass[i].getX() >= 535 && ballMass[i].getX() <= 575 && ballMass[i].getY() <= 150) // up-center
                    ballMass[i].setInHole(true);
                if (ballMass[i].getX() >= 950 && ballMass[i].getY() <= 150) // up-right
                    ballMass[i].setInHole(true);

                if (ballMass[i].getX() <= 150 && ballMass[i].getY() >= 750) // down-left
                    ballMass[i].setInHole(true);
                if (ballMass[i].getX() >= 535 && ballMass[i].getX() <= 575 && ballMass[i].getY() >= 750) // down-center
                    ballMass[i].setInHole(true);
                if (ballMass[i].getX() >= 930 && ballMass[i].getY() >= 750)  // down-right
                    ballMass[i].setInHole(true);
            }
        }

        repaint();
    }

    public int allInHole() {
        int counter = 0;
        for (int j = 0; j < getCountBall(); j++)
            if (ballMass[j].getInHole())
                counter++;
        return counter;
    }

    public int allStopped() {
        int counter = 0;
        for (int j = 0; j < getCountBall(); j++)
            if (ballMass[j].getStopped())
                counter++;
        return counter;
    }
}
