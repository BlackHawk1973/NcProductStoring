package org.top.ncproductstoring.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.Set;

//Таблица актов о браке
@Entity
@Table (name = "defective_act_t")
public class DefectiveAct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Номер акта о браке
    @Column (name = "number_f", nullable = false, unique = true)
    private String number;

    //Дата составления акта о браке
    @Column (name = "date_f", nullable = false)
//    @DateTimeFormat(pattern = "dd.MM.yyy")
    private Date date;

    //Член комиссии №1
//    @Column(name = "id_worker1_f", nullable = false)
//    private Integer idWorker1;

    @ManyToOne
    @JoinColumn(name = "id_worker1_f", nullable = false)
    private Worker worker1;

    //Член комиссии №2
//    @Column(name = "id_worker2_f", nullable = false)
//    private Integer idWorker2;

    @ManyToOne
    @JoinColumn(name = "id_worker2_f", nullable = false)
    private Worker worker2;

    //Член комиссии №3
//    @Column(name = "id_worker3_f", nullable = false)
//    private Integer idWorker3;

    @ManyToOne
    @JoinColumn(name = "id_worker3_f", nullable = false)
    private Worker worker3;

    //Член комиссии №4
//    @Column(name = "id_worker4_f")
//    private Integer idWorker4;

    @ManyToOne
    @JoinColumn(name = "id_worker4_f", nullable = false)
    private Worker worker4;

    //Связь с таблицей содержимого акта о браке
    @OneToMany(mappedBy = "defectiveAct")
    private Set<ActItem> actItemSet;


    //Конструктор
    public DefectiveAct() {
        id=0;
        number="";
        date=null;
        worker1=null;
        worker2=null;
        worker3=null;
        worker4=null;
        actItemSet=null;
    }

    //Геттеры и сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Worker getWorker1() {
        return worker1;
    }

    public void setWorker1(Worker worker1) {
        this.worker1 = worker1;
    }

    public Worker getWorker2() {
        return worker2;
    }

    public void setWorker2(Worker worker2) {
        this.worker2 = worker2;
    }

    public Worker getWorker3() {
        return worker3;
    }

    public void setWorker3(Worker worker3) {
        this.worker3 = worker3;
    }

    public Worker getWorker4() {
        return worker4;
    }

    public void setWorker4(Worker worker4) {
        this.worker4 = worker4;
    }

    public Set<ActItem> getActItemSet() {
        return actItemSet;
    }

    public void setActItemSet(Set<ActItem> actItemSet) {
        this.actItemSet = actItemSet;
    }

    @Override
    public String toString() {
        return "| id= " + id + " | number= " + number + " | date=" + date + " |";
    }
}
