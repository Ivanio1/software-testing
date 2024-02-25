package se.ifmo.task1;

public class Sin {

    // https://function-x.ru/chapter9-4/rows4_clip_image063.gif
    public static double calc(double x, int n) {

        if (Double.isInfinite(x) || Double.isNaN(x))
            throw new IllegalArgumentException("Argument can't be infinite or null!");

        double PI2 = Math.PI * 2;

        //Так как синус периодическая функция, все значения можно свести к промежутку -2pi;2pi
        x = x % PI2;

        double result = 0;
        double xx = x * x;
        double pow = x;
        double fact = 1;
        int sign = 1;       // Отвечает за знак (чередование '+' -> '-' -> '+' -> ...)

        for (int i = 1; i < n; i += 2) {
            fact *= i;
            result += sign * pow / fact;    // (-1)^(n-1) * x^(2n-1) / (2n-1)!
            sign = -sign;
            fact *= (i + 1);//Счет факториала
            pow *= xx; //Каждый раз увеличиваем степень на 2
        }

        return result;
    }
}
