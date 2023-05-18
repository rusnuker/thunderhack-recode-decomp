//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.Hud;

import net.minecraft.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.module.*;
import java.util.*;
import com.mrzak34.thunderhack.*;

public class Hud
{
    public static class_310 mc;
    public String name;
    public boolean toggled;
    public List<Setting> settings;
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
    
    public Hud(final String name, final int posX, final int posY, final int height, final int width, final boolean showSettings, final boolean toggled) {
        this.settings = new ArrayList<Setting>();
        this.name = name;
        this.toggled = false;
        this.showSettings = showSettings;
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.toggled = toggled;
    }
    
    public void addSettings(final Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    public void setToggled(final boolean toggled) {
        this.toggled = toggled;
        if (this.toggled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
    }
    
    public void toggle() {
        this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        }
        else {
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
    
    static {
        Hud.mc = class_310.method_1551();
    }
}
