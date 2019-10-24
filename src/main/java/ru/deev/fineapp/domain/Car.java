package ru.deev.fineapp.domain;

import javax.persistence.*;

//Таблица о зарегистрированных автомобилях
@Entity
public class Car {

    //Номер авто
    @Id
    private String licensePlate;

    //Модель авто
    private String carModel;

    //Марка авто
    private String carBrand;

    //ВК на водителя
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_driverId")
    private Driver owner;

    public Car() {
    }

    public Car(Driver owner, String licensePlate, String carBrand, String carModel) {
        this.owner = owner;
        this.licensePlate = licensePlate;
        this.carModel = carModel;
        this.carBrand = carBrand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public Driver getOwner() {
        return owner;
    }
}
