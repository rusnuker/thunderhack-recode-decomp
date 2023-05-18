/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.Cancellable;

public class CommandEvent
extends Cancellable {
    String command;

    public CommandEvent(String chatMessage) {
        this.command = chatMessage;
    }

    public String getCommand() {
        return this.command;
    }

    public String[] getElements() {
        return this.command.split(" ");
    }
}

