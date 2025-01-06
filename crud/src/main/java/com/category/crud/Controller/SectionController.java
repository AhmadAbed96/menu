package com.category.crud.Controller;


import com.category.crud.Model.Dto.Request.SectionRequest;
import com.category.crud.Model.Dto.Response.SectionResponse;
import com.category.crud.Model.enums.Status;
import com.category.crud.Repository.SectionRepository;
import com.category.crud.Service.SectionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j

@RequestMapping(path = "/Sections")
@RestController
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping("/getAllSection")
    public ResponseEntity<List<SectionResponse>> getAllSections(){
        return  ResponseEntity.ok(sectionService.getAllSection());
    }

    @GetMapping("/getSection/{id}")
    public ResponseEntity<SectionResponse> getSectionById(@PathVariable String id){
       log.info("inside get by id controller");
        return  ResponseEntity.ok(sectionService.getSectionById(id));
    }

    @DeleteMapping("/DeleteSection")
    public ResponseEntity<Void> deleteSection(@RequestParam String id) {
        log.info("Inside controller");
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/CreateSection")
    public ResponseEntity<SectionResponse> createSection(@Valid @RequestBody SectionRequest request){

        return  ResponseEntity.ok(sectionService.createSection(request));
    }

    @PutMapping("/undeleteSection/{id}")
    public ResponseEntity<?> undeleteSection(@Valid @PathVariable String id){
        sectionService.undeleteSection(id);
        return ResponseEntity.ok("the section is active now");
    }

    @GetMapping("/getSectionCount")
    public ResponseEntity<Long> getSectionCount(){

        return  ResponseEntity.ok(sectionService.getSectionCount());
    }


}
