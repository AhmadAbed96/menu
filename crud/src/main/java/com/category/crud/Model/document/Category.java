package com.category.crud.Model.document;
import com.category.crud.Model.enums.Status;
import com.category.crud.Validation.annotation.UniqueCategoryValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Builder
@Document(collection = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category  {
    @Id
    private String id;
    @UniqueCategoryValue
    private String name;

    @CreatedDate
    private LocalDate createdAt;

    private LocalDate deletedAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    private Status status ;

}
