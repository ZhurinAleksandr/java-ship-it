package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel {

    private static final int STANDARD_PARCEL_BASE_COST = 2;


    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int calculateDeliveryCost() {
        return getWeight() * STANDARD_PARCEL_BASE_COST;
    }

}
