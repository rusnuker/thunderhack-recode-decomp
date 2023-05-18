//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import net.minecraft.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.*;

public class FakePlayerUtil implements Util
{
    public static final List<FakePlayerEntity> ENTITIES;
    
    public static FakePlayerEntity get(final String name) {
        for (final FakePlayerEntity fp : FakePlayerUtil.ENTITIES) {
            if (fp.method_5820().equals(name)) {
                return fp;
            }
        }
        return null;
    }
    
    public static void add(final String name, final float health, final boolean copyInv) {
        final FakePlayerEntity fakePlayer = new FakePlayerEntity((class_1657)FakePlayerUtil.mc.field_1724, name, health, copyInv);
        fakePlayer.spawn();
        FakePlayerUtil.ENTITIES.add(fakePlayer);
    }
    
    public static void addWithId(final int id, final class_243 pos) {
        final FakePlayerEntity fakePlayer = new FakePlayerEntity((class_1657)FakePlayerUtil.mc.field_1687.method_8469(id), pos, (class_640)null);
        fakePlayer.spawn();
        FakePlayerUtil.ENTITIES.add(fakePlayer);
    }
    
    public static void addWithPlayer(final FakePlayerEntity player) {
        player.spawn();
        FakePlayerUtil.ENTITIES.add(player);
    }
    
    public static void remove(final FakePlayerEntity fp) {
        FakePlayerUtil.ENTITIES.removeIf(fp1 -> {
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
        if (FakePlayerUtil.ENTITIES.isEmpty()) {
            return;
        }
        FakePlayerUtil.ENTITIES.forEach(FakePlayerEntity::despawn);
        FakePlayerUtil.ENTITIES.clear();
    }
    
    public static void forEach(final Consumer<FakePlayerEntity> action) {
        for (final FakePlayerEntity fakePlayer : FakePlayerUtil.ENTITIES) {
            action.accept(fakePlayer);
        }
    }
    
    public static int count() {
        return FakePlayerUtil.ENTITIES.size();
    }
    
    public static Stream<FakePlayerEntity> stream() {
        return FakePlayerUtil.ENTITIES.stream();
    }
    
    public static boolean contains(final FakePlayerEntity fp) {
        return FakePlayerUtil.ENTITIES.contains(fp);
    }
    
    static {
        ENTITIES = new ArrayList<FakePlayerEntity>();
    }
}
