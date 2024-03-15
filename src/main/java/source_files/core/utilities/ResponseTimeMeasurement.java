package source_files.core.utilities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseTimeMeasurement {
    private long startTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public long measure() {
        return System.currentTimeMillis() - startTime;
    }
}
