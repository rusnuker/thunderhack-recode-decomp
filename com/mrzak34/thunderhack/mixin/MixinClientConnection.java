//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.*;
import com.mrzak34.thunderhack.event.events.*;

@Mixin({ class_2535.class })
public class MixinClientConnection
{
    @Inject(method = { "handlePacket" }, at = { @At("HEAD") }, cancellable = true)
    private static <T extends class_2547> void packetReceive(final class_2596<T> packet, final class_2547 listener, final CallbackInfo callback) {
        final PacketEvent.Receive event = new PacketEvent.Receive((class_2596)packet);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "send(Lnet/minecraft/network/packet/Packet;)V" }, cancellable = true, at = { @At("HEAD") })
    public void send(final class_2596<?> packet, final CallbackInfo callback) {
        final PacketEvent.Send event = new PacketEvent.Send((class_2596)packet);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
        if (packet instanceof class_2797) {
            final class_2797 chatMessageC2SPacket = (class_2797)event.getPacket();
            final ChatEvent chatEvent = new ChatEvent(chatMessageC2SPacket.comp_945());
            Main.EVENT_BUS.post((Object)chatEvent);
            if (chatEvent.isCancelled()) {
                callback.cancel();
            }
        }
        if (packet instanceof class_7472) {
            final class_7472 commandExecutionC2SPacket = (class_7472)event.getPacket();
            final CommandEvent commandEvent = new CommandEvent(commandExecutionC2SPacket.comp_808());
            Main.EVENT_BUS.post((Object)commandEvent);
            if (commandEvent.isCancelled()) {
                callback.cancel();
            }
        }
    }
}
