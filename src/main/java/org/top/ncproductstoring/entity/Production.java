package org.top.ncproductstoring.entity;

import jakarta.persistence.*;

import java.util.Set;

//Справочник видов продукции
@Entity
@Table(name = "production_t")
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Наименование вида продукции
    @Column(name = "name_f", nullable = false, unique = true)
    private String name;

    //Связь с таблицей содержимого акта о браке
    @OneToMany(mappedBy = "production")
    private Set<ActItem> actItemSet;

    //Конструкторы
    public Production() {
        id = 0;
        name = "";
        actItemSet = null;
    }

    //Геттеры и сеттеры
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

    public Set<ActItem> getActItemSet() {
        return actItemSet;
    }

    public void setActItemSet(Set<ActItem> actItemSet) {
        this.actItemSet = actItemSet;
    }

    @Override
    public String toString() {
        return "| id=" + id + "| name= " + name + " |";
    }
}
