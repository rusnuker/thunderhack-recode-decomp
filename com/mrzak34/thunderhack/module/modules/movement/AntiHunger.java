//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import net.minecraft.*;
import com.mrzak34.thunderhack.mixin.*;
import com.google.common.eventbus.*;

public class AntiHunger extends Module
{
    BooleanSetting sprint;
    BooleanSetting onGround;
    public boolean Field2015;
    
    public AntiHunger() {
        super("AntiHunger", 0, false, Category.MOVEMENT);
        this.sprint = new BooleanSetting("sprint", true);
        this.onGround = new BooleanSetting("onGround", true);
        this.Field2015 = false;
        this.addSettings(new Setting[] { this.sprint, this.onGround });
    }
    
    @Subscribe
    public void onPacketSend(final PacketEvent.Send event) {
        class_2848 cPacketEntityAction = null;
        if (event.getPacket() instanceof class_2848) {
            cPacketEntityAction = (class_2848)event.getPacket();
            if (this.sprint.isEnabled() && (cPacketEntityAction.method_12365() == class_2848.class_2849.field_12981 || cPacketEntityAction.method_12365() == class_2848.class_2849.field_12985)) {
                event.setCancelled(true);
            }
        }
        if (event.getPacket() instanceof class_2828) {
            final class_2828 cPacketPlayer = (class_2828)event.getPacket();
            final boolean bl = AntiHunger.mc.field_1724.method_24828();
            if (this.onGround.isEnabled() && this.Field2015 && bl && cPacketPlayer.method_12268(0.0) == (AntiHunger.mc.field_1724.field_3913.method_20622() ? AntiHunger.mc.field_1724.method_23318() : 0.0)) {
                ((IPlayerMoveC2SPacket)cPacketEntityAction).setOnGround(false);
            }
            this.Field2015 = bl;
        }
    }
}
