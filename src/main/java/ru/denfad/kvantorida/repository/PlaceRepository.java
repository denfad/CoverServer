package ru.denfad.kvantorida.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.denfad.kvantorida.models.Place;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    @Query("select p from Place p where p.x_cor =:x and p.y_cor = :y")
    Place getPlaceByXAndY(@Param("x") double x, @Param("y") double y);

    @Query("select p.id from Place p")
    List<Integer> getAllPlaceIds();

    @Query("select p from Place p where p.type = :type")
    List<Place> getPlacesByType(@Param("type") String type);


    @Query(
            value =  "SELECT * FROM places p WHERE POWER(:x - p.x_cor,2)+POWER(:y - p.y_cor,2) < 0.0025 AND p.type =:type ",
            nativeQuery = true
    )
    List<Place> getNearPlaces(@Param("x") double x, @Param("y") double y, @Param("type") String type);
}
