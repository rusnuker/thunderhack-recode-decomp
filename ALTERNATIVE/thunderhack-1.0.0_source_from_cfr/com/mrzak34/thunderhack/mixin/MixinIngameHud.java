/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_329
 *  net.minecraft.class_4587
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.event.events.RenderOverlayEvent;
import com.mrzak34.thunderhack.module.modules.render.DamageTint;
import com.mrzak34.thunderhack.util.RenderUtil;
import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_329;
import net.minecraft.class_4587;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_329.class})
public abstract class MixinIngameHud
implements Util {
    @Inject(at={@At(value="RETURN")}, method={"render"}, cancellable=true)
    public void render(class_4587 matrixStack, float partialTicks, CallbackInfo callback) {
        RenderOverlayEvent event = new RenderOverlayEvent(matrixStack, partialTicks);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }

    @Inject(method={"render"}, at={@At(value="FIELD", target="Lnet/minecraft/client/MinecraftClient;interactionManager:Lnet/minecraft/client/network/ClientPlayerInteractionManager;", ordinal=0)})
    private void onRender(class_4587 matrices, float tickDelta, CallbackInfo ci) {
        if (MixinIngameHud.mc.field_1724 == null && MixinIngameHud.mc.field_1761 == null) {
            return;
        }
        float threshold = (float)DamageTint.health.getValue();
        float power = (float)DamageTint.power.getValue();
        if (MixinIngameHud.mc.field_1761.method_2920().method_8388() && DamageTint.getInstance().isToggled()) {
            RenderUtil.drawVignette(threshold, power);
        }
    }
}

