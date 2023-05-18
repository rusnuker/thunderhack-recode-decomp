/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  it.unimi.dsi.fastutil.ints.IntListIterator
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_5529
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2716
 *  net.minecraft.class_2752
 *  net.minecraft.class_2833
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.Setting;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import net.minecraft.class_1297;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2716;
import net.minecraft.class_2752;
import net.minecraft.class_2833;

public class EntityDesync
extends Module {
    private class_1297 Riding = null;

    public EntityDesync() {
        super("EntityDesync", 0, false, Category.MISC);
        this.addSettings(new Setting[0]);
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2752) {
            if (this.Riding == null) {
                return;
            }
            class_2752 l_Packet = (class_2752)event.getPacket();
            class_1297 en = EntityDesync.mc.field_1687.method_8469(l_Packet.method_11841());
            if (en == this.Riding) {
                for (int i : l_Packet.method_11840()) {
                    class_1297 ent = EntityDesync.mc.field_1687.method_8469(i);
                    if (ent != EntityDesync.mc.field_1724) continue;
                    return;
                }
                this.toggle();
            }
        } else if (event.getPacket() instanceof class_2716) {
            class_2716 l_Packet = (class_2716)event.getPacket();
            IntListIterator intListIterator = l_Packet.method_36548().iterator();
            while (intListIterator.hasNext()) {
                int l_EntityId = (Integer)intListIterator.next();
                if (l_EntityId != this.Riding.method_5628()) continue;
                return;
            }
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (EntityDesync.mc.field_1724 == null) {
            this.Riding = null;
            this.toggle();
            EntityDesync.mc.field_1724.method_43496(class_2561.method_30163((String)"player null"));
            return;
        }
        if (EntityDesync.mc.field_1724.method_5854() == null) {
            this.Riding = null;
            this.toggle();
            EntityDesync.mc.field_1724.method_43496(class_2561.method_30163((String)"vehicle null"));
            return;
        }
        this.Riding = EntityDesync.mc.field_1724.method_5854();
        this.Riding.method_5636(EntityDesync.mc.field_1724.field_6241);
        EntityDesync.mc.field_1687.method_2945(this.Riding.method_5628(), class_1297.class_5529.field_26998);
    }

    @Override
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

    @Override
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

