package source_files.data.responses;

import lombok.Builder;

@Builder(builderMethodName = "tResponseBuilder") //TODO Builder isimlerini değiştirebiliyoruz, bunu göstereceğim.
public record TResponse<T>(
        T response //-> DTO nesnesini buraya göndereceğiz.
) {

}