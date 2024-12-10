package com.kotmatross.shadersfixer.mixins.late.client.hbm.client.descr;

import com.hbm.hazard.modifier.HazardModifier;
import com.hbm.hazard.type.HazardTypeCoal;
import com.hbm.util.I18nUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = HazardTypeCoal.class, priority = 999)
public class MixinHazardTypeCoal {

    @Inject(method = "addHazardInformation",
        at = @At(value = "TAIL"), remap = false)
    public void addHazardInformation(EntityPlayer player, List list, float level, ItemStack stack, List<HazardModifier> modifiers, CallbackInfo ci) {
        String H = "" + (Math.floor(level * 1000) / 1000);
        list.add(EnumChatFormatting.GRAY + I18nUtil.resolveKey("trait.danger.level.coal") + " " + H + "  ");
    }
}
