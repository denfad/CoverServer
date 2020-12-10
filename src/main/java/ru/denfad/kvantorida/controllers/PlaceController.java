package ru.denfad.kvantorida.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.denfad.kvantorida.models.Place;
import ru.denfad.kvantorida.services.PlaceService;

import java.util.List;

@RestController
@RequestMapping(path = "/places")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping(path = "/", produces = "application/json")
    public List<Place> getAllPlaces(){
        return placeService.getAllPlaces();
    }

    @PostMapping(path = "/add", consumes = "application/json",produces =  "application/json")
    public Place addPlace(@RequestBody Place place){
        return placeService.addPlace(place);
    }

    @PostMapping(path = "/", produces = "application/json")
    public Place getPlace(@RequestParam double x, @RequestParam double y){
        return  placeService.getPlace(x,y);
    }
}
