package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataGenerator;
import ru.netology.web.page.TourOrderPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DBQuery.*;
import static ru.netology.web.data.DataGenerator.*;

public class TourOrderCreditTest {
    private TourOrderPage orderPage;
    private DataGenerator.CardInfo fakeCard = getFakeCard();

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        orderPage = new TourOrderPage();
    }

    @Test
    public void shouldPaymentApprovedCard() {
        orderPage.credit(getApprovedCard());
        orderPage.approved();
        assertEquals("APPROVED", getLastCreditStatus());
        assertEquals(getLastOrderCreditId(), getLastCreditId());
    }

    @Test
    void shouldNotPaymentDeclinedCard() {
        val lastCreditId = getLastOrderCreditId();
        orderPage.credit(getDeclinedCard());
        orderPage.declined();
        assertEquals("DECLINED", getLastCreditStatus());
        assertEquals(lastCreditId, getLastOrderCreditId());
    }

    @Test
    void shouldNotPaymentFakeCard() {
        val lastCreditId = getLastOrderCreditId();
        orderPage.credit(fakeCard);
        orderPage.declined();
        assertEquals(lastCreditId, getLastOrderCreditId());
    }

    @Test
    void shouldNotPaymentWrongMonth() {
        val lastCreditId = getLastOrderCreditId();
        val wrongMonthCard = new CardInfo(fakeCard.getCardNumber(),
                "15",
                fakeCard.getYearExpired(),
                fakeCard.getCvc(),
                fakeCard.getOwnerName());
        orderPage.credit(wrongMonthCard);
        assertEquals(lastCreditId, getLastOrderCreditId());
        orderPage.wrongMonth();
    }

    @Test
    void shouldNotPaymentWrongYear() {
        val lastCreditId = getLastOrderCreditId();
        val wrongYearCard = new CardInfo(fakeCard.getCardNumber(),
                fakeCard.getMonthExpired(),
                "00",
                fakeCard.getCvc(),
                fakeCard.getOwnerName());
        orderPage.credit(wrongYearCard);
        assertEquals(lastCreditId, getLastOrderCreditId());
        orderPage.wrongYear();
    }

    @Test
    void shouldNotPaymentWrongName() {
        val lastCreditId = getLastOrderCreditId();
        val wrongNameCard = new CardInfo(fakeCard.getCardNumber(),
                fakeCard.getMonthExpired(),
                fakeCard.getYearExpired(),
                fakeCard.getCvc(),
                "Илон Маск");
        orderPage.credit(wrongNameCard);
        assertEquals(lastCreditId, getLastOrderCreditId());
        orderPage.wrongName();
    }

    @Test
    void shouldNotPaymentEmptyCardNumber() {
        val lastCreditId = getLastOrderCreditId();
        val emptyNumberCard = new CardInfo("",
                fakeCard.getMonthExpired(),
                fakeCard.getYearExpired(),
                fakeCard.getCvc(),
                fakeCard.getOwnerName());
        orderPage.credit(emptyNumberCard);
        assertEquals(lastCreditId, getLastOrderCreditId());
        orderPage.emptyField();
    }

    @Test
    void shouldNotPaymentEmptyMonthExpire() {
        val lastCreditId = getLastOrderCreditId();
        val emptyMonthCard = new CardInfo(fakeCard.getCardNumber(),
                "",
                fakeCard.getYearExpired(),
                fakeCard.getCvc(),
                fakeCard.getOwnerName());
        orderPage.credit(emptyMonthCard);
        assertEquals(lastCreditId, getLastOrderCreditId());
        orderPage.emptyField();
    }

    @Test
    void shouldNotPaymentEmptyYearExpire() {
        val lastCreditId = getLastOrderCreditId();
        val emptyYearCard = new CardInfo(fakeCard.getCardNumber(),
                fakeCard.getMonthExpired(),
                "",
                fakeCard.getCvc(),
                fakeCard.getOwnerName());
        orderPage.credit(emptyYearCard);
        assertEquals(lastCreditId, getLastOrderCreditId());
        orderPage.emptyField();
    }

    @Test
    void shouldNotPaymentEmptyName() {
        val lastCreditId = getLastOrderCreditId();
        val emptyNameCard = new CardInfo(fakeCard.getCardNumber(),
                fakeCard.getMonthExpired(),
                fakeCard.getYearExpired(),
                fakeCard.getCvc(),
                "");
        orderPage.credit(emptyNameCard);
        assertEquals(lastCreditId, getLastOrderCreditId());
        orderPage.emptyField();
    }

    @Test
    void shouldNotPaymentEmptyCVC() {
        val lastCreditId = getLastOrderCreditId();
        val emptyCVCCard = new CardInfo(fakeCard.getCardNumber(),
                fakeCard.getMonthExpired(),
                fakeCard.getYearExpired(),
                "",
                fakeCard.getOwnerName());
        orderPage.credit(emptyCVCCard);
        assertEquals(lastCreditId, getLastOrderCreditId());
        orderPage.emptyField();
    }
}
