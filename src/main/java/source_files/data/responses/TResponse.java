package source_files.data.responses;

import lombok.Builder;

@Builder
public record TResponse<T>(String message, T response, boolean isSuccess) {

}