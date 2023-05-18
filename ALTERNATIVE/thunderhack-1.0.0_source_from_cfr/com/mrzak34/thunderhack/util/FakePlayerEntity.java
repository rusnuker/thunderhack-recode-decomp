/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_5529
 *  net.minecraft.class_1657
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 *  net.minecraft.class_640
 *  net.minecraft.class_745
 */
package com.mrzak34.thunderhack.util;

import com.mojang.authlib.GameProfile;
import com.mrzak34.thunderhack.util.RotationUtil;
import com.mrzak34.thunderhack.util.TargetUtil;
import com.mrzak34.thunderhack.util.Util;
import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_640;
import net.minecraft.class_745;

public class FakePlayerEntity
extends class_745 {
    public boolean doNotPush;
    public boolean hideWhenInsideCamera;

    public FakePlayerEntity(class_1657 player, String name, float health, boolean copyInv) {
        super(Util.mc.field_1687, new GameProfile(UUID.randomUUID(), name));
        this.method_5719((class_1297)player);
        this.field_5982 = this.method_36454();
        this.field_6004 = this.method_36455();
        this.field_6259 = this.field_6241 = player.field_6241;
        this.field_6220 = this.field_6283 = player.field_6283;
        Byte playerModel = (Byte)player.method_5841().method_12789(class_1657.field_7518);
        this.field_6011.method_12778(class_1657.field_7518, (Object)playerModel);
        this.method_6127().method_26846(player.method_6127());
        this.method_18380(player.method_18376());
        this.field_7500 = this.method_23317();
        this.field_7521 = this.method_23318();
        this.field_7499 = this.method_23321();
        if (health <= 20.0f) {
            this.method_6033(health);
        } else {
            this.method_6033(health);
            this.method_6073(health - 20.0f);
        }
        if (copyInv) {
            this.method_31548().method_7377(player.method_31548());
        }
    }

    public FakePlayerEntity(class_1657 player, class_243 pos, class_640 playerListEntry) {
        super(Util.mc.field_1687, playerListEntry.method_2966());
        class_241 rotate;
        try {
            rotate = RotationUtil.lookAtEntity((class_1297)player, (class_1297)TargetUtil.getTargetWithPlayer(player, 4.2));
        }
        catch (Exception e) {
            rotate = new class_241(0.0f, 0.0f);
        }
        this.method_5719((class_1297)player);
        this.method_5808(pos.field_1352, pos.field_1351, pos.field_1350, rotate.field_1343, rotate.field_1342);
        this.field_5982 = rotate.field_1343;
        this.field_6004 = rotate.field_1342;
        this.field_6241 = Util.mc.field_1724.field_6241;
        this.field_6259 = Util.mc.field_1724.field_6241;
        this.field_6283 = Util.mc.field_1724.field_6283;
        this.field_6220 = Util.mc.field_1724.field_6283;
        Byte playerModel = (Byte)player.method_5841().method_12789(class_1657.field_7518);
        this.field_6011.method_12778(class_1657.field_7518, (Object)playerModel);
        this.method_6127().method_26846(player.method_6127());
        this.method_18380(player.method_18376());
        this.field_7500 = this.method_23317();
        this.field_7521 = this.method_23318();
        this.field_7499 = this.method_23321();
        this.method_6033(20.0f);
    }

    public void spawn() {
        this.method_31482();
        Util.mc.field_1687.method_2942(this.method_5628(), (class_1297)this);
    }

    public void despawn() {
        Util.mc.field_1687.method_2945(this.method_5628(), class_1297.class_5529.field_26999);
        this.method_31745(class_1297.class_5529.field_26999);
    }
}

