package se.ifmo.task1;

public class Sin {

    public static double calc(double x, int n) {

        double PI2 = Math.PI * 2;

        if (x >= 0) {
            while (x > PI2) {
                x -= PI2;
            }
        } else if (x < 0) {
            while (x < PI2) {
                x += PI2;
            }
        }

        double result = 0;

        double xx = x * x;
        double pow = x;
        double fact = 1;

        int sign = 1;       // Отвечает за знак (чередование '+' -> '-' -> '+' -> ...)

        for (int i = 1; i < n; i += 2) {
            fact /= i;
            result += sign * pow * fact;    // (-1)^(n-1) * x^(2n-1) / (2n-1)!
            sign = -sign;
            fact /= (i + 1);
            pow *= xx;
        }

        return result;
    }
}
