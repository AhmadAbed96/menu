package com.category.crud.Model.Dto.Request;

import com.category.crud.Validation.annotation.UniqueCategoryValue;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    @NotBlank(message = "the name is required")
    @UniqueCategoryValue
    @NonNull
    private String name;



}
