//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.mixin.*;
import net.minecraft.*;
import com.google.common.eventbus.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.util.*;

public class AutoFlyme extends Module
{
    public final BooleanSetting space;
    public final BooleanSetting instantSpeed;
    public final BooleanSetting criticals;
    public final BooleanSetting hover;
    public NumberSetting hoverY;
    private final TimerUtil timer;
    boolean cancelSomePackets;
    
    public AutoFlyme() {
        super("AutoFlyme", 0, false, Category.MOVEMENT);
        this.space = new BooleanSetting("space", true);
        this.instantSpeed = new BooleanSetting("instant", true);
        this.criticals = new BooleanSetting("criticals", true);
        this.hover = new BooleanSetting("hover", true);
        this.hoverY = new NumberSetting("hover y", 0.228f, 0.0f, 1.0f, true);
        this.timer = new TimerUtil();
        this.cancelSomePackets = false;
        this.addSettings(new Setting[] { this.space, this.instantSpeed, this.criticals, this.hover, this.hoverY });
    }
    
    public void onEnable() {
        super.onEnable();
        if (fullNullCheck()) {
            return;
        }
        AutoFlyme.mc.field_1724.field_3944.method_45730("flyme");
    }
    
    public void onTick() {
        super.onTick();
        if (!AutoFlyme.mc.field_1724.method_31549().field_7479 && this.timer.passedMs(1000L) && !AutoFlyme.mc.field_1724.method_24828() && (!this.space.isEnabled() || AutoFlyme.mc.field_1690.field_1903.method_1434())) {
            AutoFlyme.mc.field_1724.field_3944.method_45730("flyme");
            this.timer.reset();
        }
        if (this.hover.isEnabled() && AutoFlyme.mc.field_1724.method_31549().field_7479 && !AutoFlyme.mc.field_1724.method_24828() && AutoFlyme.mc.field_1687.method_8600((class_1297)AutoFlyme.mc.field_1724, AutoFlyme.mc.field_1724.method_5829().method_989(0.0, -this.hoverY.getValue(), 0.0)) == null) {
            AutoFlyme.mc.field_1724.method_18800(AutoFlyme.mc.field_1724.method_18798().field_1352, -0.05000000074505806, AutoFlyme.mc.field_1724.method_18798().field_1352);
        }
    }
    
    @Subscribe
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2824 && AutoFlyme.mc.field_1724.method_31548().method_7391().method_7909() instanceof class_1831) {
            final class_2824 packet = (class_2824)event.getPacket();
            if (((IPlayerInteractEntityC2SPacket)packet).getType().method_34211() == class_2824.class_5907.field_29172 && this.criticals.isEnabled()) {
                AutoFlyme.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(AutoFlyme.mc.field_1724.method_23317(), AutoFlyme.mc.field_1724.method_23318() + 0.1100013579, AutoFlyme.mc.field_1724.method_23321(), false));
                AutoFlyme.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(AutoFlyme.mc.field_1724.method_23317(), AutoFlyme.mc.field_1724.method_23318() - 1.3579E-6, AutoFlyme.mc.field_1724.method_23321(), false));
                this.cancelSomePackets = true;
            }
        }
        if (event.getPacket() instanceof class_2828 && this.cancelSomePackets) {
            this.cancelSomePackets = false;
            event.setCancelled(true);
        }
    }
    
    @Subscribe
    public void onUpdateWalkingPlayer(final UpdateMoveEvent event) {
        if (!this.instantSpeed.isEnabled() || !AutoFlyme.mc.field_1724.method_31549().field_7479) {
            return;
        }
        final double[] dir = MathUtil.directionSpeed(1.0499999523162842);
        if (AutoFlyme.mc.field_1724.field_3913.field_3907 != 0.0f || AutoFlyme.mc.field_1724.field_3913.field_3905 != 0.0f) {
            AutoFlyme.mc.field_1724.method_18800(dir[0], AutoFlyme.mc.field_1724.method_18798().field_1351, dir[1]);
        }
        else {
            AutoFlyme.mc.field_1724.method_18800(0.0, AutoFlyme.mc.field_1724.method_18798().field_1351, 0.0);
        }
    }
}
