package com.kotmatross.shaderfixer.mixins.late.ntm.sedna;


import com.hbm.render.item.weapon.sedna.ItemRenderWeaponBase;
import com.kotmatross.shaderfixer.shrimp.Vibe;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ItemRenderWeaponBase.class, priority = 999)
public class MixinItemRenderWeaponBase implements Vibe { }
