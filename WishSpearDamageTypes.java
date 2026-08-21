package com.wishspear;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.ResourceKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.damagesource.DamageType;

public class WishSpearDamageTypes {
    public static final ResourceKey<DamageType> GHOSTLY = ResourceKey.create(
            RegistryKeys.DAMAGE_TYPE,
            new Identifier(WishSpearMod.MOD_ID, "ghostly")
    );
}
