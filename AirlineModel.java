package avion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirlineModel {

    private Map<String, List<Integer>> reservations;
    private Map<String, List<Integer>> purchases;

    public AirlineModel() {
        reservations = new HashMap<>();
        purchases = new HashMap<>();
    }

    public List<Integer> getAvailableSeats(String category) {
        List<Integer> availableSeats = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            availableSeats.add(i);
        }
        List<Integer> reservedSeats = reservations.get(category);
        if (reservedSeats != null) {
            availableSeats.removeAll(reservedSeats);
        }
        List<Integer> purchasedSeats = purchases.get(category);
        if (purchasedSeats != null) {
            availableSeats.removeAll(purchasedSeats);
        }
        return availableSeats;
    }

    public void reserveSeats(String category, String customerId, List<Integer> seats) {
        List<Integer> reservedSeats = reservations.get(category);
        if (reservedSeats == null) {
            reservedSeats = new ArrayList<>();
            reservations.put(category, reservedSeats);
        }
        reservedSeats.addAll(seats);
    }

    public void cancelReservation(String customerId) {
        for (List<Integer> reservedSeats : reservations.values()) {
            if (reservedSeats.removeIf(seat -> seat == customerId.hashCode())) {
                return;
            }
        }
    }

    public void purchaseSeats(String category, String customerId, List<Integer> seats) {
        List<Integer> purchasedSeats = purchases.get(category);
        if (purchasedSeats == null) {
            purchasedSeats = new ArrayList<>();
            purchases.put(category, purchasedSeats);
        }
        purchasedSeats.addAll(seats);
    }

    public Integer getReservationCost(String category, String customerId) {
        List<Integer> reservedSeats = reservations.get(category);
        if (reservedSeats != null) {
            return reservedSeats.stream().filter(seat -> seat == customerId.hashCode()).mapToInt(seat -> 100).sum();
        }
        return null;
    }

    public Integer getPurchaseCost(String category, String customerId) {
        List<Integer> purchasedSeats = purchases.get(category);
        if (purchasedSeats != null) {
            return purchasedSeats.stream().filter(seat -> seat == customerId.hashCode()).mapToInt(seat -> 150).sum();
        }
        return null;
    }

    public List<Integer> getReservedSeats(String customerId) {
        List<Integer> reservedSeats = new ArrayList<>();
        for (List<Integer> seats : reservations.values()) {
            reservedSeats.addAll(seats.stream().filter(seat -> seat == customerId.hashCode()).toList());
        }
        return reservedSeats.isEmpty() ? null : reservedSeats;
    }

    public List<Integer> getPurchasedSeats(String customerId) {
        List<Integer> purchasedSeats = new ArrayList<>();
        for (List<Integer> seats : purchases.values()) {
            purchasedSeats.addAll(seats.stream().filter(seat -> seat == customerId.hashCode()).toList());
        }
        return purchasedSeats.isEmpty() ? null : purchasedSeats;
    }
}