package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Timer extends BukkitRunnable {
    private final int countOnStart;
    private AtomicInteger nowCount;
    private AtomicBoolean stopFlag;
    public Timer(final int countOnStart, final Plugin plugin) {
        if(countOnStart<=0) {
            throw new IllegalArgumentException();
        }
        this.countOnStart=countOnStart;
        this.nowCount.set(this.countOnStart);
        this.stopFlag.set(false);
        runTaskTimer(plugin,0,20);
    }
    public void StopReq() {
        stopFlag.set(true);
    }
    @Override
    public void run() {
        if(nowCount.get()<0 || stopFlag.get()) {
            cancel();
            return;
        }
        nowCount.decrementAndGet();
    }
}
