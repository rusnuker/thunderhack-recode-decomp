//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import it.unimi.dsi.fastutil.ints.*;
import com.google.common.eventbus.*;
import net.minecraft.*;

public class EntityDesync extends Module
{
    private class_1297 Riding;
    
    public EntityDesync() {
        super("EntityDesync", 0, false, Category.MISC);
        this.Riding = null;
        this.addSettings(new Setting[0]);
    }
    
    @Subscribe
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2752) {
            if (this.Riding == null) {
                return;
            }
            final class_2752 l_Packet = (class_2752)event.getPacket();
            final class_1297 en = EntityDesync.mc.field_1687.method_8469(l_Packet.method_11841());
            if (en == this.Riding) {
                for (final int i : l_Packet.method_11840()) {
                    final class_1297 ent = EntityDesync.mc.field_1687.method_8469(i);
                    if (ent == EntityDesync.mc.field_1724) {
                        return;
                    }
                }
                this.toggle();
            }
        }
        else if (event.getPacket() instanceof class_2716) {
            final class_2716 l_Packet2 = (class_2716)event.getPacket();
            for (final int l_EntityId : l_Packet2.method_36548()) {
                if (l_EntityId == this.Riding.method_5628()) {
                    return;
                }
            }
        }
    }
    
    public void onEnable() {
        super.onEnable();
        if (EntityDesync.mc.field_1724 == null) {
            this.Riding = null;
            this.toggle();
            EntityDesync.mc.field_1724.method_43496(class_2561.method_30163("player null"));
            return;
        }
        if (EntityDesync.mc.field_1724.method_5854() == null) {
            this.Riding = null;
            this.toggle();
            EntityDesync.mc.field_1724.method_43496(class_2561.method_30163("vehicle null"));
            return;
        }
        (this.Riding = EntityDesync.mc.field_1724.method_5854()).method_5636(EntityDesync.mc.field_1724.field_6241);
        EntityDesync.mc.field_1687.method_2945(this.Riding.method_5628(), class_1297.class_5529.field_26998);
    }
    
    public void onDisable() {
        super.onDisable();
        if (this.Riding != null) {
            if (!EntityDesync.mc.field_1724.method_3144()) {
                EntityDesync.mc.field_1687.method_8649(this.Riding);
                EntityDesync.mc.field_1724.method_5873(this.Riding, true);
            }
            this.Riding = null;
        }
    }
    
    public void onTick() {
        super.onTick();
        if (this.Riding == null) {
            return;
        }
        if (EntityDesync.mc.field_1724.method_3144()) {
            return;
        }
        this.Riding.method_5814(EntityDesync.mc.field_1724.method_23317(), EntityDesync.mc.field_1724.method_23318(), EntityDesync.mc.field_1724.method_23321());
        EntityDesync.mc.field_1724.field_3944.method_2883((class_2596)new class_2833(this.Riding));
    }
}
