package plc.tab;

import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import plc.PLC;
import plc.util.FileUtil;
import plc.util.StringUtil;

public class TabList {

	public static void setTabList(Player p) {
		PacketContainer pc = PLC.getInstance().getProtocal()
				.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);

		String header = FileUtil.getConfig().getString("tabHeader");
		String footer = FileUtil.getConfig().getString("tabFooter");

		header = StringUtil.colorize(header);
		footer = StringUtil.colorize(footer);

		pc.getChatComponents().write(0, WrappedChatComponent.fromText(header)).write(1,
				WrappedChatComponent.fromText(footer));
		try {
			PLC.getInstance().getProtocal().sendServerPacket(p, pc);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
