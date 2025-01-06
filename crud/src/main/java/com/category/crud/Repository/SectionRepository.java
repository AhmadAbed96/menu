package com.category.crud.Repository;

import com.category.crud.Model.document.Item;
import com.category.crud.Model.document.Section;
import com.category.crud.Model.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends MongoRepository<Section, String> {
    List<Section> findSectionyByCategoryId(String sectionName);
}
