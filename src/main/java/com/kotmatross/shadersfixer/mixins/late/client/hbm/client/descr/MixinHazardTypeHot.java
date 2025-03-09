package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.descr;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.hazard.modifier.HazardModifier;
import com.hbm.hazard.type.HazardTypeHot;
import com.hbm.util.I18nUtil;
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(value = HazardTypeHot.class, priority = 999)
public class MixinHazardTypeHot {

    @Unique
    boolean shaders_fixer$reacher;

    @Inject(method = "onUpdate", at = @At(value = "TAIL"), remap = false)
    public void onUpdate(EntityLivingBase target, float level, ItemStack stack, CallbackInfo ci,
        @Local(ordinal = 0) boolean reacher) {
        shaders_fixer$reacher = reacher; // вэн зэ эйр тёрнс ред
    }

    @Inject(method = "addHazardInformation", at = @At(value = "TAIL"), remap = false)
    public void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack,
        List<HazardModifier> modifiers, CallbackInfo ci) {
        String H = "" + (Math.floor(level * 1000) / 1000);

        if (level > 0) {
            if (shaders_fixer$reacher) {
                list.add(
                    EnumChatFormatting.STRIKETHROUGH + I18nUtil.resolveKey("trait.danger.level.hot")
                        + " "
                        + H
                        + " "
                        + I18nUtil.resolveKey("info.template__seconds")
                        + "  ");
            } else {
                list.add(
                    EnumChatFormatting.RED + I18nUtil.resolveKey("trait.danger.level.hot")
                        + " "
                        + H
                        + " "
                        + I18nUtil.resolveKey("info.template__seconds")
                        + "  ");
            }
        }

    }
}
