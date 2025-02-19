package net.id.aether.blocks.natural.tree;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.id.aether.blocks.util.DynamicColorBlock;
import net.id.aether.client.rendering.block.RenderLayerOverride;
import net.id.aether.client.rendering.shader.AetherRenderLayers;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.Random;

@EnvironmentInterface(value = EnvType.CLIENT, itf = RenderLayerOverride.class)
public class AuralLeavesBlock extends WisteriaLeavesBlock implements DynamicColorBlock, RenderLayerOverride{

    private final Vec3i[] gradientColors;

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
    }

    public AuralLeavesBlock(Settings settings, boolean collidable, Vec3i[] gradientColors) {
        super(settings, collidable);
        if (gradientColors.length != 4) {
            throw new InstantiationError("color gradient must contain exactly four colors");
        }
        this.gradientColors = gradientColors;
    }

    public static int getAuralColor(BlockPos pos, Vec3i[] colorRGBs) {
        Vec3i color1 = colorRGBs[0];
        Vec3i color2 = colorRGBs[1];
        Vec3i color3 = colorRGBs[2];
        Vec3i color4 = colorRGBs[3];
        float timeOffset = MinecraftClient.getInstance().world.getTime() * 0.003F;

        // First, we mix color 1 and color 2 using noise
        double simplex;
        // Sample simplex noise
        if (DynamicColorBlock.isFastGraphics()) {
            simplex = DynamicColorBlock.sampleNoise(pos, 14, 3300);
        } else {
            simplex = DynamicColorBlock.sampleNoise(pos, 31, 3300 + timeOffset);
        }
        // Reshape contrast curve
        double percent = DynamicColorBlock.contrastCurve(36, simplex);
        percent = percent * (2 - percent);
        // Interpolate
        double r1, g1, b1;
        r1 = (MathHelper.lerp(percent, color1.getX(), color2.getX()));
        g1 = (MathHelper.lerp(percent, color1.getY(), color2.getY()));
        b1 = (MathHelper.lerp(percent, color1.getZ(), color2.getZ()));

        // Now we mix colors 3 and 4 together using noise
        // Rinse, repeat as seen above.
        // Sample
        double simplex2;
        if (DynamicColorBlock.isFastGraphics()) {
            simplex2 = DynamicColorBlock.sampleNoise(pos, 14, 1337);
        } else {
            simplex2 = DynamicColorBlock.sampleNoise(pos, 31, 1337 + timeOffset);
        }
        // Reshape
        double percent2 = simplex2 * (2 - simplex2);
        // Interpolate
        double r2, g2, b2;
        r2 = (MathHelper.lerp(percent2, color3.getX(), color4.getX()));
        g2 = (MathHelper.lerp(percent2, color3.getY(), color4.getY()));
        b2 = (MathHelper.lerp(percent2, color3.getZ(), color4.getZ()));

        // This last section interpolates between r1, g1, b1, and r2, g2, b2, finally mixing all the colors together.
        double simplex3;
        if (DynamicColorBlock.isFastGraphics()) {
            simplex3 = DynamicColorBlock.sampleNoise(pos, 14, 1738);
        } else {
            simplex3 = DynamicColorBlock.sampleNoise(pos, 31, 1738 + timeOffset);
        }
        double finalPercent = DynamicColorBlock.contrastCurve(25, simplex3);
        // Interpolate
        int finalR, finalG, finalB;
        finalR = (int) (MathHelper.lerp(finalPercent, r1, r2));
        finalG = (int) (MathHelper.lerp(finalPercent, g1, g2));
        finalB = (int) (MathHelper.lerp(finalPercent, b1, b2));

        return MathHelper.packRgb(finalR, finalG, finalB);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public BlockColorProvider getBlockColorProvider() {
        // evil hack to send the shader the blockpos
        return (state, world, pos, tintIndex) -> MathHelper.packRgb(pos.getX(), pos.getY(), pos.getZ()) | ((2*(pos.getY() / 256 % 2) - 1) << 24);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public ItemColorProvider getBlockItemColorProvider() {
        return (stack, tintIndex) -> getAuralColor(BlockPos.ORIGIN, gradientColors);
    }
    
    @Environment(EnvType.CLIENT)
    @Override
    public RenderLayer getRenderLayerOverride(boolean fancy){
        return fancy ? AetherRenderLayers.AURAL_CUTOUT_MIPPED : AetherRenderLayers.AURAL;
    }
}
