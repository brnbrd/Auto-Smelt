package net.onvoid.autosmelt.common;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.onvoid.autosmelt.AutoSmelt;

public class AutoSmeltLootModifiers {

    private static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, AutoSmelt.MODID);

    public static final RegistryObject<SmeltLootModifier.Serializer> SMELT = LOOT_MODIFIERS.register("smelt", SmeltLootModifier.Serializer::new);

    public static void create(IEventBus bus) {
        LOOT_MODIFIERS.register(bus);
    }

}
