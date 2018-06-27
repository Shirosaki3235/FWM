package Fantasy_World.mod.gui.aoi_chest.gui_hundler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Fantasy_World.mod.gui.aoi_chest.container.aoi_container;
import Fantasy_World.mod.gui.aoi_chest.tileentity.aoi_tileentity;
import cpw.mods.fml.common.network.IGuiHandler;

public class aoi_gui_hundler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (!world.blockExists(x, y, z))
			return null;
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity instanceof aoi_tileentity) {
			if(id == 2)
			return new aoi_container(player, (aoi_tileentity) tileentity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (!world.blockExists(x, y, z))
			return null;
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity instanceof aoi_tileentity) {
			if(id == 2)
			return new aoi_container(player, (aoi_tileentity) tileentity);
		}
		return null;
	}

}