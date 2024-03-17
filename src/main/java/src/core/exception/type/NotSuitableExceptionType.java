package src.core.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotSuitableExceptionType {

    DRIVING_LICENSE_TYPE_NOT_SUITABLE(5000, "Ehliyet tipi uygun değil");
    //------------------------------------------------------------------
    private final Integer errorCode;
    private final String message;
}
