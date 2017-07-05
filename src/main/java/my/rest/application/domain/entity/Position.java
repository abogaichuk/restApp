package my.rest.application.domain.entity;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author abogaichuk
 */
public enum Position {
    WORKER, SECRETARY, MANAGER;

    public static Position getRandom() {
        return values()[ThreadLocalRandom.current().nextInt(values().length)];
    }
}
