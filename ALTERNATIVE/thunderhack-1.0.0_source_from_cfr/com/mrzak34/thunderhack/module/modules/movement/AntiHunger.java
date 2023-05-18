/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
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
import com.mrzak34.thunderhack.settings.BooleanSetting;
import net.minecraft.class_2828;
import net.minecraft.class_2848;

public class AntiHunger
extends Module {
    BooleanSetting sprint = new BooleanSetting("sprint", true);
    BooleanSetting onGround = new BooleanSetting("onGround", true);
    public boolean Field2015 = false;

    public AntiHunger() {
        super("AntiHunger", 0, false, Category.MOVEMENT);
        this.addSettings(this.sprint, this.onGround);
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        class_2848 cPacketEntityAction = null;
        if (event.getPacket() instanceof class_2848) {
            cPacketEntityAction = (class_2848)event.getPacket();
            if (this.sprint.isEnabled() && (cPacketEntityAction.method_12365() == class_2848.class_2849.field_12981 || cPacketEntityAction.method_12365() == class_2848.class_2849.field_12985)) {
                event.setCancelled(true);
            }
        }
        if (event.getPacket() instanceof class_2828) {
            class_2828 cPacketPlayer = (class_2828)event.getPacket();
            boolean bl = AntiHunger.mc.field_1724.method_24828();
            if (this.onGround.isEnabled() && this.Field2015 && bl && cPacketPlayer.method_12268(0.0) == (!AntiHunger.mc.field_1724.field_3913.method_20622() ? 0.0 : AntiHunger.mc.field_1724.method_23318())) {
                ((IPlayerMoveC2SPacket)cPacketEntityAction).setOnGround(false);
            }
            this.Field2015 = bl;
        }
    }
}

