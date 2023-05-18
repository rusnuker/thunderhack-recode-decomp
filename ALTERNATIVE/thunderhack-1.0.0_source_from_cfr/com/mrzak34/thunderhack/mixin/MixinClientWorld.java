/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297$class_5529
 *  net.minecraft.class_243
 *  net.minecraft.class_5712
 *  net.minecraft.class_5712$class_7397
 *  net.minecraft.class_638
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_5712;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_638.class})
public abstract class MixinClientWorld {
    @Shadow
    public abstract void method_32888(class_5712 var1, class_243 var2, class_5712.class_7397 var3);

    @Inject(method={"removeEntity"}, at={@At(value="RETURN", target="Lnet/minecraft/client/world/ClientWorld;removeEntity(ILnet/minecraft/entity/Entity$RemovalReason;)V")}, cancellable=true)
    private void move(int entityId, class_1297.class_5529 removalReason, CallbackInfo callback) {
    }
}

