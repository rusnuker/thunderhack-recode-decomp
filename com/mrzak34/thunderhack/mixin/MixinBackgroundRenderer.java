//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import com.mrzak34.thunderhack.module.modules.render.*;
import com.mojang.blaze3d.systems.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin({ class_758.class })
public class MixinBackgroundRenderer
{
    @Inject(method = { "applyFog" }, at = { @At("TAIL") })
    private static void onApplyFog(final class_4184 camera, final class_758.class_4596 fogType, final float viewDistance, final boolean thickFog, final float tickDelta, final CallbackInfo info) {
        if (NoRender.getInstance().isToggled() && NoRender.fog.isEnabled() && fogType == class_758.class_4596.field_20946) {
            RenderSystem.setShaderFogStart(viewDistance * 4.0f);
            RenderSystem.setShaderFogEnd(viewDistance * 4.25f);
        }
    }
    
    @Inject(method = { "getFogModifier(Lnet/minecraft/entity/Entity;F)Lnet/minecraft/client/render/BackgroundRenderer$StatusEffectFogModifier;" }, at = { @At("HEAD") }, cancellable = true)
    private static void onGetFogModifier(final class_1297 entity, final float tickDelta, final CallbackInfoReturnable<Object> info) {
        if (NoRender.getInstance().isToggled() && NoRender.blindness.isEnabled()) {
            info.setReturnValue((Object)null);
        }
    }
}
