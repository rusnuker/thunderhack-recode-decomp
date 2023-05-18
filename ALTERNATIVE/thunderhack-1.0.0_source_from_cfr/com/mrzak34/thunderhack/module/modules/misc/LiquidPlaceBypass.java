/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1755
 *  net.minecraft.class_2885
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.Setting;
import net.minecraft.class_1755;
import net.minecraft.class_2885;

public class LiquidPlaceBypass
extends Module {
    public LiquidPlaceBypass() {
        super("LiquidPlaceBypass", 0, false, Category.MISC);
        this.addSettings(new Setting[0]);
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2885 && LiquidPlaceBypass.mc.field_1724.method_31548().method_7391().method_7909() instanceof class_1755) {
            event.setCancelled(true);
        }
    }
}

