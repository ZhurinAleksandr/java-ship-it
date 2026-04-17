package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryAppTest {

    @Test
    public void shouldReturnDeliveryCost20ForStandardParcel() {
        StandardParcel standardParcel = new StandardParcel("", 10, "", 1);
        assertEquals(20, standardParcel.calculateDeliveryCost());

    }

    @Test
    public void shouldReturnDeliveryCost30ForFragileParcel() {
        FragileParcel fragileParcel = new FragileParcel("", 10, "", 1);
        assertEquals(30, fragileParcel.calculateDeliveryCost());

    }

    @Test
    public void shouldReturnDeliveryCost40ForPerishableParcel() {
        PerishableParcel perishableParcel = new PerishableParcel("", 10, "", 1, 2);
        assertEquals(40, perishableParcel.calculateDeliveryCost());

    }

    @Test
    public void shouldReturnFalseIsExpired() {
        PerishableParcel perishableParcel = new PerishableParcel("", 10, "", 1, 2);
        assertFalse(perishableParcel.isExpired(3));
    }

    @Test
    public void shouldReturnTrueIsExpired() {
        PerishableParcel perishableParcel = new PerishableParcel("", 10, "", 1, 2);
        assertTrue(perishableParcel.isExpired(4));
    }

    @Test
    public void sholdAddParcel() {
        ParcelBox<Parcel> parcelBox = new ParcelBox<Parcel>(20);
        PerishableParcel perishableParcel = new PerishableParcel("", 10, "", 1, 2);
        StandardParcel standardParcel = new StandardParcel("", 10, "", 1);
        assertTrue(parcelBox.addParcel(perishableParcel));
        assertTrue(parcelBox.addParcel(standardParcel));
    }

    @Test
    public void sholdNotAddParcel() {
        ParcelBox<Parcel> parcelBox = new ParcelBox<Parcel>(20);
        PerishableParcel perishableParcel = new PerishableParcel("", 21, "", 1, 2);
        assertFalse(parcelBox.addParcel(perishableParcel));
    }

}
