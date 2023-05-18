//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.util.*;
import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.module.modules.thread.*;
import com.google.common.eventbus.*;
import com.mrzak34.thunderhack.mixin.*;
import com.mrzak34.thunderhack.event.events.*;
import net.minecraft.*;

public class ElytraFly extends Module
{
    public ModeSetting mode;
    NumberSetting speed;
    NumberSetting glideSpeed;
    NumberSetting falling;
    NumberSetting delay;
    NumberSetting lagBackDelay;
    NumberSetting postLagBackDelay;
    NumberSetting pitch;
    TimerUtil takeofTimer;
    TimerUtil lagBackTimer;
    boolean reTakeOff;
    private static ElytraFly INSTANCE;
    
    public ElytraFly() {
        super("ElytraFly", 0, false, Category.MOVEMENT);
        this.mode = new ModeSetting("mode", "strict", new String[] { "strict", "boost" });
        this.speed = new NumberSetting("speed", 1.0f, 0.0f, 10.0f, true);
        this.glideSpeed = new NumberSetting("glideSpeed", 0.5f, 0.0f, 3.0f, true);
        this.falling = new NumberSetting("falling", 0.25f, 0.0f, 2.0f, true);
        this.delay = new NumberSetting("delay", 250.0f, 0.0f, 300.0f, false);
        this.lagBackDelay = new NumberSetting("lagBackDelay", 200.0f, 200.0f, 1000.0f, false);
        this.postLagBackDelay = new NumberSetting("postLagBackDelay", 250.0f, 0.0f, 300.0f, false);
        this.pitch = new NumberSetting("pitch", -2.3f, -90.0f, 90.0f, true);
        this.takeofTimer = new TimerUtil();
        this.lagBackTimer = new TimerUtil();
        this.reTakeOff = false;
        this.addSettings(new Setting[] { this.mode, this.speed, this.glideSpeed, this.delay, this.lagBackDelay, this.postLagBackDelay, this.pitch });
    }
    
    @Subscribe
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (!this.isToggled() || fullNullCheck() || !this.mode.getMode().equals("strict")) {
            return;
        }
        if (event.getPacket() instanceof class_2708) {
            new TakeOffThread((int)this.postLagBackDelay.getValue()).start();
            this.reTakeOff = true;
            this.lagBackTimer.reset();
        }
    }
    
    @Subscribe
    public void onPacketSend(final PacketEvent.Send event) {
        if (!this.isToggled() || fullNullCheck() || !this.mode.getMode().equals("strict")) {
            return;
        }
        if (event.getPacket() instanceof class_2828) {
            final class_2828 packet = (class_2828)event.getPacket();
            ((IPlayerMoveC2SPacket)packet).setPitch((float)this.pitch.getValue());
        }
    }
    
    @Subscribe
    public void onMovePost(final MoveEvent event) {
        ElytraFly.mc.field_1724.method_31549().field_7479 = true;
        ElytraFly.mc.field_1724.method_31549().method_7248((float)((float)this.speed.getValue() / 11.11));
        if (this.glideSpeed.getValue() != 0.0 && !ElytraFly.mc.field_1690.field_1903.method_1434() && !ElytraFly.mc.field_1690.field_1832.method_1434()) {
            ElytraFly.mc.field_1724.method_5762(0.0, -this.glideSpeed.getValue() / 10.0, 0.0);
        }
    }
    
    @Subscribe
    public void onMovePre(final MoveEvent event) {
        if (!this.isToggled() || fullNullCheck() || !this.mode.getMode().equals("strict")) {
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
        if (ElytraFly.INSTANCE == null) {
            ElytraFly.INSTANCE = new ElytraFly();
        }
        return ElytraFly.INSTANCE;
    }
    
    private void setInstance() {
        ElytraFly.INSTANCE = this;
    }
    
    public void onEnable() {
        super.onEnable();
        ElytraFly.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)ElytraFly.mc.field_1724, class_2848.class_2849.field_12982));
    }
    
    public void onDisable() {
        super.onDisable();
        ElytraFly.mc.field_1724.method_31549().field_7479 = false;
        ElytraFly.mc.field_1724.method_31549().method_7248(0.05f);
    }
    
    static {
        ElytraFly.INSTANCE = new ElytraFly();
    }
}
