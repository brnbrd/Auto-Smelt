package net.onvoid.autosmelt.common;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.onvoid.autosmelt.AutoSmelt;

public class AutoSmeltEnchants {

    private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, AutoSmelt.MODID);

    public static RegistryObject<Enchantment> AUTO_SMELT = ENCHANTMENTS.register("auto-smelt", () ->
        new AutoSmeltEnchantment()
    );

    public static void create(IEventBus bus) {
        ENCHANTMENTS.register(bus);
    }
}
