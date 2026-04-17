package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable { //хрупкая посылка

    private static final int FRAGILE_PARCEL_BASE_COST = 3;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + getDescription() + " обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка " + getDescription() + " изменила местоположение на " + newLocation);
    }

    @Override
    public int calculateDeliveryCost() {
        return getWeight() * FRAGILE_PARCEL_BASE_COST;
    }
}
