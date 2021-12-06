package io.github.bloepiloepi.pvp.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.lang.reflect.Field;

public class ReflectionUtils {

    // map the field name to the Field object if it's cached
    private static final Cache<String, Field> fieldCache = Caffeine.newBuilder().build();

    private static Field accessField(Object object, String fieldName) {
        return fieldCache.get(object.getClass().getName() + "." + fieldName, (key) -> {
            Class<?> clazz = object.getClass();
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public static Object getDeclaredField(Object object, String fieldName) {
        try {
            return accessField(object, fieldName).get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setFieldValue(Object object, String fieldName, Object value) {
        try {
            accessField(object, fieldName).set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
