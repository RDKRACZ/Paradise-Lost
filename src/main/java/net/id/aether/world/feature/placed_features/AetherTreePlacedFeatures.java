package net.id.aether.world.feature.placed_features;

import net.id.aether.world.feature.configured_features.AetherTreeConfiguredFeatures;
import net.id.aether.world.feature.placement_modifiers.ChancePlacementModifier;
import net.id.aether.world.feature.placement_modifiers.CrystalTreeIslandPlacementModifier;
import net.minecraft.block.Block;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountMultilayerPlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

import static net.id.aether.blocks.AetherBlocks.*;

public class AetherTreePlacedFeatures extends AetherPlacedFeatures{
    public static final List<Block> AETHER_GROUD = List.of(AETHER_GRASS_BLOCK, HOLYSTONE, MOSSY_HOLYSTONE, AETHER_DIRT, COARSE_AETHER_DIRT, PERMAFROST);

    /*
    Highlands
     */
    // Default
    public static final RegistryEntry<PlacedFeature> SKYROOT_TREE = register("skyroot_tree", AetherTreeConfiguredFeatures.SKYROOT_TREE, placed(SKYROOT_SAPLING));
    public static final RegistryEntry<PlacedFeature> GOLDEN_OAK_TREE = register("golden_oak_tree", AetherTreeConfiguredFeatures.GOLDEN_OAK_TREE, placed(GOLDEN_OAK_SAPLING));
    public static final RegistryEntry<PlacedFeature> CRYSTAL_TREE = register("crystal_tree", AetherTreeConfiguredFeatures.CRYSTAL_TREE, placed(CRYSTAL_SAPLING));
    public static final RegistryEntry<PlacedFeature> ORANGE_TREE = register("orange_tree", AetherTreeConfiguredFeatures.ORANGE_TREE, placed(ORANGE_SAPLING));
    // Wisteria
    public static final RegistryEntry<PlacedFeature> ROSE_WISTERIA_TREE = register("rose_wisteria_tree", AetherTreeConfiguredFeatures.ROSE_WISTERIA_TREE, placed(ROSE_WISTERIA_SAPLING));
    public static final RegistryEntry<PlacedFeature> LAVENDER_WISTERIA_TREE = register("lavender_wisteria_tree", AetherTreeConfiguredFeatures.LAVENDER_WISTERIA_TREE, placed(LAVENDER_WISTERIA_SAPLING));
    public static final RegistryEntry<PlacedFeature> FROST_WISTERIA_TREE = register("frost_wisteria_tree", AetherTreeConfiguredFeatures.FROST_WISTERIA_TREE, placed(FROST_WISTERIA_SAPLING));
    public static final RegistryEntry<PlacedFeature> BOREAL_WISTERIA_TREE = register("boreal_wisteria_tree", AetherTreeConfiguredFeatures.BOREAL_WISTERIA_TREE, placed(BOREAL_WISTERIA_SAPLING));
    // Fancy
    public static final RegistryEntry<PlacedFeature> FANCY_ROSE_WISTERIA_TREE = register("fancy_rose_wisteria_tree", AetherTreeConfiguredFeatures.FANCY_ROSE_WISTERIA_TREE, placed(ROSE_WISTERIA_SAPLING));
    public static final RegistryEntry<PlacedFeature> FANCY_LAVENDER_WISTERIA_TREE = register("fancy_lavender_wisteria_tree", AetherTreeConfiguredFeatures.FANCY_LAVENDER_WISTERIA_TREE, placed(LAVENDER_WISTERIA_SAPLING));
    public static final RegistryEntry<PlacedFeature> FANCY_FROST_WISTERIA_TREE = register("fancy_frost_wisteria_tree", AetherTreeConfiguredFeatures.FANCY_FROST_WISTERIA_TREE, placed(FROST_WISTERIA_SAPLING));
    public static final RegistryEntry<PlacedFeature> FANCY_BOREAL_WISTERIA_TREE = register("fancy_boreal_wisteria_tree", AetherTreeConfiguredFeatures.FANCY_BOREAL_WISTERIA_TREE, placed(BOREAL_WISTERIA_SAPLING));
    public static final RegistryEntry<PlacedFeature> FANCY_SKYROOT_TREE = register("fancy_skyroot_tree", AetherTreeConfiguredFeatures.FANCY_SKYROOT_TREE, placed(SKYROOT_SAPLING));
    // Tree Assortments
    public static final RegistryEntry<PlacedFeature> SCATTERED_TREES = register("scattered_trees", AetherTreeConfiguredFeatures.SCATTERED_TREES, CountMultilayerPlacementModifier.of(6), CountPlacementModifier.of(UniformIntProvider.create(0, 6)));
    public static final RegistryEntry<PlacedFeature> SHIELD_TREES = register("shield_trees", AetherTreeConfiguredFeatures.SHIELD_TREES, CountMultilayerPlacementModifier.of(5), CountPlacementModifier.of(UniformIntProvider.create(0, 6)));
    public static final RegistryEntry<PlacedFeature> DENSE_SHIELD_TREES = register("dense_shield_trees", AetherTreeConfiguredFeatures.DENSE_SHIELD_TREES, CountMultilayerPlacementModifier.of(12), ChancePlacementModifier.of(18));
    public static final RegistryEntry<PlacedFeature> PLATEAU_TREES = register("plateau_trees", AetherTreeConfiguredFeatures.PLATEAU_TREES, ChancePlacementModifier.of(3), CountMultilayerPlacementModifier.of(1), CountPlacementModifier.of(UniformIntProvider.create(0, 1)));
    public static final RegistryEntry<PlacedFeature> MIXED_TREES = register("mixed_trees", AetherTreeConfiguredFeatures.MIXED_TREES, CountMultilayerPlacementModifier.of(1), ChancePlacementModifier.of(30));
    public static final RegistryEntry<PlacedFeature> SPARSE_TREES = register("sparse_trees", AetherTreeConfiguredFeatures.SPARSE_TREES, CountMultilayerPlacementModifier.of(7), ChancePlacementModifier.of(100), CountPlacementModifier.of(UniformIntProvider.create(0, 2)));
    public static final RegistryEntry<PlacedFeature> THICKET_TREES = register("thicket_trees", AetherTreeConfiguredFeatures.THICKET_TREES, CountMultilayerPlacementModifier.of(14), CountPlacementModifier.of(UniformIntProvider.create(0, 6)));
    public static final RegistryEntry<PlacedFeature> RAINBOW_FOREST_TREES = register("wisteria_woods_trees", AetherTreeConfiguredFeatures.RAINBOW_FOREST_TREES, CountMultilayerPlacementModifier.of(17), CountPlacementModifier.of(UniformIntProvider.create(0, 4)));
    // Fallen leaves
    public static final RegistryEntry<PlacedFeature> FALLEN_LEAVES = register("fallen_leaves", AetherTreeConfiguredFeatures.FALLEN_LEAVES, SPREAD_32_ABOVE, PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, CountPlacementModifier.of(3), ChancePlacementModifier.of(5), BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(AETHER_GROUD)));
    public static final RegistryEntry<PlacedFeature> ALT_FALLEN_LEAVES = register("alt_fallen_leaves", AetherTreeConfiguredFeatures.ALT_FALLEN_LEAVES, SPREAD_32_ABOVE, PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, CountPlacementModifier.of(3), ChancePlacementModifier.of(5), BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(AETHER_GROUD)));

    public static final RegistryEntry<PlacedFeature> FALLEN_ROSE_LEAVES = register("fallen_rose_leaves", AetherTreeConfiguredFeatures.FALLEN_ROSE_LEAVES, PlacedFeatures.BOTTOM_TO_TOP_RANGE, ChancePlacementModifier.of(2), CountMultilayerPlacementModifier.of(1), CountPlacementModifier.of(UniformIntProvider.create(0, 3)));
    public static final RegistryEntry<PlacedFeature> FALLEN_LAVENDER_LEAVES = register("fallen_lavender_leaves", AetherTreeConfiguredFeatures.FALLEN_LAVENDER_LEAVES, PlacedFeatures.BOTTOM_TO_TOP_RANGE, ChancePlacementModifier.of(2), CountMultilayerPlacementModifier.of(1), CountPlacementModifier.of(UniformIntProvider.create(0, 3)));
    // Logs & Stumps
    public static final RegistryEntry<PlacedFeature> THICKET_FALLEN_LOG = register("thicket_fallen_log", AetherTreeConfiguredFeatures.THICKET_FALLEN_LOG, ChancePlacementModifier.of(3), CountMultilayerPlacementModifier.of(2), CountPlacementModifier.of(UniformIntProvider.create(0, 2)));

    public static final RegistryEntry<PlacedFeature> MOTTLED_FALLEN_LOG = register("mottled_fallen_log", AetherTreeConfiguredFeatures.MOTTLED_FALLEN_LOG, ChancePlacementModifier.of(3), CountMultilayerPlacementModifier.of(1));
    public static final RegistryEntry<PlacedFeature> MOTTLED_HOLLOW_FALLEN_LOG = register("mottled_hollow_fallen_log", AetherTreeConfiguredFeatures.MOTTLED_HOLLOW_FALLEN_LOG, ChancePlacementModifier.of(3), CountMultilayerPlacementModifier.of(1));

    public static final RegistryEntry<PlacedFeature> SHIELD_STUMPS = register("shield_stumps", AetherTreeConfiguredFeatures.SHIELD_STUMPS, CountMultilayerPlacementModifier.of(1), CountPlacementModifier.of(UniformIntProvider.create(0, 1)));
    public static final RegistryEntry<PlacedFeature> SHIELD_HOLLOW_STUMPS = register("shield_hollow_stumps", AetherTreeConfiguredFeatures.SHIELD_HOLLOW_STUMPS, CountMultilayerPlacementModifier.of(1));

    public static final RegistryEntry<PlacedFeature> SHIELD_FALLEN_LEAVES = register("shield_fallen_leaves", AetherTreeConfiguredFeatures.SHIELD_FALLEN_LEAVES, PlacedFeatures.BOTTOM_TO_TOP_RANGE, CountMultilayerPlacementModifier.of(2), ChancePlacementModifier.of(4), CountPlacementModifier.of(UniformIntProvider.create(0, 3)), BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(AETHER_GROUD)));
    // Crystal Tree Island
    public static final RegistryEntry<PlacedFeature> CRYSTAL_TREE_ISLAND = register("crystal_tree_island", AetherTreeConfiguredFeatures.CRYSTAL_TREE_ISLAND, CrystalTreeIslandPlacementModifier.of());

    private static PlacementModifier placed(Block sapling){
        return PlacedFeatures.wouldSurvive(sapling);
    }

    public static void init(){}
}
