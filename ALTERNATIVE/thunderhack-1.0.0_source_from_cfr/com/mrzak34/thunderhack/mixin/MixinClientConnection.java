/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2535
 *  net.minecraft.class_2547
 *  net.minecraft.class_2596
 *  net.minecraft.class_2797
 *  net.minecraft.class_7472
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.event.events.ChatEvent;
import com.mrzak34.thunderhack.event.events.CommandEvent;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import net.minecraft.class_2535;
import net.minecraft.class_2547;
import net.minecraft.class_2596;
import net.minecraft.class_2797;
import net.minecraft.class_7472;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_2535.class})
public class MixinClientConnection {
    @Inject(method={"handlePacket"}, at={@At(value="HEAD")}, cancellable=true)
    private static <T extends class_2547> void packetReceive(class_2596<T> packet, class_2547 listener, CallbackInfo callback) {
        PacketEvent.Receive event = new PacketEvent.Receive(packet);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }

    @Inject(method={"send(Lnet/minecraft/network/packet/Packet;)V"}, cancellable=true, at={@At(value="HEAD")})
    public void send(class_2596<?> packet, CallbackInfo callback) {
        PacketEvent.Send event = new PacketEvent.Send(packet);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
        if (packet instanceof class_2797) {
            class_2797 chatMessageC2SPacket = (class_2797)event.getPacket();
            ChatEvent chatEvent = new ChatEvent(chatMessageC2SPacket.comp_945());
            Main.EVENT_BUS.post((Object)chatEvent);
            if (chatEvent.isCancelled()) {
                callback.cancel();
            }
        }
        if (packet instanceof class_7472) {
            class_7472 commandExecutionC2SPacket = (class_7472)event.getPacket();
            CommandEvent commandEvent = new CommandEvent(commandExecutionC2SPacket.comp_808());
            Main.EVENT_BUS.post((Object)commandEvent);
            if (commandEvent.isCancelled()) {
                callback.cancel();
            }
        }
    }
}

