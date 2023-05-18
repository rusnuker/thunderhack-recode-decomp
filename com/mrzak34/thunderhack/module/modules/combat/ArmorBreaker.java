//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.mixin.*;
import net.minecraft.*;
import com.google.common.eventbus.*;

public class ArmorBreaker extends Module
{
    NumberSetting factor;
    
    public ArmorBreaker() {
        super("ArmorBreaker", 0, false, Category.MISC);
        this.factor = new NumberSetting("factor", 3.0f, 1.0f, 10.0f, false);
        this.addSettings(new Setting[] { this.factor });
    }
    
    @Subscribe
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2824 && ArmorBreaker.mc.field_1724.method_31548().method_7391().method_7909() instanceof class_1831) {
            final class_2824 packet = (class_2824)event.getPacket();
            if (((IPlayerInteractEntityC2SPacket)packet).getType().method_34211() == class_2824.class_5907.field_29172 && ((com.mrzak34.thunderhack.ducks.IPlayerInteractEntityC2SPacket)packet).isNatural()) {
                final class_2824 unnatural = packet;
                ((com.mrzak34.thunderhack.ducks.IPlayerInteractEntityC2SPacket)unnatural).setNatural(false);
                for (int i = 0; i < this.factor.getValue(); ++i) {
                    ArmorBreaker.mc.method_1562().method_2883((class_2596)unnatural);
                }
            }
        }
    }
}
