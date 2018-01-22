package code.matthew.plc.api;

import code.matthew.plc.util.Placeholder;

/**
 * When implemented, can be used by {@link Placeholder#registerServerPlaceholder(IServerPlaceHolder)}
 * This can be used to add a placeholder to the scoreboard or future things
 * Note: This is made for server side variables or other things. No player is provided
 */
public interface IServerPlaceHolder {

	/**
	 * Return a value like online_players. %%'s
	 * will be added to this.
	 */
	public String getPlaceholder();
	
	/**
	 * The thing put in place of the place holder
	 * @return The data to show
	 */
	public String getReplacement();
}
