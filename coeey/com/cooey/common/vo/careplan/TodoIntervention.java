package com.cooey.common.vo.careplan;

import io.realm.RealmObject;
import io.realm.TodoInterventionRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class TodoIntervention extends RealmObject implements TodoInterventionRealmProxyInterface {
    private Goal goal;
    private Todo todo;

    public Goal realmGet$goal() {
        return this.goal;
    }

    public Todo realmGet$todo() {
        return this.todo;
    }

    public void realmSet$goal(Goal goal) {
        this.goal = goal;
    }

    public void realmSet$todo(Todo todo) {
        this.todo = todo;
    }

    public TodoIntervention() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public Todo getTodo() {
        return realmGet$todo();
    }

    public void setTodo(Todo todo) {
        realmSet$todo(todo);
    }

    public Goal getGoal() {
        return realmGet$goal();
    }

    public void setGoal(Goal goal) {
        realmSet$goal(goal);
    }
}
