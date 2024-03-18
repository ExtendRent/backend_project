package src.core.utilities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseTimeMeasurement {
    private long startTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public double end() {
        return (double) (System.currentTimeMillis() - startTime) / 1000;
    }
}
