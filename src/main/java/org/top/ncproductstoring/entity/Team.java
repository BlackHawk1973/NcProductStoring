package org.top.ncproductstoring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "team_t")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Наименование производственного участка
    @Column(name = "name_f", nullable = false, unique = true)
    private String name;

    //Конструкторы
    public Team() {
        id = 0;
        name = "";
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

    @Override
    public String toString() {
        return "| id=" + id + "| name= " + name + " |";
    }
}
