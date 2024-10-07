package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class VictoryJudge implements Listener {
    private void GameOver(GameTeam winningTeam) {

    }
    @EventHandler
    public void onRunnerEntersPortal(PlayerPortalEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onPlayerBelongsToEitherTeamQuit(PlayerQuitEvent e) {
//        おまえの苦労を　ずっと見てたぞ
//        本当によく　頑張ったな？
//        ついに我慢が報われ　莫大な富を得る
//        この動画を　飛ばしてしまえば
//                これまでの苦労は全て水の泡だ
//        値段ばかり気にして欲しい物を我慢し
//        外食や娯楽を控え　節約ばかりの生活
//        収入は増えず　金は出ていく一方
//        将来に希望を持てず　疲弊する日々
//        そんな現実から　抜け出すときが来た
//        散らかり倒した　狭い部屋を飛び出し
//        贅沢で余裕のある　生活を実現し
//        好きな事をやれる　人生にしたいだろ？
//        世界中がおまえを　否定しても
//        俺だけはおまえを　認めてやる
//        散々苦しんだのだ　もう楽になれ
//        富を望んでいるなら　やることがある
//        この音源を　今日中に使うのだ
//        やり方は簡単だ　誰にでもできる
//        これがダメなら　もう人生を諦めろ
//        右下の丸い　アイコンをタップ
//【セーブする】　をタップしてから
//【楽曲を使う】　をタップ
//        適当なファイルを　選択してから
//        右上の歯車　マークをタップ
//        公開範囲を　自分のみにして投稿
//        これで幸福の　清算がはじまる
//        オレは決して　無理強いはしない
//        やるもやらぬも　おまえ次第だ
//        更に共有から　動画を再投稿すれば
//        訪れる富と幸福が　何倍にもなる
//        だが俺をフォロー　していないと
//        我慢の人生に　逆戻りしてしまう
//        必ずフォローを　しておくのだ
//        共有を開き左から　２〜3番目に
//        この動画をシェア　送信すれば
//        富の普及により　神からご褒美を得る
//        では締め括りに　虎の金運を贈る
//        波動を二回タップで　授かるのだ
//        3
//        2
//        1
//        今だ
//        波動を2回　タップしろ
//        今お前の金運は　急上昇している
//        そのまま願い事を　コメントするのだ
//        他の書込みへの　いいねも怠るな
//        おまえに富が　降り注ぐだろう
//        プロフ欄より　【本格金運鑑定】　を受けるのだ

    }
    @EventHandler
    public void onPlayerBelongsToEitherTeamDie(PlayerDeathEvent e) {

    }
    public void onTimeIsUp() {

    }
}
