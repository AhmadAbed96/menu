package com.category.crud.Controller;


import com.category.crud.Model.Dto.Request.SizeRequest;
import com.category.crud.Model.Dto.Response.SizeResponse;
import com.category.crud.Model.document.Size;
import com.category.crud.Service.SizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/size")
@RestController
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping("/GetSizeById/{id}")
    public ResponseEntity<SizeResponse> getItemById(@Valid @PathVariable String id){
        return ResponseEntity.ok(sizeService.getSizeById(id));
    }

    @GetMapping("/getSizeById/{id}")
    public ResponseEntity<SizeResponse> getSizeById(@Valid @PathVariable String id){
        return ResponseEntity.ok(sizeService.getSizeById(id));
    }
    @PostMapping("/createSize")
    public ResponseEntity createSize(@RequestBody SizeRequest sizeRequest){
        sizeService.createSize(sizeRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/getAllSizesBySectionId/{id}")
    public ResponseEntity<List<Size>> getAllSizesBySectionId( @PathVariable String id){
        return ResponseEntity.ok(sizeService.getSizeBySectionId(id));
    }

}
