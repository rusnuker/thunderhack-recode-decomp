/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1262
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1738
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1806
 *  net.minecraft.class_1831
 *  net.minecraft.class_1840
 *  net.minecraft.class_1843
 *  net.minecraft.class_1887
 *  net.minecraft.class_1937
 *  net.minecraft.class_22
 *  net.minecraft.class_2371
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2561
 *  net.minecraft.class_2561$class_2562
 *  net.minecraft.class_2960
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_5348
 *  net.minecraft.class_5481
 *  net.minecraft.class_757
 *  net.minecraft.class_7833
 */
package com.mrzak34.thunderhack.module.modules.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrzak34.thunderhack.event.events.RenderWorldEvent;
import com.mrzak34.thunderhack.manager.TotemPopManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.settings.ParentSetting;
import com.mrzak34.thunderhack.util.EntityUtil;
import com.mrzak34.thunderhack.util.FriendUtil;
import com.mrzak34.thunderhack.util.RenderUtil;
import java.awt.Color;
import java.io.Serializable;
import java.util.Objects;
import net.minecraft.class_1262;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1738;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1806;
import net.minecraft.class_1831;
import net.minecraft.class_1840;
import net.minecraft.class_1843;
import net.minecraft.class_1887;
import net.minecraft.class_1937;
import net.minecraft.class_22;
import net.minecraft.class_2371;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import net.minecraft.class_757;
import net.minecraft.class_7833;

public class Nametags
extends Module {
    public static BooleanSetting smartScale = new BooleanSetting("smart scale", true);
    public static NumberSetting factor = new NumberSetting("factor", 10.0f, 1.0f, 25.0f, false);
    public static ModeSetting heartMode = new ModeSetting("heart mode", "default", "default", "hard");
    public static ModeSetting frameMode = new ModeSetting("frame mode", "out", "out", "in");
    public static ParentSetting heartBar = new ParentSetting("heart bar", true, true, heartMode, frameMode);
    public static NumberSetting height = new NumberSetting("height", 50.0f, 0.0f, 100.0f, false);
    public static BooleanSetting ping = new BooleanSetting("ping", true);
    public static BooleanSetting entityID = new BooleanSetting("entity id", false);
    public static BooleanSetting gamemode = new BooleanSetting("gamemode", false);
    public static BooleanSetting health = new BooleanSetting("health", true);
    public static BooleanSetting pops = new BooleanSetting("pops", true);
    public static BooleanSetting armor = new BooleanSetting("armor", true);
    public static BooleanSetting heldStackName = new BooleanSetting("held stack name", true);
    public static BooleanSetting head = new BooleanSetting("head", true);
    public static ParentSetting display = new ParentSetting("display", false, false, ping, entityID, gamemode, health, pops, armor, head);
    public static NumberSetting backgroundRed = new NumberSetting("back red", 0.0f, 0.0f, 255.0f, false);
    public static NumberSetting backgroundGreen = new NumberSetting("back green", 0.0f, 0.0f, 255.0f, false);
    public static NumberSetting backgroundBlue = new NumberSetting("back blue", 0.0f, 0.0f, 255.0f, false);
    public static NumberSetting backgroundAlpha = new NumberSetting("back alpha", 100.0f, 0.0f, 255.0f, false);
    public static ParentSetting background = new ParentSetting("background", true, true, backgroundRed, backgroundGreen, backgroundBlue, backgroundAlpha);
    public static NumberSetting width = new NumberSetting("width", 8.0f, 1.0f, 20.0f, false);
    public static NumberSetting outlineRed = new NumberSetting("outline red", 0.0f, 0.0f, 255.0f, false);
    public static NumberSetting outlineGreen = new NumberSetting("outline green", 0.0f, 0.0f, 255.0f, false);
    public static NumberSetting outlineBlue = new NumberSetting("outline blue", 0.0f, 0.0f, 255.0f, false);
    public static NumberSetting outlineAlpha = new NumberSetting("outline alpha", 255.0f, 0.0f, 255.0f, false);
    public static ParentSetting outline = new ParentSetting("outline", true, true, outlineRed, outlineGreen, outlineBlue, outlineAlpha, width);
    public static ModeSetting friendMode = new ModeSetting("friend mode", "outline", "back", "outline");
    public static NumberSetting friendRed = new NumberSetting("friend red", 0.0f, 0.0f, 255.0f, false);
    public static NumberSetting friendGreen = new NumberSetting("friend green", 255.0f, 0.0f, 255.0f, false);
    public static NumberSetting friendBlue = new NumberSetting("friend blue", 255.0f, 0.0f, 255.0f, false);
    public static ParentSetting friend = new ParentSetting("friend", false, false, friendMode, friendRed, friendGreen, friendBlue);
    public static NumberSetting mapScale = new NumberSetting("map scale", 0.3f, 0.1f, 1.0f, true);
    public static ParentSetting mapPeek = new ParentSetting("map peek", true, true, mapScale);
    public static NumberSetting bookScale = new NumberSetting("book scale", 0.8f, 0.1f, 1.0f, true);
    public static ParentSetting bookPeek = new ParentSetting("book peek", true, true, bookScale);
    public static NumberSetting shulkerScale = new NumberSetting("shulker scale", 0.8f, 0.1f, 1.0f, true);
    public static ParentSetting shulkerPeek = new ParentSetting("shulker peek", true, true, shulkerScale);
    private static final class_2960 HEALTH_TEXTURE = new class_2960("textures/gui/icons.png");
    private static final class_2960 MAP_TEXTURE = new class_2960("textures/map/map_background.png");
    private static final class_2960 BOOK_TEXTURE = new class_2960("textures/gui/book.png");
    private static final class_2960 TEXTURE_CONTAINER_BACKGROUND = new class_2960("textures/container.png");
    private static Nametags INSTANCE = new Nametags();

    public Nametags() {
        super("Nametags", 0, false, Category.RENDER);
        this.addSettings(smartScale, factor, height, heartBar, display, background, outline, friend, mapPeek, bookPeek, shulkerPeek);
        this.setInstance();
    }

    @Subscribe
    public void onRenderWorld(RenderWorldEvent event) {
        try {
            for (class_1657 player : Nametags.mc.field_1687.method_18456()) {
                if (player == null || player.equals((Object)Nametags.mc.field_1724)) continue;
                class_243 pos = RenderUtil.smoothMovement((class_1297)player).method_1031(0.0, (double)player.method_17682() + height.getValue() / 100.0, 0.0);
                this.renderNametag(player, pos);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void renderNametag(class_1657 player, class_243 pos) {
        class_327 tr = Nametags.mc.field_1772;
        class_4184 camera = Nametags.mc.field_1773.method_19418();
        double distance = Math.sqrt(player.method_5707(camera.method_19326()));
        double scale = distance * factor.getValue() / 100.0;
        if (distance <= 8.0 && smartScale.isEnabled()) {
            scale = 1.0;
        }
        String displayTag = this.getDisplayTag(player);
        int width = tr.method_1727(displayTag) / 2;
        Color background = new Color((int)backgroundRed.getValue(), (int)backgroundGreen.getValue(), (int)backgroundBlue.getValue(), (int)backgroundAlpha.getValue());
        Color outline = new Color((int)outlineRed.getValue(), (int)outlineGreen.getValue(), (int)outlineBlue.getValue(), (int)outlineAlpha.getValue());
        Color friend = new Color((int)friendRed.getValue(), (int)friendGreen.getValue(), (int)friendBlue.getValue());
        class_4587 matrices = RenderUtil.matrixFrom(pos);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        matrices.method_22904(0.0, 0.0, 0.0);
        matrices.method_22905(-0.025f * (float)scale, -0.025f * (float)scale, 1.0f);
        if (Nametags.background.isEnabled()) {
            int n = frameMode.getMode().equals("in") ? -Nametags.getMinWith(width) - 1 : -width - 2;
            Objects.requireNonNull(tr);
            class_332.method_25294((class_4587)matrices, (int)n, (int)(-9 - 4), (int)(frameMode.getMode().equals("in") ? Nametags.getMinWith(width) + 1 : width + 2), (int)(frameMode.getMode().equals("in") && heartBar.isEnabled() ? 12 : -2), (int)(friendMode.getMode().equals("back") ? (FriendUtil.isFriend(player.method_5477().getString()) ? new Color(friend.getRed(), friend.getGreen(), friend.getBlue(), background.getAlpha()).getRGB() : background.getRGB()) : background.getRGB()));
        }
        if (Nametags.outline.isEnabled()) {
            int n = frameMode.getMode().equals("in") ? -Nametags.getMinWith(width) - 1 : -width - 2;
            Objects.requireNonNull(tr);
            RenderUtil.drawOutlineRectTest(matrices, n, -9 - 4, frameMode.getMode().equals("in") ? Nametags.getMinWith(width) + 1 : width + 2, frameMode.getMode().equals("in") && heartBar.isEnabled() ? 12 : -2, friendMode.getMode().equals("outline") ? (FriendUtil.isFriend(player.method_5477().getString()) ? new Color(friend.getRed(), friend.getGreen(), friend.getBlue(), outline.getAlpha()).getRGB() : outline.getRGB()) : outline.getRGB(), (int)Nametags.width.getValue());
        }
        int n = -width;
        Objects.requireNonNull(tr);
        RenderUtil.drawString(matrices, displayTag, n, -9 - 2, -1);
        if (heartBar.isEnabled()) {
            Nametags.drawHeartsBar(matrices, player, 0, heartMode.getMode());
        }
        class_1799 renderMainHand = player.method_6047().method_7972();
        if (heldStackName.isEnabled() && renderMainHand != null && renderMainHand.method_7909() != class_1802.field_8162) {
            String stackName = renderMainHand.method_7964().getString();
            int stackNameWidth = Nametags.mc.field_1772.method_1727(stackName) / 2;
            matrices.method_22903();
            matrices.method_22905(0.75f, 0.75f, 0.0f);
            RenderUtil.drawString(matrices, stackName, -stackNameWidth, (int)(-((float)this.getBiggestArmorTag(player) + 20.0f)), -1);
            matrices.method_22905(1.5f, 1.5f, 1.0f);
            matrices.method_22909();
        }
        if (armor.isEnabled()) {
            int xOffset = 0;
            for (class_1799 stack : player.method_31548().field_7548) {
                if (stack == null) continue;
                xOffset -= 8;
            }
            xOffset -= 8;
            class_1799 renderOffhand = player.method_6079().method_7972();
            if (!renderOffhand.method_7942() || renderOffhand.method_7909() instanceof class_1831 || renderOffhand.method_7909() instanceof class_1738) {
                // empty if block
            }
            RenderUtil.renderItemStack(matrices, renderOffhand, xOffset, -23, null);
            xOffset += 16;
            for (class_1799 stack2 : player.method_31548().field_7548) {
                if (stack2 == null) continue;
                class_1799 armourStack = stack2.method_7972();
                if (!armourStack.method_7942() || armourStack.method_7909() instanceof class_1831 || armourStack.method_7909() instanceof class_1738) {
                    // empty if block
                }
                RenderUtil.renderItemStack(matrices, armourStack, xOffset, -23, null);
                xOffset += 16;
            }
            RenderUtil.renderItemStack(matrices, renderMainHand, xOffset, -23, null);
        }
        if (head.isEnabled()) {
            try {
                this.drawHead(matrices, Objects.requireNonNull(mc.method_1562().method_2871(player.method_5667()).method_2968()), Nametags.outline.isEnabled() ? -(width + 17) : -(width + 14), Nametags.outline.isEnabled() ? -14 : -13, Nametags.outline.isEnabled() ? 13 : 11);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        if (mapPeek.isEnabled()) {
            this.drawMap(matrices, player.method_6047(), Nametags.outline.isEnabled() ? Nametags.getMinWith(width) + 3 : Nametags.getMinWith(width) + 1, Nametags.outline.isEnabled() ? -14 : -13, (float)mapScale.getValue());
        }
        if (bookPeek.isEnabled()) {
            this.drawBookPeek(matrices, player.method_6047(), Nametags.outline.isEnabled() ? Nametags.getMinWith(width) + 3 : Nametags.getMinWith(width) + 1, Nametags.outline.isEnabled() ? -14 : -13, (float)bookScale.getValue());
        }
        if (shulkerPeek.isEnabled()) {
            this.drawShulkerPeek(matrices, player.method_6047(), Nametags.outline.isEnabled() ? Nametags.getMinWith(width) + 3 : Nametags.getMinWith(width) + 1, Nametags.outline.isEnabled() ? -14 : -13, (float)shulkerScale.getValue());
        }
    }

    public void drawShulkerPeek(class_4587 matrices, class_1799 stack, int x, int y, float scale) {
        if (stack.method_7909() == class_1802.field_8545) {
            class_2487 compoundTag = stack.method_7941("BlockEntityTag");
            class_2371 itemStacks = class_2371.method_10213((int)27, (Object)class_1799.field_8037);
            class_1262.method_5429((class_2487)compoundTag, (class_2371)itemStacks);
            matrices.method_22903();
            matrices.method_46416((float)x, (float)y, 0.0f);
            matrices.method_22905(scale, scale, scale);
            RenderSystem.setShader(class_757::method_34542);
            RenderSystem.setShaderTexture((int)0, (class_2960)TEXTURE_CONTAINER_BACKGROUND);
            class_332.method_25291((class_4587)matrices, (int)x, (int)y, (int)0, (float)0.0f, (float)0.0f, (int)176, (int)67, (int)176, (int)67);
            int row = 0;
            int i = 0;
            for (class_1799 itemStack : itemStacks) {
                RenderUtil.renderItemStack(matrices, itemStack, 8 + i * 18, 7 + row * 18, null);
                if (++i < 9) continue;
                i = 0;
                ++row;
            }
            matrices.method_22909();
        }
    }

    public void drawBookPeek(class_4587 matrices, class_1799 stack, int x, int y, float scale) {
        if (stack.method_7909() instanceof class_1840 || stack.method_7909() instanceof class_1843) {
            class_2561 page = this.getFirstPage(stack);
            matrices.method_46416((float)x, (float)y, 0.0f);
            matrices.method_22905(scale, scale, scale);
            RenderSystem.setShader(class_757::method_34543);
            RenderSystem.setShaderTexture((int)0, (class_2960)BOOK_TEXTURE);
            class_332.method_25290((class_4587)matrices, (int)0, (int)0, (float)12.0f, (float)0.0f, (int)112, (int)134, (int)179, (int)179);
            matrices.method_22903();
            matrices.method_46416(16.0f, 12.0f, 0.0f);
            matrices.method_22905(0.7f, 0.7f, 1.0f);
            int offset = 0;
            for (class_5481 line : Nametags.mc.field_1772.method_1728((class_5348)page, 112)) {
                Nametags.mc.field_1772.method_27528(matrices, line, 0.0f, (float)offset, 0);
                offset += 8;
            }
            matrices.method_22909();
        }
    }

    private class_2561 getFirstPage(class_1799 stack) {
        class_2487 tag = stack.method_7969();
        if (tag == null) {
            return null;
        }
        class_2499 pages = tag.method_10554("pages", 8);
        if (pages.size() < 1) {
            return null;
        }
        if (stack.method_7909() == class_1802.field_8674) {
            return class_2561.method_43470((String)pages.method_10608(0));
        }
        try {
            return class_2561.class_2562.method_10873((String)pages.method_10608(0));
        }
        catch (Exception e) {
            return class_2561.method_43470((String)"Invalid book data");
        }
    }

    public static int getMinWith(int with) {
        if (heartBar.isEnabled()) {
            if (with >= 52) {
                return with;
            }
            return 52;
        }
        return with;
    }

    public void drawMap(class_4587 matrices, class_1799 stack, int x, int y, float scale) {
        if (stack.method_7909() == class_1802.field_8204) {
            Integer mapId = class_1806.method_8003((class_1799)stack);
            class_22 state = class_1806.method_7997((Integer)mapId, (class_1937)Nametags.mc.field_1687);
            class_4597.class_4598 consumer = mc.method_22940().method_23000();
            if (state == null) {
                return;
            }
            matrices.method_22903();
            matrices.method_46416((float)x, (float)y, 0.0f);
            matrices.method_22905(scale * 2.0f, scale * 2.0f, 0.0f);
            matrices.method_22905(1.125f, 1.125f, 0.0f);
            RenderSystem.setShader(class_757::method_34543);
            RenderSystem.setShaderTexture((int)0, (class_2960)MAP_TEXTURE);
            class_332.method_25291((class_4587)matrices, (int)0, (int)0, (int)0, (float)0.0f, (float)0.0f, (int)64, (int)64, (int)64, (int)64);
            matrices.method_22909();
            matrices.method_22903();
            matrices.method_46416((float)x, (float)y, 0.0f);
            matrices.method_22905(scale, scale, 0.0f);
            matrices.method_46416(8.0f, 8.0f, 0.0f);
            Nametags.mc.field_1773.method_3194().method_1773(matrices, (class_4597)consumer, mapId.intValue(), state, false, 0xF000F0);
            consumer.method_22993();
            matrices.method_22909();
        }
    }

    public void drawHead(class_4587 matrices, class_2960 skin, int x, int y, int scale) {
        RenderSystem.setShaderTexture((int)0, (class_2960)skin);
        class_332.method_25293((class_4587)matrices, (int)x, (int)y, (int)scale, (int)scale, (float)8.0f, (float)8.0f, (int)8, (int)8, (int)64, (int)64);
        class_332.method_25293((class_4587)matrices, (int)x, (int)y, (int)scale, (int)scale, (float)40.0f, (float)8.0f, (int)8, (int)8, (int)64, (int)64);
    }

    private int getBiggestArmorTag(class_1657 player) {
        class_1799 renderOffHand;
        class_1887 enc;
        int index;
        float enchantmentY = 0.0f;
        boolean arm = false;
        for (class_1799 stack : player.method_31548().field_7548) {
            float encY = 0.0f;
            if (stack != null) {
                class_2499 enchants = stack.method_7921();
                index = 0;
                while ((long)index < enchants.stream().count()) {
                    short id = enchants.method_10602(index).method_10568("id");
                    enc = class_1887.method_8191((int)id);
                    if (enc != null) {
                        encY += 8.0f;
                        arm = true;
                    }
                    ++index;
                }
            }
            if (encY <= enchantmentY) continue;
            enchantmentY = encY;
        }
        class_1799 renderMainHand = player.method_6047().method_7972();
        if (renderMainHand.method_7942()) {
            float encY2 = 0.0f;
            class_2499 enchants2 = renderMainHand.method_7921();
            int index2 = 0;
            while ((long)index2 < enchants2.stream().count()) {
                short id = enchants2.method_10602(index2).method_10568("id");
                class_1887 enc2 = class_1887.method_8191((int)id);
                if (enc2 != null) {
                    encY2 += 8.0f;
                    arm = true;
                }
                ++index2;
            }
            if (encY2 > enchantmentY) {
                enchantmentY = encY2;
            }
        }
        if ((renderOffHand = player.method_6079().method_7972()).method_7942()) {
            float encY2 = 0.0f;
            class_2499 enchants2 = renderOffHand.method_7921();
            index = 0;
            while ((long)index < enchants2.stream().count()) {
                short id2 = enchants2.method_10602(index).method_10568("id");
                enc = class_1887.method_8191((int)id2);
                if (enc != null) {
                    encY2 += 8.0f;
                    arm = true;
                }
                ++index;
            }
            if (encY2 > enchantmentY) {
                enchantmentY = encY2;
            }
        }
        return (int)((float)(arm ? 0 : 20) + enchantmentY);
    }

    private String getDisplayTag(class_1657 player) {
        Object name = player.method_5477().getString();
        float health = EntityUtil.getHealth((class_1309)player);
        String color = health > 18.0f ? "\u00a7a" : (health > 16.0f ? "\u00a72" : (health > 12.0f ? "\u00a7e" : (health > 8.0f ? "\u00a76" : (health > 5.0f ? "\u00a7c" : "\u00a74"))));
        String pops = Nametags.pops.isEnabled() ? " " + TotemPopManager.getPlayerPops(player) : "";
        Object pingStr = "";
        if (ping.isEnabled()) {
            try {
                int responseTime = Objects.requireNonNull(mc.method_1562().method_2871(player.method_5667()).method_2959());
                pingStr = (String)pingStr + responseTime + "ms ";
            }
            catch (Exception responseTime) {
                // empty catch block
            }
        }
        Object idString = "";
        if (entityID.isEnabled()) {
            idString = (String)idString + "ID: " + player.method_5628() + " ";
        }
        Object gameModeStr = "";
        if (gamemode.isEnabled()) {
            Object object = player.method_7337() ? (String)gameModeStr + "[C] " : (gameModeStr = player.method_7325() || player.method_5767() ? (String)gameModeStr + "[I] " : (String)gameModeStr + "[S] ");
        }
        name = Math.floor(health) == (double)health ? (String)name + color + " " + (Serializable)(health > 0.0f ? Integer.valueOf((int)Math.floor(health)) : "dead") : (String)name + color + " " + (Serializable)(health > 0.0f ? Integer.valueOf((int)health) : "dead");
        return (String)pingStr + (String)idString + (String)gameModeStr + (String)name + "\u00a7c" + pops;
    }

    public static void drawHeartsBar(class_4587 matrices, class_1657 player, int y, String mode) {
        int health = (int)Math.ceil(player.method_6032());
        int maxhearts = (int)Math.ceil(player.method_6063() / 2.0f);
        int hearts = (int)Math.ceil(health / 2);
        int semiheart = health % 2;
        int goldhearts = (int)Math.ceil(player.method_6067() / 2.0f);
        int goldsemiheart = (int)Math.ceil(player.method_6067() % 2.0f);
        for (int deadheart = 0; deadheart < maxhearts; ++deadheart) {
            Nametags.drawHeart(matrices, HEALTH_TEXTURE, deadheart * 10 - maxhearts / 2 * 10, y, 9, mode, "dead");
        }
        for (int heart = 0; heart < hearts; ++heart) {
            Nametags.drawHeart(matrices, HEALTH_TEXTURE, heart * 10 - maxhearts / 2 * 10, y, 9, mode, "heart");
        }
        if (semiheart == 1) {
            Nametags.drawHeart(matrices, HEALTH_TEXTURE, hearts * 10 - maxhearts / 2 * 10, y, 9, mode, "semi");
        }
        for (int goldheart = 0; goldheart < goldhearts; ++goldheart) {
            Nametags.drawHeart(matrices, HEALTH_TEXTURE, goldheart * 10 - maxhearts / 2 * 10, y, 9, mode, "gold");
        }
        if (goldsemiheart == 1) {
            Nametags.drawHeart(matrices, HEALTH_TEXTURE, goldhearts * 10 - maxhearts / 2 * 10, y, 9, mode, "goldsemi");
        }
    }

    public static void drawHeart(class_4587 matrices, class_2960 heart, int x, int y, int scale, String mode, String type) {
        int heartX = 0;
        int heartY = mode.equals("default") ? 0 : 45;
        if (type.equals("dead")) {
            heartX = 16;
        }
        if (type.equals("heart")) {
            heartX = 52;
        }
        if (type.equals("semi")) {
            heartX = 61;
        }
        if (type.equals("gold")) {
            heartX = 160;
        }
        if (type.equals("goldsemi")) {
            heartX = 169;
        }
        RenderSystem.setShaderTexture((int)0, (class_2960)heart);
        class_332.method_25293((class_4587)matrices, (int)x, (int)y, (int)scale, (int)scale, (float)heartX, (float)heartY, (int)9, (int)9, (int)256, (int)256);
    }

    public static Nametags getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Nametags();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

