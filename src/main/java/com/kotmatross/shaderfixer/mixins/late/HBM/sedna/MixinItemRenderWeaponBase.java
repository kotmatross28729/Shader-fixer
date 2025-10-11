package com.kotmatross.shaderfixer.mixins.late.HBM.sedna;

import java.util.List;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.items.weapon.sedna.ItemGunBaseNT;
import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import com.kotmatross.shaderfixer.shrimp.NTMRenderGetters;
import com.kotmatross.shaderfixer.shrimp.Vibe;
import com.kotmatross.shaderfixer.shrimp.nonsense.FuckingCursed;
import com.kotmatross.shaderfixer.utils.Utils;

@FuckingCursed
@Mixin(value = ItemRenderWeaponBase.class, priority = 999)
public class MixinItemRenderWeaponBase implements Vibe, NTMRenderGetters {

    @Inject(method = "renderSmokeNodes", at = @At(value = "HEAD"), remap = false)
    private static void renderSmokeNodes(List<ItemGunBaseNT.SmokeNode> nodes, double scale, CallbackInfo ci) {
        Utils.fix();
    }

    @Shadow
    protected float getBaseFOV(ItemStack stack) {
        return 70.0F;
    }

    @Shadow
    protected float getSwayMagnitude(ItemStack stack) {
        return ItemGunBaseNT.getIsAiming(stack) ? 0.1F : 0.5F;
    }

    @Shadow
    protected float getSwayPeriod(ItemStack stack) {
        return 0.75F;
    }

    @Shadow
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
