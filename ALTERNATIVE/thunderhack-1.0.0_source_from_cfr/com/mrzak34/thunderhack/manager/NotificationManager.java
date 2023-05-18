/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_2561
 */
package com.mrzak34.thunderhack.manager;

import com.mrzak34.thunderhack.manager.TotemPopManager;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_1657;
import net.minecraft.class_2561;

public class NotificationManager
implements Util {
    public static void notif(String message) {
        NotificationManager.mc.field_1724.method_43496(class_2561.method_30163((String)("\u00a76[\u00a7e\u26a1\u00a76]\u00a7r" + message)));
    }

    public static void pop(class_1657 player) {
        NotificationManager.mc.field_1724.method_43496(class_2561.method_30163((String)("\u00a76[\u00a7f\u2620\u00a76]\u00a7r" + player.method_5477().getString() + " pop " + TotemPopManager.getPlayerPops(player) + " totems")));
    }

    public static void toggleOn(Module module) {
        NotificationManager.mc.field_1724.method_43496(class_2561.method_30163((String)("\u00a76[\u00a72\u2714\u00a76]\u00a72" + module.getName() + " Enabled")));
    }

    public static void toggleOff(Module module) {
        NotificationManager.mc.field_1724.method_43496(class_2561.method_30163((String)("\u00a76[\u00a74\u2716\u00a76]\u00a74" + module.getName() + " Disabled")));
    }
}

