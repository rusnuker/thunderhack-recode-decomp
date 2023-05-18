//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import net.minecraft.*;

public final class CrystalPosition
{
    private final class_2338 position;
    private final double targetDamage;
    private final double selfDamage;
    
    public CrystalPosition(final class_2338 position, final double targetDamage, final double selfDamage) {
        this.position = position;
        this.targetDamage = targetDamage;
        this.selfDamage = selfDamage;
    }
    
    public class_2338 getPosition() {
        return this.position;
    }
    
    public double getTargetDamage() {
        return this.targetDamage;
    }
    
    public double getSelfDamage() {
        return this.selfDamage;
    }
}
