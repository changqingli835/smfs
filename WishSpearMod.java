package com.wishspear;

import com.wishspear.item.WishSpearItem;
import com.wishspear.mixin.ChatCommandHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class WishSpearMod implements ModInitializer {
    public static final String MOD_ID = "wish_spear";

    public static final Item WISH_SPEAR = new WishSpearItem(
            new FabricItemSettings().maxCount(1)
    );

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "wish_spear"), WISH_SPEAR);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(WISH_SPEAR);
        });
        // 注册聊天口令监听（我说 斩杀）
        ChatCommandHandler.register();
    }
}
