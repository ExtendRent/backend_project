package source_files.exception.exceptionTypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotSuitableExceptionType {

    DRIVING_LICENSE_TYPE_NOT_SUITABLE(5000, "Driving License Type Not Suitable !");
    //------------------------------------------------------------------
    private final Integer errorCode;
    private final String message;
}
