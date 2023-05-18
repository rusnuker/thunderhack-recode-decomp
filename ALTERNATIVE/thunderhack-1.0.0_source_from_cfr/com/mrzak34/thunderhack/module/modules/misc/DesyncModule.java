/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_2828
 *  net.minecraft.class_2833
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.Setting;
import net.minecraft.class_2828;
import net.minecraft.class_2833;

public class DesyncModule
extends Module {
    public DesyncModule() {
        super("DesyncModule", 0, false, Category.MISC);
        this.addSettings(new Setting[0]);
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2828) {
            event.setCancelled(true);
        }
        if (event.getPacket() instanceof class_2833) {
            event.setCancelled(true);
        }
        if (event.getPacket() instanceof class_2828) {
            event.setCancelled(true);
        }
    }
}

