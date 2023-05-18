//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.module.*;
import java.util.concurrent.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.google.common.eventbus.*;
import java.util.*;
import net.minecraft.*;

public class PearlBait extends Module
{
    BooleanSetting guarantee;
    private final Queue<class_2828> packets;
    private int thrownPearlId;
    
    public PearlBait() {
        super("PearlBait", 0, false, Category.MOVEMENT);
        this.guarantee = new BooleanSetting("guarantee", true);
        this.packets = new ConcurrentLinkedQueue<class_2828>();
        this.thrownPearlId = -1;
        this.addSettings(new Setting[] { this.guarantee });
    }
    
    @Subscribe
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2604) {
            final class_2604 packet = (class_2604)event.getPacket();
            if (packet.method_11169() == class_1299.field_6082) {
                final class_2604 class_2604;
                final class_2604 class_2605;
                PearlBait.mc.field_1687.method_18456().stream().min(Comparator.comparingDouble(p -> p.method_5649(class_2604.method_11175(), class_2604.method_11174(), class_2604.method_11176()))).ifPresent(player -> {
                    if (player.equals((Object)PearlBait.mc.field_1724) && PearlBait.mc.field_1724.method_24828()) {
                        PearlBait.mc.field_1724.method_18800(0.0, 0.0, 0.0);
                        PearlBait.mc.field_1724.field_3913.field_3905 = 0.0f;
                        PearlBait.mc.field_1724.field_3913.field_3907 = 0.0f;
                        PearlBait.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(PearlBait.mc.field_1724.method_23317(), PearlBait.mc.field_1724.method_23318() + 1.0, PearlBait.mc.field_1724.method_23321(), false));
                        this.thrownPearlId = class_2605.method_11167();
                    }
                });
            }
        }
        else if (event.getPacket() instanceof class_2828 && this.guarantee.isEnabled() && this.thrownPearlId != -1) {
            this.packets.add((class_2828)event.getPacket());
            event.setCancelled(true);
        }
    }
    
    public void onTick() {
        super.onTick();
        if (this.thrownPearlId != -1) {
            for (final class_1297 entity : PearlBait.mc.field_1687.method_18112()) {
                if (entity.method_5628() == this.thrownPearlId && entity instanceof class_1684) {
                    final class_1684 pearl = (class_1684)entity;
                    if (!pearl.method_31481()) {
                        continue;
                    }
                    this.thrownPearlId = -1;
                }
            }
        }
        else if (!this.packets.isEmpty()) {
            do {
                PearlBait.mc.field_1724.field_3944.method_2883((class_2596)this.packets.poll());
            } while (!this.packets.isEmpty());
        }
    }
}
