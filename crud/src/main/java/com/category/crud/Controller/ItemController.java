package com.category.crud.Controller;

import com.category.crud.Model.Dto.Request.ItemRequest;
import com.category.crud.Model.Dto.Response.ItemResponse;
import com.category.crud.Model.Dto.Response.ItemSizeResponse;
import com.category.crud.Model.document.Item;
import com.category.crud.Repository.SizeRepository;
import com.category.crud.Service.ItemService;
import com.category.crud.Service.SizeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping(path = "/items")
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private SizeService sizeService;
    @GetMapping("/GetAllItem")
    public ResponseEntity<List<ItemResponse>> getAllItems(){
        return ResponseEntity.ok(itemService.getAllItems());
    }
    @GetMapping("/GetItemById/{id}")
    public ResponseEntity<ItemResponse> getItemById(@Valid @PathVariable String id){
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @GetMapping("/GetAllItem/{id}")
    public ResponseEntity<List<Item>> getAllItemsBySectionId(String id){
        return ResponseEntity.ok(itemService.getItemBySectionId(id));
    }

    @PostMapping ("/CreateItem")
    public ResponseEntity<String> createItem(@Valid @RequestBody ItemRequest itemRequest){
        log.info("inside controller {} ", itemRequest);
        itemService.createItem(itemRequest);
        return ResponseEntity.ok("the item created");
    }

    @DeleteMapping("/DeleteItem/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable String id){
        itemService.delete(id);
        return ResponseEntity.ok("the item deleted");
    }

    @GetMapping("/ItemStatus")
    public ResponseEntity<Boolean> checkStatus(
            @RequestParam String itemId,
            @RequestParam String sizeId
    ) {
        log.info("inside Check Status {}, {}", itemId, sizeId);
        Boolean status = itemService.checkStatus(itemId) && sizeService.checkSizeStatus(sizeId);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/getItemSizeNameById/{itemId}/{sizeId}")
    public ResponseEntity<ItemSizeResponse> getItemSizeName(@Valid @RequestParam String itemId,
                                                            @RequestParam String sizeId){
        ItemSizeResponse itemSizeResponse = itemService.getItemSizeName(itemId, sizeId);
        return ResponseEntity.ok(itemSizeResponse);
    }
}
