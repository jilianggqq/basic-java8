package edu.gqq.stream;

public class ActionBase {
    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getActionID() {
        return actionID;
    }

    public void setActionID(int actionID) {
        this.actionID = actionID;
    }

    private String actionName;
    private int actionID;
}

class Action1 extends ActionBase{
    private String reason = "Action1";

    public String getReason() {
        return reason;
    }
}

class Action2 extends ActionBase{

}