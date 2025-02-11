package net.id.aether.tag;

import net.id.aether.Aether;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class AetherFluidTags {
    public static final TagKey<Fluid> SWET_TRANSFORMERS_BLUE = register("swet_transformers/blue");
    public static final TagKey<Fluid> SWET_TRANSFORMERS_GOLDEN = register("swet_transformers/golden");
    public static final TagKey<Fluid> SWET_TRANSFORMERS_PURPLE = register("swet_transformers/purple");
    public static final TagKey<Fluid> SWET_TRANSFORMERS_VERMILION = register("swet_transformers/vermilion");
    public static final TagKey<Fluid> SPRING_WATER = register("spring_water");

    private static TagKey<Fluid> register(String id) {
        return TagKey.of(Registry.FLUID_KEY, Aether.locate(id));
    }
}
