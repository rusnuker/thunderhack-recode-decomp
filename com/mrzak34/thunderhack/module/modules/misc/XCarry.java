//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import net.minecraft.*;
import com.google.common.eventbus.*;

public class XCarry extends Module
{
    public XCarry() {
        super("XCarry", 0, false, Category.MISC);
        this.addSettings(new Setting[0]);
    }
    
    @Subscribe
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2645) {
            event.setCancelled(true);
        }
    }
}
