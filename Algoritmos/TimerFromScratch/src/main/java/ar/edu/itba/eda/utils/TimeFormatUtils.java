package ar.edu.itba.eda.utils;

public class TimeFormatUtils {
    public static String formatInterval(long interval) {
        if (interval < 0) return "Intervalo erroneo";
        float seconds = (float) interval / 1000;
        int minutes = (int) seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;
        return "(%d ms) %d days, %d hours, %d minutes, %.3f seconds".formatted(
                interval,
                days,
                hours % 24,
                minutes % 60,
                seconds % 60
        );
    }
}
