/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.module.modules.combat.Velocity;
import net.minecraft.class_1297;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={class_1297.class})
public class MixinEntity {
    @Redirect(method={"pushAwayFrom"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void onPushAwayFrom(class_1297 entity, double deltaX, double deltaY, double deltaZ) {
        if (!Velocity.noPush.isEnabled()) {
            return;
        }
        entity.method_5750(entity.method_18798().field_1352 + deltaX, entity.method_18798().field_1351 + deltaY, entity.method_18798().field_1350 + deltaZ);
    }
}

