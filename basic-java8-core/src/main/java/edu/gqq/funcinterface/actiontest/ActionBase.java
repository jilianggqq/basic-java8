package edu.gqq.funcinterface.actiontest;

import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;

public class ActionBase {

    private String actionName;
    private int actionID;

    public ActionBase(int actId, String name) {
        this.actionID = actId;
        this.actionName = name;
    }

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

    @Override
    public String toString() {
        return "ActionBase{" +
            "actionName='" + actionName + '\'' +
            ", actionID=" + actionID +
            '}';
    }
}

class BlockAction extends ActionBase {

    private final String blockReason;

    public BlockAction(int actId, String name, String reason) {
        super(actId, name);
        this.blockReason = StringUtils.isBlank(reason) ? "UNKNOWN" : reason;
    }

    public String getBlockReason() {
        return blockReason;
    }
}

class SendSMSAction extends ActionBase {

    private final String smsMsg;
    private final BigInteger accountNumber;

    public String getSmsMsg() {
        return smsMsg;
    }

    public BigInteger getAccountNumber() {
        return accountNumber;
    }

    public SendSMSAction(int actId, String name, String accNo, String smsMsg) {
        super(actId, name);
        this.smsMsg = smsMsg;
        this.accountNumber = new BigInteger(accNo);
    }
}