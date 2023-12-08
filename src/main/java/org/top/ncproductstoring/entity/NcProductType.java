package org.top.ncproductstoring.entity;

import jakarta.persistence.*;

//Справочник видов брака
@Entity
@Table(name = "nc_prod_type_t")
public class NcProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Код вида брака, согласно ТУ
    @Column(name = "code_f", nullable = false, unique = true)
    private Integer code;

    //Тип брака
    @Column(name = "type_f", nullable = false)
    private String type;

    //Наименование вида брака
    @Column(name = "name_f", nullable = false)
    private String name;

    //Конструкторы
    public NcProductType() {
        id = 0;
        code = 0;
        type = "";
        name = "";
    }

    //Геттеры и сеттеры

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //toString

    @Override
    public String toString() {
        return "id - " + id + "| code - " + code + "| type - " + type + "| name - " + name;
    }
}