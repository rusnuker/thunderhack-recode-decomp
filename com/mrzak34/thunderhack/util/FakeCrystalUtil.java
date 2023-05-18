//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import java.util.function.*;
import java.util.stream.*;
import java.util.*;

public class FakeCrystalUtil implements Util
{
    private static final List<FakeCrystalEntity> ENTITIES;
    
    public static FakeCrystalEntity get(final String name) {
        for (final FakeCrystalEntity fp : FakeCrystalUtil.ENTITIES) {
            if (fp.method_5820().equals(name)) {
                return fp;
            }
        }
        return null;
    }
    
    public static void add(final double x, final double y, final double z) {
        final FakeCrystalEntity fakePlayer = new FakeCrystalEntity(x, y, z);
        fakePlayer.spawn();
        FakeCrystalUtil.ENTITIES.add(fakePlayer);
    }
    
    public static void remove(final FakeCrystalEntity fp) {
        FakeCrystalUtil.ENTITIES.removeIf(fp1 -> {
            if (fp1.method_5820().equals(fp.method_5820())) {
                fp1.despawn();
                return true;
            }
            else {
                return false;
            }
        });
    }
    
    public static void clear() {
        if (FakeCrystalUtil.ENTITIES.isEmpty()) {
            return;
        }
        FakeCrystalUtil.ENTITIES.forEach(FakeCrystalEntity::despawn);
        FakeCrystalUtil.ENTITIES.clear();
    }
    
    public static void forEach(final Consumer<FakeCrystalEntity> action) {
        for (final FakeCrystalEntity fakePlayer : FakeCrystalUtil.ENTITIES) {
            action.accept(fakePlayer);
        }
    }
    
    public static int count() {
        return FakeCrystalUtil.ENTITIES.size();
    }
    
    public static Stream<FakeCrystalEntity> stream() {
        return FakeCrystalUtil.ENTITIES.stream();
    }
    
    public static boolean contains(final FakeCrystalEntity fp) {
        return FakeCrystalUtil.ENTITIES.contains(fp);
    }
    
    static {
        ENTITIES = new ArrayList<FakeCrystalEntity>();
    }
}
