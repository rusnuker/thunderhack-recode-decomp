/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.Cancellable;

public class ChatEvent
extends Cancellable {
    String chatMessage;

    public ChatEvent(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public String getMessage() {
        return this.chatMessage;
    }
}

