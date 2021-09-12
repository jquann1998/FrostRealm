package baguchan.frostrealm.registry;

import baguchan.frostrealm.block.FrostGrassBlock;
import baguchan.frostrealm.block.FrostPortalBlock;
import baguchan.frostrealm.block.FrostTorchBlock;
import baguchan.frostrealm.block.WallFrostTorchBlock;
import baguchan.frostrealm.world.tree.FrostrootTree;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = "frostrealm", bus = EventBusSubscriber.Bus.MOD)
public class FrostBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "frostrealm");

	public static final RegistryObject<FrostPortalBlock> FROST_PORTAL = BLOCKS.register("frostrealm_portal", () -> new FrostPortalBlock(BlockBehaviour.Properties.of(Material.PORTAL).noOcclusion().noCollission().randomTicks().lightLevel((state) -> {
		return 11;
	}).strength(-1.0F).sound(SoundType.GLASS)));

	public static final RegistryObject<Block> FROZEN_GRASS_BLOCK = BLOCKS.register("frozen_grass_block", () -> new FrostGrassBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(0.6F).sound(SoundType.GRASS)));
	public static final RegistryObject<Block> FROZEN_DIRT = BLOCKS.register("frozen_dirt", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));
	public static final RegistryObject<Block> FRIGID_STONE = BLOCKS.register("frigid_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.NETHERRACK)));

	public static final RegistryObject<SlabBlock> FRIGID_STONE_SLAB = BLOCKS.register("frigid_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 6.0F).noOcclusion().requiresCorrectToolForDrops().sound(SoundType.NETHERRACK)));
	public static final RegistryObject<StairBlock> FRIGID_STONE_STAIRS = BLOCKS.register("frigid_stone_stairs", () -> new StairBlock(FRIGID_STONE.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).noOcclusion().strength(1.5F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.NETHERRACK)));
	public static final RegistryObject<Block> FRIGID_STONE_BRICK = BLOCKS.register("frigid_stone_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)));
	public static final RegistryObject<SlabBlock> FRIGID_STONE_BRICK_SLAB = BLOCKS.register("frigid_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 6.0F).noOcclusion().requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)));
	public static final RegistryObject<StairBlock> FRIGID_STONE_BRICK_STAIRS = BLOCKS.register("frigid_stone_brick_stairs", () -> new StairBlock(FRIGID_STONE_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 6.0F).noOcclusion().requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)));
	public static final RegistryObject<Block> FRIGID_STONE_SMOOTH_BRICK = BLOCKS.register("frigid_stone_smooth_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)));

	public static final RegistryObject<RotatedPillarBlock> FROSTROOT_LOG = BLOCKS.register("frostroot_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<LeavesBlock> FROSTROOT_LEAVES = BLOCKS.register("frostroot_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).sound(SoundType.GRASS)));
	public static final RegistryObject<SaplingBlock> FROSTROOT_SAPLING = BLOCKS.register("frostroot_sapling", () -> new SaplingBlock(new FrostrootTree(), BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS)));
	public static final RegistryObject<Block> FROSTROOT_PLANKS = BLOCKS.register("frostroot_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<SlabBlock> FROSTROOT_PLANKS_SLAB = BLOCKS.register("frostroot_planks_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final RegistryObject<StairBlock> FROSTROOT_PLANKS_STAIRS = BLOCKS.register("frostroot_planks_stairs", () -> new StairBlock(FROSTROOT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final RegistryObject<FenceBlock> FROSTROOT_FENCE = BLOCKS.register("frostroot_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final RegistryObject<FenceGateBlock> FROSTROOT_FENCE_GATE = BLOCKS.register("frostroot_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));

	public static final RegistryObject<Block> FROST_CRYSTAL_ORE = BLOCKS.register("frost_crystal_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.NETHERRACK)));
	public static final RegistryObject<Block> GLIMMERROCK_ORE = BLOCKS.register("glimmerrock_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops().lightLevel((state) -> {
		return 10;
	}).sound(SoundType.NETHERRACK)));
	public static final RegistryObject<Block> STARDUST_CRYSTAL_ORE = BLOCKS.register("stardust_crystal_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.NETHERRACK)));
	public static final RegistryObject<Block> STARDUST_CRYSTAL_CLUSTER = BLOCKS.register("stardust_crystal_cluster", () -> new Block(BlockBehaviour.Properties.of(Material.GLASS).requiresCorrectToolForDrops().lightLevel((state) -> {
		return 10;
	}).strength(5.0F, 6.0F).noOcclusion().sound(SoundType.GLASS)));

	public static final RegistryObject<Block> FROST_TORCH = BLOCKS.register("frost_torch", () -> new FrostTorchBlock(BlockBehaviour.Properties.copy(Blocks.TORCH)));
	public static final RegistryObject<Block> WALL_FROST_TORCH = BLOCKS.register("wall_frost_torch", () -> new WallFrostTorchBlock(BlockBehaviour.Properties.copy(Blocks.WALL_TORCH)));

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> registry) {
		FrostItems.register(registry, new BlockItem(FROZEN_GRASS_BLOCK.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FRIGID_STONE.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FRIGID_STONE_SLAB.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FRIGID_STONE_STAIRS.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FRIGID_STONE_BRICK.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FRIGID_STONE_BRICK_SLAB.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FRIGID_STONE_BRICK_STAIRS.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FROZEN_DIRT.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FRIGID_STONE_SMOOTH_BRICK.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FROSTROOT_LOG.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FROSTROOT_LEAVES.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FROSTROOT_SAPLING.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FROSTROOT_PLANKS.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FROSTROOT_PLANKS_SLAB.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FROSTROOT_PLANKS_STAIRS.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FROSTROOT_FENCE.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FROSTROOT_FENCE_GATE.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(FROST_CRYSTAL_ORE.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(GLIMMERROCK_ORE.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(STARDUST_CRYSTAL_ORE.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
		FrostItems.register(registry, new BlockItem(STARDUST_CRYSTAL_CLUSTER.get(), (new Item.Properties()).tab(FrostGroups.TAB_FROSTREALM)));
	}
}
