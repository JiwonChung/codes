package view.D3;

import javax.swing.*;
import java.awt.*;

public class D3Panel extends JPanel {

    public D3Panel() {

    }

    @Override
    public void paint(Graphics g) {
        // 상
        g.setColor(new Color(83, 162, 115));
        g.fillRect(0, 0, 400, 400);

        // 하
        g.setColor(new Color(228, 180, 7));
        g.fillRect(400, 0, 400, 400);

        // 좌
        g.setColor(new Color(203, 88, 88));
        g.fillRect(800, 0, 400, 400);

        // 우
        g.setColor(new Color(228, 180, 7));
        g.fillRect(0, 400, 400, 400);

        // 앞
        g.setColor(new Color(203, 88, 88));
        g.fillRect(400, 400, 400, 400);

        // 뒤
        g.setColor(new Color(83, 162, 115));
        g.fillRect(800, 400, 400, 400);

    }
}
