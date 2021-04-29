package org.example.springtrain.repository;

import java.util.List;
import org.example.springtrain.model.DataWithIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataWithIndexRepository extends JpaRepository<DataWithIndex, Long> {

    List<DataWithIndex> findByF1(long f1);
}
