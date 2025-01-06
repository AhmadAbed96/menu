package com.category.crud.Model.Dto.Response;

import com.category.crud.Model.document.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemSizeResponse {
    private String itemName;
    private String sizeName;
}
