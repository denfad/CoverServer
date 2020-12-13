package ru.denfad.kvantorida.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "places")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "coefficient")
    private Double coefficient;

    @Column(name = "x_cor")
    private double x_cor;

    @Column(name = "y_cor")
    private double y_cor;

    @ManyToMany(mappedBy = "places", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Person> persons = new ArrayList<>();

    public Place() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public double getX_cor() {
        return x_cor;
    }

    public void setX_cor(double x_cor) {
        this.x_cor = x_cor;
    }

    public double getY_cor() {
        return y_cor;
    }

    public void setY_cor(double y_cor) {
        this.y_cor = y_cor;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person p){
        persons.add(p);
    }
}
