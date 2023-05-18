//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import net.minecraft.*;

public class Particle implements Util
{
    public static void spawnParticle(final class_4587 matrices, final int x, final int y, final int range) {
        class_5945.method_34682((class_1937)Particle.mc.field_1687, Particle.mc.field_1724.method_24515(), (class_2394)class_2398.field_11220, (class_6017)null);
    }
    
    public static int particleDistance(final int cetreX, final int cetreY, final int particleX, final int particleY) {
        final int distance = (int)Math.sqrt((cetreX - particleX) * (cetreX - particleX) + (cetreY - particleY) * (cetreY - particleY));
        return distance;
    }
    
    private static class_241 getRandomVelocity(final class_5819 random) {
        return new class_241(class_3532.method_15344(random, -0.5f, 0.5f), class_3532.method_15344(random, -0.5f, 0.5f));
    }
}
