package com.limeshulkerbox.pinthat.commands;

import com.limeshulkerbox.pinthat.ModInitializer;
import com.limeshulkerbox.pinthat.config.ConfigStructure;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public class PinColorCommand implements Command<FabricClientCommandSource> {
    @Override
    public int run(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException {
        ChatFormatting formatting = context.getArgument("color", ChatFormatting.class);
        ModInitializer.config.textColor = formatting.getColor();
        context.getSource().sendFeedback(
                Component.literal("Pinned message color set to " + formatting.getName())
        );
        AutoConfig.getConfigHolder(ConfigStructure.class).save();
        return 0;
    }
}
