package Fantasy_World.mod;

import net.minecraft.item.Item;
import Fantasy_World.mod.api.blocks;
import Fantasy_World.mod.api.items;
import Fantasy_World.mod.api.weapons;
import Fantasy_World.mod.blocks.FwmBlocks;
import Fantasy_World.mod.creativetabs.creativetabs;
import Fantasy_World.mod.entity.magicentity.magic_rod_entity;
import Fantasy_World.mod.enums.FwmEnumToolMaterial;
import Fantasy_World.mod.item.FwmFood;
import Fantasy_World.mod.item.FwmItem;
import Fantasy_World.mod.recipe.BlockRecipe;
import Fantasy_World.mod.recipe.ItemRecipe;
import Fantasy_World.mod.register.RegisterBlocks;
import Fantasy_World.mod.register.RegisterItems;
import Fantasy_World.mod.register.RegisterOre;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid = "Fantasy_World_mod", name = "Fantasy World mod", version = "0.0.2")
public class fantasy_world {
/*
	// プロキシの登録
	@SidedProxy(clientSide = "fantasy_world.mod.client.ClientProxyF",
			serverSide = "fantasy_world.mod.common.CommonProxyF")
	public static Commons proxy;
*/
	// 追加するアイテムの宣言
	// 複数追加も可

	public static items items;

	public static weapons weapons;

	public static Item foods;

	public static FwmEnumToolMaterial FwmToolMaterial;

	//public static RegisterItems RegisterItems;

	public static blocks blocks;

	public static creativetabs Tabs;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		FwmToolMaterial = new FwmEnumToolMaterial();

		Tabs = new creativetabs();

		// アイテムの追加
		new FwmItem();

		new FwmFood();

		//new blocks();

		//weapons = new weapons();

		// アイテム登録
		new RegisterItems();

	}
	// ブロックの設定
	@EventHandler
	public void perInit(FMLPreInitializationEvent event) {

		// ブロックの追加
		new FwmBlocks();

		// ブロック登録
		new RegisterBlocks();

	}
	// レシピや鉱石生成の設定
	@EventHandler
	public void init(FMLInitializationEvent event){

		// アイテムレシピの追加
		new ItemRecipe();

		// ブロックレシピの追加
		new BlockRecipe();

		// 武器レシピの追加

		// 防具レシピの追加

		// 鉱石生成
		new RegisterOre();

		// entity
		int idFlame = 1;
		EntityRegistry.registerModEntity(magic_rod_entity.class, "dcsflame.entity_flame", idFlame, this, 128, 5, true);

		// render
		//proxy.registerRenderers();
	}
}

// メモ
// テクスチャ保存場所
// src/main/resources/assets/ドメイン(fantasy_ｗorld)/textures/items/









