/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_2663
 */
package com.mrzak34.thunderhack.manager;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.module.modules.client.Notifications;
import com.mrzak34.thunderhack.util.TimerUtil;
import com.mrzak34.thunderhack.util.Util;
import java.util.HashMap;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2663;

public class TotemPopManager
implements Util {
    static HashMap<class_1657, Integer> popcount = new HashMap();
    static HashMap<class_1657, TimerUtil> popDelay = new HashMap();

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        class_2663 packet;
        if (event.getPacket() instanceof class_2663 && (packet = (class_2663)event.getPacket()).method_11470() == 35 && packet.method_11469((class_1937)TotemPopManager.mc.field_1687) instanceof class_1657) {
            class_1657 player = (class_1657)packet.method_11469((class_1937)TotemPopManager.mc.field_1687);
            if (popcount.containsKey((Object)player)) {
                if (!popDelay.get((Object)player).passedMs(50L)) {
                    return;
                }
                popcount.replace(player, popcount.get((Object)player) + 1);
                if (Notifications.getInstance().isToggled() && Notifications.getInstance().pops.isEnabled()) {
                    NotificationManager.pop(player);
                }
            } else {
                popcount.put(player, 1);
                popDelay.put(player, new TimerUtil());
                popDelay.get((Object)player).reset();
                if (Notifications.getInstance().isToggled() && Notifications.getInstance().pops.isEnabled()) {
                    NotificationManager.pop(player);
                }
            }
        }
    }

    public static int getPlayerPops(class_1657 player) {
        if (popcount.containsKey((Object)player)) {
            return popcount.get((Object)player);
        }
        return 0;
    }

    public static void onTick() {
        if (Module.fullNullCheck()) {
            return;
        }
        for (class_1657 player : TotemPopManager.mc.field_1687.method_18456()) {
            if (!player.method_29504()) continue;
            popcount.replace(player, 0);
        }
    }
}

