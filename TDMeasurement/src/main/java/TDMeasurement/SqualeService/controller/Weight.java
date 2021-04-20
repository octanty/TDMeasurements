package TDMeasurement.SqualeService.controller;

import java.util.HashMap;
import java.util.Map;

enum Weight {
    HIGH(30),
    MEDIUM(9),
    SOFT(3);

    private int value;
    private static Map map = new HashMap<>();

    private Weight(int value) {
        this.value = value;
    }

    static {
        for (Weight weight : Weight.values()) {
            map.put(weight.value, weight);
        }
    }

    public static Weight valueOf(int weight) {
        return (Weight) map.get(weight);
    }

    public int getValue() {
        return value;
    }
}
