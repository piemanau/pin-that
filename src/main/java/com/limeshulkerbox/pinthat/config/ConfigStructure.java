package com.limeshulkerbox.pinthat.config;

import com.limeshulkerbox.pinthat.enums.WhatCorner;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "pinthat")
@Config.Gui.Background("minecraft:textures/block/oak_planks.png")
public
class ConfigStructure implements ConfigData {
    public ConfigStructure() {
    }
    public boolean pinEnabled = true;

        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public WhatCorner whatCorner = WhatCorner.TOPR;

        public int xOffset = 2;
        public int yOffset = 2;
    }