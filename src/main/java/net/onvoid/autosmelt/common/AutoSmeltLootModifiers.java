package net.onvoid.autosmelt.common;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.onvoid.autosmelt.AutoSmelt;

public class AutoSmeltLootModifiers {

    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AutoSmelt.MODID);

    public static final RegistryObject<Codec<SmeltLootModifier>> SMELT = GLM.register("smelt", () -> SmeltLootModifier.CODEC);

    public static void create(IEventBus bus) {
        GLM.register(bus);
    }

}
