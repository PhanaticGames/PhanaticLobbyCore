package plc.api;

import java.util.List;

import org.bukkit.Material;

public interface IGUIItem {

	public String getID();

	public String getName();

	public Material getMaterial();

	public int getType();

	public List<String> getLore();

	public int getData();

}
