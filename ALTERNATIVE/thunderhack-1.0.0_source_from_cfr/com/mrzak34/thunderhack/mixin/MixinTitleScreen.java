/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_364
 *  net.minecraft.class_4185
 *  net.minecraft.class_437
 *  net.minecraft.class_442
 *  net.minecraft.class_4587
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.ui.click.AccountSwitcherScreen;
import com.mrzak34.thunderhack.ui.click.ThunderGui;
import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_2561;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_4587;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_442.class})
public abstract class MixinTitleScreen
extends class_437 {
    public AccountSwitcherScreen accountSwitcher = new AccountSwitcherScreen();
    public ThunderGui thunderGui = new ThunderGui();

    protected MixinTitleScreen(class_2561 title) {
        super(title);
    }

    @Inject(method={"initWidgetsNormal"}, at={@At(value="RETURN")})
    private void addCustomButton(int y, int spacingY, CallbackInfo info) {
        String playerName = Util.mc.method_1548().method_1676();
        this.method_37063((class_364)class_4185.method_46430((class_2561)class_2561.method_30163((String)playerName), button -> Util.mc.method_1507((class_437)this.accountSwitcher)).method_46434(this.field_22789 / 2 - 100 + 205, y, Util.mc.field_1772.method_1727(playerName) + 8, 20).method_46431());
        this.method_37063((class_364)class_4185.method_46430((class_2561)class_2561.method_30163((String)"ThunderHack"), button -> Util.mc.method_1507((class_437)this.thunderGui)).method_46434(0, 30, Util.mc.field_1772.method_1727("ThunderHack") + 8, 20).method_46431());
    }

    @Inject(method={"render"}, at={@At(value="RETURN")})
    public void render(class_4587 matrices, int mouseX, int mouseY, float delta, CallbackInfo info) {
    }
}

