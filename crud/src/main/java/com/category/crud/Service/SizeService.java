package com.category.crud.Service;


import com.category.crud.Exception.NotFound;
import com.category.crud.Exception.SectionNotFound;
import com.category.crud.Model.Dto.Request.SectionRequest;
import com.category.crud.Model.Dto.Request.SizeRequest;
import com.category.crud.Model.Dto.Response.SectionResponse;
import com.category.crud.Model.Dto.Response.SizeResponse;
import com.category.crud.Model.document.Item;
import com.category.crud.Model.document.Section;
import com.category.crud.Model.document.Size;
import com.category.crud.Model.enums.Status;
import com.category.crud.Repository.ItemRepository;
import com.category.crud.Repository.SizeRepository;
import com.category.crud.mapper.ItemMapper;
import com.category.crud.mapper.SizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository ;

    @Autowired
    private SizeMapper sizeMapper;

    public SizeResponse getSizeById(String id) {
//        log.info("find by id");
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new NotFound("size with id: " + id + " not found"));
        if (size.getStatus() == Status.DELETED) {
            throw new NotFound("the category with ID: " + id + " is deleted");
        }
        return sizeMapper.toResponse(size);
    }

    public List<Size> getSizeBySectionId(String sectionId){
        List<Size> sizesBySection = sizeRepository.findSizeBySectionId(sectionId);
        if (sizesBySection.isEmpty()){
            throw new NotFound("there is no size in that section " +sectionId);
        }
        return sizesBySection;
    }
    public SizeResponse createSize(SizeRequest sizeRequest){
        if(sizeRequest != null){
//            log.info("inside if");
            Size size = sizeMapper.toDocument(sizeRequest);
            sizeRepository.insert(size);
//            log.info("Add section");
            return sizeMapper.toResponse(size);
        }
        else {
            throw new IllegalArgumentException("the size request is null");
        }
    }

    public Boolean checkSizeStatus(String id){
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new NotFound("Size with id: " + id + " not found"));
        if (size.getStatus() == Status.DELETED) {
            return false;
        }
        return true;
    }
}
