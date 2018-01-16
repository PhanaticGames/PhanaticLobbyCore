package plc.sb;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import plc.util.FileUtil;
import plc.util.Placeholder;
import plc.util.StringUtil;

public class ScoreboardManager {

	private static Scoreboard board;

	public static void setScoreBoard(Player p) {
		board = Bukkit.getScoreboardManager().getNewScoreboard();

		Objective obj = board.registerNewObjective("servername", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(StringUtil.colorize(FileUtil.getSB().getString("title")));
		// ☗
		List<String> lines = FileUtil.getSB().getStringList("lines");

		lines = StringUtil.colorizeList(lines);

		for (int i = 0; i < lines.size(); i++) {
			String str = lines.get(i);
			str = str.replaceAll("%br%", StringUtil.colorize("&r"));
			str = Placeholder.runServerPlaceholders(str);
			str = Placeholder.runPlayerPlaceholders(str, p);
			Score score = obj.getScore(str);
			// Math
			score.setScore(10 - i);
		}
		p.setScoreboard(board);
	}
	
}
