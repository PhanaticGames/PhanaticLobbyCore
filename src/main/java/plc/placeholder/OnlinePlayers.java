package plc.placeholder;

import org.bukkit.Bukkit;

import plc.api.IServerPlaceHolder;

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
