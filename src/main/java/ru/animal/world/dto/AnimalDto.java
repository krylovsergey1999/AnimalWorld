package ru.animal.world.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDto implements BaseDto {

    private Long animalId;

    private String animalName;

    public int getFieldsCount() {
        return this.getClass().getDeclaredFields().length;
    }
}
