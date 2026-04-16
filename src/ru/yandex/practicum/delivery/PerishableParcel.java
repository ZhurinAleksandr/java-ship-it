package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel { //скоропортящаяся посылка

    private static final int PERISHABLE_PARCEL_BASE_COST = 4;


    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public boolean isExpired(int currentDay) {
        if ((getSendDay() + getTimeToLive()) >= currentDay) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int calculateDeliveryCost() {
        return getWeight() * PERISHABLE_PARCEL_BASE_COST;
    }
}

