package com.limeshulkerbox.pinthat.mixins;

import com.limeshulkerbox.pinthat.ModInitializer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
class GuiMixin {

    @Shadow @Final private Minecraft minecraft;

    @Shadow private int screenWidth;

    @Shadow private int screenHeight;

    @Inject(method = "render", at = @At("TAIL"))
    public void renderPinnedMessage(PoseStack matrices, float tickDelta, CallbackInfo ci) {
        if (!ModInitializer.config.pinEnabled || minecraft.options.renderDebug) return;

        int textLength = this.minecraft.font.width(ModInitializer.config.pinnedMessage);
        int textHeight = this.minecraft.font.lineHeight;

        int xOffset = ModInitializer.config.xOffset;
        int yOffset = ModInitializer.config.yOffset;
        int colour = ModInitializer.config.textColor;

        int myX = 0;
        int myY = 0;
        switch (ModInitializer.config.whatCorner) {
            case TOPL -> {
                myX = xOffset;
                myY = yOffset;
            }
            case TOPR -> {
                myX = screenWidth - xOffset - textLength;
                myY = yOffset;
            }
            case BOTTOML -> {
                myX = xOffset;
                myY = screenHeight - yOffset - textHeight;
            }
            case BOTTOMR -> {
                myX = screenWidth - xOffset - textLength;
                myY = screenHeight - yOffset - textHeight;
            }
        }

        this.minecraft.font.draw(matrices, Component.literal(ModInitializer.config.pinnedMessage), myX, myY, colour);
    }
}
