# 许愿长枪 (Wish Spear) - Minecraft 1.20.1 Fabric 模组

## 模组特性
- **武器名称**：许愿长枪
- **3D手持模型**：适配第一/第三人称、物品栏、地面掉落
- **无限耐久**：不掉耐久、不显示耐久条
- **右键技能**：7米范围灵异斩杀，造成等于目标当前血量的伤害（无上限，可秒BOSS）
- **聊天口令**：主手持有长枪时，聊天输入 `我说 斩杀` 触发技能（与右键共用8秒冷却）
- **自定义伤害类型**：ghostly 灵异伤害，专属死亡提示
- **左键普攻**：下界合金剑属性

## 使用方法
1. 安装 JDK 17
2. 用 IntelliJ IDEA 打开本项目（或命令行进入项目目录）
3. 首次运行生成 wrapper：`gradle wrapper --gradle-version 8.4`（如果系统已装gradle）
   - 或者直接用 IDEA 打开，IDE 会自动处理
4. 编译打包：`./gradlew build`（Windows 用 `gradlew.bat build`）
5. 成品 jar 在 `build/libs/wish_spear-1.0.0.jar`
6. 放入 Minecraft 1.20.1 的 mods 文件夹，**必须同时安装 Fabric API**

## 游戏内获取
- 创造模式物品栏 → 战斗物品组 → 许愿长枪
- 或使用指令：`/give @p wish_spear:wish_spear`

## 口令说明
必须主手持有许愿长枪，在聊天框发送：
- `我说 斩杀` → 触发范围灵异斩杀技能（8秒冷却）

## 贴图替换
当前贴图位于：
`src/main/resources/assets/wish_spear/textures/item/wish_spear.png`
如需替换，直接覆盖该文件即可（建议16x16或32x32像素PNG）。

## 目录结构
```
wish_spear/
├── build.gradle
├── settings.gradle
├── gradle.properties
├── LICENSE
├── README.md
└── src/main/
    ├── java/com/wishspear/
    │   ├── WishSpearMod.java          # 主类
    │   ├── WishSpearDamageTypes.java  # 灵异伤害类型
    │   ├── item/
    │   │   └── WishSpearItem.java     # 长枪逻辑（斩杀/无限耐久）
    │   └── mixin/
    │       └── ChatCommandHandler.java # 聊天口令监听
    └── resources/
        ├── fabric.mod.json
        ├── assets/wish_spear/
        │   ├── lang/zh_cn.json
        │   ├── lang/en_us.json
        │   ├── models/item/wish_spear.json  # 3D模型
        │   └── textures/item/wish_spear.png # 贴图
        └── data/wish_spear/
            └── damage_type/ghostly.json     # 伤害类型定义
```
