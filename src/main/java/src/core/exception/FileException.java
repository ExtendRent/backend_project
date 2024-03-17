package src.core.exception;

import lombok.Getter;
import src.core.exception.type.FileExceptionType;

@Getter
public class FileException extends RuntimeException {

    private final FileExceptionType fileExceptionType;

    private final String detail;

    public FileException(FileExceptionType fileExceptionType, String detail) {
        super(fileExceptionType.getMessage());
        this.fileExceptionType = fileExceptionType;
        this.detail = detail;
    }

    public FileException(FileExceptionType fileExceptionType) {
        super(fileExceptionType.getMessage());
        this.fileExceptionType = fileExceptionType;
        this.detail = fileExceptionType.getMessage();
    }
}
