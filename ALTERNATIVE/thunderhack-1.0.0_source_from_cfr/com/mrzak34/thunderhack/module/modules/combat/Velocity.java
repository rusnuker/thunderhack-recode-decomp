/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_2664
 *  net.minecraft.class_2743
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.mixin.IEntityVelocityUpdateS2CPacket;
import com.mrzak34.thunderhack.mixin.IExplosionS2CPacket;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import net.minecraft.class_2664;
import net.minecraft.class_2743;

public class Velocity
extends Module {
    public NumberSetting horizontal = new NumberSetting("horizontal", 100.0f, 0.0f, 100.0f, false);
    public NumberSetting vertical = new NumberSetting("vertical", 100.0f, 0.0f, 100.0f, false);
    public BooleanSetting fishingHook = new BooleanSetting("fishingHook", true);
    public static BooleanSetting noPush = new BooleanSetting("nopush", true);

    public Velocity() {
        super("Velocity", 0, false, Category.COMBAT);
        this.addSettings(this.horizontal, this.vertical, this.fishingHook, noPush);
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2743) {
            class_2743 sPacketEntityVelocity = (class_2743)event.getPacket();
            if (Velocity.mc.field_1687.method_8469(sPacketEntityVelocity.method_11818()) == Velocity.mc.field_1724) {
                if (this.horizontal.getValue() > 0.0 || this.vertical.getValue() > 0.0) {
                    ((IEntityVelocityUpdateS2CPacket)sPacketEntityVelocity).setVelocityX((int)((double)sPacketEntityVelocity.method_11815() * (this.horizontal.getValue() / 100.0)));
                    ((IEntityVelocityUpdateS2CPacket)sPacketEntityVelocity).setVelocityY((int)((double)sPacketEntityVelocity.method_11816() * (this.vertical.getValue() / 100.0)));
                    ((IEntityVelocityUpdateS2CPacket)sPacketEntityVelocity).setVelocityZ((int)((double)sPacketEntityVelocity.method_11819() * (this.horizontal.getValue() / 100.0)));
                }
                event.setCancelled(true);
            }
        } else if (event.getPacket() instanceof class_2664) {
            class_2664 sPacketExplosion = (class_2664)event.getPacket();
            ((IExplosionS2CPacket)sPacketExplosion).setVelocityX((int)((double)sPacketExplosion.method_11472() * (this.horizontal.getValue() / 100.0)));
            ((IExplosionS2CPacket)sPacketExplosion).setVelocityY((int)((double)sPacketExplosion.method_11473() * (this.vertical.getValue() / 100.0)));
            ((IExplosionS2CPacket)sPacketExplosion).setVelocityZ((int)((double)sPacketExplosion.method_11474() * (this.horizontal.getValue() / 100.0)));
        }
    }
}

