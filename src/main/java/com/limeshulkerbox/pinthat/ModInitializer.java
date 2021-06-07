package com.limeshulkerbox.pinthat;

import com.limeshulkerbox.pinthat.commands.PinClearCommand;
import com.limeshulkerbox.pinthat.commands.PinCommand;
import com.limeshulkerbox.pinthat.config.ConfigStructure;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.MessageArgumentType;
import net.minecraft.util.registry.DynamicRegistryManager;

public class ModInitializer implements net.fabricmc.api.ModInitializer {

	public static ConfigStructure config;

	@Override
	public void onInitialize() {
		AutoConfig.register(ConfigStructure.class, JanksonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ConfigStructure.class).getConfig();

		ClientCommandManager.DISPATCHER.register(
				ClientCommandManager.literal
						(
								"pin"
						)
						.then(ClientCommandManager.literal
								(
								"pin"
						)
								.then(ClientCommandManager.argument
								(
										"message", MessageArgumentType.message()
								)
								.executes(new PinCommand())
								)
						)
				.then(ClientCommandManager.literal("clear").executes(new PinClearCommand()))
		);
	}{}
}
