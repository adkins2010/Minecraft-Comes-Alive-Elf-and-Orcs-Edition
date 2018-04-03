package mca.packets;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import mca.entity.passive.EntityVillagerMCA;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import radixcore.modules.RadixNettyIO;
import radixcore.modules.net.AbstractPacket;

public class PacketSetSize extends AbstractPacket<PacketSetSize> {
	private UUID entityUUID;
	private int entityId;
	private float width;
	private float height;

	public PacketSetSize() {
	}

	public PacketSetSize(EntityVillagerMCA human, float width, float height) {
		this.entityUUID = human.getUniqueID();
		this.entityId = human.getEntityId();
		this.width = width;
		this.height = height;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf) {
		this.entityUUID = (UUID) RadixNettyIO.readObject(byteBuf);
		this.entityId = byteBuf.readInt();
		this.width = byteBuf.readFloat();
		this.height = byteBuf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf byteBuf) {
		RadixNettyIO.writeObject(byteBuf, this.entityUUID);
		byteBuf.writeInt(entityId);
		byteBuf.writeFloat(this.width);
		byteBuf.writeFloat(this.height);
	}

	@Override
	public void processOnGameThread(PacketSetSize packet, MessageContext context) {
		EntityPlayer player = getPlayer(context);
		World world = player.world;
		EntityVillagerMCA human = null;

		for (Object obj : world.loadedEntityList) {
			Entity entity = (Entity) obj;

			try {
				// Two-factor check for different MC versions. UUIDs do not appear to work
				// properly in 1.7.10, and
				// I believe actual entity IDs are deprecated later.
				if (entity.getUniqueID().equals(packet.entityUUID)
						|| entity.getEntityId() == packet.entityId) {
					human = (EntityVillagerMCA) entity;
					break;
				}
			}
			catch (ClassCastException e) {
				String msg = String.format(
						"Class Cast Exception occurred!%nPacketSetSize.java:60 Call to String.equals(java.util.UUID) in mca.packets.PacketSetSize.processOnGameThread(PacketSetSize, MessageContext) [Scariest(1), High confidence]%nMessage: %s%n",
						e.getLocalizedMessage());
				FMLLog.warning(msg, e);
				// java.util.logging.LogManager.getLogManager().getLogger(this.getClass().getName()).warning(msg);
				org.apache.logging.log4j.LogManager.getLogger(this.getClass().getName()).warn(msg);
				// java.util.logging.Logger.getLogger(this.getClass().getName()).warning(msg);

			}
			catch (NullPointerException e) {
				String msg = String.format("Null Pointer Exception occurred!%nMessage: %s%n", e.getLocalizedMessage());
				FMLLog.warning(msg, e);
				// java.util.logging.LogManager.getLogManager().getLogger(this.getClass().getName()).warning(msg);
				org.apache.logging.log4j.LogManager.getLogger(this.getClass().getName()).warn(msg);
				// java.util.logging.Logger.getLogger(this.getClass().getName()).warning(msg);
			}
			// ClassCast or NullPointer is possible here. Ignore.
		}

		if (human != null) {
			human.attributes.setSize(packet.width, packet.height);
		}
	}
}
