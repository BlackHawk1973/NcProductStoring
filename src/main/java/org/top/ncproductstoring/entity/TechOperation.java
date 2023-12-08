package org.top.ncproductstoring.entity;

import jakarta.persistence.*;

//Справочник видов технологических операций
@Entity
@Table(name = "tech-operation_t")
public class TechOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Наименование вида продукции
    @Column(name = "name_f", nullable = false, unique = true)
    private String name;

    //Конструкторы
    public TechOperation() {
        id = 0;
        name = "";
    }

    public TechOperation(Integer id, String name) {
        this.id = id;
        this.name = name;
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
