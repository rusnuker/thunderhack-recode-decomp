/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_2561
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_897
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.module.modules.render.Nametags;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2561;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_897.class})
public class MixinEntityRenderer {
    @Inject(method={"renderLabelIfPresent"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderLabel(class_1297 entity, class_2561 text, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
        if (entity instanceof class_1657 && Nametags.getInstance().isToggled()) {
            ci.cancel();
        }
    }
}

