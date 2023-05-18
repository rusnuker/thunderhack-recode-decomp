/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package com.mrzak34.thunderhack.module;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.modules.client.Notifications;
import com.mrzak34.thunderhack.settings.KeybindSetting;
import com.mrzak34.thunderhack.settings.Setting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import net.minecraft.class_310;

public abstract class Module {
    public static class_310 mc = class_310.method_1551();
    public String name;
    public boolean binding;
    KeybindSetting keyCode = new KeybindSetting("bind", 0, false);
    private Category category;
    public boolean toggled;
    public List<Setting> settings = new ArrayList<Setting>();
    public boolean showSettings;
    public int progress = 0;

    public static boolean fullNullCheck() {
        return Module.mc.field_1724 == null || Module.mc.field_1687 == null;
    }

    public Module(String name, int key, boolean keyDown, Category category) {
        this.name = name;
        this.keyCode.code = key;
        this.keyCode.keyDown = keyDown;
        this.category = category;
        this.binding = false;
        this.toggled = false;
        this.addSettings(this.keyCode);
        ++category.modules;
    }

    public void addSettings(Setting ... settings) {
        this.settings.addAll(Arrays.asList(settings));
        this.settings.sort(Comparator.comparingInt(s -> s == this.keyCode ? 1 : 0));
    }

    public int getKey() {
        return this.keyCode.code;
    }

    public void setKey(int key) {
        this.keyCode.code = key;
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

    public void binding() {
        this.binding = !this.binding;
    }

    public void onEnable() {
        Main.EVENT_BUS.register((Object)this);
        if (Notifications.getInstance().isToggled() && Notifications.getInstance().toggle.isEnabled()) {
            NotificationManager.toggleOn(this);
        }
    }

    public void onDisable() {
        Main.EVENT_BUS.unregister((Object)this);
        if (Notifications.getInstance().isToggled() && Notifications.getInstance().toggle.isEnabled()) {
            NotificationManager.toggleOff(this);
        }
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    public void onTick() {
    }
}

