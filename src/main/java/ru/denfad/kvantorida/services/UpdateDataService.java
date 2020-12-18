package ru.denfad.kvantorida.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.denfad.kvantorida.models.Person;
import ru.denfad.kvantorida.models.Place;

import java.util.*;


@EnableScheduling
@Component
@Transactional
public class UpdateDataService {

    private final int delay = 10000;
    private final PlaceService placeService;
    private final PersonService personService;
    private static Map<String, Double> statusMap = new HashMap<>();
    private static Map<String, Double> statusMapPlace = new HashMap<>();

    static {
        statusMap.put("Есть положительный тест", 0.65);
        statusMap.put("Есть отрицательный тест",0.0);
        statusMap.put("Ожидание результатов",0.1);
        statusMap.put("Потенциально контактировал",0.3);
        statusMap.put("Контактировал с больным",0.45);
        statusMap.put("Есть симптомы",0.4);

        statusMapPlace.put("Магазин",0.5);
        statusMapPlace.put("Аптека",0.75);
        statusMapPlace.put("Больница",0.9);
        statusMapPlace.put("Гос. учреждение",0.3);
        statusMapPlace.put("Общественное место",0.55);
        statusMapPlace.put("Школа",0.6);

    }

    @Autowired
    public UpdateDataService(PlaceService placeService, PersonService personService) {
        this.placeService = placeService;
        this.personService = personService;
    }

    @Scheduled(fixedRate = 86400000)
    public void update(){
        List<Integer> ids = placeService.getPlacesId();
        for(int id: ids){
            Place p = placeService.getPlaceById(id);
            double coef = statusMapPlace.get(p.getType());
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
}

