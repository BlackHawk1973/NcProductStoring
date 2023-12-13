package org.top.ncproductstoring.entity;

import jakarta.persistence.*;

import java.sql.Date;

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
    private Date date;

    //Член комиссии №1
    @Column(name = "id_worker1_f", nullable = false)
    private Integer idWorker1;

    //Член комиссии №2
    @Column(name = "id_worker2_f", nullable = false)
    private Integer idWorker2;

    //Член комиссии №3
    @Column(name = "id_worker3_f", nullable = false)
    private Integer idWorker3;

    //Член комиссии №4
    @Column(name = "id_worker4_f")
    private Integer idWorker4;


    //Конструктор
    public DefectiveAct() {
        id=0;
        number="";
        date=null;
        idWorker1=0;
        idWorker2=0;
        idWorker3=0;
        idWorker4=0;
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

    public Integer getIdWorker1() {
        return idWorker1;
    }

    public void setIdWorker1(Integer idWorker1) {
        this.idWorker1 = idWorker1;
    }

    public Integer getIdWorker2() {
        return idWorker2;
    }

    public void setIdWorker2(Integer idWorker2) {
        this.idWorker2 = idWorker2;
    }

    public Integer getIdWorker3() {
        return idWorker3;
    }

    public void setIdWorker3(Integer idWorker3) {
        this.idWorker3 = idWorker3;
    }

    public Integer getIdWorker4() {
        return idWorker4;
    }

    public void setIdWorker4(Integer idWorker4) {
        this.idWorker4 = idWorker4;
    }

    @Override
    public String toString() {
        return "| id= " + id + " | number= " + number + " | date=" + date + " |";
    }
}
