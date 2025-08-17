package ar.edu.itba.eda;

import ar.edu.itba.eda.utils.TimeFormatUtils;

public class MyTimer {
    private final Long start;
    private Long end;

    public MyTimer(long startMillis) {
        start = startMillis;
    }

    public MyTimer() {
        this(System.currentTimeMillis());
    }

    public void stop() {
        this.end = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        if (end == null) throw new RuntimeException();
        return TimeFormatUtils.formatInterval(end - start);
    }
}
