package org.example.springtrain.controller;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springtrain.model.ChildEntityLongLongLongNam;
import org.example.springtrain.model.ParentEntityLongLongLongNam;
import org.example.springtrain.repository.ParentEntityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParentEntityController {

    private final ParentEntityRepository parentEntityRepository;

    @GetMapping("/parent/all")
    public List<ParentEntityLongLongLongNam> getAllParentsWithChildren() {
        List<ParentEntityLongLongLongNam> parentEntities = parentEntityRepository.findAll();
        return parentEntities;
    }

    @PostMapping("/parent/create")
    public ParentEntityLongLongLongNam createParentWithChildren() {
        ChildEntityLongLongLongNam child15 = new ChildEntityLongLongLongNam("C15", "C15", "P3");
        ChildEntityLongLongLongNam child16 = new ChildEntityLongLongLongNam("C16", "C16", "P3");
        ChildEntityLongLongLongNam child17 = new ChildEntityLongLongLongNam("C17", "C17", "P3");
        List<ChildEntityLongLongLongNam> childEntities = Arrays.asList(child15, child16, child17);
        ParentEntityLongLongLongNam parent3 = new ParentEntityLongLongLongNam("P3", "P3", childEntities);
        parent3 = parentEntityRepository.saveAndFlush(parent3);
        return parent3;
    }
}
