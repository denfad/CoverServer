package ru.denfad.kvantorida.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.denfad.kvantorida.models.Person;
import ru.denfad.kvantorida.models.Place;

import java.util.*;

@Transactional
@Service
public class UpdateDataService {

    private final int delay = 86400000;
    private final PlaceService placeService;
    private final PersonService personService;
    private static Map<String, Double> statusMap = new HashMap<>();

    static {
        statusMap.put("Есть положительный тест", 0.65);
        statusMap.put("Есть отрицательный тест",0.0);
        statusMap.put("Ожидание результатов",0.1);
        statusMap.put("Потенциально контактировал",0.3);
        statusMap.put("Контактировал с больным",0.45);
        statusMap.put("Есть симптомы",0.4);

    }

    @Autowired
    public UpdateDataService(PlaceService placeService, PersonService personService) {
        this.placeService = placeService;
        this.personService = personService;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<Integer> ids = placeService.getPlacesId();
                for(int id: ids){
                    Place p = placeService.getPlaceById(id);
                    double coef = p.getCoefficient();
                    double mid = 0;
                    for(Person pers: p.getPersons()){
                        mid = mid + statusMap.get(pers.getStatus());
                    }
                    mid = mid / p.getPersons().size();
                    coef = coef + mid + Double.valueOf(personService.getIllPersons("Есть положительный тест") )/ 100000;
                    p.setCoefficient(coef);
                    placeService.addPlace(p);
                }
            }
        }, delay, delay);
    }
}

