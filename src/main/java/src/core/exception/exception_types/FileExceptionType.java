package src.core.exception.exception_types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileExceptionType {

    PHOTO_UPLOAD_FAILED(6000, "Fotoğraf yükleme başarısız"),
    PHOTO_DELETE_FAILED(6001, "Fotoğraf silme başarısız");
    //------------------------------------------------------------------
    private final Integer errorCode;
    private final String message;
}
