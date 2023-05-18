/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_243
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.FakePlayerEntity;
import com.mrzak34.thunderhack.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.minecraft.class_1657;
import net.minecraft.class_243;

public class FakePlayerUtil
implements Util {
    public static final List<FakePlayerEntity> ENTITIES = new ArrayList<FakePlayerEntity>();

    public static FakePlayerEntity get(String name) {
        for (FakePlayerEntity fp : ENTITIES) {
            if (!fp.method_5820().equals(name)) continue;
            return fp;
        }
        return null;
    }

    public static void add(String name, float health, boolean copyInv) {
        FakePlayerEntity fakePlayer = new FakePlayerEntity((class_1657)FakePlayerUtil.mc.field_1724, name, health, copyInv);
        fakePlayer.spawn();
        ENTITIES.add(fakePlayer);
    }

    public static void addWithId(int id, class_243 pos) {
        FakePlayerEntity fakePlayer = new FakePlayerEntity((class_1657)FakePlayerUtil.mc.field_1687.method_8469(id), pos, null);
        fakePlayer.spawn();
        ENTITIES.add(fakePlayer);
    }

    public static void addWithPlayer(FakePlayerEntity player) {
        player.spawn();
        ENTITIES.add(player);
    }

    public static void remove(FakePlayerEntity fp) {
        ENTITIES.removeIf(fp1 -> {
            if (fp1.method_5820().equals(fp.method_5820())) {
                fp1.despawn();
                return true;
            }
            return false;
        });
    }

    public static void clear() {
        if (ENTITIES.isEmpty()) {
            return;
        }
        ENTITIES.forEach(FakePlayerEntity::despawn);
        ENTITIES.clear();
    }

    public static void forEach(Consumer<FakePlayerEntity> action) {
        for (FakePlayerEntity fakePlayer : ENTITIES) {
            action.accept(fakePlayer);
        }
    }

    public static int count() {
        return ENTITIES.size();
    }

    public static Stream<FakePlayerEntity> stream() {
        return ENTITIES.stream();
    }

    public static boolean contains(FakePlayerEntity fp) {
        return ENTITIES.contains((Object)fp);
    }
}

