/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_4184
 *  net.minecraft.class_758
 *  net.minecraft.class_758$class_4596
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.mrzak34.thunderhack.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mrzak34.thunderhack.module.modules.render.NoRender;
import net.minecraft.class_1297;
import net.minecraft.class_4184;
import net.minecraft.class_758;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_758.class})
public class MixinBackgroundRenderer {
    @Inject(method={"applyFog"}, at={@At(value="TAIL")})
    private static void onApplyFog(class_4184 camera, class_758.class_4596 fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo info) {
        if (NoRender.getInstance().isToggled() && NoRender.fog.isEnabled() && fogType == class_758.class_4596.field_20946) {
            RenderSystem.setShaderFogStart((float)(viewDistance * 4.0f));
            RenderSystem.setShaderFogEnd((float)(viewDistance * 4.25f));
        }
    }

    @Inject(method={"getFogModifier(Lnet/minecraft/entity/Entity;F)Lnet/minecraft/client/render/BackgroundRenderer$StatusEffectFogModifier;"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onGetFogModifier(class_1297 entity, float tickDelta, CallbackInfoReturnable<Object> info) {
        if (NoRender.getInstance().isToggled() && NoRender.blindness.isEnabled()) {
            info.setReturnValue(null);
        }
    }
}

