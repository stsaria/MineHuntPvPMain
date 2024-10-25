package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

public class BossBarTimer {
    private final BossBar bossBar;
    public BossBarTimer(GamePlayersList gamePlayersList) {
        bossBar = Bukkit.createBossBar("", BarColor.WHITE, BarStyle.SOLID,BarFlag.DARKEN_SKY);
        for(GamePlayer gamePlayer : gamePlayersList.playersList) {
            gamePlayer.getOnlinePlayer().ifPresent(bossBar::addPlayer);
        }
    }
    public void Update(GameTime max,GameTime now) {
        bossBar.setProgress(((double) now.Tick() /(double) max.Tick()));
        bossBar.setTitle(now.Format());
    }
    public void Remove() {
        bossBar.removeAll();
    }
}
