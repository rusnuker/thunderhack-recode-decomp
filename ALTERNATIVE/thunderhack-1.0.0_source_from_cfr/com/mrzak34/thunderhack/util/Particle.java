/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2394
 *  net.minecraft.class_2398
 *  net.minecraft.class_241
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_5819
 *  net.minecraft.class_5945
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_241;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_5819;
import net.minecraft.class_5945;

public class Particle
implements Util {
    public static void spawnParticle(class_4587 matrices, int x, int y, int range) {
        class_5945.method_34682((class_1937)Particle.mc.field_1687, (class_2338)Particle.mc.field_1724.method_24515(), (class_2394)class_2398.field_11220, null);
    }

    public static int particleDistance(int cetreX, int cetreY, int particleX, int particleY) {
        int distance = (int)Math.sqrt((cetreX - particleX) * (cetreX - particleX) + (cetreY - particleY) * (cetreY - particleY));
        return distance;
    }

    private static class_241 getRandomVelocity(class_5819 random) {
        return new class_241(class_3532.method_15344((class_5819)random, (float)-0.5f, (float)0.5f), class_3532.method_15344((class_5819)random, (float)-0.5f, (float)0.5f));
    }
}

