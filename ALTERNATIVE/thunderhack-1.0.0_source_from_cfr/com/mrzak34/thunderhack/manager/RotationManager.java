/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_2708
 *  net.minecraft.class_2828
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_2828$class_2831
 */
package com.mrzak34.thunderhack.manager;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.MovementEvent;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.mixin.IPlayerMoveC2SPacket;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_2708;
import net.minecraft.class_2828;

public class RotationManager
implements Util {
    private static float yaw;
    private static float serverYaw;
    private static float pitch;
    private static float serverPitch;

    @Subscribe
    public void onMovmentPost(MovementEvent.Post event) {
        if (!Module.fullNullCheck()) {
            this.restoreRotations();
        }
    }

    public void updateRotations() {
        yaw = RotationManager.mc.field_1724.method_36454();
        pitch = RotationManager.mc.field_1724.method_36455();
    }

    public void restoreRotations() {
        RotationManager.mc.field_1724.method_36456(yaw);
        RotationManager.mc.field_1724.method_36457(pitch);
    }

    @Subscribe
    public void onMovmentPre(MovementEvent.Pre event) {
        if (!Module.fullNullCheck()) {
            this.updateRotations();
        }
    }

    @Subscribe
    public void PacketSend(PacketEvent.Send event) {
        class_2828 packet;
        if (event.getPacket() instanceof class_2828.class_2831) {
            packet = (class_2828)event.getPacket();
            serverYaw = ((IPlayerMoveC2SPacket)packet).getYaw();
            serverPitch = ((IPlayerMoveC2SPacket)packet).getPitch();
        }
        if (event.getPacket() instanceof class_2828.class_2830) {
            packet = (class_2828)event.getPacket();
            serverYaw = ((IPlayerMoveC2SPacket)packet).getYaw();
            serverPitch = ((IPlayerMoveC2SPacket)packet).getPitch();
        }
    }

    @Subscribe
    public void PacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2708) {
            class_2708 packet = (class_2708)event.getPacket();
            serverYaw = packet.method_11736();
            serverPitch = packet.method_11739();
        }
    }

    public static float getServerYaw() {
        return serverYaw;
    }

    public static float getServerPitch() {
        return serverPitch;
    }
}

