/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_2886
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.google.common.collect.Sets;
import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.mixin.IMinecraftClient;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.NumberSetting;
import java.util.Set;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2886;

public class FastUse
extends Module {
    NumberSetting delay = new NumberSetting("delay", 1.0f, 0.0f, 4.0f, false);
    private static final Set<class_1792> THROWABLE = Sets.newHashSet((Object[])new class_1792[]{class_1802.field_8543, class_1802.field_8803, class_1802.field_8287, class_1802.field_8449, class_1802.field_8634, class_1802.field_8436, class_1802.field_8150});

    public FastUse() {
        super("FastUse", 0, false, Category.MISC);
        this.addSettings(this.delay);
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2886) {
            ((IMinecraftClient)mc).setItemUseCooldown((int)(this.delay.getValue() * 4.0));
        }
    }
}

