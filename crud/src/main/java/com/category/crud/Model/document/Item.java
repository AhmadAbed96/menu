package com.category.crud.Model.document;

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
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "items")
public class Item {

    @Id
    private String id;
    @ValidSection
    @NotBlank
    private String sectionId;
    @NotBlank
    private String name;
    @Min(0)
    private Status status;
    @NotEmpty
    private List<String> sizes;
    @NotEmpty
    private String description;
    @CreatedDate
    private LocalDate createdAt;
    private LocalDate updatedDate;
    private LocalDate deletedAt;



}
