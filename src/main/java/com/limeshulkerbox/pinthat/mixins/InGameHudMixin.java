package com.limeshulkerbox.pinthat.mixins;

import com.limeshulkerbox.pinthat.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
class InGameHudMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private int scaledWidth;

    @Shadow
    private int scaledHeight;

    @Inject(method = "render", at = @At("TAIL"))
    public void renderPinnedMessage(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (!ModInitializer.config.pinEnabled || client.options.debugEnabled) return;

        int textLength = this.client.textRenderer.getWidth(ModInitializer.config.pinnedMessage);
        int textHeight = this.client.textRenderer.fontHeight;

        int xOffset = ModInitializer.config.xOffset;
        int yOffset = ModInitializer.config.yOffset;
        int colour = ModInitializer.config.textColor;

        int myX = 0;
        int myY = 0;
        switch (ModInitializer.config.whatCorner) {
            case TOPL:
                myX = xOffset;
                myY = yOffset;
                break;
            case TOPR:
                myX = scaledWidth - xOffset - textLength;
                myY = yOffset;
                break;
            case BOTTOML:
                myX = xOffset;
                myY = scaledHeight - yOffset - textHeight;
                break;
            case BOTTOMR:
                myX = scaledWidth - xOffset - textLength;
                myY = scaledHeight - yOffset - textHeight;
                break;
        }

        this.client.textRenderer.draw(matrices, new LiteralText(ModInitializer.config.pinnedMessage), myX, myY, colour);
    }
}