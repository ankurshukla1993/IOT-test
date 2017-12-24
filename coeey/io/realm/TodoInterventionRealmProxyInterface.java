package io.realm;

import com.cooey.common.vo.careplan.Goal;
import com.cooey.common.vo.careplan.Todo;

public interface TodoInterventionRealmProxyInterface {
    Goal realmGet$goal();

    Todo realmGet$todo();

    void realmSet$goal(Goal goal);

    void realmSet$todo(Todo todo);
}
