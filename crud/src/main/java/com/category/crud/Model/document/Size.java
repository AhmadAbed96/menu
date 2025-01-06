package com.category.crud.Model.document;

import com.category.crud.Model.enums.Status;
import com.category.crud.Validation.annotation.ValidSection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "size")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {

    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
//    @ValidSection

    private String sectionId;
    @NotBlank
    private double price;
    private Status status;
}
