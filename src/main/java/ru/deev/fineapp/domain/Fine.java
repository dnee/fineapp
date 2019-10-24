package ru.deev.fineapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Таблица-справочник по штрафам
@Entity
public class Fine {

    //Основной ключ
    //Номер правонарушения
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fineId;

    //Описание правонарушения
    private String fineDescription;

    //Размер пошлины
    private int fineTax;

    public Fine() {
    }

    public Fine(String fineDescription, int fineTax) {
        this.fineDescription = fineDescription;
        this.fineTax = fineTax;
    }

    public Long getFineId() {
        return fineId;
    }

    public String getFineDescription() {
        return fineDescription;
    }

    public int getFineTax() {
        return fineTax;
    }
}
