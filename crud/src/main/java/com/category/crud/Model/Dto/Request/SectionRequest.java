package com.category.crud.Model.Dto.Request;

import com.category.crud.Model.document.Item;
import com.category.crud.Model.document.Section;
import com.category.crud.Model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String categoryId;

}
