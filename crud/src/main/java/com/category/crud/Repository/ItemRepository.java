package com.category.crud.Repository;

import com.category.crud.Model.document.Category;
import com.category.crud.Model.document.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findItemBySectionId(String id);

}
