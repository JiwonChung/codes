package com.company.view.lib;

import javax.swing.*;
import java.awt.*;

public class JGradientButton extends JButton {
    Color color1;
    Color color2;
    public JGradientButton(String text, Color color1, Color color2) {
        super(text);
        this.color1 = color1;
        this.color2 = color2;
        setContentAreaFilled(false);
        setFocusPainted(false); // used for demonstration
    }

    public JGradientButton(String text) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0),
                color1,
                new Point(getWidth(), getHeight()),
                color2));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }

    public JGradientButton newInstance() {
        return new JGradientButton(super.getText());
    }
}