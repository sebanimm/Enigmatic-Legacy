package com.integral.enigmaticlegacy.items.generic;

import com.integral.enigmaticlegacy.EnigmaticLegacy;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

public class GenericBlockItem extends BlockItem {

	public GenericBlockItem(Block blockIn) {
		super(blockIn, GenericBlockItem.getDefaultProperties());
		this.setRegistryName(blockIn.getRegistryName());
	}

	public GenericBlockItem(Block blockIn, Properties props) {
		super(blockIn, props);
		this.setRegistryName(blockIn.getRegistryName());
	}

	public static Properties getDefaultProperties() {
		Properties props = new Item.Properties();

		props.tab(EnigmaticLegacy.enigmaticTab);
		props.stacksTo(64);
		props.rarity(Rarity.COMMON);

		return props;
	}

}
