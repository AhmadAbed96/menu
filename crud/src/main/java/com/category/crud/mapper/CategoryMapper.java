package com.category.crud.mapper;
import com.category.crud.Model.Dto.Request.CategoryRequest;
import com.category.crud.Model.Dto.Response.CategoryResponse;
import com.category.crud.Model.document.Category;
import com.category.crud.Model.enums.Status;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Builder
@Component
public class CategoryMapper {

    public  Category toDocument(CategoryRequest categoryRequest){
        return Category.builder()
                .name(categoryRequest.getName())
                .status(Status.ACTIVE)
                .build();
    };

    public  CategoryRequest toRequest(Category category){
        return CategoryRequest.builder()
                .name(category.getName())
                .build();
    };
    public  CategoryResponse toResponse(Category categoryDocument){
        return CategoryResponse.builder()
                .name(categoryDocument.getName())
                .status(categoryDocument.getStatus())
                .build();
    }

}
