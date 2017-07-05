package my.rest.application.domain.entity;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author abogaichuk
 */
public enum City {
    KIEV, LVIV, ODESSA, KHARKIV, DNIPRO;

    public static City getRandom() {
        return values()[ThreadLocalRandom.current().nextInt(values().length)];
    }
}
