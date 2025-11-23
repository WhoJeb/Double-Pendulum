import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DoublePendulumPanel extends JPanel implements ActionListener {

    DoublePendulum pendulum;
    Timer timer;

    final int originX = 720;
    final int originY = 405;

    // store trail points
    ArrayList<Point> trail = new ArrayList<>();
    final int TRAIL_MAX = 800; // number of points in trail

    public DoublePendulumPanel() {
        this.setPreferredSize(new Dimension(1440, 810));
        this.setBackground(Color.black);

        pendulum = new DoublePendulum();

        timer = new Timer(4, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pendulum.update(0.032);

        // record trail point
        int[] pos = pendulum.getPositions(originX, originY);
        int x2 = pos[2];
        int y2 = pos[3];
        trail.add(new Point(x2, y2));

        // keep trail small
        if (trail.size() > TRAIL_MAX)
            trail.remove(0);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Rendering quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int[] pos = pendulum.getPositions(originX, originY);
        int x1 = pos[0], y1 = pos[1];
        int x2 = pos[2], y2 = pos[3];

        // --- Draw trail ---
        for (int i = 0; i < trail.size(); i++) {
            float alpha = (float)i / trail.size();  // fade from dark to bright
            g2d.setColor(new Color(0f, 1f, 1f, alpha)); // teal fading trail

            Point p = trail.get(i);
            g2d.fillOval(p.x - 2, p.y - 2, 4, 4);
        }

        // --- Draw rods ---
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.WHITE);

        g2d.drawLine(originX, originY, x1, y1);
        g2d.fillOval(x1 - 6, y1 - 6, 12, 12);

        g2d.drawLine(x1, y1, x2, y2);
        g2d.fillOval(x2 - 6, y2 - 6, 12, 12);
    }
}

