package org.example.springtrain.repository;

import org.example.springtrain.model.ParentEntityLongLongLongNam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentEntityRepository extends JpaRepository<ParentEntityLongLongLongNam, Long> {

}
