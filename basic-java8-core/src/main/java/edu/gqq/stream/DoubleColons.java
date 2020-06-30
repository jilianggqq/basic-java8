package edu.gqq.stream;

public class DoubleColons {
    public static void main(String[] args) {
        Finder f = WithStaticMethod::contains;
        System.out.println(f.find("abc", "ab")); //0

        // f2 is equal to f3
        //Instance method on parameter objects
        Finder f2 = String::indexOf;
        Finder f3 = (s1, s2) -> s1.indexOf(s2);

        // Instance method
        StringConverter sc = new StringConverter();
        Deserializer deserializer = sc::convertToInt;

        // Constructor
        //The create() method of this interface matches the signature of one of the constructors in the String class.
        // Therefore this constructor can be used as a lambda. Here is an example of how that looks:
        Factory factory = String::new;
    }
}

interface Finder {
    public int find(String s1, String s2);
}

/**
 * Since the parameters of the Finder.find() and WithStaticMethod.contains() methods match,
 * it is possible to create a lambda expression that implements Finder.find() and references the WithStaticMethod.contains() method.
 */
class WithStaticMethod {
    public static int contains(String s1, String s2) {
        return s1.indexOf(s2);
    }
}

interface Deserializer {
    public int deserialize(String v1);
}

/**
 * The convertToInt() method has the same signature as the deserialize() method of the Deserializer deserialize() method.
 * Because of that, we can create an instance of StringConverter and reference its convertToInt() method from a Java lambda expression,
 */
class StringConverter {
    public int convertToInt(String v1) {
        return Integer.valueOf(v1);
    }
}

interface Factory {
    public String create(char[] val);
}