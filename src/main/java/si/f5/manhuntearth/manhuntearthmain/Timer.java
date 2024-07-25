package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {
    private final int countOnStart;
    private int nowCount;
    private boolean stopFlag;
    public Timer(final int countOnStart, final Plugin plugin) {
        if(countOnStart<=0) {
            throw new IllegalArgumentException();
        }
        this.countOnStart=countOnStart;
        this.nowCount=this.countOnStart;
        this.stopFlag=false;
        runTaskTimer(plugin,0,20);
    }
    public void StopReq() {
        stopFlag=true;
    }
    @Override
    public void run() {
        if(nowCount<0 || stopFlag) {
            cancel();
            return;
        }
        nowCount--;
    }
}
