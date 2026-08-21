package com.wishspear.item;

import com.wishspear.WishSpearDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class WishSpearItem extends SwordItem {
    private static final int COOLDOWN = 160;
    private static final float SKILL_RANGE = 7F;

    public WishSpearItem(Settings settings) {
        super(ToolMaterials.NETHERITE, 6, 1.7f, settings);
    }

    // 无限耐久（等效耐久-1）
    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        triggerSkill(user, hand);
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    // 公共技能方法，右键与聊天口令共用
    public void triggerSkill(PlayerEntity user, Hand hand) {
        World world = user.getWorld();
        ItemStack stack = user.getStackInHand(hand);
        if (world.isClient() || user.getItemCooldownManager().isCoolingDown(this)) {
            return;
        }

        Box hitBox = Box.of(user.getPos(), SKILL_RANGE, SKILL_RANGE, SKILL_RANGE);
        var damageTypeHolder = world.registryAccess()
                .registryOrThrow(RegistryKeys.DAMAGE_TYPE)
                .getHolderOrThrow(WishSpearDamageTypes.GHOSTLY);

        for (Entity entity : world.getEntitiesByClass(LivingEntity.class, hitBox, e -> e != user)) {
            LivingEntity target = (LivingEntity) entity;
            // 无上限：直接造成目标当前血量等额灵异伤害
            float targetHp = target.getHealth();
            target.damage(damageTypeHolder, user, targetHp);
        }
        user.getItemCooldownManager().set(this, COOLDOWN);
    }
}
