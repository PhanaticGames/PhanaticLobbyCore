package plc.util;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;

public class StringUtil {

	public static String colorize(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}
	
	public static List<String> colorizeList(List<String> strs) {
		strs = strs.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList());
		return strs;
	}
}
