package si.f5.manhuntearth.manhuntearthmain.items;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import si.f5.manhuntearth.manhuntearthmain.GamePlayer;
import si.f5.manhuntearth.manhuntearthmain.GameTime;
import si.f5.manhuntearth.manhuntearthmain.roles.HunterTeam;
import si.f5.manhuntearth.manhuntearthmain.roles.RunnerTeam;

import java.util.*;

public class TrackerCompass extends GameItem {
    @Override
    Material MATERIAL() {
        return Material.COMPASS;
    }

    @Override
    String NAME() {
        return "追跡機";
    }

    @Override
    List<String> LORE() {
        return new ArrayList<>(Arrays.asList("最も近い標的を指す。","位置情報は設定された時間が経過した後に更新される。","追われる者が持っても効果がない。"));
    }

    @Override
    Optional<Map<Enchantment, Integer>> ENCHANTMENT() {
        return Optional.empty();
    }

    public void TryUpdate(HunterTeam hunterTeam,RunnerTeam runnerTeam,List<GameTime> updateTime,GameTime now) {
        if(updateTime.contains(now)) {
            if(updateTime.indexOf(now) == (updateTime.size()-1)) {
                Update(hunterTeam,runnerTeam,Optional.empty());
            } else {
                Update(hunterTeam,runnerTeam,Optional.of(updateTime.get(updateTime.indexOf(now)+1)));
            }
        }
    }
    private void Update(HunterTeam hunterTeam, RunnerTeam runnerTeam, Optional<GameTime> nextUpdate) {
        hunterTeam.SendMessage(NAME()+"の位置情報が更新された...");
        runnerTeam.SendMessage(hunterTeam.BUKKIT_TEAM_DISPLAY_NAME()+"に位置情報が送信された...");
        if(nextUpdate.isPresent()) {
            hunterTeam.SendMessage("次の更新: ["+nextUpdate.get().Format()+"]");
            runnerTeam.SendMessage("次の更新: ["+nextUpdate.get().Format()+"]");
        } else {
            hunterTeam.SendMessage("次の更新: [なし]");
            runnerTeam.SendMessage("次の更新: [なし]");
        }
        for(GamePlayer hunter:hunterTeam.GetGamePlayers()) {
            Location huntersLocation =hunter.GetLocation();
            Optional<GamePlayer> optionalNearestRunner = Optional.empty();
            for(GamePlayer runner:runnerTeam.GetGamePlayers()) {
                if(optionalNearestRunner.isEmpty()) {
                    optionalNearestRunner=Optional.of(runner);
                    continue;
                }
                GamePlayer nearestRunner=optionalNearestRunner.get();
                double distance = runner.GetLocation().distance(huntersLocation);
                if(nearestRunner.GetLocation().distance(huntersLocation) > distance) {
                    optionalNearestRunner=Optional.of(runner);
                }
            }
            optionalNearestRunner.ifPresent(nearestRunner-> hunter.SetCompassTarget(nearestRunner.GetLocation()));
        }
    }
}
