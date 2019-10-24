package ru.deev.fineapp.domain;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.Entity;
import javax.persistence.Id;

//Таблица владельцев автомобилей (согласно заданию сочетание фамилии, имени и отчества является уникальным значением).
@Entity
public class Driver {

    //Основной ключ
    //Уникальный идентификатор водителя (sha256hex Ф + И + О (все в нижнем регистре))
    @Id
    private String driverId;

    //Фамилия владельца
    private String secondName;

    //Имя владельца
    private String firstName;

    //Отчество владельца
    private String middleName;

    public Driver() {
    }

    public Driver(String secondName, String firstName, String middleName) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.driverId = DigestUtils.sha256Hex(secondName.toLowerCase() + firstName.toLowerCase() + middleName.toLowerCase());
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getDriverId() {
        return driverId;
    }
}
