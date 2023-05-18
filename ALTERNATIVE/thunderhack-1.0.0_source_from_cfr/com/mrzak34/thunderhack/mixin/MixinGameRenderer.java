/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_757
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.module.modules.render.NoRender;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_757.class})
public class MixinGameRenderer {
    @Inject(method={"tiltViewWhenHurt"}, at={@At(value="HEAD")}, cancellable=true)
    private void bobView(class_4587 matrixStack, float f, CallbackInfo ci) {
        if (NoRender.getInstance().isToggled() && NoRender.getInstance().hurtcam.isEnabled()) {
            ci.cancel();
        }
    }

    @Inject(method={"showFloatingItem"}, at={@At(value="HEAD")}, cancellable=true)
    private void showFloatingItem(class_1799 floatingItem, CallbackInfo ci) {
        if (NoRender.getInstance().isToggled() && NoRender.getInstance().totem.isEnabled() && floatingItem.method_7909() == class_1802.field_8288) {
            ci.cancel();
        }
    }

    @Redirect(method={"renderWorld"}, at=@At(value="INVOKE", target="Lnet/minecraft/util/math/MathHelper;lerp(FFF)F", ordinal=0), require=0)
    private float nauseaWobble(float delta, float first, float second) {
        return NoRender.getInstance().isToggled() && NoRender.getInstance().nausea.isEnabled() ? 0.0f : class_3532.method_16439((float)delta, (float)first, (float)second);
    }
}

