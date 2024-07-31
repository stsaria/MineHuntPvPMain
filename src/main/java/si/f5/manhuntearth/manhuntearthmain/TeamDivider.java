package si.f5.manhuntearth.manhuntearthmain;

import java.util.ArrayList;
import java.util.List;

public class TeamDivider {
    List<Role> roles;
    public TeamDivider(GamePlayersList gamePlayersList,Role... roles) {
        this.roles=new ArrayList<>();
        for(Role role:roles){
            this.roles.add(role);
        }
    }
}
