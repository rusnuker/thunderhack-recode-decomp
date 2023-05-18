//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import it.unimi.dsi.fastutil.ints.*;
import com.google.common.eventbus.*;
import net.minecraft.*;

public class GodModule extends Module
{
    ModeSetting mode;
    private static GodModule INSTANCE;
    public class_1297 Riding;
    
    public GodModule() {
        super("GodModule", 0, false, Category.MISC);
        this.mode = new ModeSetting("mode", "teleport", new String[] { "teleport", "move" });
        this.Riding = null;
        this.addSettings(new Setting[] { this.mode });
        this.setInstance();
    }
    
    @Subscribe
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2752) {
            if (this.Riding == null) {
                return;
            }
            final class_2752 l_Packet = (class_2752)event.getPacket();
            final class_1297 en = GodModule.mc.field_1687.method_8469(l_Packet.method_11841());
            if (en == this.Riding) {
                for (final int i : l_Packet.method_11840()) {
                    final class_1297 ent = GodModule.mc.field_1687.method_8469(i);
                    if (ent == GodModule.mc.field_1724) {
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
        if (event.getPacket() instanceof class_7439) {
            final class_7439 packet = (class_7439)event.getPacket();
            final String a = packet.comp_763().getString();
            if (a.contains("\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u0435")) {
                GodModule.mc.field_1724.method_43496(class_2561.method_30163("/back"));
            }
        }
    }
    
    @Subscribe
    public void onPacketSend(final PacketEvent.Send event) {
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
        if (GodModule.INSTANCE == null) {
            GodModule.INSTANCE = new GodModule();
        }
        return GodModule.INSTANCE;
    }
    
    private void setInstance() {
        GodModule.INSTANCE = this;
    }
    
    public void onEnable() {
        super.onEnable();
        if (GodModule.mc.field_1724 == null) {
            this.Riding = null;
            this.toggle();
            GodModule.mc.field_1724.method_43496(class_2561.method_30163("player null"));
            return;
        }
        if (GodModule.mc.field_1724.method_5854() == null) {
            this.Riding = null;
            this.toggle();
            GodModule.mc.field_1724.method_43496(class_2561.method_30163("vehicle null"));
            return;
        }
        this.Riding = GodModule.mc.field_1724.method_5854();
        GodModule.mc.field_1687.method_2945(this.Riding.method_5628(), class_1297.class_5529.field_26999);
    }
    
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
    
    public void onDisable() {
        super.onDisable();
    }
    
    static {
        GodModule.INSTANCE = new GodModule();
    }
}
