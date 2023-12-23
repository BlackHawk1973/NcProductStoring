package org.top.ncproductstoring.entity;

import jakarta.persistence.*;

import java.sql.Date;

//Таблица содержимого акта о браке
@Entity
@Table(name = "act_item_t")
public class ActItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Номер партии
    @Column(name = "lot_number_f")
    private String lotNumber;

    //Дата производства
    @Column(name = "product_date", nullable = false)
    private Date productDate;

    //Участок
    @ManyToOne
    @JoinColumn(name = "id_sector_f", nullable = false)
    private Sector sector;

    //Технологическая операция
    @ManyToOne
    @JoinColumn(name = "id_tech_operation_f", nullable = false)
    private TechOperation techOperation;

    //Бригада
    @ManyToOne
    @JoinColumn(name = "id_team_f", nullable = false)
    private Team team;

    //Тип продукции
    @ManyToOne
    @JoinColumn(name = "id_product_f", nullable = false)
    private Production production;

    //Количество лент (лент в секции)
    @Column(name = "quantity_f")
    private Integer quantity;

    //Вес
    @Column(name = "weight_f", nullable = false)
    private Double weight;

    //Вид брака
    @ManyToOne
    @JoinColumn(name = "id_nc_product_type_f", nullable = false)
    private NcProductType ncProductType;

    //Причина брака
    @ManyToOne
    @JoinColumn(name = "id_nc_product_cause_f", nullable = false)
    private NcProductCause ncProductCause;

    //Акт о браке
    @ManyToOne
    @JoinColumn(name = "id_defective_act_f", nullable = false)
    private DefectiveAct defectiveAct;

    //Конструктор
    public ActItem() {
        id = 0L;
        lotNumber = "";
        productDate = null;
        sector = null;
        techOperation = null;
        team = null;
        production = null;
        quantity = 0;
        weight = 0.0;
        ncProductType = null;
        ncProductCause = null;
        defectiveAct = null;
    }

    //Геттеры и сеттеры


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public TechOperation getTechOperation() {
        return techOperation;
    }

    public void setTechOperation(TechOperation techOperation) {
        this.techOperation = techOperation;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public NcProductType getNcProductType() {
        return ncProductType;
    }

    public void setNcProductType(NcProductType ncProductType) {
        this.ncProductType = ncProductType;
    }

    public NcProductCause getNcProductCause() {
        return ncProductCause;
    }

    public void setNcProductCause(NcProductCause ncProductCause) {
        this.ncProductCause = ncProductCause;
    }

    public DefectiveAct getDefectiveAct() {
        return defectiveAct;
    }

    public void setDefectiveAct(DefectiveAct defectiveAct) {
        this.defectiveAct = defectiveAct;
    }

    @Override
    public String toString() {
        return "| id= " + id + " | lotNumber= " + lotNumber + " | date= " + productDate + " | quantity= "
                + quantity + " | weight= " + weight + " |";
    }
}
