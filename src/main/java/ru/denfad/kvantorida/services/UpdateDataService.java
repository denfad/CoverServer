package ru.denfad.kvantorida.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.denfad.kvantorida.models.Place;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Transactional
@Service
public class UpdateDataService {

    private final int delay = 1000;
    private final PlaceService placeService;

    @Autowired
    public UpdateDataService(PlaceService placeService) {
        this.placeService = placeService;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<Integer> ids = placeService.getPlacesId();
                for(int id: ids){
                    Place p = placeService.getPlaceById(id);

                }
            }
        }, delay, delay);
    }
}

