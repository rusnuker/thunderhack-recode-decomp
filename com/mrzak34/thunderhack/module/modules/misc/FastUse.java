//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.misc;

import java.util.*;
import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.mixin.*;
import com.google.common.eventbus.*;
import net.minecraft.*;
import com.google.common.collect.*;

public class FastUse extends Module
{
    NumberSetting delay;
    private static final Set<class_1792> THROWABLE;
    
    public FastUse() {
        super("FastUse", 0, false, Category.MISC);
        this.delay = new NumberSetting("delay", 1.0f, 0.0f, 4.0f, false);
        this.addSettings(new Setting[] { this.delay });
    }
    
    @Subscribe
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2886) {
            ((IMinecraftClient)FastUse.mc).setItemUseCooldown((int)(this.delay.getValue() * 4.0));
        }
    }
    
    static {
        THROWABLE = Sets.newHashSet((Object[])new class_1792[] { class_1802.field_8543, class_1802.field_8803, class_1802.field_8287, class_1802.field_8449, class_1802.field_8634, class_1802.field_8436, class_1802.field_8150 });
    }
}
