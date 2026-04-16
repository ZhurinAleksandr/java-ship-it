package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {

    private List<T> parcels;
    private int currentWeight = 0;
    private int maxBoxWeight;

    public ParcelBox(int maxParcelWeight) {
        this.parcels = new ArrayList<>();
        this.maxBoxWeight = maxParcelWeight;
    }

    public boolean addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() <= maxBoxWeight) {
            parcels.add(parcel);
            System.out.println("Посылка добавлена в коробку");
            currentWeight += parcel.getWeight();
            return true;
        } else {
            System.out.println("В коробке недостаточно места");
            return false;
        }
    }

    public List<T> getAllParcels() {
        return parcels;
    }

}
