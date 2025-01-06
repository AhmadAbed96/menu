package com.category.crud.mapper;

import com.category.crud.Model.Dto.Request.CategoryRequest;
import com.category.crud.Model.Dto.Request.ItemRequest;
import com.category.crud.Model.Dto.Response.ItemResponse;
import com.category.crud.Model.document.Category;
import com.category.crud.Model.document.Item;
import com.category.crud.Model.document.Size;
import com.category.crud.Model.enums.Status;
import com.category.crud.Repository.SizeRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

@Component
@Builder
@Slf4j
public class ItemMapper {

    @Autowired
    private SizeRepository sizeRepository;
    public  ItemRequest toRequest(Item item){
        return ItemRequest.builder()
                .name(item.getName())
                .sizes(item.getSizes())
                .sectionId(item.getSectionId())
                .description(item.getDescription())
                .build();
    }

    public  ItemResponse toResponse(Item itemDocument){
        List<Size> sizes = sizeRepository.findSizeBySectionId(itemDocument.getSectionId());
        List<Size> selectedSizes = itemDocument.getSizes().stream()
                .map(sizeId -> sizes.stream()
                        .filter(size -> size.getId().equals(sizeId))
                        .findFirst()
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return ItemResponse.builder()
                .name(itemDocument.getName())
                .sizes(selectedSizes)
                .sectionId(itemDocument.getSectionId())
                .description(itemDocument.getDescription())
                .build();
    }

    public Item toDocument(ItemRequest itemRequest){
        log.info("item request mapper {}" , itemRequest);
        return Item.builder()
                .name(itemRequest.getName())
                .sectionId(itemRequest.getSectionId())
                .sizes(itemRequest.getSizes())
                .description(itemRequest.getDescription())
                .status(Status.ACTIVE)
                .build();
    };
}
