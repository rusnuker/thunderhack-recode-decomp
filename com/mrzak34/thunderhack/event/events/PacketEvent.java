//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.*;
import net.minecraft.*;

public class PacketEvent extends Cancellable
{
    private final class_2596<?> packet;
    
    public PacketEvent(final class_2596<?> packet) {
        this.packet = packet;
    }
    
    public class_2596<?> getPacket() {
        return this.packet;
    }
    
    public static class Send extends PacketEvent
    {
        public Send(final class_2596<?> packet) {
            super(packet);
        }
    }
    
    public static class Receive extends PacketEvent
    {
        public Receive(final class_2596<?> packet) {
            super(packet);
        }
    }
}
