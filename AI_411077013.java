package test;

import java.util.Arrays;

public class AI_411077013 {
    public static void main(String[] args) {

        double alpha = 0.1;
        double e = 1.0;
        int x1, x2, yd;
        int t = 0;

        double[] answer1 = new double[4];
        double[] answer2 = new double[4];
        double[] answer3 = new double[4];

        double w13 = 0.5, w14 = 0.9, w23 = 0.4, w24 = 1.0;
        double w35 = -1.2, w45 = 1.1;
        double theta3 = 0.8, theta4 = -0.1, theta5 = 0.3;

        while (true) {
            int i = 0;
            double etotal = 0;

            while (i < 4) {

                switch (i) {
                    case 0 -> {
                        x1 = 1; x2 = 1; yd = 0;
                    }
                    case 1 -> {
                        x1 = 0; x2 = 1; yd = 1;
                    }
                    case 2 -> {
                        x1 = 1; x2 = 0; yd = 1;
                    }
                    default -> {
                        x1 = 0; x2 = 0; yd = 0;
                    }
                }

                double y3 = 1 / (1 + Math.exp(-(x1 * w13 + x2 * w23 - theta3)));
                double y4 = 1 / (1 + Math.exp(-(x1 * w14 + x2 * w24 - theta4)));
                double y5 = 1 / (1 + Math.exp(-(y3 * w35 + y4 * w45 - theta5)));

                e = yd - y5;

                double delta5 = y5 * (1 - y5) * e;

                double deltaW35 = alpha * y3 * delta5;
                double deltaW45 = alpha * y4 * delta5;
                double deltatheta5 = alpha * (-1) * delta5;

                double delta3 = y3 * (1 - y3) * delta5 * w35;
                double delta4 = y4 * (1 - y4) * delta5 * w45;

                double deltaW13 = alpha * x1 * delta3;
                double deltaW23 = alpha * x2 * delta3;
                double deltatheta3 = alpha * (-1) * delta3;
                double deltaW14 = alpha * x1 * delta4;
                double deltaW24 = alpha * x2 * delta4;
                double deltatheta4 = alpha * (-1) * delta4;

                w13 += deltaW13;
                w14 += deltaW14;
                w23 += deltaW23;
                w24 += deltaW24;
                w35 += deltaW35;
                w45 += deltaW45;
                theta3 += deltatheta3;
                theta4 += deltatheta4;
                theta5 += deltatheta5;

                etotal += Math.pow(e, 2);

                answer1[i] = y5;
                answer2[i] = e;
                answer3[i] = etotal;

                i++;
            }
            t++;

            if (etotal < 0.001 ) { 
                System.out.println("\n\n");
                System.out.println("totally run " + t + " rounds\n\n");
                System.out.println("    input    expect    actual      e      e^2   ");
                System.out.println(" x1     x2   output    output            total  ");
                System.out.println("--------------------------------------------------------------------");
                for (int j = 0; j < 4; j++) {
                    System.out.printf(" %d      %d      %d       %.4f   %.4f   %.4f%n",
                            j < 2 ? 1 : 0, j % 2, j == 1 || j == 2 ? 1 : 0,
                            answer1[j], answer2[j], answer3[j]);
                }
                break;
            }
        }
    }
}
