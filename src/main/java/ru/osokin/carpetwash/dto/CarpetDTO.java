package ru.osokin.carpetwash.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarpetDTO {
    @Min(value = 0)
    private double length;
    @Min(value = 0)
    private double width;
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "Введите корректный номер телефона")
    private String clientPhoneNumber;
    private String clientName;
}
