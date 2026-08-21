package com.wishspear.mixin;

import com.wishspear.item.WishSpearItem;
import net.fabricmc.fabric.api.event.player.PlayerChatMessageEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

/**
 * 聊天口令监听：主手持有许愿长枪时，发送"我说 斩杀"触发技能。
 * 使用 Fabric API 的 PlayerChatMessageEvents 事件，无需真正的 Mixin 注入。
 * 在静态初始化块中注册事件监听器。
 */
public class ChatCommandHandler {
    public static void register() {
        PlayerChatMessageEvents.BEFORE_CHAT.register((player, message, signedMessage) -> {
            String text = message.getString().trim();
            ItemStack mainHand = player.getMainHandStack();

            // 必须主手持有许愿长枪
            if (!(mainHand.getItem() instanceof WishSpearItem)) {
                return true;
            }

            // 匹配"我说 "前缀
            if (text.startsWith("我说 ")) {
                String cmd = text.substring(3).trim();
                if ("斩杀".equals(cmd)) {
                    WishSpearItem item = (WishSpearItem) mainHand.getItem();
                    item.triggerSkill(player, Hand.MAIN_HAND);
                    // 如需隐藏聊天消息不发公屏，改为 return false;
                }
            }
            return true;
        });
    }
}
