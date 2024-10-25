package si.f5.manhuntearth.manhuntearthmain;

public class GameTime {
    private final int tick;
    private final int SECOND=20;
    private final int MINUTE =60*SECOND;
    public GameTime(final int tick) {
        if(tick<0) throw new IllegalArgumentException("0以上でなければいけません");
        this.tick=tick;
    }
    public GameTime Add(GameTime time) {
        return new GameTime(this.tick+ time.tick);
    }
    public GameTime Increment() {
        return new GameTime(this.tick+1);
    }
    public GameTime Decrement() {
        return new GameTime(this.tick-1);
    }
    public String Format() {
        StringBuilder format=new StringBuilder();
        if(Minutes()<10) {
            format.append(0);
        }
        format.append(Minutes());
        format.append(":");
        if(Seconds()<10) {
            format.append(0);
        }
        format.append(Seconds());
        return format.toString();
    }
    private int Minutes() {
        return this.tick/ MINUTE;
    }
    private int Seconds() {
        return (this.tick% MINUTE)/ SECOND;
    }
    public boolean isZero() {
        return tick==0;
    }
}
