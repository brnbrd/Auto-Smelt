package net.onvoid.autosmelt.proxy;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.onvoid.autosmelt.AutoSmelt;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.onvoid.autosmelt.common.AutoSmeltEnchants;

@Mod.EventBusSubscriber(modid = AutoSmelt.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    public void start() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        AutoSmeltEnchants.create(modBus);
        registerListeners();
    }

    public void registerListeners(){
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(this::autoSmeltBreak);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void autoSmeltBreak(BlockEvent.BreakEvent e) {
        Player player = e.getPlayer();
        Level world = (Level) e.getWorld();
        BlockPos pos = e.getPos();
        InteractionHand hand = player.getUsedItemHand();
        ItemStack stack = player.getItemInHand(hand);
        if (EnchantmentHelper.getItemEnchantmentLevel(AutoSmeltEnchants.AUTO_SMELT.get(), stack) == 0){
            return;
        }
        List<ItemStack> finalDrops = new ArrayList<>();
        int recipesFound = 0;
        for (ItemStack dropStack : Block.getDrops(e.getState(), (ServerLevel) world, pos, null, player, stack)) {
            Optional<SmeltingRecipe> potential = world.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(dropStack), world);
            if (potential.isPresent()) {
                ItemStack result = potential.get().getResultItem().copy();
                result.setCount(result.getCount() * dropStack.getCount());
                if (!result.equals(dropStack)) {
                    finalDrops.add(result);
                    recipesFound += 1;
                } else {
                    finalDrops.add(result);
                }
            } else {
                finalDrops.add(dropStack.copy());
            }
        }
        if (recipesFound > 0){
            e.setCanceled(true);
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
            for (ItemStack finalDrop : finalDrops){
                world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), finalDrop));
            }
        }
    }
}
