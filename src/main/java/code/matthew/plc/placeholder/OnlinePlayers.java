package code.matthew.plc.placeholder;

import code.matthew.plc.api.IServerPlaceHolder;
import org.bukkit.Bukkit;

public class OnlinePlayers implements IServerPlaceHolder {

	@Override
	public String getPlaceholder() {
		return "online_players";
	}

	@Override
	public String getReplacement() {
		return String.valueOf(Bukkit.getOnlinePlayers().size());
	}

	
}
