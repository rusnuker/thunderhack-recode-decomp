/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1297
 *  net.minecraft.class_1831
 *  net.minecraft.class_2596
 *  net.minecraft.class_2824
 *  net.minecraft.class_2824$class_5907
 *  net.minecraft.class_2828
 *  net.minecraft.class_2828$class_2829
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.event.events.UpdateMoveEvent;
import com.mrzak34.thunderhack.mixin.IPlayerInteractEntityC2SPacket;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.MathUtil;
import com.mrzak34.thunderhack.util.TimerUtil;
import net.minecraft.class_1297;
import net.minecraft.class_1831;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_2828;

public class AutoFlyme
extends Module {
    public final BooleanSetting space = new BooleanSetting("space", true);
    public final BooleanSetting instantSpeed = new BooleanSetting("instant", true);
    public final BooleanSetting criticals = new BooleanSetting("criticals", true);
    public final BooleanSetting hover = new BooleanSetting("hover", true);
    public NumberSetting hoverY = new NumberSetting("hover y", 0.228f, 0.0f, 1.0f, true);
    private final TimerUtil timer = new TimerUtil();
    boolean cancelSomePackets = false;

    public AutoFlyme() {
        super("AutoFlyme", 0, false, Category.MOVEMENT);
        this.addSettings(this.space, this.instantSpeed, this.criticals, this.hover, this.hoverY);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (AutoFlyme.fullNullCheck()) {
            return;
        }
        AutoFlyme.mc.field_1724.field_3944.method_45730("flyme");
    }

    @Override
    public void onTick() {
        super.onTick();
        if (!(AutoFlyme.mc.field_1724.method_31549().field_7479 || !this.timer.passedMs(1000L) || AutoFlyme.mc.field_1724.method_24828() || this.space.isEnabled() && !AutoFlyme.mc.field_1690.field_1903.method_1434())) {
            AutoFlyme.mc.field_1724.field_3944.method_45730("flyme");
            this.timer.reset();
        }
        if (this.hover.isEnabled() && AutoFlyme.mc.field_1724.method_31549().field_7479 && !AutoFlyme.mc.field_1724.method_24828() && AutoFlyme.mc.field_1687.method_8600((class_1297)AutoFlyme.mc.field_1724, AutoFlyme.mc.field_1724.method_5829().method_989(0.0, -this.hoverY.getValue(), 0.0)) == null) {
            AutoFlyme.mc.field_1724.method_18800(AutoFlyme.mc.field_1724.method_18798().field_1352, (double)-0.05f, AutoFlyme.mc.field_1724.method_18798().field_1352);
        }
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        class_2824 packet;
        if (event.getPacket() instanceof class_2824 && AutoFlyme.mc.field_1724.method_31548().method_7391().method_7909() instanceof class_1831 && ((IPlayerInteractEntityC2SPacket)(packet = (class_2824)event.getPacket())).getType().method_34211() == class_2824.class_5907.field_29172 && this.criticals.isEnabled()) {
            AutoFlyme.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(AutoFlyme.mc.field_1724.method_23317(), AutoFlyme.mc.field_1724.method_23318() + 0.1100013579, AutoFlyme.mc.field_1724.method_23321(), false));
            AutoFlyme.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(AutoFlyme.mc.field_1724.method_23317(), AutoFlyme.mc.field_1724.method_23318() - 1.3579E-6, AutoFlyme.mc.field_1724.method_23321(), false));
            this.cancelSomePackets = true;
        }
        if (event.getPacket() instanceof class_2828 && this.cancelSomePackets) {
            this.cancelSomePackets = false;
            event.setCancelled(true);
        }
    }

    @Subscribe
    public void onUpdateWalkingPlayer(UpdateMoveEvent event) {
        if (!this.instantSpeed.isEnabled() || !AutoFlyme.mc.field_1724.method_31549().field_7479) {
            return;
        }
        double[] dir = MathUtil.directionSpeed(1.05f);
        if (AutoFlyme.mc.field_1724.field_3913.field_3907 != 0.0f || AutoFlyme.mc.field_1724.field_3913.field_3905 != 0.0f) {
            AutoFlyme.mc.field_1724.method_18800(dir[0], AutoFlyme.mc.field_1724.method_18798().field_1351, dir[1]);
        } else {
            AutoFlyme.mc.field_1724.method_18800(0.0, AutoFlyme.mc.field_1724.method_18798().field_1351, 0.0);
        }
    }
}

