package source_files.core.exception;

import lombok.Getter;
import source_files.core.exception.exceptionTypes.FileExceptionType;

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
