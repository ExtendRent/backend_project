package source_files.data.responses;

import lombok.Builder;

@Builder(builderMethodName = "tResponseBuilder")
public record TResponse<T>(
        T response //-> DTO nesnesini buraya göndereceğiz.
) {

}