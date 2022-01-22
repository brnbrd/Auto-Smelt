package net.onvoid.autosmelt.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.onvoid.autosmelt.AutoSmelt;
import net.onvoid.autosmelt.common.AutoSmeltEnchants;
import net.onvoid.autosmelt.common.AutoSmeltLootModifiers;

@Mod.EventBusSubscriber(modid = AutoSmelt.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    public void start() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        AutoSmeltEnchants.create(modBus);
        AutoSmeltLootModifiers.create(modBus);
    }
}
