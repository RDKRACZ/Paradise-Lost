package net.id.aether.fluids;

import net.id.aether.client.rendering.block.FluidRenderSetup;
import net.id.aether.registry.AetherRegistryQueues;
import net.id.aether.util.RenderUtils;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;

import static net.id.aether.Aether.locate;
import static net.id.incubus_core.util.RegistryQueue.Action;
import static net.id.incubus_core.util.RegistryQueue.onClient;

public class AetherFluids {
    private static final Action<Fluid> translucent = onClient((id, fluid) -> RenderUtils.transparentRenderLayer(fluid));

    private static Action<Fluid> renderSetup(Fluid flowing, int color){ return onClient((id, fluid) -> FluidRenderSetup.setupFluidRendering(fluid, flowing, id, (view, pos, state)->color));}
    private static Action<Fluid> renderSetup(Fluid flowing, FluidRenderSetup.FluidColorProvider provider){ return onClient((id, fluid) -> FluidRenderSetup.setupFluidRendering(fluid, flowing, id, provider));}
    private static Action<Fluid> onlyStillRenderSetup(int color) {return renderSetup(null, (view, pos, state)->color);}
    private static Action<Fluid> onlyStillRenderSetup(FluidRenderSetup.FluidColorProvider provider) {return renderSetup(null, provider);}

    public static final FlowableFluid DENSE_AERCLOUD = add("dense_aercloud", new DenseAercloudFluid(), translucent, onlyStillRenderSetup(0xFFFFFF));
    public static final FlowableFluid FLOWING_SPRING_WATER = add("flowing_spring_water", new SpringWaterFluid.Flowing(), translucent);
    public static final FlowableFluid SPRING_WATER = add("spring_water", new SpringWaterFluid.Still(), translucent, renderSetup(FLOWING_SPRING_WATER, (view, pos, state)->view == null ? 0xFF8AC5D5 : BiomeColors.getWaterColor(view, pos)));

    @SafeVarargs
    private static <V extends Fluid> V add(String id, V fluid, Action<? super V>... additionalActions) {
        return AetherRegistryQueues.FLUID.add(locate(id), fluid, additionalActions);
    }

    public static void init() {
        AetherRegistryQueues.FLUID.register();
    }

}
