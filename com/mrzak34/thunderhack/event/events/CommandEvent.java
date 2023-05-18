//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.*;

public class CommandEvent extends Cancellable
{
    String command;
    
    public CommandEvent(final String chatMessage) {
        this.command = chatMessage;
    }
    
    public String getCommand() {
        return this.command;
    }
    
    public String[] getElements() {
        return this.command.split(" ");
    }
}
