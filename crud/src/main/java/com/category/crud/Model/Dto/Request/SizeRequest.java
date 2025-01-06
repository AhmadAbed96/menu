package com.category.crud.Model.Dto.Request;

import com.category.crud.Model.enums.Status;
import com.category.crud.Validation.annotation.ValidSection;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeRequest {


    @NotBlank
    private String name;
    @NotBlank
    @ValidSection
    private String sectionId;
    @NotBlank
    @Min(value = 0, message = "the price must be at least 0")
    private double price;
}
