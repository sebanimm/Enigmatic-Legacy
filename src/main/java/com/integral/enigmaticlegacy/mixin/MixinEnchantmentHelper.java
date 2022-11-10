package com.integral.enigmaticlegacy.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.integral.enigmaticlegacy.registry.EnigmaticEnchantments;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper {

	@Inject(method = "hasBindingCurse", at = @At("RETURN"), cancellable = true, require = 1)
	private static void onBindingCurseCheck(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
		if (!info.getReturnValue()) {
			if (EnchantmentHelper.getItemEnchantmentLevel(EnigmaticEnchantments.eternalBindingCurse, stack) > 0) {
				info.setReturnValue(true);
			}
		}
	}

}
