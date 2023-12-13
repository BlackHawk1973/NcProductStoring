package org.top.ncproductstoring.entity;

import jakarta.persistence.*;

//Справочник сотрудников
@Entity
@Table(name = "worker_t")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name_f", nullable = false)
    String name;

    @Column(name = "post_f", nullable = false)
    String post;

    //Конструктор
    public Worker() {
        id = 0;
        name = "";
        post = "";
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "| id=" + id + "| name= " + name + " | post= " + post + "|";
    }
}