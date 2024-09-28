package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

public class BossBarTimer {
    private final BossBar bossBar;
    private int highlightUntil;
    public BossBarTimer(GamePlayersList gamePlayersList) {
        bossBar = Bukkit.createBossBar("", BarColor.WHITE, BarStyle.SOLID,BarFlag.DARKEN_SKY);
        for(GamePlayer gamePlayer : gamePlayersList.playersList) {
            gamePlayer.getOnlinePlayer().ifPresent(bossBar::addPlayer);
        }
    }
    public void Update(int max,int now,boolean highlight) {
        if(highlight) {
            highlightUntil=now-20;
        }
        if(highlightUntil<=now) {
            bossBar.setColor(BarColor.RED);
        } else {
            bossBar.setColor(BarColor.WHITE);
        }
        bossBar.setProgress((double)now/(double)max);
        int min=now/Main.MINUTES;
        int sec=(now%Main.MINUTES)/Main.SECOND;
        bossBar.setTitle((min<10?"0":"") + (min) + (":") + (sec<10?"0":"") + (sec));
    }
    public void Remove() {
        bossBar.removeAll();
    }
}
