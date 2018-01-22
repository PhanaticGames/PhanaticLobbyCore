package code.matthew.plc.listeners.env;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Weather implements Listener {

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
}
