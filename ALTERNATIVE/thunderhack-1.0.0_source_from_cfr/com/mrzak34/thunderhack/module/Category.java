/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.module;

public final class Category
extends Enum<Category> {
    public static final /* enum */ Category COMBAT = new Category("Combat", 10, 10, false, true);
    public static final /* enum */ Category MISC = new Category("Misc", 120, 10, false, true);
    public static final /* enum */ Category MOVEMENT = new Category("Movement", 230, 10, false, true);
    public static final /* enum */ Category RENDER = new Category("Render", 340, 10, false, true);
    public static final /* enum */ Category CLIENT = new Category("Client", 450, 10, false, true);
    public String name;
    public int posX;
    public int posY;
    public boolean mouseClicked;
    public boolean showModules;
    public int modules = 0;
    private static final /* synthetic */ Category[] $VALUES;

    public static Category[] values() {
        return (Category[])$VALUES.clone();
    }

    public static Category valueOf(String name) {
        return Enum.valueOf(Category.class, name);
    }

    private Category(String name, int posX, int posY, boolean mouseClicked, boolean showModules) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.mouseClicked = mouseClicked;
        this.showModules = showModules;
    }

    private static /* synthetic */ Category[] $values() {
        return new Category[]{COMBAT, MISC, MOVEMENT, RENDER, CLIENT};
    }

    static {
        $VALUES = Category.$values();
    }
}

