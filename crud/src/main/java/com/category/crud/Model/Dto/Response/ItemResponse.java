package com.category.crud.Model.Dto.Response;

import com.category.crud.Model.document.Item;
import com.category.crud.Model.document.Size;
import com.category.crud.Model.enums.Status;
import com.category.crud.Validation.annotation.ValidSection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {


    private String sectionId;
    private String name;
    private Status status;
    private List<Size> sizes;
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedDate;
    private LocalDate deletedAt;

}
