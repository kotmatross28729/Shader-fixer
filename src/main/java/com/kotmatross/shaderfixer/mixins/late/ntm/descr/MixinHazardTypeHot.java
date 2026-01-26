package com.kotmatross.shaderfixer.mixins.late.ntm.descr;

import java.util.List;

import net.minecraft.client.resources.I18n;
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
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(value = HazardTypeHot.class, priority = 999)
public class MixinHazardTypeHot {

    @Unique
    boolean shader_fixer$reacher;

    @Inject(method = "onUpdate", at = @At(value = "TAIL"), remap = false)
    public void onUpdate(CallbackInfo ci, @Local(ordinal = 0) boolean reacher) {
        shader_fixer$reacher = reacher; // вэн зэ эйр тёрнс ред
    }

    @Inject(method = "addHazardInformation", at = @At(value = "TAIL"), remap = false)
    public void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack,
        List<HazardModifier> modifiers, CallbackInfo ci) {

        if (level > 0) {
            if (shader_fixer$reacher) {
                list.add(
                    EnumChatFormatting.STRIKETHROUGH + I18n.format("trait.danger.level.hot")
                        + level
                        + I18n.format("info.template__seconds"));
            } else {
                list.add(
                    EnumChatFormatting.RED + I18n.format("trait.danger.level.hot")
                        + level
                        + I18n.format("info.template__seconds"));
            }
        }
    }

}
