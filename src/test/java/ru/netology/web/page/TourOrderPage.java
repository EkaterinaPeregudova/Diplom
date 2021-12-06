package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class TourOrderPage {
    private SelenideElement heading = $("h2").shouldHave(text("Путешествие"));
    private SelenideElement purchasePayment = $(withText("Купить"));
    private SelenideElement paymentDashboard = $(withText("Оплата по карте"));
    private SelenideElement purchaseCredit = $(withText("Купить в кредит"));
    private SelenideElement creditDashboard = $(withText("Кредит по данным карты"));
    private SelenideElement cardNumber = $$(".input__control").get(0);
    private SelenideElement monthExpire = $$(".input__control").get(1);
    private SelenideElement yearExpire = $$(".input__control").get(2);
    private SelenideElement owner = $$(".input__control").get(3);
    private SelenideElement cvc = $$(".input__control").get(4);
    private SelenideElement submit = $(".form-field .button");
    private SelenideElement approvedPurchase = $(withText("Операция одобрена Банком."));
    private SelenideElement declinedPurchase = $(withText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement wrongMonthError = $(withText("Неверно указан срок действия карты"));
    private SelenideElement wrongYearError = $(withText("Истёк срок действия карты"));
    private SelenideElement wrongNameError = $(withText("Неверно указано имя владельца карты"));
    private SelenideElement emptyFieldError = $(withText("Поле обязательно для заполнения"));
    private SelenideElement request = $(withText("Отправляем запрос"));

    public TourOrderPage() {
        heading.shouldBe(visible);
    }
}
