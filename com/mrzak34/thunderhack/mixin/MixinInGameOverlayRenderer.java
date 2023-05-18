//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.module.modules.render.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.*;

@Mixin({ class_4603.class })
public class MixinInGameOverlayRenderer
{
    @Inject(method = { "renderFireOverlay" }, at = { @At("HEAD") }, cancellable = true)
    private static void onRenderFireOverlay(final class_310 minecraftClient, final class_4587 matrixStack, final CallbackInfo ci) {
        if (NoRender.getInstance().isToggled() && NoRender.getInstance().fire.isEnabled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "renderUnderwaterOverlay" }, at = { @At("HEAD") }, cancellable = true)
    private static void onRenderUnderwaterOverlay(final class_310 minecraftClient, final class_4587 matrixStack, final CallbackInfo ci) {
        if (NoRender.getInstance().isToggled() && NoRender.getInstance().water.isEnabled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "renderInWallOverlay" }, at = { @At("HEAD") }, cancellable = true)
    private static void render(final class_1058 sprite, final class_4587 matrices, final CallbackInfo ci) {
        if (NoRender.getInstance().isToggled() && NoRender.blockOverlay.isEnabled()) {
            ci.cancel();
        }
    }
}
