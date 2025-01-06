package com.category.crud.Model.Dto.Response;

import com.category.crud.Model.document.Category;
import com.category.crud.Model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private String name;
    private Status status;
}
