/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1297
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.mixin.IPlayerMoveC2SPacket;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.TimerUtil;
import net.minecraft.class_1297;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2848;

public class PreElytraFly
extends Module {
    ModeSetting mode = new ModeSetting("mode", "vanilla", "vanilla", "packet", "static");
    static NumberSetting speed = new NumberSetting("speed", 4.0f, 0.0f, 10.0f, true);
    NumberSetting glideSpeed = new NumberSetting("glideSpeed", 0.5f, 0.0f, 0.3f, true);
    NumberSetting falling = new NumberSetting("falling", 0.25f, 0.0f, 2.0f, true);
    NumberSetting delay = new NumberSetting("delay", 250.0f, 0.0f, 300.0f, false);
    NumberSetting lagBackDelay = new NumberSetting("lagBackDelay", 200.0f, 200.0f, 1000.0f, false);
    NumberSetting pitch = new NumberSetting("pitch", -2.3f, -90.0f, 90.0f, true);
    TimerUtil takeofTimer = new TimerUtil();
    boolean reTakeOff = false;
    private boolean elytraIsEquipped = false;
    private int elytraDurability = 0;
    private boolean outOfDurability = false;
    private boolean wasInLiquid = false;
    private boolean isFlying = false;
    private boolean isPacketFlying = false;
    private boolean isStandingStillH = false;
    private boolean isStandingStill = false;
    private float speedPercentage = 0.0f;
    private double hoverTarget = -1.0;
    private static float packetYaw = 0.0f;
    private float packetPitch = 0.0f;
    private boolean hoverState = false;
    private int boostingTick = 0;

    public PreElytraFly() {
        super("PreElytraFly", 0, false, Category.MOVEMENT);
        this.addSettings(this.mode, speed, this.glideSpeed, this.falling, this.delay, this.lagBackDelay, this.pitch);
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2828) {
            class_2828 packet = (class_2828)event.getPacket();
            ((IPlayerMoveC2SPacket)packet).setPitch((float)this.pitch.getValue());
        }
    }

    private static float getYaw() {
        float yawRad = PreElytraFly.calcMoveYaw();
        packetYaw = (float)Math.toDegrees(yawRad);
        return yawRad;
    }

    public static float calcMoveYaw() {
        float strafe = 90.0f * PreElytraFly.mc.field_1724.field_3913.field_3907;
        float yaw = PreElytraFly.mc.field_1724.method_36454() - (strafe *= PreElytraFly.mc.field_1724.field_3913.field_3905 != 0.0f ? PreElytraFly.mc.field_1724.field_3913.field_3905 * 0.5f : 1.0f);
        return (float)Math.toRadians(yaw -= PreElytraFly.mc.field_1724.field_3913.field_3905 < 0.0f ? 180.0f : 0.0f);
    }

    private static void setSpeed(double yaw) {
        double acceleratedSpeed = speed.getValue();
        PreElytraFly.mc.field_1724.method_18800(Math.sin(-yaw) * acceleratedSpeed, PreElytraFly.mc.field_1724.method_18798().field_1351, Math.cos(yaw) * acceleratedSpeed);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        PreElytraFly.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)PreElytraFly.mc.field_1724, class_2848.class_2849.field_12982));
    }
}

