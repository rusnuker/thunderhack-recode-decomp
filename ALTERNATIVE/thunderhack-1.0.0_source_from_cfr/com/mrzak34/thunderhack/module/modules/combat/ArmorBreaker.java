/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1831
 *  net.minecraft.class_2596
 *  net.minecraft.class_2824
 *  net.minecraft.class_2824$class_5907
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.ducks.IPlayerInteractEntityC2SPacket;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.NumberSetting;
import net.minecraft.class_1831;
import net.minecraft.class_2596;
import net.minecraft.class_2824;

public class ArmorBreaker
extends Module {
    NumberSetting factor = new NumberSetting("factor", 3.0f, 1.0f, 10.0f, false);

    public ArmorBreaker() {
        super("ArmorBreaker", 0, false, Category.MISC);
        this.addSettings(this.factor);
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        class_2824 packet;
        if (event.getPacket() instanceof class_2824 && ArmorBreaker.mc.field_1724.method_31548().method_7391().method_7909() instanceof class_1831 && ((com.mrzak34.thunderhack.mixin.IPlayerInteractEntityC2SPacket)(packet = (class_2824)event.getPacket())).getType().method_34211() == class_2824.class_5907.field_29172 && ((IPlayerInteractEntityC2SPacket)packet).isNatural()) {
            class_2824 unnatural = packet;
            ((IPlayerInteractEntityC2SPacket)unnatural).setNatural(false);
            int i = 0;
            while ((double)i < this.factor.getValue()) {
                mc.method_1562().method_2883((class_2596)unnatural);
                ++i;
            }
        }
    }
}

