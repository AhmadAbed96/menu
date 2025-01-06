package com.category.crud.Service;
//import com.category.crud.Exception.CategoryNotFound;
import com.category.crud.Exception.NotFound;
import com.category.crud.Exception.CategoryNotUnique;
import com.category.crud.Model.Dto.Request.CategoryRequest;
import com.category.crud.Model.Dto.Response.CategoryResponse;
import com.category.crud.Model.document.Category;
import com.category.crud.Model.document.Section;
import com.category.crud.Model.enums.Status;
import com.category.crud.Repository.CategoryRepository;
//import com.category.crud.customException.ApiRequestException;
import com.category.crud.Repository.SectionRepository;
import com.category.crud.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    SectionRepository sectionRepo;
    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream().map(categoryMapper::toResponse).toList();

    }

    public List<CategoryResponse> getAllActiveCategory() {

        List<Category> activeCategories = categoryRepo.findCategoryByStatus(Status.ACTIVE);

        return activeCategories.stream().map(categoryMapper::toResponse)
                .collect(Collectors.toList());


    }

//    public CategoryResponse findCategoryByName(String name){
//
//            Category category = categoryRepo.findCategoryByName(name);
//            return categoryMapper.toResponse(category);
//    }

    public CategoryResponse getCategorById(String id) {
        log.info("find by id");
        Category categoryOptional = categoryRepo.findById(id)
                .orElseThrow(() -> new NotFound("Category with id: " + id + " not found"));
        if (categoryOptional.getStatus() == Status.DELETED) {
            throw new NotFound("the category with ID: " + id + " is deleted");
        }
        return categoryMapper.toResponse(categoryOptional);
    }


    public void deleteCategoryById(String id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(RuntimeException::new);
        List<Section> sections = sectionRepo.findSectionyByCategoryId(id);
        if (!sections.isEmpty()) {
            for (Section section : sections) {
                section.setStatus(Status.DELETED);
                sectionRepo.save(section);
            }
            category.setStatus(Status.DELETED);
            category.setDeletedAt(LocalDate.now());
            categoryRepo.save(category);
        }

        category.setStatus(Status.DELETED);
        category.setDeletedAt(LocalDate.now());
        categoryRepo.save(category);
    }


    public void createCategory(CategoryRequest categoryRequest) {
        if (categoryRequest != null){
           Category category= categoryRepo.insert(categoryMapper.toDocument(categoryRequest));
           categoryRepo.save(category);
        }
        else {
            throw new CategoryNotUnique("the name must be unique");
        }
    }

    public void undeleteCategory(String id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new NotFound("Category not found"));
        category.setStatus(Status.ACTIVE);
        category.setUpdatedAt(LocalDate.now());
        categoryRepo.save(category);

    }

    public void updateCategory (String id ,CategoryRequest categoryRequest){
        log.info("inside Update Service");

        Category category = categoryRepo.findById(id)
                .orElseThrow(NotFound::new);
        category.setName(categoryRequest.getName());
        category.setUpdatedAt(LocalDate.now());
        categoryRepo.save(categoryMapper.toDocument(categoryRequest));

    }
}