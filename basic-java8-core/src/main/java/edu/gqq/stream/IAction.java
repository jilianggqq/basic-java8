package edu.gqq.stream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * interface testing
 *
 * @param <T>
 */
public interface IAction<T extends ActionBase> {
    Consumer<Decision> processAction(Map<String, String> map, T action);
}

/**
 * please look at the return value.
 * 1.if it's a primitive or Object, you can return specific one.
 * 2. if it's a interface which only has one method, please use lambda expression.
 *      (parm1, param2 ...) -> {
 *          return type.
 *      }
 *
 * 3. int a = 1. Object o = new Object(). Action a = x -> {}
 * 4. If return type is interface, that means return a method to be used in the future. This interface will use some features of the
 * parameters which will be passed in the future.
 */
class ActionTest {
    public IAction<Action1> getAction1() {
        return (map, act) -> {
            return decision -> {
                if (map.containsKey(act.getReason())) {
                    System.out.println("That's correct!" + decision.getStatus());
                } else {
                    System.out.println("That's wrong!");
                }
            };
        };
    }

    public static void main(String[] args) {
        ActionTest actionTest = new ActionTest();
        Map<String, String> map = new HashMap<>();
        map.put("Action1", "Action1");
        // should be "That's correct! null"
        // Here is the place of future using.
        // 1. we get returned interfaces.
        // 2. we are using the methods of these interfaces.
        // 3. we pass parameters into these methods of the interfaces.
        actionTest.getAction1().processAction(map, new Action1()).accept(new Decision());
    }
}

