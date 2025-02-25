package com.netcracker.library.utils;

import java.util.function.Consumer;

public class EntityUpdateUtil {

    /**
     * Generic method to update fields only if the new value is not null.
     * @param setter The setter method reference for the field.
     * @param newValue The new value to be set, if not null.
     * @param <T> The type of the field.
     */
    public static <T> void updateIfNotNull(Consumer<T> setter, T newValue) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }
}
