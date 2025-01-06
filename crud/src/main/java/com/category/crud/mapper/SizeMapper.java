package com.category.crud.mapper;

import com.category.crud.Model.Dto.Request.ItemRequest;
import com.category.crud.Model.Dto.Request.SizeRequest;
import com.category.crud.Model.Dto.Response.ItemResponse;
import com.category.crud.Model.Dto.Response.SizeResponse;
import com.category.crud.Model.document.Item;
import com.category.crud.Model.document.Size;
import com.category.crud.Model.enums.Status;
import lombok.Builder;
import org.springframework.stereotype.Component;


@Builder
@Component
public class SizeMapper {
    public SizeRequest toRequest(Size size){
        return SizeRequest.builder()
                .price(size.getPrice())
                .sectionId(size.getSectionId())
                .name(size.getName())
                .build();
    }

    public SizeResponse toResponse(Size sizeDocument){
        return SizeResponse.builder()
                .name(sizeDocument.getName())
                .sectionId(sizeDocument.getSectionId())
                .price(sizeDocument.getPrice())
                .build();
    }

    public Size toDocument(SizeRequest sizeRequest){
//        log.info("item request mapper {}" , itemRequest);
        return Size.builder()
                .price(sizeRequest.getPrice())
                .sectionId(sizeRequest.getSectionId())
                .name(sizeRequest.getName())
                .status(Status.ACTIVE)
                .build();
    };
}
