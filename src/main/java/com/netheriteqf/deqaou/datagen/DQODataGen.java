package com.netheriteqf.deqaou.datagen;

import com.netheriteqf.deqaou.registry.DQOItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.registry.RegistryBuilder;
import org.jetbrains.annotations.Nullable;

public class DQODataGen implements DataGeneratorEntrypoint {
    public class DQOModelGen extends FabricModelProvider {
        private DQOModelGen(FabricDataOutput generator) {
            super(generator);
        }
        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.register(DQOItems.EVIL_GOGGLES, Models.GENERATED);
        }

        public static void post() {

        }
    }
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        DQOModelGen.post();
    }
}
