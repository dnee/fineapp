package ru.deev.fineapp.domain;

import javax.persistence.*;

//Таблица-список штрафов
@Entity
public class FineHistory {

    //Основной ключ
    //Номер штрафа
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //ВК на информацию о штрафе
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fine_fineId")
    private Fine fine;

    //ВК на автомобиль
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_licensePlate")
    private Car car;

    public FineHistory() {
    }

    public FineHistory(Fine fine, Car car) {
        this.fine = fine;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public Fine getFine() {
        return fine;
    }

    public Car getCar() {
        return car;
    }

}
