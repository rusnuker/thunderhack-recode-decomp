/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_238
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.util.TimerUtil;
import net.minecraft.class_1657;
import net.minecraft.class_238;

public class TestModule
extends Module {
    BooleanSetting troughWalls = new BooleanSetting("trough walls", false);
    ModeSetting mode = new ModeSetting("mode", "box", "box", "entity");
    class_1657 target;
    public static class_238 hitBox;
    TimerUtil messageTimer = new TimerUtil();

    public TestModule() {
        super("TestModule", 0, false, Category.MISC);
        this.addSettings(this.troughWalls, this.mode);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        NotificationManager.notif("getObjectiveNames: " + TestModule.mc.field_1687.method_8428().method_1163());
        NotificationManager.notif("teams: " + TestModule.mc.field_1687.method_8428().method_1159());
        NotificationManager.notif("teams2: " + TestModule.mc.field_1687.method_8428().method_1196());
        NotificationManager.notif("objectives: " + TestModule.mc.field_1687.method_8428().method_1151());
    }
}

