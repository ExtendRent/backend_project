package source_files.data.DTO.Mappers;

import io.swagger.models.Model;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

public interface ModelMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();
}
