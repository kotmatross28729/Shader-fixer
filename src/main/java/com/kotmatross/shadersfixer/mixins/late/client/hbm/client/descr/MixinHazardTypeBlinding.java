package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.descr;

import com.hbm.hazard.modifier.HazardModifier;
import com.hbm.hazard.type.HazardTypeBlinding;
import com.hbm.util.I18nUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = HazardTypeBlinding.class, priority = 999)
public class MixinHazardTypeBlinding {

    @Inject(method = "addHazardInformation",
        at = @At(value = "TAIL"), remap = false)
    public void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack, List<HazardModifier> modifiers, CallbackInfo ci) {
        double H = (Math.floor(level * 1000) / 1000);
        list.add(EnumChatFormatting.BLUE + I18nUtil.resolveKey("trait.danger.level.blinding") + " " + (H / 20) + " " + I18nUtil.resolveKey("info.template__seconds") + "  ");
    }
}