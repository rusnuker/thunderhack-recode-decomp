//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.module.modules.render.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ class_757.class })
public class MixinGameRenderer
{
    @Inject(method = { "tiltViewWhenHurt" }, at = { @At("HEAD") }, cancellable = true)
    private void bobView(final class_4587 matrixStack, final float f, final CallbackInfo ci) {
        if (NoRender.getInstance().isToggled() && NoRender.getInstance().hurtcam.isEnabled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "showFloatingItem" }, at = { @At("HEAD") }, cancellable = true)
    private void showFloatingItem(final class_1799 floatingItem, final CallbackInfo ci) {
        if (NoRender.getInstance().isToggled() && NoRender.getInstance().totem.isEnabled() && floatingItem.method_7909() == class_1802.field_8288) {
            ci.cancel();
        }
    }
    
    @Redirect(method = { "renderWorld" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;lerp(FFF)F", ordinal = 0), require = 0)
    private float nauseaWobble(final float delta, final float first, final float second) {
        return (NoRender.getInstance().isToggled() && NoRender.getInstance().nausea.isEnabled()) ? 0.0f : class_3532.method_16439(delta, first, second);
    }
}
