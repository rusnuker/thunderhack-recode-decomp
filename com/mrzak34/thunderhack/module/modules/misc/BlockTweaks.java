//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import net.minecraft.*;
import com.google.common.eventbus.*;

public class BlockTweaks extends Module
{
    BooleanSetting abort;
    
    public BlockTweaks() {
        super("BlockTweaks", 0, false, Category.MISC);
        this.abort = new BooleanSetting("abort", true);
        this.addSettings(new Setting[] { this.abort });
    }
    
    @Subscribe
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2846) {
            final class_2846 packet = (class_2846)event.getPacket();
            if (packet.method_12363() == class_2846.class_2847.field_12973 || packet.method_12363() == class_2846.class_2847.field_12971) {
                event.setCancelled(true);
            }
        }
    }
}
