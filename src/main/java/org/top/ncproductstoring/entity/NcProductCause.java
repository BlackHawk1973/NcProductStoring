package org.top.ncproductstoring.entity;

import jakarta.persistence.*;

//Справочник причин брака
@Entity
@Table(name = "nc_prod_cause_t")
public class NcProductCause {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Наименование причины брака
    @Column(name = "name_f", nullable = false, unique = true)
    private String name;

    //Конструкторы
    public NcProductCause() {
        id = 0;
        name = "";
    }

    public NcProductCause(Integer id, String name) {
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
