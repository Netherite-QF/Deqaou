package com.netheriteqf.deqaou.api;

import com.google.common.collect.Lists;
import com.google.gson.*;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import net.fabricmc.fabric.api.recipe.v1.ingredient.FabricIngredient;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FluidIngredient implements Predicate<FluidStack>, FabricIngredient {
    public static final FluidIngredient EMPTY = new FluidIngredient(Stream.empty());
    public final Entry[] entries;
    @Nullable
    private FluidStack[] matchingStacks;
    public FluidIngredient(Stream<? extends FluidIngredient.Entry> entries) {
        this.entries = entries.toArray(Entry[]::new);
    }

    public boolean isEmpty() {
        return this.entries.length == 0;
    }

    @Override
    public boolean test(@Nullable FluidStack fluidStack) {
        if (fluidStack == null) {
            return false;
        } else if (fluidStack.isEmpty()) {
            return fluidStack.isEmpty();
        } else {
            FluidStack[] var2 = this.getMatchingStacks();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                FluidStack fluidStack2 = var2[var4];
                if (fluidStack.getFluid().matchesType(fluidStack2.getFluid())) {
                    return true;
                }
            }
            return false;
        }
    }

    public JsonElement toJson() {
        if (this.entries.length == 1) {
            return this.entries[0].toJson();
        } else {
            JsonArray jsonArray = new JsonArray();
            FluidIngredient.Entry[] var2 = this.entries;
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                FluidIngredient.Entry entry = var2[var4];
                jsonArray.add(entry.toJson());
            }

            return jsonArray;
        }
    }

    public FluidStack[] getMatchingStacks() {
        if (this.matchingStacks == null) {
            this.matchingStacks = Arrays.stream(this.entries).flatMap((entry) -> entry.getStacks().stream()).distinct().toArray(FluidStack[]::new);
        }

        return this.matchingStacks;
    }
    private static FluidIngredient ofEntries(Stream<? extends FluidIngredient.Entry> entries) {
        FluidIngredient ingredient = new FluidIngredient(entries);
        return ingredient.isEmpty() ? EMPTY : ingredient;
    }

    public void write(PacketByteBuf buf) {
        buf.writeCollection(Arrays.asList(this.getMatchingStacks()), (buf1, matchingStacks) -> matchingStacks.writeToBuf(buf));
    }
    public static FluidIngredient fromPacket(PacketByteBuf buf) {
        return ofEntries(buf.readList(FluidStack::readFromBuf).stream().map(fluidStack -> new StackEntry(fluidStack.getFluid())));
    }
    public static FluidIngredient fromJson(@Nullable JsonElement json) {
        return fromJson(json, true);
    }
    public static FluidIngredient fromJson(@Nullable JsonElement json, boolean allowAir) {
        if (json != null && !json.isJsonNull()) {
            if (json.isJsonObject()) {
                return ofEntries(Stream.of(entryFromJson(json.getAsJsonObject())));
            } else if (json.isJsonArray()) {
                JsonArray jsonArray = json.getAsJsonArray();
                if (jsonArray.size() == 0 && !allowAir) {
                    throw new JsonSyntaxException("Item array cannot be empty, at least one item must be defined");
                } else {
                    return ofEntries(StreamSupport.stream(jsonArray.spliterator(), false).map((jsonElement) -> {
                        return entryFromJson(JsonHelper.asObject(jsonElement, "item"));
                    }));
                }
            } else {
                throw new JsonSyntaxException("Expected item to be object or array of objects");
            }
        } else {
            throw new JsonSyntaxException("Item cannot be null");
        }
    }
    private static FluidIngredient.Entry entryFromJson(JsonObject json) {
        if (json.has("fluid") && json.has("tag")) {
            throw new JsonParseException("An ingredient entry is either a tag or an fluid, not both");
        } else if (json.has("fluid")) {
            Fluid fluid = Registries.FLUID.get(new Identifier(JsonHelper.getString(json, "fluid")));
            return new StackEntry(fluid);
        } else if (json.has("tag")) {
            Identifier identifier = new Identifier(JsonHelper.getString(json, "tag"));
            TagKey<Fluid> tagKey = TagKey.of(RegistryKeys.FLUID, identifier);
            return new FluidIngredient.TagEntry(tagKey);
        } else {
            throw new JsonParseException("An ingredient entry needs either a tag or an fluid");
        }
    }

    private interface Entry {
        Collection<Fluid> getStacks();
        JsonObject toJson();
    }

    private record TagEntry(TagKey<Fluid> tag) implements Entry {

        public Collection<Fluid> getStacks() {
                List<Fluid> list = Lists.newArrayList();

                for (RegistryEntry<Fluid> fluidRegistryEntry : Registries.FLUID.iterateEntries(this.tag)) {
                    list.add(fluidRegistryEntry.value());
                }
                return list;
            }

            public JsonObject toJson() {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("tag", this.tag.id().toString());
                return jsonObject;
            }
        }

    static class StackEntry implements Entry {
        private final Fluid fluid;
        StackEntry(Fluid fluid) {
            this.fluid = fluid;
        }

        public Collection<Fluid> getStacks() {
            return Collections.singleton(this.fluid);
        }

        public JsonObject toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("fluid", Registries.FLUID.getId(this.fluid).toString());
            return jsonObject;
        }
    }
}
