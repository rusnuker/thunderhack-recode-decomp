//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import net.minecraft.*;
import com.mrzak34.thunderhack.util.*;

public interface IEntity2
{
    boolean inWeb();
    
    class_1299 getType();
    
    long getDeathTime();
    
    boolean isPseudoDead();
    
    void setPseudoDead(final boolean p0);
    
    TimerUtil getPseudoTime();
    
    long getTimeStamp();
    
    void setDummy(final boolean p0);
    
    long getOldServerPosX();
    
    long getOldServerPosY();
    
    long getOldServerPosZ();
    
    void setOldServerPos(final long p0, final long p1, final long p2);
}
