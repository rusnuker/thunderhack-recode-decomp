/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_2708
 *  net.minecraft.class_2793
 *  net.minecraft.class_2827
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_2828$class_2831
 *  net.minecraft.class_2828$class_5911
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.ParentSetting;
import net.minecraft.class_2708;
import net.minecraft.class_2793;
import net.minecraft.class_2827;
import net.minecraft.class_2828;

public class AntiPacket
extends Module {
    BooleanSetting TeleportConfirmC2SPacket = new BooleanSetting("TeleportConfirmC2SPacket", true);
    BooleanSetting PlayerPositionLookS2CPacket = new BooleanSetting("PlayerPositionLookS2CPacket", true);
    BooleanSetting KeepAliveC2SPacket = new BooleanSetting("KeepAliveC2SPacket", true);
    BooleanSetting LookAndOnGround = new BooleanSetting("LookAndOnGround", true);
    BooleanSetting OnGroundOnly = new BooleanSetting("OnGroundOnly", true);
    BooleanSetting PositionAndOnGround = new BooleanSetting("PositionAndOnGround", true);
    BooleanSetting Full = new BooleanSetting("Full", true);
    ParentSetting PlayerMoveC2SPacket = new ParentSetting("PlayerMoveC2SPacket", false, false, this.LookAndOnGround, this.OnGroundOnly, this.PositionAndOnGround, this.Full);

    public AntiPacket() {
        super("AntiPacket", 0, false, Category.MISC);
        this.addSettings(this.TeleportConfirmC2SPacket, this.KeepAliveC2SPacket, this.PlayerMoveC2SPacket, this.PlayerPositionLookS2CPacket);
    }

    @Subscribe
    public void PacketSend(PacketEvent.Send event) {
        if (this.TeleportConfirmC2SPacket.isEnabled() && event.getPacket() instanceof class_2793) {
            event.setCancelled(true);
        }
        if (this.KeepAliveC2SPacket.isEnabled() && event.getPacket() instanceof class_2827) {
            event.setCancelled(true);
        }
        if (this.LookAndOnGround.isEnabled() && event.getPacket() instanceof class_2828.class_2831) {
            event.setCancelled(true);
        }
        if (this.PositionAndOnGround.isEnabled() && event.getPacket() instanceof class_2828.class_2829) {
            event.setCancelled(true);
        }
        if (this.Full.isEnabled() && event.getPacket() instanceof class_2828.class_2830) {
            event.setCancelled(true);
        }
        if (this.OnGroundOnly.isEnabled() && event.getPacket() instanceof class_2828.class_5911) {
            event.setCancelled(true);
        }
    }

    @Subscribe
    public void PacketReceive(PacketEvent.Receive event) {
        if (this.PlayerPositionLookS2CPacket.isEnabled() && event.getPacket() instanceof class_2708) {
            event.setCancelled(true);
        }
    }
}

