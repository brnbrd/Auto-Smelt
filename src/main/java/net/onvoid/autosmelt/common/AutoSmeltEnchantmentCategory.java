package net.onvoid.autosmelt.common;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AutoSmeltEnchantmentCategory {
    public static final EnchantmentCategory PICKAXE_SHOVEL = EnchantmentCategory.create("pickaxe_shovel", item ->
            item instanceof PickaxeItem || item instanceof ShovelItem);
}
