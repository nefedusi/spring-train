package org.example.springtrain.contoller;

import java.util.List;
import org.example.springtrain.model.Thing;
import org.example.springtrain.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThingController {

    private final ThingRepository thingRepository;

    @Autowired
    public ThingController(ThingRepository thingRepository) {
        this.thingRepository = thingRepository;
    }

    @GetMapping("/thing/all")
    public List<Thing> getAll() {
        return thingRepository.findAll();
    }
}
