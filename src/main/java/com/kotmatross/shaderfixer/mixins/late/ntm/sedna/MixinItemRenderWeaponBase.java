package com.kotmatross.shaderfixer.mixins.late.ntm.sedna;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.hbm.items.weapon.sedna.ItemGunBaseNT;
import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import com.kotmatross.shaderfixer.shrimp.NTMRenderGetters;
import com.kotmatross.shaderfixer.shrimp.Vibe;
import com.kotmatross.shaderfixer.shrimp.nonsense.FuckingCursed;

@FuckingCursed
@Mixin(value = ItemRenderWeaponBase.class, priority = 999)
public class MixinItemRenderWeaponBase implements Vibe, NTMRenderGetters {

    @Shadow(remap = false)
    protected float getBaseFOV(ItemStack stack) {
        return 70.0F;
    }

    @Shadow(remap = false)
    protected float getSwayMagnitude(ItemStack stack) {
        return ItemGunBaseNT.getIsAiming(stack) ? 0.1F : 0.5F;
    }

    @Shadow(remap = false)
    protected float getSwayPeriod(ItemStack stack) {
        return 0.75F;
    }

    @Shadow(remap = false)
    protected float getTurnMagnitude(ItemStack stack) {
        return 2.75F;
    }

    @Override
    public float shader_fixer$getGunsBaseFOV(ItemStack stack) {
        return getBaseFOV(stack);
    }

    @Override
    public float shader_fixer$getGunsSwayMagnitude(ItemStack stack) {
        return getSwayMagnitude(stack);
    }

    @Override
    public float shader_fixer$getGunsSwayPeriod(ItemStack stack) {
        return getSwayPeriod(stack);
    }

    @Override
    public float shader_fixer$getGunsTurnMagnitude(ItemStack stack) {
        return getTurnMagnitude(stack);
    }

}
