package Fantasy_World.mod.gui.magic_bench.gui_handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Fantasy_World.mod.gui.magic_bench.container.magic_workbench_container;
import Fantasy_World.mod.gui.magic_bench.gui.magic_workbench_gui;
import Fantasy_World.mod.gui.magic_bench.tileentity.magic_workbench_tileentity;
import cpw.mods.fml.common.network.IGuiHandler;

public class magic_workbench_guihandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (!world.blockExists(x, y, z))
			return null;
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity instanceof magic_workbench_tileentity) {
			return new magic_workbench_container(player, (magic_workbench_tileentity) tileentity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (!world.blockExists(x, y, z))
			return null;
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity instanceof magic_workbench_tileentity) {
			return new magic_workbench_gui(player, (magic_workbench_tileentity) tileentity);
		}
		return null;
	}

}