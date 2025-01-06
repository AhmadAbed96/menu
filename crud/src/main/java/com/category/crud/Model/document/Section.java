package com.category.crud.Model.document;

import com.category.crud.Model.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "section")
public class Section {
    @Id
    private String id;
    private String name;
    private String categoryId;
    private Status status;
    private List<Item> items;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDate UpdatedAt;
    private Date deletedAt;

}
