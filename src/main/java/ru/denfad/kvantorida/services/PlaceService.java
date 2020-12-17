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

    public Place findNearestPlace(double x, double y, String type){
        double rad = 80;
        Place pl = new Place();
        for(Place p: placeRepository.getNearPlaces(x,y,type)){
            if( Math.pow(p.getX_cor()-x,2)+Math.pow(p.getY_cor()-y,2)<rad){
                System.out.println( Math.pow(p.getX_cor()-x,2)+Math.pow(p.getY_cor()-y,2));
                rad = Math.pow(p.getX_cor()-x,2)+Math.pow(p.getY_cor()-y,2);
                pl = p;
            }
        }
        return pl;
    }

    public List<Integer> getPlacesId(){
        return placeRepository.getAllPlaceIds();
    }

    public List<Place> getPlacesByType(String type){ return placeRepository.getPlacesByType(type);}

}
