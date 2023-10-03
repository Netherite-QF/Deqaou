package com.netheriteqf.deqaou.recipe.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.netheriteqf.deqaou.api.FluidIngredient;
import com.netheriteqf.deqaou.recipe.CrucibleRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.ArrayList;
import java.util.List;

public class CrucibleRecipeSerializer implements RecipeSerializer<CrucibleRecipe> {
    @Override
    public CrucibleRecipe read(Identifier id, JsonObject json) {
        Ingredient[] inputItem;
        ItemStack failedOutput = ItemStack.EMPTY;
        ItemStack successOutput = ItemStack.EMPTY;
        FluidIngredient[] fluid;
        int inventoryLevel = JsonHelper.getInt(json, "level");;
        int time = JsonHelper.getInt(json, "time");
        float chance = 1F;
        if (json.has("failed_output")) {
            failedOutput = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "failed_output"));
        }
        if (json.has("success_output")) {
            successOutput = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "success_output"));
        }
        if (json.has("chance")) {
            chance = JsonHelper.getFloat(json, "chance");
        }
        if (json.has("time")) {
            chance = JsonHelper.getInt(json, "time");
        }
        if (json.has("level")) {
            chance = JsonHelper.getInt(json, "level");
        }
        if (json.get("input") instanceof JsonArray array) {
            List<Ingredient> list = new ArrayList<>();
            for (JsonElement element : array) {
                var ingredient = Ingredient.fromJson(element, false);
                if (!ingredient.isEmpty())
                    list.add(ingredient);
            }
            inputItem = list.toArray(list.toArray(new Ingredient[0]));
        } else {
            throw new JsonParseException("No input for crucible");
        }
        if (json.get("fluid") instanceof JsonArray fluids) {
            List<FluidIngredient> list = new ArrayList<>();
            for (JsonElement singleFluid : fluids) {
                var ingredient = FluidIngredient.fromJson(singleFluid, false);
                if (!ingredient.isEmpty())
                    list.add(ingredient);
            }
            fluid = list.toArray(list.toArray(new FluidIngredient[0]));
        } else {
            throw new JsonParseException("No input for crucible");
        }

        return new CrucibleRecipe(inputItem, failedOutput, successOutput, fluid, inventoryLevel, time, chance, id);
    }

    @Override
    public CrucibleRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient[] inputItem = new Ingredient[buf.readInt()];
        for (int i = 0; i < inputItem.length; i++) {
            inputItem[i] = Ingredient.fromPacket(buf);
        }
        FluidIngredient[] fluid = new FluidIngredient[buf.readInt()];
        for (int i = 0; i < fluid.length; i++) {
            fluid[i] = FluidIngredient.fromPacket(buf);
        }
        ItemStack failedOutput = buf.readItemStack();
        ItemStack successOutput = buf.readItemStack();
        int inventoryLevel = buf.readInt();
        int time = buf.readInt();
        float chance = buf.readFloat();
        return new CrucibleRecipe(inputItem, failedOutput, successOutput, fluid, inventoryLevel, time, chance, id);
    }

    @Override
    public void write(PacketByteBuf buf, CrucibleRecipe recipe) {
        buf.writeInt(recipe.inputItem.length);
        for (Ingredient ingredient : recipe.inputItem) {
            ingredient.write(buf);
        }
        buf.writeInt(recipe.fluid.length);
        for (FluidIngredient fluidIngredient : recipe.fluid) {
            fluidIngredient.write(buf);
        }

        buf.writeItemStack(recipe.failedOutput);
        buf.writeItemStack(recipe.successOutput);

        buf.writeInt(recipe.time);
        buf.writeInt(recipe.inventoryLevel);
        buf.writeFloat(recipe.chance);
    }
}
