/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 */
package com.mrzak34.thunderhack.ui.click;

import com.mrzak34.thunderhack.Account.Account;
import com.mrzak34.thunderhack.Account.Auth;
import com.mrzak34.thunderhack.Account.OfflineAccount;
import com.mrzak34.thunderhack.manager.AccountManager;
import com.mrzak34.thunderhack.util.KeyUtil;
import java.awt.Color;
import java.util.Objects;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public class AccountSwitcherScreen
extends class_437 {
    public static class_310 mc = class_310.method_1551();
    int WindowX = 0;
    int WindowY = 0;
    int oldWindowX;
    int oldWindowY;
    boolean mouseClicked;
    boolean editing = false;
    int editingX = this.field_22789 / 2 - 100;
    int editingY = this.field_22790 / 2 - 50;
    int oldEditingX;
    int oldEditingY;
    boolean editingMouseClicked;
    boolean editingString = false;
    String nameStrig;
    Color buttonColor = new Color(70, 70, 70);

    public AccountSwitcherScreen() {
        super(class_2561.method_30163((String)"Thunder AccountSwitcher"));
    }

    public void method_25394(class_4587 matrices, int mouseX, int mouseY, float partialTicks) {
        class_327 tr = AccountSwitcherScreen.mc.field_1772;
        Objects.requireNonNull(AccountSwitcherScreen.mc.field_1772);
        int fontPosY = 7 - 9 / 2;
        this.method_25420(matrices);
        if (!this.editing) {
            if (this.mouseClicked) {
                this.WindowX += mouseX - this.oldWindowX;
                this.WindowY += mouseY - this.oldWindowY;
            }
            class_332.method_25294((class_4587)matrices, (int)this.WindowX, (int)this.WindowY, (int)(this.WindowX + this.field_22789 / 3), (int)(this.WindowY + this.field_22790 / 2), (int)new Color(70, 70, 70).getRGB());
            class_332.method_25294((class_4587)matrices, (int)(this.WindowX + 10), (int)(this.WindowY + this.field_22790 / 2 - 30), (int)(this.WindowX + 50), (int)(this.WindowY + this.field_22790 / 2 - 10), (int)new Color(0, 255, 0).getRGB());
            int accountY = this.WindowY + 10;
            try {
                for (Account account : AccountManager.accounts) {
                    class_332.method_25294((class_4587)matrices, (int)(this.WindowX + 10), (int)accountY, (int)(this.WindowX + this.field_22789 / 3 - 20), (int)(this.WindowY + 10), (int)new Color(100, 100, 100).getRGB());
                    class_332.method_25303((class_4587)matrices, (class_327)tr, (String)account.name(), (int)(this.WindowX + 15), (int)(accountY + 2), (int)-1);
                    accountY += 30;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.oldWindowX = mouseX;
            this.oldWindowY = mouseY;
        } else {
            if (this.editingMouseClicked) {
                this.editingX += mouseX - this.oldEditingX;
                this.editingY += mouseY - this.oldEditingY;
            }
            class_332.method_25294((class_4587)matrices, (int)this.editingX, (int)this.editingY, (int)(this.editingX + 200), (int)(this.editingY + 100), (int)new Color(70, 70, 70).getRGB());
            class_332.method_25294((class_4587)matrices, (int)(this.editingX + 10), (int)(this.editingY + 10), (int)(this.editingX + 190), (int)(this.editingY + 20), (int)new Color(0, 0, 0).getRGB());
            class_332.method_25303((class_4587)matrices, (class_327)tr, (String)this.nameStrig, (int)(this.editingX + 12), (int)(this.editingY + 12), (int)-1);
            this.oldEditingX = mouseX;
            this.oldEditingY = mouseY;
        }
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        if (!this.editing) {
            if (mouseX >= (double)this.WindowX && mouseX <= (double)(this.WindowX + this.field_22789 / 3) && mouseY >= (double)this.WindowY && mouseY <= (double)(this.WindowY + this.field_22790 / 2) && button == 0) {
                this.mouseClicked = true;
            }
            if (mouseX >= (double)(this.WindowX + 10) && mouseX <= (double)(this.WindowX + 50) && mouseY >= (double)(this.WindowY + this.field_22790 / 2 - 30) && mouseY <= (double)(this.WindowY + this.field_22790 / 2 - 10) && button == 0) {
                this.editing = true;
            }
        } else {
            if (mouseX >= (double)this.editingX && mouseX <= (double)(this.editingX + 200) && mouseY >= (double)this.editingY && mouseY <= (double)(this.editingY + 100) && button == 0) {
                this.editingMouseClicked = true;
            }
            if (mouseX >= (double)(this.editingX + 10) && mouseX <= (double)(this.editingX + 190) && mouseY >= (double)(this.editingY + 10) && mouseY <= (double)(this.editingY + 20) && button == 0) {
                this.editingString = true;
            }
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        this.mouseClicked = false;
        this.editingMouseClicked = false;
        return super.method_25406(mouseX, mouseY, button);
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (this.editing && this.editingString) {
            if (keyCode != 259 && keyCode != 256 && keyCode != 257) {
                this.nameStrig = this.nameStrig + KeyUtil.getKeyName(keyCode);
            }
            if (keyCode == 259 && this.nameStrig.length() >= 1) {
                this.nameStrig = this.nameStrig.substring(0, this.nameStrig.length() - 1);
            }
            if (keyCode == 257) {
                this.editing = false;
                this.editingString = false;
                OfflineAccount account = new OfflineAccount(this.nameStrig, Auth.resolveUUID(this.nameStrig));
                try {
                    AccountManager.accounts.add(account);
                }
                catch (Exception e) {
                    System.out.println("name:" + account.name() + " uuid:" + account.uuid());
                }
                this.nameStrig = "";
            }
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }
}

