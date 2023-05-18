//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.command;

public class ThunderString
{
    private String name;
    private String tag;
    private String value;
    
    public ThunderString(final String name, final String tag, final String string) {
        this.name = name;
        this.tag = tag;
        this.value = string;
    }
    
    public void set_value(final String string) {
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
    
    public static String to_string(final String value) {
        return value;
    }
    
    public static String to_string(final boolean value) {
        return Boolean.toString(value);
    }
    
    public static String to_string(final double value) {
        return Double.toString(value);
    }
    
    public static String to_string(final float value) {
        return Float.toString(value);
    }
    
    public static String to_string(final int value) {
        return Integer.toString(value);
    }
}
