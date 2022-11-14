package com.limeshulkerbox.pinthat.commands;

import com.limeshulkerbox.pinthat.ModInitializer;
import com.limeshulkerbox.pinthat.config.ConfigStructure;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.network.chat.Component;

public final class PinClearCommand implements Command<FabricClientCommandSource> {
    @Override
    public int run(CommandContext<FabricClientCommandSource> context) {
        ModInitializer.config.pinnedMessage = "";
        context.getSource().sendFeedback(Component.literal("Pinned message cleared"));
        AutoConfig.getConfigHolder(ConfigStructure.class).save();
        return 0;
    }
}
