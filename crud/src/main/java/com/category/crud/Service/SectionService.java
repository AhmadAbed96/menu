package com.category.crud.Service;

import com.category.crud.Exception.NotFound;
import com.category.crud.Exception.SectionNotFound;
import com.category.crud.Model.Dto.Request.SectionRequest;
import com.category.crud.Model.Dto.Response.SectionResponse;
import com.category.crud.Model.document.Item;
import com.category.crud.Model.document.Section;
import com.category.crud.Model.enums.Status;
import com.category.crud.Repository.ItemRepository;
import com.category.crud.Repository.SectionRepository;
import com.category.crud.mapper.SectionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepo;

    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private SectionMapper sectionMapper;

    public List<SectionResponse> getAllSection(){
        List<SectionResponse> sections = sectionRepo.findAll().stream()
                .filter(x-> x.getStatus() == Status.ACTIVE)
                .map(sectionMapper::toResponse).toList();
        if (sections.isEmpty() || sections.size() == 0){
            throw new NotFound("the section is empty");
        }
        return sections;
    }

    public SectionResponse getSectionById(String id) {
        log.info("find by id");
        Section section = sectionRepo.findById(id)
                .orElseThrow(() -> new SectionNotFound("Section with id: " + id + " not found"));
        if (section.getStatus() == Status.DELETED) {
            throw new NotFound("the section with ID: " + id + " is deleted");
        }
        return sectionMapper.toResponse(section);
    }
    public SectionResponse createSection(SectionRequest sectionRequest){
        if(sectionRequest != null){
            log.info("inside if");
            Section section = sectionMapper.toDocument(sectionRequest);
            sectionRepo.insert(section);
            log.info("Add section");
            return sectionMapper.toResponse(section);
        }
        else {
            throw new RuntimeException();
        }
    }

    public void deleteSection(String id){
        log.info("Inside Delete service method");
        Section section = sectionRepo.findById(id)
                .orElseThrow(() -> new SectionNotFound("The section not found"));
        List<Item> items = itemRepo.findItemBySectionId(id);
        if (!items.isEmpty()) {
            for (Item item : items) {
                item.setStatus(Status.DELETED);
                itemRepo.save(item);
            }
        }
        section.setStatus(Status.DELETED);
        sectionRepo.save(section);
        }

    public void undeleteSection(String id) {
        Section section = sectionRepo.findById(id)
                .orElseThrow(() -> new SectionNotFound("Section not found"));
        List<Item> items = itemRepo.findItemBySectionId(id);
        if (!items.isEmpty()) {
            for (Item item : items) {
                item.setStatus(Status.ACTIVE);
                itemRepo.save(item);
            }
        }
        section.setStatus(Status.ACTIVE);
        section.setUpdatedAt(LocalDate.now());
        sectionRepo.save(section);
    }

    public Long getSectionCount(){
        Long sectionCount = sectionRepo.count();
        return sectionCount;
    }

}
