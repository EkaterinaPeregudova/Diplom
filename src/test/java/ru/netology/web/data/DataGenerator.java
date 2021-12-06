package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    @Value
    public static class CardInfo {
        private String cardNumber;
        private String monthExpired;
        private String yearExpired;
        private String cvc;
        private String ownerName;
    }

    public static String[] getFakes() {
        String[] data = new String[4];
        val faker = new Faker();
        data[0] = faker.business().creditCardNumber();                                      //получаем номер карты
        data[1] = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("MM"));  //получаем месяц карты
        data[2] = LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));   //получаем год карты
        data[3] = faker.number().digits(3);                                           //получаем cvc/cvv карты
        data[4] = faker.name().firstName() + " " + faker.name().lastName();                 //получаем имя владельца
        return data;
    }

    public static CardInfo getApprovedCard() {
        String[] data = getFakes();
        return new CardInfo("4444 4444 4444 4441", data[1], data[2], data[3], data[4]);
    }

    public static CardInfo getDeclinedCard() {
        String[] data = getFakes();
        return new CardInfo("4444 4444 4444 4442", data[1], data[2], data[3], data[4]);
    }

    public static CardInfo getFakeCard() {
        String[] data = getFakes();
        return new CardInfo(data[0], data[1], data[2], data[3], data[4]);
    }
}
