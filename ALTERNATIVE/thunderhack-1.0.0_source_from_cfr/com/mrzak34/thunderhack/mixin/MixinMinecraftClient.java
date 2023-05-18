/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_437
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.event.events.OpenScreenEvent;
import com.mrzak34.thunderhack.manager.ConfigManager;
import com.mrzak34.thunderhack.manager.EyeOfGod;
import com.mrzak34.thunderhack.manager.TotemPopManager;
import com.mrzak34.thunderhack.module.modules.misc.FpsLimiter;
import net.minecraft.class_310;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_310.class})
public class MixinMinecraftClient {
    @Shadow
    private boolean field_1695;

    @Inject(method={"tick"}, at={@At(value="HEAD")}, cancellable=true)
    public void onTick(CallbackInfo ci) {
        Main.INSTANCE.onTick();
        TotemPopManager.onTick();
        EyeOfGod.listerner();
    }

    @Inject(method={"setScreen"}, at={@At(value="HEAD")}, cancellable=true)
    private void onOpenScreen(class_437 screen, CallbackInfo callback) {
        OpenScreenEvent event = new OpenScreenEvent(screen);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }

    @Inject(method={"close"}, at={@At(value="HEAD")}, cancellable=true)
    private void close(CallbackInfo ci) {
        ConfigManager.saveSettings();
    }

    @Inject(method={"getFramerateLimit"}, at={@At(value="HEAD")}, cancellable=true)
    public void getFramerateLimit(CallbackInfoReturnable<Integer> cir) {
        if (FpsLimiter.getInstance().isToggled() && !this.field_1695) {
            cir.setReturnValue((Object)((int)FpsLimiter.limit.getValue()));
        }
    }
}

