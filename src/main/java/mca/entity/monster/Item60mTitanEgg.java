package mca.entity.monster;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
//import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

// Referenced classes of package mods.TheTitan:
//            Entity60mTitan

public class Item60mTitanEgg extends Item
{

    public Item60mTitanEgg()
    {
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, 
            float par8, float par9, float par10)
    {
        Entity60mTitan entitytitan = new Entity60mTitan(par3World);
        if(!par3World.isRemote)
        {
			// entitytitan.setLocationAndAngles((double)par4 + 0.5D, (double)par5 + 1.5D,
			// (double)par6 + 0.5D, MathHelper.func_76142_g(par3World.rand.nextFloat() *
			// 360F), 0.0F);
			// entitytitan.field_70759_as = entitytitan.field_70177_z;
			// entitytitan.field_70761_aq = entitytitan.field_70177_z;
            par3World.spawnEntity(entitytitan);
        }
		// if(!par2EntityPlayer.field_71075_bZ.field_75098_d)
		// par1ItemStack.stackSize--;
        return true;
    }
}
