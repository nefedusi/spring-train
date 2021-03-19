package org.example.springtrain.controller;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springtrain.model.ChildEntity;
import org.example.springtrain.model.ParentEntity;
import org.example.springtrain.repository.ParentEntityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParentEntityController {

    private final ParentEntityRepository parentEntityRepository;

    @GetMapping("/parent/all")
    public List<ParentEntity> getAllParentsWithChildren() {
        List<ParentEntity> parentEntities = parentEntityRepository.findAll();
        return parentEntities;
    }

    @PostMapping("/parent/create")
    public ParentEntity createParentWithChildren() {
        ChildEntity child5 = new ChildEntity(5L, "C5", 3L);
        ChildEntity child6 = new ChildEntity(6L, "C6", 3L);
        ChildEntity child7 = new ChildEntity(7L, "C7", 3L);
        List<ChildEntity> childEntities = Arrays.asList(child5, child6, child7);
        ParentEntity parent3 = new ParentEntity(3L, "P3", childEntities);
        parent3 = parentEntityRepository.saveAndFlush(parent3);
        return parent3;
    }
}
