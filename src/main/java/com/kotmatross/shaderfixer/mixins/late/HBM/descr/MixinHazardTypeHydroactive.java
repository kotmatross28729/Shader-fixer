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
import com.hbm.hazard.type.HazardTypeHydroactive;

@Mixin(value = HazardTypeHydroactive.class, priority = 999)
public class MixinHazardTypeHydroactive {

    @Inject(method = "addHazardInformation", at = @At(value = "TAIL"), remap = false)
    public void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack,
        List<HazardModifier> modifiers, CallbackInfo ci) {
        String H = "" + ((Math.floor(level * 1000) / 1000));
        list.add(EnumChatFormatting.AQUA + I18n.format("trait.danger.level.hydro") + " " + H + "  ");
    }
}
