package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class TourOrderPage {
    private SelenideElement heading = $("h2").shouldHave(text("Путешествие"));
    private SelenideElement purchasePayment = $(withText("Купить"));
    private SelenideElement paymentDashboard = $(withText("Оплата по карте"));
    private SelenideElement purchaseCredit = $(withText("Купить в кредит"));
    private SelenideElement creditDashboard = $(withText("Кредит по данным карты"));
    private SelenideElement cardNumber = $(withText("Номер карты")).sibling(0).$(".input__control");
    private SelenideElement monthExpire = $(withText("Месяц")).sibling(0).$(".input__control");
    private SelenideElement yearExpire = $(withText("Год")).sibling(0).$(".input__control");
    private SelenideElement owner = $(withText("Владелец")).sibling(0).$(".input__control");
    private SelenideElement cvc = $(withText("CVC/CVV")).sibling(0).$(".input__control");
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

    public void purchase(DataGenerator.CardInfo card) {
        purchasePayment.click();
        paymentDashboard.shouldBe(visible, Duration.ofSeconds(4));
        cardNumber.setValue(card.getCardNumber());
        monthExpire.setValue(card.getMonthExpired());
        yearExpire.setValue(card.getYearExpired());
        owner.setValue(card.getOwnerName());
        cvc.setValue(card.getCvc());
        submit.click();
    }

    public void credit(DataGenerator.CardInfo card) {
        purchaseCredit.click();
        creditDashboard.shouldBe(visible, Duration.ofSeconds(4));
        cardNumber.setValue(card.getCardNumber());
        monthExpire.setValue(card.getMonthExpired());
        yearExpire.setValue(card.getYearExpired());
        owner.setValue(card.getOwnerName());
        cvc.setValue(card.getCvc());
        submit.click();
    }

    public void approved() {
        request.shouldBe(visible, Duration.ofSeconds(4));
        approvedPurchase.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void declined() {
        request.shouldBe(visible, Duration.ofSeconds(4));
        declinedPurchase.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void wrongMonth() {
        request.shouldNotBe(visible, Duration.ofSeconds(4));
        wrongMonthError.shouldBe(visible, Duration.ofSeconds(3));
    }

    public void wrongYear() {
        request.shouldNotBe(visible, Duration.ofSeconds(4));
        wrongYearError.shouldBe(visible, Duration.ofSeconds(3));
    }

    public void wrongName() {
        request.shouldNotBe(visible, Duration.ofSeconds(4));
        wrongNameError.shouldBe(visible, Duration.ofSeconds(3));
    }

    public void emptyField() {
        request.shouldNotBe(visible, Duration.ofSeconds(4));
        emptyFieldError.shouldBe(visible, Duration.ofSeconds(3));
    }
}
