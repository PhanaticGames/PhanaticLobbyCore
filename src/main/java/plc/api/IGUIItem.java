package plc.api;

import org.bukkit.Material;

import java.util.List;

public interface IGUIItem {

	String getID();

	String getName();

	Material getMaterial();

	int getType();

	List<String> getLore();

	int getData();

}
