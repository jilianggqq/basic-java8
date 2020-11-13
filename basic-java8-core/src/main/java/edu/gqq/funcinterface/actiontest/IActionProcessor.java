package edu.gqq.funcinterface.actiontest;

import edu.gqq.funcinterface.actiontest.Decision.DecisionStatus;
import java.security.cert.CertPathValidatorException.BasicReason;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * interface testing action processor. It returns a Decision Consumer. (map, action) -> Consumer<Decision>
 */
public interface IActionProcessor<T extends ActionBase> {

    Consumer<Decision> getProcessActionConsumer(Map<String, ActionBase> map, T action);
}

/**
 * please look at the return value.
 *
 * 1.if it's a primitive or Object, you can return specific one.
 *
 * 2. if it's a interface which only has one method, please use lambda expression.
 *
 * (parm1, param2 ...) -> {return type. }
 *
 * 3. int a = 1. Object o = new Object(). Action a = x -> {}
 *
 * 4. If return type is interface, that means return a method to be used in the future. This interface will
 * use some features of the parameters which will be passed in the future.
 */
class ActionTest {

    private static Logger logger = LoggerFactory.getLogger(ActionTest.class);

    // This is a connection between decision, map and action.
    // like map contains action id. decision reason == action reason. Then we can output matched.
    public IActionProcessor<BlockAction> getBlockActionProcessor() {
        return (map, act) ->
            decision -> {
                if (DecisionStatus.Disabled.equals(decision.getStatus())) {
                    logger.debug("decision Status is disabled");
                    return;
                }
                if (map.containsKey(act.getActionID() + "")
                    && decision.getReason() != null
                    && decision.getReason().toString().equals(act.getBlockReason())) {
                    logger.debug(String.format("block action is taking effect. ID:%s, reason: %s",
                        act.getActionID(), act.getBlockReason()));
                } else {
                    logger.debug(String.format("block action %s is NOT taking effect", act.getActionID()));
                }
            };
    }

    public IActionProcessor<SendSMSAction> getSendSMSActionProcessor() {
        return (map, act) ->
            decision -> {
                if (DecisionStatus.Disabled.equals(decision.getStatus())) {
                    logger.debug("decision Status is disabled");
                    return;
                }
                if (map.containsKey(act.getActionID() + "")
                    && decision.getReason() != null
                    && decision.getReason().toString().equals(act.getSmsMsg())) {
                    logger.debug(String.format("block action is taking effect. ID:%s, reason: %s",
                        act.getActionID(), act.getSmsMsg()));
                } else {
                    logger.debug(String.format("block action %s is NOT taking effect", act.getActionID()));
                }
            };
    }

    public static void main(String[] args) {
        ActionTest actionTest = new ActionTest();
        Map<String, ActionBase> map = new HashMap<>();
        BlockAction validAction = new BlockAction(12135, "validAction", "EXPIRED");
        BlockAction validAction2 = new BlockAction(12136, "validAction2", "NOT_YET_VALID");
        BlockAction validAction3 = new BlockAction(12137, "validAction3", "INVALID_SIGNATURE");
        map.put(validAction.getActionID() + "", validAction);
        map.put(validAction2.getActionID() + "", validAction2);
        map.put(validAction3.getActionID() + "", validAction3);

        Decision enabledDecision = new Decision(DecisionStatus.Enabled, BasicReason.EXPIRED);
        Decision enabledDecision2 = new Decision(DecisionStatus.Enabled, BasicReason.INVALID_SIGNATURE);
        Decision disableDecision = new Decision(DecisionStatus.Disabled, BasicReason.EXPIRED);

        IActionProcessor<BlockAction> blockActionProcessor = actionTest.getBlockActionProcessor();
        Consumer<Decision> decisionConsumer = blockActionProcessor.getProcessActionConsumer(map, validAction);
        decisionConsumer.accept(enabledDecision);
        decisionConsumer.accept(enabledDecision2);
        decisionConsumer.accept(disableDecision);

        System.out.println();
        actionTest.getBlockActionProcessor().getProcessActionConsumer(map, validAction3).accept(enabledDecision2);
        actionTest.getBlockActionProcessor().getProcessActionConsumer(map, validAction3).accept(disableDecision);

        logger.info("---------------------- test SMS info ----------------------");
        IActionProcessor<SendSMSAction> sendSMSActionProcessor = actionTest.getSendSMSActionProcessor();
        Map<String, ActionBase> smsMap = new HashMap<>();
        SendSMSAction sendSMSAction1 = new SendSMSAction(20000, "20000", "100001", "No");
        SendSMSAction sendSMSAction2 = new SendSMSAction(20001, "20001", "100001", "ALGORITHM_CONSTRAINED");

        smsMap.put(sendSMSAction1.getActionID() + "", sendSMSAction1);
        smsMap.put(sendSMSAction2.getActionID() + "", sendSMSAction2);
        Decision finalDecision = new Decision(DecisionStatus.Enabled, BasicReason.ALGORITHM_CONSTRAINED);
        sendSMSActionProcessor.getProcessActionConsumer(smsMap, sendSMSAction1).accept(finalDecision);
        sendSMSActionProcessor.getProcessActionConsumer(smsMap, sendSMSAction2).accept(finalDecision);
    }
}

