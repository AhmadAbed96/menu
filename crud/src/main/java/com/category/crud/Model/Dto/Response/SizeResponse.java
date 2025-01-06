package com.category.crud.Model.Dto.Response;


import com.category.crud.Model.enums.Status;
import com.category.crud.Validation.annotation.ValidSection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SizeResponse {


    private String name;
    private String sectionId;
    private double price;
}
