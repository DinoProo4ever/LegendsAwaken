package com.dinoproo.legendsawaken.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import vazkii.patchouli.common.item.PatchouliDataComponents;
import vazkii.patchouli.common.item.PatchouliItems;

import java.util.ArrayList;
import java.util.List;

import static com.dinoproo.legendsawaken.recipe.ModRecipes.*;

public record ShapelessBookRecipe(List<Ingredient> ingredients, String bookId) implements CraftingRecipe {

    @Override
    public boolean matches(CraftingRecipeInput craftingRecipeInput, World world) {
        if (craftingRecipeInput.getStackCount() != this.ingredients.size()) {
            return false;
        } else {
            return craftingRecipeInput.getSize() == 1 && this.ingredients.size() == 1
                    ? this.ingredients.getFirst().test(craftingRecipeInput.getStackInSlot(0))
                    : craftingRecipeInput.getRecipeMatcher().match(this, null);
        }
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack book = new ItemStack(PatchouliItems.BOOK);
        book.set(PatchouliDataComponents.BOOK, Identifier.of(bookId));
        return book;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= ingredients.size();
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.addAll(ingredients);
        return list;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        ItemStack book = new ItemStack(PatchouliItems.BOOK);
        book.set(PatchouliDataComponents.BOOK, Identifier.of(bookId));
        return book;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SHAPELESS_BOOK_SERIALIZER;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.MISC;
    }

    public static class Serializer implements RecipeSerializer<ShapelessBookRecipe> {

            public static final MapCodec<ShapelessBookRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").forGetter(ShapelessBookRecipe::ingredients),
                    Codec.STRING.fieldOf("book").forGetter(ShapelessBookRecipe::bookId)
            ).apply(inst, ShapelessBookRecipe::new));

        public static final PacketCodec<RegistryByteBuf, ShapelessBookRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        PacketCodecs.collection(ArrayList::new, Ingredient.PACKET_CODEC), ShapelessBookRecipe::ingredients,
                        PacketCodecs.STRING, ShapelessBookRecipe::bookId,
                        ShapelessBookRecipe::new
                );

        @Override
        public MapCodec<ShapelessBookRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ShapelessBookRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
