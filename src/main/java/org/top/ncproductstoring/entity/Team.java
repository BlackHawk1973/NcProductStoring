package org.top.ncproductstoring.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "team_t")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Наименование производственного участка
    @Column(name = "name_f", nullable = false, unique = true)
    private String name;

    //Связь с таблицей содержимого акта о браке
    @OneToMany(mappedBy = "team")
    private Set<ActItem> actItemSet;

    //Конструкторы
    public Team() {
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
