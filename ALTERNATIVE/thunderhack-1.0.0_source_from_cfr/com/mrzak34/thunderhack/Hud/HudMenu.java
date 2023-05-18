/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.Hud;

public final class HudMenu
extends Enum<HudMenu> {
    public static final /* enum */ HudMenu HUD = new HudMenu("HUD", 320, 10, false, true);
    public String name;
    public int posX;
    public int posY;
    public boolean mouseClicked;
    public boolean showHuds;
    private static final /* synthetic */ HudMenu[] $VALUES;

    public static HudMenu[] values() {
        return (HudMenu[])$VALUES.clone();
    }

    public static HudMenu valueOf(String name) {
        return Enum.valueOf(HudMenu.class, name);
    }

    private HudMenu(String name, int posX, int posY, boolean mouseClicked, boolean showHuds) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.mouseClicked = mouseClicked;
        this.showHuds = showHuds;
    }

    private static /* synthetic */ HudMenu[] $values() {
        return new HudMenu[]{HUD};
    }

    static {
        $VALUES = HudMenu.$values();
    }
}

