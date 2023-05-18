//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.*;

public class ChatEvent extends Cancellable
{
    String chatMessage;
    
    public ChatEvent(final String chatMessage) {
        this.chatMessage = chatMessage;
    }
    
    public String getMessage() {
        return this.chatMessage;
    }
}
