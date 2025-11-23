import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // Ensure Swing runs on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Double Pendulum with Trail");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DoublePendulumPanel panel = new DoublePendulumPanel();
            frame.add(panel);

            frame.pack();
            frame.setLocationRelativeTo(null);  // center on screen
            
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}

