package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum State {
    PICKUP("Pickup"),

    INTRANSPORT("InTransport"),

    INTRUCKDELIVERY("InTruckDelivery"),

    TRANSFERRED("Transferred"),

    DELIVERED("Delivered");

    private String value;
}
