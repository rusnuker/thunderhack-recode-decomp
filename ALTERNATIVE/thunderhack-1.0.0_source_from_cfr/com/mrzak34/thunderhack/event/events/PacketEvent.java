/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 */
package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.Cancellable;
import net.minecraft.class_2596;

public class PacketEvent
extends Cancellable {
    private final class_2596<?> packet;

    public PacketEvent(class_2596<?> packet) {
        this.packet = packet;
    }

    public class_2596<?> getPacket() {
        return this.packet;
    }

    public static class Receive
    extends PacketEvent {
        public Receive(class_2596<?> packet) {
            super(packet);
        }
    }

    public static class Send
    extends PacketEvent {
        public Send(class_2596<?> packet) {
            super(packet);
        }
    }
}

