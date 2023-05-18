/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_5529
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2716
 *  net.minecraft.class_2752
 *  net.minecraft.class_2828
 *  net.minecraft.class_2833
 *  net.minecraft.class_7439
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.ModeSetting;
import net.minecraft.class_1297;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2716;
import net.minecraft.class_2752;
import net.minecraft.class_2828;
import net.minecraft.class_2833;
import net.minecraft.class_7439;

public class GodModule
extends Module {
    ModeSetting mode = new ModeSetting("mode", "teleport", "teleport", "move");
    private static GodModule INSTANCE = new GodModule();
    public class_1297 Riding = null;

    public GodModule() {
        super("GodModule", 0, false, Category.MISC);
        this.addSettings(this.mode);
        this.setInstance();
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        class_7439 packet;
        String a;
        if (event.getPacket() instanceof class_2752) {
            if (this.Riding == null) {
                return;
            }
            l_Packet = (class_2752)event.getPacket();
            en = GodModule.mc.field_1687.method_8469(l_Packet.method_11841());
            if (en == this.Riding) {
                for (int i : l_Packet.method_11840()) {
                    class_1297 ent = GodModule.mc.field_1687.method_8469(i);
                    if (ent != GodModule.mc.field_1724) continue;
                    return;
                }
                this.toggle();
            }
        } else if (event.getPacket() instanceof class_2716) {
            l_Packet = (class_2716)event.getPacket();
            en = l_Packet.method_36548().iterator();
            while (en.hasNext()) {
                int l_EntityId = (Integer)en.next();
                if (l_EntityId != this.Riding.method_5628()) continue;
                return;
            }
        }
        if (event.getPacket() instanceof class_7439 && (a = (packet = (class_7439)event.getPacket()).comp_763().getString()).contains("\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u0435")) {
            GodModule.mc.field_1724.method_43496(class_2561.method_30163((String)"/back"));
        }
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2828) {
            if (this.Riding == null) {
                return;
            }
            if (GodModule.mc.field_1724.method_3144()) {
                return;
            }
            this.Riding.method_5814(GodModule.mc.field_1724.method_23317(), GodModule.mc.field_1724.method_23318(), GodModule.mc.field_1724.method_23321());
            this.Riding.method_5636(GodModule.mc.field_1724.field_6241);
            GodModule.mc.field_1724.field_3944.method_2883((class_2596)new class_2833(this.Riding));
        }
    }

    public static GodModule getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GodModule();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (GodModule.mc.field_1724 == null) {
            this.Riding = null;
            this.toggle();
            GodModule.mc.field_1724.method_43496(class_2561.method_30163((String)"player null"));
            return;
        }
        if (GodModule.mc.field_1724.method_5854() == null) {
            this.Riding = null;
            this.toggle();
            GodModule.mc.field_1724.method_43496(class_2561.method_30163((String)"vehicle null"));
            return;
        }
        this.Riding = GodModule.mc.field_1724.method_5854();
        GodModule.mc.field_1687.method_2945(this.Riding.method_5628(), class_1297.class_5529.field_26999);
    }

    @Override
    public void onTick() {
        super.onTick();
        if (this.mode.getMode().equals("teleport")) {
            if (this.Riding == null) {
                return;
            }
            if (GodModule.mc.field_1724.method_3144()) {
                return;
            }
            this.Riding.method_5814(GodModule.mc.field_1724.method_23317(), GodModule.mc.field_1724.method_23318(), GodModule.mc.field_1724.method_23321());
            GodModule.mc.field_1724.field_3944.method_2883((class_2596)new class_2833(this.Riding));
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}

