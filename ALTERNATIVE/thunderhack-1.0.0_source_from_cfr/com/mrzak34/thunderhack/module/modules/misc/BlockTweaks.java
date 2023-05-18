/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import net.minecraft.class_2846;

public class BlockTweaks
extends Module {
    BooleanSetting abort = new BooleanSetting("abort", true);

    public BlockTweaks() {
        super("BlockTweaks", 0, false, Category.MISC);
        this.addSettings(this.abort);
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        class_2846 packet;
        if (event.getPacket() instanceof class_2846 && ((packet = (class_2846)event.getPacket()).method_12363() == class_2846.class_2847.field_12973 || packet.method_12363() == class_2846.class_2847.field_12971)) {
            event.setCancelled(true);
        }
    }
}

