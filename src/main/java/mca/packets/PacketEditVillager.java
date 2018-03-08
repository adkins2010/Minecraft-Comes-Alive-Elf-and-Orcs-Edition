package mca.packets;

import java.util.List;

import io.netty.buffer.ByteBuf;
import mca.core.MCA;
import mca.data.PlayerMemory;
import mca.entity.EntityVillagerMCA;
import mca.enums.EnumEditAction;
import mca.enums.EnumGender;
import mca.enums.EnumPersonality;
import mca.enums.EnumProfession;
import mca.enums.EnumRace;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.apache.logging.log4j.LogManager;
import radixcore.datastructures.CyclicIntList;
import radixcore.modules.RadixNettyIO;
import radixcore.modules.net.AbstractPacket;

public class PacketEditVillager extends AbstractPacket<PacketEditVillager> {
	private int entityId;
	private EnumEditAction editAction;
	private Object editData;

	private CyclicIntList jobs;
	private CyclicIntList races;
	private CyclicIntList personalities;
	private CyclicIntList textures;

	public PacketEditVillager() {
	}

	public PacketEditVillager(int entityId, EnumEditAction editAction, Object editData) {
		this.entityId = entityId;
		this.editAction = editAction;
		this.editData = editData;
	}

	public PacketEditVillager(int entityId, EnumEditAction editAction) {
		this.entityId = entityId;
		this.editAction = editAction;
		this.editData = null;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf) {
		this.entityId = byteBuf.readInt();
		this.editAction = EnumEditAction.byId(byteBuf.readInt());

		boolean hasEditData = byteBuf.readBoolean();

		if (hasEditData) {
			this.editData = RadixNettyIO.readObject(byteBuf);
		}
	}

	@Override
	public void toBytes(ByteBuf byteBuf) {
		byteBuf.writeInt(entityId);
		byteBuf.writeInt(editAction.getId());

		if (editData != null) {
			byteBuf.writeBoolean(true);
			RadixNettyIO.writeObject(byteBuf, editData);
		} else {
			byteBuf.writeBoolean(false);
		}
	}

	@Override
	public void processOnGameThread(PacketEditVillager message, MessageContext context) {
		PacketEditVillager packet = (PacketEditVillager) message;
		EntityVillagerMCA villager = null;
		EntityPlayer player = null;

		for (WorldServer world : FMLCommonHandler.instance().getMinecraftServerInstance().worlds) {
			player = getPlayer(context);
			villager = (EntityVillagerMCA) world.getEntityByID(packet.entityId);

			if (player != null && villager != null) {
				break;
			}
		}

		if (player != null && villager != null) {
			EnumEditAction action = packet.editAction;

			jobs = CyclicIntList.fromList(EnumProfession.getListOfIds());
			jobs.setIndex(villager.attributes.getProfessionEnum().getId());

			personalities = CyclicIntList.fromList(EnumPersonality.getListOfIds());
			personalities.setIndex(0); //Catch all just in case
			for (int i = 0; i < personalities.size(); i++) {
				if (personalities.get(i) == villager.attributes.getPersonality().getId()) {
					personalities.setIndex(i);
					break;
				}
			}

			textures = villager.attributes.getProfessionSkinGroup()
					.getListOfSkinIDs(villager.attributes.getGender() == EnumGender.MALE,
							villager.attributes.getRaceEnum());
			LogManager.getLogger(PacketEditVillager.class)
					.debug("Head Texture:  " + villager.attributes.getHeadTexture());
			textures.setIndex(Integer.valueOf(villager.attributes.getHeadTexture().replaceAll("[^0-9]", "")) - 1);

			switch (action) {
				case GIRTH_DOWN:
					villager.attributes.setScaleWidth(villager.attributes.getScaleWidth() - 0.01F);
					break;
				case GIRTH_UP:
					villager.attributes.setScaleWidth(villager.attributes.getScaleWidth() + 0.01F);
					break;
				case HEIGHT_DOWN:
					villager.attributes.setScaleHeight(villager.attributes.getScaleHeight() - 0.01F);
					break;
				case HEIGHT_UP:
					villager.attributes.setScaleHeight(villager.attributes.getScaleHeight() + 0.01F);
					break;
				case PROFESSION_DOWN:
					villager.attributes.setProfession(EnumProfession.getProfessionById(jobs.previous()));
					villager.attributes.assignRandomSkin();
					break;
				case PROFESSION_UP:
					villager.attributes.setProfession(EnumProfession.getProfessionById(jobs.next()));
					villager.attributes.assignRandomSkin();
					break;
				case RACE_DOWN:
					villager.attributes.setRace(EnumRace.getRaceById(races.next()));
					villager.attributes.assignRandomSkin();
					break;
				case RACE_UP:
					villager.attributes.setRace(EnumRace.getRaceById(races.previous()));
					villager.attributes.assignRandomSkin();
					break;
				case RANDOM_NAME:
					villager.attributes.assignRandomName();
					break;
				case SET_NAME:
					villager.attributes.setName((String) packet.editData);
					break;
				case SWAP_GENDER:
					villager.attributes.setGender(villager.attributes.getGender() == EnumGender.MALE ?
					                              EnumGender.FEMALE :
					                              EnumGender.MALE);
					villager.attributes.assignRandomSkin();
					break;
				case TEXTURE_DOWN:
					villager.attributes.setHeadTexture(villager.attributes.getHeadTexture()
							.replaceAll("\\d+", String.valueOf(textures.previous())));
					villager.attributes.setClothesTexture(villager.attributes.getHeadTexture());
					break;
				case TEXTURE_UP:
					villager.attributes.setHeadTexture(villager.attributes.getHeadTexture()
							.replaceAll("\\d+", String.valueOf(textures.next())));
					villager.attributes.setClothesTexture(villager.attributes.getHeadTexture());
					break;
				case TOGGLE_INFECTED:
					villager.attributes.setIsInfected(!villager.attributes.getIsInfected());
					break;
				case TRAIT_DOWN:
					villager.attributes.setPersonality(EnumPersonality.getById(personalities.previous()));
					break;
				case TRAIT_UP:
					villager.attributes.setPersonality(EnumPersonality.getById(personalities.next()));
					break;
				default:
					break;

			}
		}
	}
}