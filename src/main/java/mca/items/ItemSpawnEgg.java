package mca.items;

import mca.entity.EntityVillagerMCA;
import mca.enums.EnumGender;
import mca.enums.EnumRace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSpawnEgg extends Item {
	protected boolean isMale;

	public ItemSpawnEgg(boolean isMale) {
		this.isMale = isMale;
		this.setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player,
			World world,
			BlockPos pos,
			EnumHand hand,
			EnumFacing side,
			float hitX,
			float hitY,
			float hitZ) {
		int posX = pos.getX();
		int posY = pos.getY() + 1;
		int posZ = pos.getZ();

		if (!world.isRemote) {
			double verticalOffset = 0.0D;

			spawnCreature(world, posX + 0.5D, posY + verticalOffset, posZ + 0.5D);

			if (!player.capabilities.isCreativeMode) {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
			}
		}

		return EnumActionResult.PASS;
	}

	public void spawnCreature(World world, double posX, double posY, double posZ) {
		EntityVillagerMCA villager = new EntityVillagerMCA(world);
		villager.attributes.setGender(isMale ? EnumGender.MALE : EnumGender.FEMALE);
		villager.attributes.assignRandomName();
		villager.attributes.assignRandomProfession();
		villager.attributes.assignRandomPersonality();
		villager.attributes.assignRandomSkin();
		villager.attributes.setRace(EnumRace.Villager);
		villager.setPosition(posX, posY, posZ);
		world.spawnEntity(villager);
		// if (RadixLogic.getBooleanWithProbability(2)) {
		// MCA.naturallySpawnVillagers(new Point3D(posX, posY, posZ), world,
		// villager.getProfession());
		// }
	}
}
