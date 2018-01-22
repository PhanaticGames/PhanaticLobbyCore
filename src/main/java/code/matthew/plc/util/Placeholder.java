package code.matthew.plc.util;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import code.matthew.plc.api.IPlayerPlaceholder;
import code.matthew.plc.api.IServerPlaceHolder;

public class Placeholder {

	private static ArrayList<IServerPlaceHolder> serverPlaceholders = new ArrayList<>();
	private static ArrayList<IPlayerPlaceholder> playerPlaceholder = new ArrayList<>();
	
	public static void registerServerPlaceholder(IServerPlaceHolder holder) {
		serverPlaceholders.add(holder);
	}
	
	public static void registerPlayerPlaceholder(IPlayerPlaceholder holder) {
		playerPlaceholder.add(holder);
	}
	
	
	public static String runServerPlaceholders(String str) {
		for (int i = 0; i < serverPlaceholders.size(); i++) {
			String placeholder = serverPlaceholders.get(i).getPlaceholder();
			placeholder = "%" + placeholder + "%";
			if(str.contains(placeholder)) {
				str = str.replaceAll(placeholder, serverPlaceholders.get(i).getReplacement());
			}
		}
		return str;
	}
	
	public static String runPlayerPlaceholders(String str, Player p) {
		for (int i = 0; i < playerPlaceholder.size(); i++) {
			String placeholder = playerPlaceholder.get(i).getPlaceholder();
			placeholder = "%" + placeholder + "%";
			if(str.contains(placeholder)) {
				str = str.replaceAll(placeholder, playerPlaceholder.get(i).getReplacement(p));
			}
		}
		return str;
	}
}
