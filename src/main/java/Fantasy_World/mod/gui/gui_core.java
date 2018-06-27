package Fantasy_World.mod.gui;

import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.gui.aoi_chest.gui_hundler.aoi_gui_hundler;
import Fantasy_World.mod.gui.aoi_chest.tileentity.aoi_tileentity;
import Fantasy_World.mod.gui.magic_bench.magic_workbench;
import Fantasy_World.mod.gui.magic_bench.gui_handler.magic_workbench_guihandler;
import Fantasy_World.mod.gui.magic_bench.tileentity.magic_workbench_tileentity;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class gui_core{
	public gui_core(){
		// GUIブロックの追加
		fantasy_world.gui.magic_workbench = new magic_workbench().setBlockName("magic_workbench").setBlockTextureName("fantasy_world:magic_workbench");

		// GUIブロックの登録
		GameRegistry.registerBlock(fantasy_world.gui.magic_workbench, "magic_workbench");

		// TileEntityの登録
		GameRegistry.registerTileEntity(magic_workbench_tileentity.class, "magic_workbench");
		GameRegistry.registerTileEntity(aoi_tileentity.class, "aoi_chest");

		// GUIHandlerの登録
		NetworkRegistry.INSTANCE.registerGuiHandler(fantasy_world.instance, new magic_workbench_guihandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(fantasy_world.instance, new aoi_gui_hundler());
	}
}