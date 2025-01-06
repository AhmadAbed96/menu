package com.category.crud.Model.Dto.Request;

import com.category.crud.Validation.annotation.ValidSection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequest {
    @NotBlank
    private String name;
    @Size(min = 1)
    @NotEmpty
    private List<String> sizes;
    @NotBlank
    @ValidSection
    private String sectionId;
    @NotBlank
    private String description;

}
