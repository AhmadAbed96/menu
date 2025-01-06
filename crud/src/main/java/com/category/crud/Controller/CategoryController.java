package com.category.crud.Controller;

import com.category.crud.Model.Dto.Request.CategoryRequest;
import com.category.crud.Model.Dto.Response.CategoryResponse;
import com.category.crud.Model.enums.Status;
import com.category.crud.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping(path = "/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllActive")
    public ResponseEntity<List<CategoryResponse>> getAllActive(){
        return ResponseEntity.ok(categoryService.getAllActiveCategory());
    };

    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable String id){

        return ResponseEntity.ok(categoryService.getCategorById(id));
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        log.info("Delete Controller");
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/undelete/{id}")
    public ResponseEntity<?> undelete(@Valid @PathVariable String id){
        categoryService.undeleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<?> updateCategory(@Valid @PathVariable String id, CategoryRequest categoryRequest){
        log.info("inside put controller");
        categoryService.updateCategory(id, categoryRequest);
        return  ResponseEntity.noContent().build();
    }

    @PostMapping("/addCategory")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryRequest request){
        log.info("inside create controller");
        categoryService.createCategory(request);
        return ResponseEntity.ok("the category created");
    }

}
