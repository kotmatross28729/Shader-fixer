package com.kotmatross.shaderfixer.mixins.late.HBM.descr;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.hazard.modifier.HazardModifier;
import com.hbm.hazard.type.HazardTypeCoal;

@Mixin(value = HazardTypeCoal.class, priority = 999)
public class MixinHazardTypeCoal {

    @Inject(method = "addHazardInformation", at = @At(value = "TAIL"), remap = false)
    public void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack,
        List<HazardModifier> modifiers, CallbackInfo ci) {
        list.add(EnumChatFormatting.GRAY + I18n.format("trait.danger.level.coal") + (level / 20F));
    }
}
