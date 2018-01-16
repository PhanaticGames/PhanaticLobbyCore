package plc.sb;

import code.matthew.psc.utils.strings.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import plc.util.FileUtil;
import plc.util.Placeholder;

import java.util.List;

public class ScoreboardManager {

	private static Scoreboard board;

	public static void setScoreBoard(Player p) {
		board = Bukkit.getScoreboardManager().getNewScoreboard();

		Objective obj = board.registerNewObjective("servername", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ColorUtil.colorStr(FileUtil.getSB().getString("title")));
		List<String> lines = FileUtil.getSB().getStringList("lines");

		lines = ColorUtil.colorList(lines);

		for (int i = 0; i < lines.size(); i++) {
			String str = lines.get(i);
			str = str.replaceAll("%br%", ColorUtil.colorStr("&r"));
			str = Placeholder.runServerPlaceholders(str);
			str = Placeholder.runPlayerPlaceholders(str, p);
			Score score = obj.getScore(str);
			// Math
			score.setScore(10 - i);
		}
		p.setScoreboard(board);
	}
	
}
