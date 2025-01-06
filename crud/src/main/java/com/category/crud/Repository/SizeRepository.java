package com.category.crud.Repository;

import com.category.crud.Model.document.Size;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SizeRepository extends MongoRepository<Size, String> {
    List<Size> findSizeBySectionId(String id);
}
