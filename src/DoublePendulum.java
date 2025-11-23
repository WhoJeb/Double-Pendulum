public class DoublePendulum {

    public double g = 9.81;

    public double L1 = 150;
    public double L2 = 150;
    public double m1 = 20;
    public double m2 = 20;

    public double theta1 = Math.PI / 2;
    public double theta2 = Math.PI / 2;

    // Angular Forces
    public double omega1 = 0.5; // Outer Arm
    public double omega2 = 0; // Inner Arm

    public DoublePendulum() {}

    public void update(double dt) {
        double num1 = -g * (2 * m1 + m2) * Math.sin(theta1);
        double num2 = -m2 * g * Math.sin(theta1 - 2 * theta2);
        double num3 = -2 * Math.sin(theta1 - theta2) * m2;
        double num4 = omega2 * omega2 * L2 + omega1 * omega1 * L1 * Math.cos(theta1 - theta2);
        double den1 = L1 * (2 * m1 + m2 - m2 * Math.cos(2 * theta1 - 2 * theta2));

        double a1 = (num1 + num2 + num3 * num4) / den1;

        double num5 = 2 * Math.sin(theta1 - theta2);
        double num6 = omega1 * omega1 * L1 * (m1 + m2);
        double num7 = g * (m1 + m2) * Math.cos(theta1);
        double num8 = omega2 * omega2 * L2 * m2 * Math.cos(theta1 - theta2);
        double den2 = L2 * (2 * m1 + m2 - m2 * Math.cos(2 * theta1 - 2 * theta2));

        double a2 = (num5 * (num6 + num7 + num8)) / den2;

        omega1 += a1 * dt;
        omega2 += a2 * dt;

        theta1 += omega1 * dt;
        theta2 += omega2 * dt;
    }

    public int[] getPositions(int originX, int originY) {
        int x1 = originX + (int)(L1 * Math.sin(theta1));
        int y1 = originY + (int)(L1 * Math.cos(theta1));

        int x2 = x1 + (int)(L2 * Math.sin(theta2));
        int y2 = y1 + (int)(L2 * Math.cos(theta2));

        return new int[] {x1, y1, x2, y2};
    }
}

