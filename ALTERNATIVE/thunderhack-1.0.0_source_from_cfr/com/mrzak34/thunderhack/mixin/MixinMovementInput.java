/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_3675
 *  net.minecraft.class_408
 *  net.minecraft.class_743
 *  net.minecraft.class_744
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.module.modules.movement.InventoryMove;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import net.minecraft.class_408;
import net.minecraft.class_743;
import net.minecraft.class_744;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={class_743.class}, priority=0x7FFFFFFF)
public abstract class MixinMovementInput
extends class_744 {
    @Redirect(method={"tick"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/option/KeyBinding;isPressed()Z"))
    public boolean isKeyPressed(class_304 keyBinding) {
        if (class_310.method_1551().field_1724 != null && InventoryMove.getInstance().isToggled() && !(class_310.method_1551().field_1755 instanceof class_408) && class_310.method_1551().field_1755 != null && class_310.method_1551().field_1690.field_1832 != keyBinding) {
            return class_3675.method_15987((long)class_310.method_1551().method_22683().method_4490(), (int)keyBinding.method_1429().method_1444());
        }
        return keyBinding.method_1434();
    }
}

