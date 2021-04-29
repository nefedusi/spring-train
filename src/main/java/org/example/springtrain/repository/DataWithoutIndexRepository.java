package org.example.springtrain.repository;

import java.util.List;
import org.example.springtrain.model.DataWithoutIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataWithoutIndexRepository extends JpaRepository<DataWithoutIndex, Long> {

    List<DataWithoutIndex> findByF1(long f1);
}
