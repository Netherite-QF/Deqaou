package com.netheriteqf.deqaou.registry;

import com.netheriteqf.deqaou.Deqaou;
import com.netheriteqf.deqaou.block.CrucibleBlock;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;

public class DQOBlocks implements BlockRegistryContainer {
    public static final Block CLEAR_GLASS = new Block(FabricBlockSettings.copyOf(Blocks.GLASS));
    public static final Block EVIL_ORE = new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE));
    public static final Block GHOST_ORE = new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE));
    public static final Block OBSIDIAN_CRUCIBLE = new CrucibleBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON).instrument(Instrument.BASEDRUM).requiresTool().strength(50.0F, 1200.0F));
    @Override
    public BlockItem createBlockItem(Block block, String identifier) {
        return new BlockItem(block, new OwoItemSettings().group(Deqaou.DEQAOU_TAB));
    }
}
