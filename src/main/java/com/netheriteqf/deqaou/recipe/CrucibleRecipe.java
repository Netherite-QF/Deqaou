package com.netheriteqf.deqaou.recipe;

import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.netheriteqf.deqaou.api.FluidIngredient;
import com.netheriteqf.deqaou.block.entity.CrucibleBlockEntity;
import com.netheriteqf.deqaou.registry.DQORecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public class CrucibleRecipe implements Recipe<CrucibleBlockEntity> {
    public final Ingredient[] inputItem;
    public final ItemStack failedOutput;
    public final ItemStack successOutput;
    public final FluidIngredient[] fluid;
    public final int inventoryLevel;
    public final int time;
    public final float chance;
    public final Identifier id;

    public CrucibleRecipe(Ingredient[] inputItem, ItemStack failedOutput, ItemStack successOutput, FluidIngredient[] fluid, int inventoryLevel, int time, float chance, Identifier id) {
        this.inputItem = inputItem;
        this.failedOutput = failedOutput;
        this.successOutput = successOutput;
        this.fluid = fluid;
        this.inventoryLevel = inventoryLevel;
        this.time = time;
        this.chance = chance;
        this.id = id;
    }

    @Override
    public boolean matches(CrucibleBlockEntity crucibleBlockEntity, World world) {
        return false;
    }

    @Override
    public ItemStack craft(CrucibleBlockEntity crucibleBlockEntity, DynamicRegistryManager registryManager) {
        return this.successOutput.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return this.successOutput;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return DQORecipeTypes.CRUCIBLE_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return DQORecipeTypes.CRUCIBLE_RECIPE;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.addAll(List.of(this.inputItem));
        return list;
    }

    public FluidIngredient[] getFluid() {
        return fluid;
    }

    public float getChance() {
        return chance;
    }

    public int getTime() {
        return time;
    }

    public ItemStack getSuccessOutput() {
        return successOutput;
    }

    public ItemStack getFailedOutput() {
        return failedOutput;
    }

    public int getInventoryLevel() {
        return inventoryLevel;
    }

    @Override
    public ItemStack createIcon() {
        return Items.CAULDRON.getDefaultStack();
    }
}
