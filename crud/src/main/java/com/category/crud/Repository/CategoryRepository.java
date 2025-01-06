package com.category.crud.Repository;

import com.category.crud.Model.Dto.Response.CategoryResponse;
import com.category.crud.Model.document.Category;
import com.category.crud.Model.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    List<Category> findCategoryByName(String categoryName);
    List<Category> findCategoryByStatus(Status status);
    Optional<Category> findByIdAndStatus(String id, Status status );
//    @Query("{ '_id': ?0 }")
//    @Transactional
//    @Update("{ '$set': { 'status': ?1, 'deletedAt': ?2 } }")
//    void softDeleteCategory(String id, Status status, Date deletedAt);

}
