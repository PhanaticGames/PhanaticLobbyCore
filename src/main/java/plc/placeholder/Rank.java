package plc.placeholder;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import plc.api.IPlayerPlaceholder;
import net.milkbowl.vault.permission.Permission;

public class Rank implements IPlayerPlaceholder {

	private Permission perms;
	private JavaPlugin pl;
	
	public Rank(JavaPlugin pl) {
		this.pl = pl;
	}
	
	@Override
	public String getPlaceholder() {
		return "rank";
	}

	@Override
	public String getReplacement(Player p) {
		RegisteredServiceProvider<Permission> rsp = pl.getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms.getPrimaryGroup(p);
	}

}
