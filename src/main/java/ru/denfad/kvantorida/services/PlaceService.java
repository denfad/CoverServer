package ru.denfad.kvantorida.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.denfad.kvantorida.models.Place;
import ru.denfad.kvantorida.repository.PlaceRepository;

import java.util.List;

@Service
@Transactional
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place addPlace(Place p){
        return placeRepository.save(p);
    }

    public List<Place> getAllPlaces(){
        return placeRepository.findAll();
    }

    public Place getPlace(double x, double y){
        return placeRepository.getPlaceByXAndY(x, y);
    }

    public Place getPlaceById(int id){
        return placeRepository.getOne(id);
    }

    public Place findNearestPlace(double x, double y){
        return null;
    }

    public List<Integer> getPlacesId(){
        return placeRepository.getAllPlaceIds();
    }

}
