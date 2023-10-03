package com.netheriteqf.deqaou.registry;

import com.netheriteqf.deqaou.recipe.CrucibleRecipe;
import com.netheriteqf.deqaou.recipe.serializer.CrucibleRecipeSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.netheriteqf.deqaou.Deqaou.MODID;

public class DQORecipeTypes implements ModInitializer {
    public static final RecipeType<CrucibleRecipe> CRUCIBLE_RECIPE;
    public static final RecipeSerializer<CrucibleRecipe> CRUCIBLE_RECIPE_SERIALIZER;

    static {
        CRUCIBLE_RECIPE = Registry.register(Registries.RECIPE_TYPE, new Identifier(MODID, "crucible"), new RecipeType<CrucibleRecipe>() {
            @Override
            public String toString() {return "crucible";}
        });
        CRUCIBLE_RECIPE_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MODID, "crucible"), new CrucibleRecipeSerializer());
    }

    @Override
    public void onInitialize() {

    }
}
