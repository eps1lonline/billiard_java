package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {
    private Font font = new Font("Arial", Font.BOLD, 50);
    private int w = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int h = Toolkit.getDefaultToolkit().getScreenSize().height;
    Thread thr;

    public StartFrame() {
        thr = new Thread();
        thr.start();

        JFrame startFrame = new JFrame("Billiard");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setBounds(0, 0, w, h);
        startFrame.setVisible(true);

        // label EnterNumberOfBalls ***************************************************
        JLabel lNumberOfBalls = new JLabel("Enter number of balls:");
        lNumberOfBalls.setFont(font);
        lNumberOfBalls.setBounds(w / 2 - 225, h / 2 - 100, 550, 50);

        // textField EnterNumberOfBalls ***********************************************
        JTextField tNumberOfBalls = new JTextField("");
        tNumberOfBalls.setFont(font);
        tNumberOfBalls.setBackground(Color.white);
        tNumberOfBalls.setBounds(w / 2 - 120, h / 2 - 20, 300, 100);

        // button Start ***************************************************************
        JButton bStart = new JButton("Start");
        bStart.setFont(font);
        bStart.setBackground(Color.white);
        bStart.setBounds(w / 2 - 120, h / 2 + 100, 300, 100);

        // Handling a button click ****************************************************
        bStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isDigit(tNumberOfBalls.getText()) && tNumberOfBalls.getText().length() != 0 && Integer.parseInt(tNumberOfBalls.getText()) <= 30) { // Check for number input + checking for empty input
                    startFrame.setVisible(false);

                    JFrame mainFrame = new JFrame("Бильярд");
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mainFrame.setBounds(0, 0, w, h);
                    mainFrame.setVisible(true);

                    // button Pause **************************************************************
                    JButton bPause = new JButton("");
                    bPause.setFont(font);
                    bPause.setBackground(Color.white);
                    bPause.setBounds(1550, 100, 300, 100);

                    // button Stop ***************************************************************
                    JButton bStop = new JButton("");
                    bStop.setFont(font);
                    bStop.setBackground(Color.white);
                    bStop.setBounds(1550, 210, 300, 100);

                    mainFrame.add(bPause);
                    mainFrame.add(bStop);

                    MainFrame mf = new MainFrame(Integer.parseInt(tNumberOfBalls.getText()));
                    mainFrame.add(mf);

                    // Handling a button click ****************************************************
                    bPause.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!mf.getPause())
                                mf.setPause(true);
                            else
                                mf.setPause(false);
                        }
                    });

                    bStop.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mainFrame.setVisible(false);
                            thr.stop();
                        }
                    });
                }
            }
        });

        Container container = startFrame.getContentPane();
        container.setBackground(Color.GRAY);
        container.setLayout(null);

        container.add(lNumberOfBalls);
        container.add(tNumberOfBalls);
        container.add(bStart);
    }

    // Check for number input *********************************************************
    private static boolean isDigit(String str) throws NumberFormatException {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
