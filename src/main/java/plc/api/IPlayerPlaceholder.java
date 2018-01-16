package plc.api;

import org.bukkit.entity.Player;

import plc.util.Placeholder;

/**
 * When implemented, can be used by {@link Placeholder#registerPlaceholder(IPlaceHolder)}
 * This can be used to add a placeholder to the scoreboard or future things
 * Note: This is made for player side variables or other things. Player is provided
 */
public interface IPlayerPlaceholder {

	/**
	 * Return a value like online_players. %%'s
	 * will be added to this.
	 */
	public String getPlaceholder();
	
	/**
	 * The thing put in place of the place holder
	 * @param p The player. THIS IS AUTO FILLED.
	 * @return The data to show
	 */
	public String getReplacement(Player p);
}
