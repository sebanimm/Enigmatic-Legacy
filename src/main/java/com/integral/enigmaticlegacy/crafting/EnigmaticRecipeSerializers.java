package com.integral.enigmaticlegacy.crafting;

import com.integral.enigmaticlegacy.EnigmaticLegacy;
import com.integral.enigmaticlegacy.helpers.EnabledCondition;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnigmaticRecipeSerializers {
	public static final IRecipeSerializer<MendingMixtureRepairRecipe> CRAFTING_MENDING_MIXTURE_REPAIR = new MendingMixtureRepairRecipe.Serializer();
	public static final IRecipeSerializer<ShapelessNoReturnRecipe> SHAPELESS_NO_RETURN = new ShapelessNoReturnRecipe.Serializer();
	public static final IRecipeSerializer<OblivionStoneCombineRecipe> OBLIVION_STONE_COMBINE = new OblivionStoneCombineRecipe.Serializer();
	public static final IRecipeSerializer<EnchantmentTransposingRecipe> ENCHANTMENT_TRANSPOSING = new EnchantmentTransposingRecipe.Serializer();
	public static final IRecipeSerializer<BindToPlayerRecipe> BIND_TO_PLAYER = new BindToPlayerRecipe.Serializer();

	@SubscribeEvent
	public void onRegisterSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
		EnigmaticLegacy.enigmaticLogger.info("Initializing recipe serializers registration...");
		CraftingHelper.register(EnabledCondition.Serializer.INSTANCE);
		
		event.getRegistry().register(CRAFTING_MENDING_MIXTURE_REPAIR.setRegistryName(new ResourceLocation(EnigmaticLegacy.MODID, "mending_mixture_repair")));
		event.getRegistry().register(SHAPELESS_NO_RETURN.setRegistryName(new ResourceLocation(EnigmaticLegacy.MODID, "shapeless_no_return_craft")));
		event.getRegistry().register(OBLIVION_STONE_COMBINE.setRegistryName(new ResourceLocation(EnigmaticLegacy.MODID, "oblivion_stone_combine")));
		event.getRegistry().register(ENCHANTMENT_TRANSPOSING.setRegistryName(new ResourceLocation(EnigmaticLegacy.MODID, "enchantment_transposing")));
		event.getRegistry().register(BIND_TO_PLAYER.setRegistryName(new ResourceLocation(EnigmaticLegacy.MODID, "bind_to_player")));
		EnigmaticLegacy.enigmaticLogger.info("Recipe serializers registered successfully.");
	}
}