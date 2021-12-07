package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.web.page.TourOrderPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DBQuery.*;
import static ru.netology.web.data.DataGenerator.*;

public class TourOrderTest {
    private TourOrderPage orderPage;
    private int purchasePrice = 45000;
    private CardInfo fakeCard = getFakeCard();

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        orderPage = new TourOrderPage();
    }

    @Test
    public void shouldPaymentApprovedCard() {
        orderPage.purchase(getApprovedCard());
        orderPage.approved();
        assertEquals("APPROVED", getLastPaymentStatus());
        assertEquals(getLastOrderPaymentId(), getLastPaymentId());
        assertEquals(purchasePrice, getLastPaymentAmount());
    }

    @Test
    void shouldNotPaymentDeclinedCard() {
        val currentPaymentId = getLastOrderPaymentId();
        orderPage.purchase(getDeclinedCard());
        orderPage.declined();
        assertEquals("DECLINED", getLastPaymentStatus());
        assertEquals(currentPaymentId, getLastOrderPaymentId());
    }

    @Test
    void shouldNotPaymentFakeCard() {
        val lastOrderPaymentId = getLastOrderPaymentId();
        orderPage.purchase(fakeCard);
        orderPage.declined();
        assertEquals(lastOrderPaymentId, getLastOrderPaymentId());
    }

    @Test
    void shouldNotPaymentWrongMonth() {
        val lastOrderPaymentId = getLastOrderPaymentId();
        val wrongMonthCard = new CardInfo(fakeCard.getCardNumber(),
                "15",
                fakeCard.getYearExpired(),
                fakeCard.getCvc(),
                fakeCard.getOwnerName());
        orderPage.purchase(wrongMonthCard);
        assertEquals(lastOrderPaymentId, getLastOrderPaymentId());
        orderPage.wrongMonth();
    }

    @Test
    void shouldNotPaymentWrongYear() {
        val lastOrderPaymentId = getLastOrderPaymentId();
        val wrongYearCard = new CardInfo(fakeCard.getCardNumber(),
                fakeCard.getMonthExpired(),
                "00",
                fakeCard.getCvc(),
                fakeCard.getOwnerName());
        orderPage.purchase(wrongYearCard);
        assertEquals(lastOrderPaymentId, getLastOrderPaymentId());
        orderPage.wrongYear();
    }

    @Test
    void shouldNotPaymentWrongName() {
        val lastOrderPaymentId = getLastOrderPaymentId();
        val wrongNameCard = new CardInfo(fakeCard.getCardNumber(),
                fakeCard.getMonthExpired(),
                fakeCard.getYearExpired(),
                fakeCard.getCvc(),
                "Илон Маск");
        orderPage.purchase(wrongNameCard);
        assertEquals(lastOrderPaymentId, getLastOrderPaymentId());
        orderPage.wrongName();
    }

    @Test
    void shouldNotPaymentEmptyCardNumber() {
        val lastOrderPaymentId = getLastOrderPaymentId();
        val emptyNumberCard = new CardInfo("",
                fakeCard.getMonthExpired(),
                fakeCard.getYearExpired(),
                fakeCard.getCvc(),
                fakeCard.getOwnerName());
        orderPage.purchase(emptyNumberCard);
        assertEquals(lastOrderPaymentId, getLastOrderPaymentId());
        orderPage.emptyField();
    }

    @Test
    void shouldNotPaymentEmptyMonthExpire() {
        val lastOrderPaymentId = getLastOrderPaymentId();
        val emptyMonthCard = new CardInfo(fakeCard.getCardNumber(),
                "",
                fakeCard.getYearExpired(),
                fakeCard.getCvc(),
                fakeCard.getOwnerName());
        orderPage.purchase(emptyMonthCard);
        assertEquals(lastOrderPaymentId, getLastOrderPaymentId());
        orderPage.emptyField();
    }

    @Test
    void shouldNotPaymentEmptyYearExpire() {
        val lastOrderPaymentId = getLastOrderPaymentId();
        val emptyYearCard = new CardInfo(fakeCard.getCardNumber(),
                fakeCard.getMonthExpired(),
                "",
                fakeCard.getCvc(),
                fakeCard.getOwnerName());
        orderPage.purchase(emptyYearCard);
        assertEquals(lastOrderPaymentId, getLastOrderPaymentId());
        orderPage.emptyField();
    }

    @Test
    void shouldNotPaymentEmptyName() {
        val lastOrderPaymentId = getLastOrderPaymentId();
        val emptyNameCard = new CardInfo(fakeCard.getCardNumber(),
                fakeCard.getMonthExpired(),
                fakeCard.getYearExpired(),
                fakeCard.getCvc(),
                "");
        orderPage.purchase(emptyNameCard);
        assertEquals(lastOrderPaymentId, getLastOrderPaymentId());
        orderPage.emptyField();
    }

    @Test
    void shouldNotPaymentEmptyCVC() {
        val lastOrderPaymentId = getLastOrderPaymentId();
        val emptyCVCCard = new CardInfo(fakeCard.getCardNumber(),
                fakeCard.getMonthExpired(),
                fakeCard.getYearExpired(),
                "",
                fakeCard.getOwnerName());
        orderPage.purchase(emptyCVCCard);
        assertEquals(lastOrderPaymentId, getLastOrderPaymentId());
        orderPage.emptyField();
    }
}
