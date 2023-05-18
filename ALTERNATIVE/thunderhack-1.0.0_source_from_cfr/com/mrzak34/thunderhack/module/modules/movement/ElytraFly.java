/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1297
 *  net.minecraft.class_2596
 *  net.minecraft.class_2708
 *  net.minecraft.class_2828
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.MoveEvent;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.mixin.IPlayerMoveC2SPacket;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.module.modules.thread.TakeOffThread;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.TimerUtil;
import net.minecraft.class_1297;
import net.minecraft.class_2596;
import net.minecraft.class_2708;
import net.minecraft.class_2828;
import net.minecraft.class_2848;

public class ElytraFly
extends Module {
    public ModeSetting mode = new ModeSetting("mode", "strict", "strict", "boost");
    NumberSetting speed = new NumberSetting("speed", 1.0f, 0.0f, 10.0f, true);
    NumberSetting glideSpeed = new NumberSetting("glideSpeed", 0.5f, 0.0f, 3.0f, true);
    NumberSetting falling = new NumberSetting("falling", 0.25f, 0.0f, 2.0f, true);
    NumberSetting delay = new NumberSetting("delay", 250.0f, 0.0f, 300.0f, false);
    NumberSetting lagBackDelay = new NumberSetting("lagBackDelay", 200.0f, 200.0f, 1000.0f, false);
    NumberSetting postLagBackDelay = new NumberSetting("postLagBackDelay", 250.0f, 0.0f, 300.0f, false);
    NumberSetting pitch = new NumberSetting("pitch", -2.3f, -90.0f, 90.0f, true);
    TimerUtil takeofTimer = new TimerUtil();
    TimerUtil lagBackTimer = new TimerUtil();
    boolean reTakeOff = false;
    private static ElytraFly INSTANCE = new ElytraFly();

    public ElytraFly() {
        super("ElytraFly", 0, false, Category.MOVEMENT);
        this.addSettings(this.mode, this.speed, this.glideSpeed, this.delay, this.lagBackDelay, this.postLagBackDelay, this.pitch);
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        if (!this.isToggled() || ElytraFly.fullNullCheck() || !this.mode.getMode().equals("strict")) {
            return;
        }
        if (event.getPacket() instanceof class_2708) {
            new TakeOffThread((int)this.postLagBackDelay.getValue()).start();
            this.reTakeOff = true;
            this.lagBackTimer.reset();
        }
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        if (!this.isToggled() || ElytraFly.fullNullCheck() || !this.mode.getMode().equals("strict")) {
            return;
        }
        if (event.getPacket() instanceof class_2828) {
            class_2828 packet = (class_2828)event.getPacket();
            ((IPlayerMoveC2SPacket)packet).setPitch((float)this.pitch.getValue());
        }
    }

    @Subscribe
    public void onMovePost(MoveEvent event) {
        ElytraFly.mc.field_1724.method_31549().field_7479 = true;
        ElytraFly.mc.field_1724.method_31549().method_7248((float)((double)((float)this.speed.getValue()) / 11.11));
        if (this.glideSpeed.getValue() != 0.0 && !ElytraFly.mc.field_1690.field_1903.method_1434() && !ElytraFly.mc.field_1690.field_1832.method_1434()) {
            ElytraFly.mc.field_1724.method_5762(0.0, -this.glideSpeed.getValue() / 10.0, 0.0);
        }
    }

    @Subscribe
    public void onMovePre(MoveEvent event) {
        if (!this.isToggled() || ElytraFly.fullNullCheck() || !this.mode.getMode().equals("strict")) {
            return;
        }
        if (this.reTakeOff) {
            event.setCancelled(true);
            ElytraFly.mc.field_1724.method_18800(0.0, 0.0, 0.0);
            if (this.lagBackTimer.passedMs((long)this.lagBackDelay.getValue())) {
                this.reTakeOff = false;
                this.lagBackTimer.reset();
                this.takeofTimer.reset();
            }
        }
    }

    public static ElytraFly getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ElytraFly();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        ElytraFly.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)ElytraFly.mc.field_1724, class_2848.class_2849.field_12982));
    }

    @Override
    public void onDisable() {
        super.onDisable();
        ElytraFly.mc.field_1724.method_31549().field_7479 = false;
        ElytraFly.mc.field_1724.method_31549().method_7248(0.05f);
    }
}

