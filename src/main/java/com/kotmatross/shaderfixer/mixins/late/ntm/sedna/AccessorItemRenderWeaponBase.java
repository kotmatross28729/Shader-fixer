package com.kotmatross.shaderfixer.mixins.late.ntm.sedna;

import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = ItemRenderWeaponBase.class, priority = 999, remap = false)
public interface AccessorItemRenderWeaponBase {
	@Invoker
	float invokeGetBaseFOV(ItemStack stack);
	@Invoker
	float invokeGetSwayMagnitude(ItemStack stack);
	@Invoker
	float invokeGetSwayPeriod(ItemStack stack);
	@Invoker
	float invokeGetTurnMagnitude(ItemStack stack);
}
