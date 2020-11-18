package org.example.springtrain.controller;

import java.util.List;
import org.example.springtrain.model.Thing;
import org.example.springtrain.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThingController {

    private final ThingRepository thingRepository;

    @Autowired //unnecessary
    public ThingController(ThingRepository thingRepository) {
        this.thingRepository = thingRepository;
    }

    //curl -i http://localhost:8080/thing/all
    //curl -i -u user:userpass http://localhost:8080/thing/all
    //Помнить, что при повторном запросе от того же пользователя, но с другим паролем, доступ к эндпоинту будет! Т.к. отправляется cookie JSESSIONID
    @GetMapping("/thing/all")
    public List<Thing> getAll() {
        return thingRepository.findAll();
    }

    @GetMapping("/unsafe/some")
    public String unsafeAnother() {
        return "Some unsafe endpoint";
    }
}
