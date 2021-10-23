package edu.gqq.funcinterface.actiontest;


import edu.gqq.funcinterface.actiontest.Decision.DecisionStatus;
import java.security.cert.CertPathValidatorException.BasicReason;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicActionProcessor<T extends ActionBase> implements IActionProcessor<T> {

    private static Logger logger = LoggerFactory.getLogger(BasicActionProcessor.class);

    @Override
    public Consumer<Decision> getProcessActionConsumer(Map<String, ActionBase> map, T action) {
        return (Decision x) -> {
            if (x == null || action == null || MapUtils.isEmpty(map)) {
                logger.debug("decision, action or map is null");
                return;
            }

            if (DecisionStatus.Disabled.equals(x.getStatus())) {
                logger.debug("decision status is disabled");
                return;
            }

            logger.debug(map.toString());
            logger.debug(action.toString());
            logger.debug(x.toString());
        };
    }

    public static void main(String[] args) {
        BasicActionProcessor<ActionBase> basicAction = new BasicActionProcessor<>();
        ActionBase action1 = new ActionBase(101, "basicAction1");
        ActionBase action2 = new ActionBase(102, "basicAction2");
        Map<String, ActionBase> map = new HashMap<>();
        map.put("101", action1);
        map.put("102", action2);

        basicAction.getProcessActionConsumer(null, action1).
            accept(new Decision(DecisionStatus.Disabled, BasicReason.EXPIRED));
        basicAction.getProcessActionConsumer(map, null).
            accept(new Decision(DecisionStatus.Disabled, BasicReason.EXPIRED));
        basicAction.getProcessActionConsumer(map, null).
            accept(null);
        basicAction.getProcessActionConsumer(map, action1).
            accept(new Decision(DecisionStatus.Disabled, BasicReason.EXPIRED));
        basicAction.getProcessActionConsumer(map, action1).
            accept(new Decision(DecisionStatus.Enabled, BasicReason.EXPIRED));
    }
}
