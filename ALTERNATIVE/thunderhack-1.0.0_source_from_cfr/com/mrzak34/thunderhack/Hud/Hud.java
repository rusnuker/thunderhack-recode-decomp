/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package com.mrzak34.thunderhack.Hud;

import com.mrzak34.thunderhack.Hud.HudMenu;
import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.Setting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.class_310;

public class Hud {
    public static class_310 mc = class_310.method_1551();
    public String name;
    public boolean toggled;
    public List<Setting> settings = new ArrayList<Setting>();
    public boolean showSettings;
    private HudMenu hudmenu;
    public boolean mouseClicked;
    public int posX;
    public int posY;
    public int height;
    public int width;
    public int hudoldposX;
    public int hudoldposY;

    public static boolean fullNullCheck() {
        return Module.mc.field_1724 == null || Module.mc.field_1687 == null;
    }

    public Hud(String name, int posX, int posY, int height, int width, boolean showSettings, boolean toggled) {
        this.name = name;
        this.toggled = false;
        this.showSettings = showSettings;
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.toggled = toggled;
    }

    public void addSettings(Setting ... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public void toggle() {
        boolean bl = this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public void onEnable() {
        Main.EVENT_BUS.register((Object)this);
    }

    public void onDisable() {
        Main.EVENT_BUS.register((Object)this);
    }

    public String getName() {
        return this.name;
    }

    public HudMenu getHud() {
        return this.hudmenu;
    }
}

