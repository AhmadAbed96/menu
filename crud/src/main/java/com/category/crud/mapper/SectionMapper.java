package com.category.crud.mapper;

import com.category.crud.Model.Dto.Request.CategoryRequest;
import com.category.crud.Model.Dto.Request.SectionRequest;
import com.category.crud.Model.Dto.Response.SectionResponse;
import com.category.crud.Model.document.Section;
import com.category.crud.Model.enums.Status;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
public class SectionMapper {
    public SectionResponse toResponse(Section section){
        return SectionResponse.builder()
                .name(section.getName())
                .status(section.getStatus())
                .categoryId(section.getCategoryId())
                .build();
    }

    public  SectionRequest toRequest(Section section){
        return SectionRequest.builder()
                .name(section.getName())
                .build();
    }

    public  Section toDocument(SectionRequest sectionRequest){
        return Section.builder()
                .name(sectionRequest.getName())
                .categoryId(sectionRequest.getCategoryId())
                .status(Status.ACTIVE)
                .build();
    }
}
