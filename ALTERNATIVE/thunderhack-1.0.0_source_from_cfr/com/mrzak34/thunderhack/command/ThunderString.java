/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.command;

public class ThunderString {
    private String name;
    private String tag;
    private String value;

    public ThunderString(String name, String tag, String string) {
        this.name = name;
        this.tag = tag;
        this.value = string;
    }

    public void set_value(String string) {
        this.value = string;
    }

    public String get_name() {
        return this.name;
    }

    public String get_tag() {
        return this.tag;
    }

    public String get_value() {
        return this.value;
    }

    public static String to_string(String value) {
        return value;
    }

    public static String to_string(boolean value) {
        return Boolean.toString(value);
    }

    public static String to_string(double value) {
        return Double.toString(value);
    }

    public static String to_string(float value) {
        return Float.toString(value);
    }

    public static String to_string(int value) {
        return Integer.toString(value);
    }
}

