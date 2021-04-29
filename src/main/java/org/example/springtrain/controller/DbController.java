package org.example.springtrain.controller;

import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springtrain.model.DataWithIndex;
import org.example.springtrain.model.DataWithoutIndex;
import org.example.springtrain.repository.DataWithIndexRepository;
import org.example.springtrain.repository.DataWithoutIndexRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/unsafe/db")
@RequiredArgsConstructor
public class DbController {

    private final DataWithoutIndexRepository dataWithoutIndexRepository;
    private final DataWithIndexRepository dataWithIndexRepository;
    private final EntityManager entityManager;

    @GetMapping("/ping")
    public String ping() {
        return "DbController ping method called";
    }

    @GetMapping("/get-data-with-two-methods")
    public String getDataWithTwoMethods(@RequestParam int size) {
        log.info("getDataWithTwoMethods called, size = {}", size);
        dataWithoutIndexRepository.deleteAll();
        dataWithoutIndexRepository.flush();
        dataWithIndexRepository.deleteAll();
        dataWithIndexRepository.flush();
        entityManager.clear();
        //todo разобраться с ошибкой с дубликатами id в Hibernate-контексте даже после удаления записей из БД

        String createReport = createData(size);
        String searchReport = searchData(size);

        return "getDataWithTwoMethods called, size = " + size + "\n" + createReport + searchReport;
    }

    private String createData(int size) {
        long insertTimeWithoutIndex = 0;
        long insertTimeWithIndex = 0;
        for (int i = 0; i < size; i++) {
            long f1 = (int) (Math.random() * 100);
            long f2 = (int) (Math.random() * 100);

//            DataWithoutIndex dataWithoutIndex = new DataWithoutIndex();
//            dataWithoutIndex.setId(i);
//            dataWithoutIndex.setF1(f1);
//            dataWithoutIndex.setF2(f2);
//            long t1WithoutIndex = System.currentTimeMillis();
//            dataWithoutIndexRepository.save(dataWithoutIndex);
//            long t2WithoutIndex = System.currentTimeMillis();
//            insertTimeWithoutIndex += t2WithoutIndex - t1WithoutIndex;

            DataWithIndex dataWithIndex = new DataWithIndex();
            dataWithIndex.setId(i);
            dataWithIndex.setF1(f1);
            dataWithIndex.setF2(f2);
            long t1WithIndex = System.currentTimeMillis();
            dataWithIndexRepository.save(dataWithIndex);
            long t2WithIndex = System.currentTimeMillis();
            insertTimeWithIndex += t2WithIndex - t1WithIndex;

            DataWithoutIndex dataWithoutIndex = new DataWithoutIndex();
            dataWithoutIndex.setId(i);
            dataWithoutIndex.setF1(f1);
            dataWithoutIndex.setF2(f2);
            long t1WithoutIndex = System.currentTimeMillis();
            dataWithoutIndexRepository.save(dataWithoutIndex);
            long t2WithoutIndex = System.currentTimeMillis();
            insertTimeWithoutIndex += t2WithoutIndex - t1WithoutIndex;
        }
        return "insert time without index = " + insertTimeWithoutIndex + ", insert time with index = " + insertTimeWithIndex + "\n";
    }

    private String searchData(int size) {
        long searchTimeWithoutIndex = 0;
        long searchTimeWithIndex = 0;
        for (int i = 0; i < size; i++) {
            long searchedValue = (int) (Math.random() * 100);

//            long t1WithoutIndex = System.currentTimeMillis();
//            dataWithoutIndexRepository.findByF1(searchedValue);
//            long t2WithoutIndex = System.currentTimeMillis();
//            searchTimeWithoutIndex += t2WithoutIndex - t1WithoutIndex;

            long t1WithIndex = System.currentTimeMillis();
            dataWithIndexRepository.findByF1(searchedValue);
            long t2WithIndex = System.currentTimeMillis();
            searchTimeWithIndex += t2WithIndex - t1WithIndex;

            long t1WithoutIndex = System.currentTimeMillis();
            dataWithoutIndexRepository.findByF1(searchedValue);
            long t2WithoutIndex = System.currentTimeMillis();
            searchTimeWithoutIndex += t2WithoutIndex - t1WithoutIndex;
        }
        return "search time without index = " + searchTimeWithoutIndex + ", search time with index = " + searchTimeWithIndex + "\n";
    }
}
