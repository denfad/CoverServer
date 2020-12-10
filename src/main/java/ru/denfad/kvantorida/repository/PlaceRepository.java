package ru.denfad.kvantorida.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.denfad.kvantorida.models.Place;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    @Query("select p from Place p where p.x_cor =:x and p.y_cor = :y")
    Place getPlaceByXAndY(@Param("x") double x, @Param("y") double y);
}
