/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1058
 *  net.minecraft.class_310
 *  net.minecraft.class_4587
 *  net.minecraft.class_4603
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.module.modules.render.NoRender;
import net.minecraft.class_1058;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4603;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_4603.class})
public class MixinInGameOverlayRenderer {
    @Inject(method={"renderFireOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onRenderFireOverlay(class_310 minecraftClient, class_4587 matrixStack, CallbackInfo ci) {
        if (NoRender.getInstance().isToggled() && NoRender.getInstance().fire.isEnabled()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderUnderwaterOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onRenderUnderwaterOverlay(class_310 minecraftClient, class_4587 matrixStack, CallbackInfo ci) {
        if (NoRender.getInstance().isToggled() && NoRender.getInstance().water.isEnabled()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderInWallOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private static void render(class_1058 sprite, class_4587 matrices, CallbackInfo ci) {
        if (NoRender.getInstance().isToggled() && NoRender.blockOverlay.isEnabled()) {
            ci.cancel();
        }
    }
}

