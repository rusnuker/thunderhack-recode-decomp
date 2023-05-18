/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1684
 *  net.minecraft.class_2596
 *  net.minecraft.class_2604
 *  net.minecraft.class_2828
 *  net.minecraft.class_2828$class_2829
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1684;
import net.minecraft.class_2596;
import net.minecraft.class_2604;
import net.minecraft.class_2828;

public class PearlBait
extends Module {
    BooleanSetting guarantee = new BooleanSetting("guarantee", true);
    private final Queue<class_2828> packets = new ConcurrentLinkedQueue<class_2828>();
    private int thrownPearlId = -1;

    public PearlBait() {
        super("PearlBait", 0, false, Category.MOVEMENT);
        this.addSettings(this.guarantee);
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2604) {
            class_2604 packet = (class_2604)event.getPacket();
            if (packet.method_11169() == class_1299.field_6082) {
                PearlBait.mc.field_1687.method_18456().stream().min(Comparator.comparingDouble(p -> p.method_5649(packet.method_11175(), packet.method_11174(), packet.method_11176()))).ifPresent(player -> {
                    if (player.equals((Object)PearlBait.mc.field_1724) && PearlBait.mc.field_1724.method_24828()) {
                        PearlBait.mc.field_1724.method_18800(0.0, 0.0, 0.0);
                        PearlBait.mc.field_1724.field_3913.field_3905 = 0.0f;
                        PearlBait.mc.field_1724.field_3913.field_3907 = 0.0f;
                        PearlBait.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(PearlBait.mc.field_1724.method_23317(), PearlBait.mc.field_1724.method_23318() + 1.0, PearlBait.mc.field_1724.method_23321(), false));
                        this.thrownPearlId = packet.method_11167();
                    }
                });
            }
        } else if (event.getPacket() instanceof class_2828 && this.guarantee.isEnabled() && this.thrownPearlId != -1) {
            this.packets.add((class_2828)event.getPacket());
            event.setCancelled(true);
        }
    }

    @Override
    public void onTick() {
        super.onTick();
        if (this.thrownPearlId != -1) {
            for (class_1297 entity : PearlBait.mc.field_1687.method_18112()) {
                class_1684 pearl;
                if (entity.method_5628() != this.thrownPearlId || !(entity instanceof class_1684) || !(pearl = (class_1684)entity).method_31481()) continue;
                this.thrownPearlId = -1;
            }
        } else if (!this.packets.isEmpty()) {
            do {
                PearlBait.mc.field_1724.field_3944.method_2883((class_2596)this.packets.poll());
            } while (!this.packets.isEmpty());
        }
    }
}

