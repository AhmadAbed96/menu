package com.category.crud.Service;

import com.category.crud.Exception.ItemNotFound;
import com.category.crud.Exception.NotFound;
import com.category.crud.Exception.SectionNotFound;
import com.category.crud.Model.Dto.Request.ItemCartRequest;
import com.category.crud.Model.Dto.Request.ItemRequest;
import com.category.crud.Model.Dto.Response.ItemResponse;
import com.category.crud.Model.Dto.Response.ItemSizeResponse;
import com.category.crud.Model.Dto.Response.SizeResponse;
import com.category.crud.Model.document.Item;
import com.category.crud.Model.document.Size;
import com.category.crud.Model.enums.Status;
import com.category.crud.Repository.ItemRepository;
import com.category.crud.Repository.SizeRepository;
import com.category.crud.mapper.ItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j

@Service
public class ItemService  {
    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SizeRepository sizeRepo;
    public List<ItemResponse> getAllItems(){
        List<Item> items = itemRepo.findAll();
        if (items.isEmpty()){
            throw new NotFound("there is no items");
        }
        return items.stream().map(itemMapper::toResponse ).toList();
    }

    public List<Item> getItemBySectionId(String sectionId){
        return itemRepo.findItemBySectionId(sectionId);
    }
    public ItemResponse getItemById(String id) {
        log.info("find by id");
        Item item = itemRepo.findById(id)
                .orElseThrow(() -> new SectionNotFound("Category with id: " + id + " not found"));
        if (item.getStatus() == Status.DELETED) {
            throw new ItemNotFound("the item with ID: " + id + " is deleted");
        }
        return itemMapper.toResponse(item);
    }
    public void createItem(ItemRequest itemRequest){
            List<Size> sizes = sizeRepo.findSizeBySectionId( itemRequest.getSectionId());
            if (sizes.isEmpty()){
                throw new NotFound("there is no size for sectionId: " + itemRequest.getSectionId());
            }
        List<String> requestedSizes = itemRequest.getSizes();
        boolean allSizesExist = requestedSizes.stream()
                        .allMatch(size -> sizes.stream()
                        .anyMatch(existingSize -> existingSize.getId().equals(size)));

        if (!allSizesExist) {
            throw new NotFound("The section doesn't have all the requested sizes.");
        }

        Item item = itemMapper.toDocument(itemRequest);
        Item savedItem = itemRepo.save(item);
        log.info("Saved item {}", savedItem);
        log.info("Item request not null");

    }

    public void delete(String id){
        Item item = itemRepo.findById(id)
                .orElseThrow(()-> new ItemNotFound("the item not found"));

        item.setStatus(Status.DELETED);
        item.setDeletedAt(LocalDate.now());
        itemRepo.save(item);
    }

    public void undeleteItem(String id) {
        Item item =itemRepo.findById(id)
                .orElseThrow(() -> new ItemNotFound("item not found"));
        item.setStatus(Status.ACTIVE);
        item.setUpdatedDate(LocalDate.now());
        itemRepo.save(item);
    }

    public void updateItem(String id, ItemRequest itemRequest){
        Item item = itemRepo.findById(id)
                .orElseThrow(() -> new ItemNotFound("the item not found"));
        item.setName(itemRequest.getName());
        item.setUpdatedDate(LocalDate.now());
        itemRepo.save(itemMapper.toDocument(itemRequest));
    }

    public List<ItemCartRequest> getItemCart (ItemCartRequest itemCart, int quantity ){
        Item item = itemRepo.findById(itemCart.getItemId())
                .orElseThrow(()-> new ItemNotFound("the item not found"));
        List<ItemCartRequest> itemCarts = new ArrayList<>();

        if (item.getStatus() == Status.DELETED) {
            throw new ItemNotFound("the item with ID: " + itemCart.getItemId() + " is deleted");
        }
        else {

            itemCart.setQuantity(quantity);
            itemCarts.add(itemCart);
        }

        return itemCarts;
    }

    public Boolean checkStatus(String id){
        Item item = itemRepo.findById(id)
                .orElseThrow(() -> new SectionNotFound("Category with id: " + id + " not found"));
        if (item.getStatus() == Status.DELETED) {
            return false;
        }
        return true;
    }


    public ItemSizeResponse getItemSizeName(String itemId, String sizeId){
        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new SectionNotFound("section with id: " + itemId + " not found"));
        Size size = sizeRepo.findById(sizeId)
                .orElseThrow(() -> new SectionNotFound("Category with id: " + sizeId + " not found"));
        return ItemSizeResponse.builder()
                .itemName(item.getName())
                .sizeName(size.getName())
                .build();

    }
}


