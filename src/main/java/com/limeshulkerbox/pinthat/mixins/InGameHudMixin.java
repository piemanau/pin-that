package com.limeshulkerbox.pinthat.mixins;

import com.limeshulkerbox.pinthat.ModInitializer;
import com.limeshulkerbox.pinthat.commands.PinCommand;
import com.limeshulkerbox.pinthat.enums.WhatCorner;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

@Mixin(InGameHud.class)
class InGameHudMixin {
	@Shadow
	@Final
	private MinecraftClient client;

	@Shadow private int scaledWidth;

	@Shadow private int scaledHeight;

	short Colour;

	@Inject(method = "render", at = @At(value = "HEAD"))
	public void renderPinnedMessage(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
		if (!ModInitializer.config.pinEnabled) return;

		// 0 = top left, 1 = top right, 2 = bottom left, 3 = bottom right
		int WhatCorner = ModInitializer.config.whatCorner.value;

		int myX;
		int myY;
		int xOffset;
		int yOffset;
		int TextLength = this.client.textRenderer.getWidth(PinCommand.PinnedMessage);
		int TextHeight = this.client.textRenderer.fontHeight;

		xOffset = ModInitializer.config.xOffset;
		yOffset = ModInitializer.config.yOffset;
		Colour = 0xffffffff;
		if ((WhatCorner & 1) == 0) //left
		{
			myX = xOffset;
		}
		else //right
		{
			myX = scaledWidth - xOffset - TextLength;
		}
		if ((WhatCorner & 2) == 0) //top
		{
			myY = yOffset;
		}
		else //bottom
		{
			myY = scaledHeight - yOffset - TextHeight;
		}
		this.client.textRenderer.draw(matrices, new LiteralText(PinCommand.PinnedMessage), myX, myY, Colour);
	}
}