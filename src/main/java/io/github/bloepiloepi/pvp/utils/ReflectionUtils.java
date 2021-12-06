package io.github.bloepiloepi.pvp.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.lang.reflect.Field;

public class ReflectionUtils {

    // map the field name to the Field object if it's cached
    private static final Cache<String, Field> fieldCache = Caffeine.newBuilder().build();

    private static Field accessField(Class<?> clazz, String fieldName) {
        return fieldCache.get(clazz.getName() + "." + fieldName, (key) -> {
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

    public static Object getDeclaredField(Class<?> clazz, Object object, String fieldName) {
        try {
            return accessField(clazz, fieldName).get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setFieldValue(Class<?> clazz, Object object, String fieldName, Object value) {
        try {
            accessField(clazz, fieldName).set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
