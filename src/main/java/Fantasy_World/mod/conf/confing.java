package Fantasy_World.mod.conf;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;



public class confing{
	private static ModMetadata meta;
	public confing(FMLPreInitializationEvent event){
		// MODの情報を登録する。
		ModInfoCore.registerInfo(meta);
		// コンフィグを読み込む。
		ModConfigCore.loadConfig(event);
	}

	public confing(FMLInitializationEvent event){
		// EventHandlerを登録する。
		FMLCommonHandler.instance().bus().register(new ModEventHandler());
		// 利用のサンプル。「レシピの追加」を参照。簡潔化のためこのチュートリアルではアルミニウム鉱石やアルミニウムインゴットは追加していない。
		// GameRegistry.addSmelting(AluminiumMod.oreAluminium, new ItemStack(AluminiumMod.ingotAluminium, AluminiumModConfigCore.amountSmelting), 0.7F);
		// 利用のサンプル。「鉱石の追加」を参照。
		//GameRegistry.registerWorldGenerator(new OreGenerator(), 0);
	}
}