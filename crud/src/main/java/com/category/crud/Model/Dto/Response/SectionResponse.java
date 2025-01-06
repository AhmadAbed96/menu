package com.category.crud.Model.Dto.Response;

import com.category.crud.Model.document.Section;
import com.category.crud.Model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionResponse {
    private String name;
    private String categoryId;
    private Status status;


}
