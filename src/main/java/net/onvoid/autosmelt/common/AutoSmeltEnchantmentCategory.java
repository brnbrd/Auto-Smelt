package net.onvoid.autosmelt.common;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AutoSmeltEnchantmentCategory {
    public static final EnchantmentCategory SMELTERS = EnchantmentCategory.create("smelters", item ->
            item instanceof PickaxeItem || item instanceof ShovelItem || item instanceof AxeItem || item instanceof SwordItem);
}
