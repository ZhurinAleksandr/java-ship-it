package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Parcel> trackableParcels = new ArrayList<>();
    private static final ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(10);
    private static final ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(13);
    private static final ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(11);


    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatusTrackableParcels();
                    break;
                case 5:
                    showBoxContains();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отследить все посылки, поддерживающие трекинг");
        System.out.println("5 — Показать содержимое выбранного типа коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        // При добавлении новой хрупкой посылки добавляйте её в этот список.
        System.out.println("Какой тип посылки Вы хотели бы отправить:" +
                "1 - Стандартная; 2 - Хрупкая; 3 - Скоропортящаяся");
        int parcelType = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите краткое описание");
        String description = scanner.nextLine();
        System.out.println("Введите вес посылки");
        int weight = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите адрес доставки");
        String deliveryAddress = scanner.nextLine();
        System.out.println("Введите день отправки посылки");
        int sendDay = scanner.nextInt();
        scanner.nextLine();

        if (parcelType == 1) {
            StandardParcel parcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
            allParcels.add(parcel);
            standardParcelBox.addParcel(parcel);
        } else if (parcelType == 2) {
            FragileParcel parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
            allParcels.add(parcel);
            trackableParcels.add(parcel);
            fragileParcelBox.addParcel(parcel);
        } else if (parcelType == 3) {
            System.out.println("Введите срок хранения посылки");
            int timeToLive = scanner.nextInt();
            scanner.nextLine();
            PerishableParcel parcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
            allParcels.add(parcel);
            perishableParcelBox.addParcel(parcel);
        } else {
            System.out.println("Неправильный код");
        }


    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int sum = 0;
        for (Parcel parcel : allParcels) {
            sum += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок составляет " + sum);
    }

    private static void reportStatusTrackableParcels() {
        for (Parcel parcel : trackableParcels) {
            if (parcel instanceof Trackable) {
                System.out.println("Введите место прибытия посылки");
                String newLocation = scanner.nextLine();
                ((Trackable) parcel).reportStatus(newLocation);
            }
        }
    }

    private static void showBoxContains() {
        // Показать содержимое коробки
        System.out.println("Введите тип коробки, содержимое которой необходимо посмотреть:" +
                "1 - Стандартная; 2 - Хрупкая; 3 - Скоропортящаяся");
        int boxType = scanner.nextInt();
        scanner.nextLine();
        if (boxType == 1) {
            for (Parcel parcel : standardParcelBox.getAllParcels()) {
                System.out.println(parcel.getDescription());
            }
        } else if (boxType == 2) {
            for (Parcel parcel : fragileParcelBox.getAllParcels()) {
                System.out.println(parcel.getDescription());
            }
        } else if (boxType == 3) {
            for (Parcel parcel : perishableParcelBox.getAllParcels()) {
                System.out.println(parcel.getDescription());
            }
        } else {
            System.out.println("Такого типа нет");
        }
    }

}

