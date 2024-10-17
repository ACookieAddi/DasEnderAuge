package de.luwallia;

import de.luwallia.cookie.item.custom.JumpBackItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries; // For accessing pre-defined registry types
import net.minecraft.registry.Registry; // For registering items
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DEA implements ModInitializer {
	public static final String MOD_ID = "dea";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Item JUMP_BACK_ITEM = new JumpBackItem(new Item.Settings().maxCount(1));

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "jump_back_item"), JUMP_BACK_ITEM);
	}
}