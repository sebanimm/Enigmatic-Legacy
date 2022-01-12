package com.integral.etherium.core;

import com.integral.enigmaticlegacy.api.materials.EnigmaticArmorMaterials;
import com.integral.enigmaticlegacy.api.materials.EnigmaticMaterials;
import com.integral.enigmaticlegacy.config.EtheriumConfigHandler;
import com.integral.enigmaticlegacy.objects.Perhaps;
import com.integral.enigmaticlegacy.packets.clients.PacketPlayerMotion;
import com.integral.etherium.EtheriumMod;
import com.integral.omniconfig.wrappers.Omniconfig;

import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.IItemTier;
import net.minecraft.world.item.ItemGroup;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fmllegacy.network.PacketDistributor;

public class EtheriumConfig implements IEtheriumConfig {
	private static IntValue shieldThreshold;
	private static IntValue shieldReduction;
	private static IntValue axeMiningVolume;
	private static IntValue pickaxeMiningRadius;
	private static IntValue pickaxeMiningDepth;
	private static IntValue scytheMiningVolume;
	private static IntValue shovelMiningRadius;
	private static IntValue shovelMiningDepth;
	private static IntValue swordCooldown;
	private static BooleanValue disableAOEShiftInhibition;
	
	public EtheriumConfig() {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		
		builder.comment("Options that allow to adjust some item effects and power.").push("General Options");
		
		shieldThreshold = builder
				.comment("The value of health to which player wearing full Etherium Armor set should be brough to activate the shield ability. Defined as percentage.")
				.defineInRange("shieldThreshold", 40, 0, 100);

		shieldReduction = builder
				.comment("Damage reduction of shield generated by Etherium Armor. Defined as percentage.")
				.defineInRange("shieldReduction", 50, 0, 100);
		
		axeMiningVolume = builder
				.comment("The volume Etherium Waraxe AOE mining. Set to -1 to disable the feature.")
				.defineInRange("axeMiningVolume", 3, -1, 128-1);
		
		pickaxeMiningRadius = builder
				.comment("The radius of Etherium Pickaxe AOE mining. Set to -1 to disable the feature.")
				.defineInRange("pickaxeMiningRadius", 3, -1, 128-1);
		
		pickaxeMiningDepth = builder
				.comment("The depth of Etherium Pickaxe AOE mining.")
				.defineInRange("pickaxeMiningDepth", 1, -1, 128-1);
		
		scytheMiningVolume = builder
				.comment("The volume Etherium Scythe AOE mining. Set to -1 to disable the feature.")
				.defineInRange("scytheMiningVolume", 3, -1, 128-1);
		
		shovelMiningRadius = builder
				.comment("The radius of Etherium Shovel AOE mining. Set to -1 to disable the feature.")
				.defineInRange("shovelMiningRadius", 3, -1, 128-1);
		
		shovelMiningDepth = builder
				.comment("The depth of Etherium Shovel AOE mining.")
				.defineInRange("shovelMiningDepth", 1, -1, 128-1);
		
		swordCooldown = builder
				.comment("Cooldown of Etherium Broadsword ability. Measured in ticks.")
				.defineInRange("swordCooldown", 40, -1, 128-1);
		
		disableAOEShiftInhibition = builder
				.comment("If true, AOE effects of etherium tools will not be inhibited while crouching.")
				.define("disableAOEShiftInhibition", false);
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, builder.build(), "Etherium.toml");
	}
	
	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.of(EtheriumMod.etheriumIngot);
	}

	@Override
	public ItemGroup getCreativeTab() {
		return null;
	}

	@Override
	public String getOwnerMod() {
		return EtheriumMod.MODID;
	}

	@Override
	public ArmorMaterial getArmorMaterial() {
		return EnigmaticArmorMaterials.ETHERIUM;
	}

	@Override
	public IItemTier getToolMaterial() {
		return EnigmaticMaterials.ETHERIUM;
	}

	@Override
	public Perhaps getShieldThreshold() {
		return new Perhaps(shieldThreshold.get());
	}

	@Override
	public Perhaps getShieldReduction() {
		return new Perhaps(shieldReduction.get());
	}

	@Override
	public boolean disableAOEShiftInhibition() {
		return disableAOEShiftInhibition.get();
	}

	@Override
	public SoundEvent getAOESoundOn() {
		return EtheriumMod.HHON;
	}

	@Override
	public SoundEvent getAOESoundOff() {
		return EtheriumMod.HHOFF;
	}

	@Override
	public SoundEvent getShieldTriggerSound() {
		return EtheriumMod.SHIELD_TRIGGER;
	}

	@Override
	public int getAxeMiningVolume() {
		return axeMiningVolume.get();
	}

	@Override
	public int getScytheMiningVolume() {
		return scytheMiningVolume.get();
	}

	@Override
	public int getPickaxeMiningRadius() {
		return pickaxeMiningRadius.get();
	}

	@Override
	public int getPickaxeMiningDepth() {
		return pickaxeMiningDepth.get();
	}

	@Override
	public int getShovelMiningRadius() {
		return shovelMiningRadius.get();
	}

	@Override
	public int getShovelMiningDepth() {
		return shovelMiningDepth.get();
	}

	@Override
	public int getSwordCooldown() {
		return swordCooldown.get();
	}

	@Override
	public void knockBack(Player entityIn, float strength, double xRatio, double zRatio) {
		entityIn.hasImpulse = true;
		Vec3 vec3d = new Vec3(0D, 0D, 0D);
		Vec3 vec3d1 = (new Vec3(xRatio, 0.0D, zRatio)).normalize().scale(strength);

		EtheriumMod.packetInstance.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) entityIn), new PacketPlayerMotion(vec3d.x / 2.0D - vec3d1.x, entityIn.isOnGround() ? Math.min(0.4D, vec3d.y / 2.0D + strength) : vec3d.y, vec3d.z / 2.0D - vec3d1.z));
		entityIn.setDeltaMovement(vec3d.x / 2.0D - vec3d1.x, entityIn.isOnGround() ? Math.min(0.4D, vec3d.y / 2.0D + strength) : vec3d.y, vec3d.z / 2.0D - vec3d1.z);
	}

	@Override
	public boolean isStandalone() {
		return true;
	}
	
}