package Fantasy_World.mod.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.entity.magicentity.normal_entity;
import Fantasy_World.mod.entity.mobentity.seyana_entity;
import Fantasy_World.mod.entity.npcentity.aoi_entity;
import Fantasy_World.mod.render.entity.mob.seyana_render;
import Fantasy_World.mod.render.entity.npc.aoi_render;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;


public class entity_core{
		private int ID_NPC = 100;
	public entity_core() {
		//BiomeGenBase[] FWM_Biome;
		//paisonnha FWM_Biome = BiomeDictionary.getBiomesForType();

		// Entityの登録
		EntityRegistry.registerModEntity(aoi_entity.class, "Aoi", ID_NPC, fantasy_world.instance, 250, 1, false);
		EntityRegistry.registerModEntity(seyana_entity.class, "seyana", ID_NPC + 1, fantasy_world.instance, 250, 1, false);

		// 魔法攻撃Entity
		EntityRegistry.registerModEntity(normal_entity.class, "normal_magic", ID_NPC + 2, fantasy_world.instance, 128, 5, true);

		// 自然スポーンの登録
		EntityRegistry.addSpawn(aoi_entity.class, 0, 1, 1, EnumCreatureType.creature, BiomeGenBase.plains);
		EntityRegistry.addSpawn(seyana_entity.class, 20, 1, 20, EnumCreatureType.creature, fantasy_world.biome.magic_forest);

		// レンダーの登録
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
		RenderingRegistry.registerEntityRenderingHandler(aoi_entity.class, new aoi_render());
		RenderingRegistry.registerEntityRenderingHandler(seyana_entity.class, new seyana_render());
        }
	}
}

/*
void registerModEntity(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
Mod用のMOBを追加するメソッド。
引数は、以下の通り。
引数	内容
entityClass	追加するEntityをClass型で渡す。
entityName	追加するEntityの名称。langファイルで、entity.[MODIDで登録した名称].[entityNameで登録した名称].name=[ローカル名]でローカル名を登録できる。
id	追加するEntityのID。同一MOD内で被るとエラーを吐く。
mod	MODを渡す。thisで渡せばOK。
trackingRange	MOBの更新範囲。
updateFrequency	MOBの更新頻度。何tickごとにMOBを更新するかの値。基本的に2以下の値を渡す。
sendsVelocityUpdates	MOBの更新時に加速度の情報を更新させるか否か。MOBや動物の場合は基本的にtrueを渡す。

void addSpawn(Class<? extends EntityLiving> entityClass, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, BiomeGenBase... biomes)
Entityの自然スポーンを追加するメソッド。
引数は、以下の通り。
引数	内容
entityClass	スポーンを追加させるEntityをClass型で渡す。
weightProb	Entityのスポーンがどれくらいの頻度で抽選されるかの値。値が大きいほうがスポーンしやすい。
min	Entityがスポーンする際の最低数。
max	Entityがスポーンする際の最高数。
typeOfCreature	Entityのスポーンタイプ。
EnumCreatureType一覧
-monster...敵性MOBの属性。夜にスポーンする。
-creature...友好MOB(とオオカミ、ヤマネコ)の属性。昼にスポーンする。
-ambient...コウモリの属性。
-waterCreature...イカの属性。これのみ、スポーン場所が空気中でなく水中になる。
biomes	Entityがスポーンするバイオーム。複数バイオームを渡す場合は、先にfinal定数でリストを作ってからそのリストをそのまま引数に渡した方がいい。

*/