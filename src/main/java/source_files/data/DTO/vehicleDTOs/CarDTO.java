package source_files.data.DTO.vehicleDTOs;

import lombok.Builder;

@Builder
public record CarDTO(String brand,
                     String Color,
                     int year,
                     String bodyType,
                     String model,
                     String licensePlate,
                     String details) {
}
