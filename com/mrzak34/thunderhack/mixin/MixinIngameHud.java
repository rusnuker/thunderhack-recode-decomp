//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.*;
import org.spongepowered.asm.mixin.injection.*;
import com.mrzak34.thunderhack.module.modules.render.*;
import com.mrzak34.thunderhack.util.*;

@Mixin({ class_329.class })
public abstract class MixinIngameHud implements Util
{
    @Inject(at = { @At("RETURN") }, method = { "render" }, cancellable = true)
    public void render(final class_4587 matrixStack, final float partialTicks, final CallbackInfo callback) {
        final RenderOverlayEvent event = new RenderOverlayEvent(matrixStack, partialTicks);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "render" }, at = { @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;interactionManager:Lnet/minecraft/client/network/ClientPlayerInteractionManager;", ordinal = 0) })
    private void onRender(final class_4587 matrices, final float tickDelta, final CallbackInfo ci) {
        if (MixinIngameHud.mc.field_1724 == null && MixinIngameHud.mc.field_1761 == null) {
            return;
        }
        final float threshold = (float)DamageTint.health.getValue();
        final float power = (float)DamageTint.power.getValue();
        if (MixinIngameHud.mc.field_1761.method_2920().method_8388() && DamageTint.getInstance().isToggled()) {
            RenderUtil.drawVignette(threshold, power);
        }
    }
}
