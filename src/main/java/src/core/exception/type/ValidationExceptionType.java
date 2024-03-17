package src.core.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ValidationExceptionType {

    VALIDATION_EXCEPTION(3000, "Girilen bilgiler uygun deðil");

    //------------------------------------------------------------------
    private final Integer errorCode;
    private final String message;


}
