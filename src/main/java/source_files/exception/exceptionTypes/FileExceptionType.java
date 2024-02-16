package source_files.exception.exceptionTypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileExceptionType {

    PHOTO_UPLOAD_FAILED(6000, "Photo Upload Failed !"),
    PHOTO_DELETE_FAILED(6001, "Photo Delete Failed !");
    //------------------------------------------------------------------
    private final Integer errorCode;
    private final String message;
}
