package edu.gqq.funcinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoubleColons {

    private static final Logger logger = LoggerFactory.getLogger(DoubleColons.class);

    public static void main(String[] args) {
        Finder f = WithStaticMethod::contains;
        System.out.println(f.find("abc", "ab")); //0

        // f2 is equal to f3
        //Instance method on parameter objects
        Finder f2 = String::indexOf;
        Finder f3 = (s1, s2) -> s1.indexOf(s2);

        logger.info("----------------------different Interfaces testing----------------------\n");
        // Instance method
        StringConverter sc = new StringConverter();
        IConverter convertor1 = sc::convertToInt;
        int res1 = convertor1.deserialize("1231");
        logger.debug(res1 + "");

        IConverter2 converter2 = sc::convertToInt;
        int res2 = converter2.deserialize("1232");
        logger.debug(res2 + "");

        IConverter4<String> converter4 = sc::convertToInt;
        int res4 = converter4.deserialize("1234");
        logger.debug("res4:" + res4);


        IConverter5<String, Integer> converter5 = sc::convertToInt;
        int res5 = converter5.deserialize("1235");
        logger.debug("res5:" + res5);

        logger.info("----------------------Constructor----------------------\n");
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
 * Since the parameters of the Finder.find() and WithStaticMethod.contains() methods match, it is possible to
 * create a lambda expression that implements Finder.find() and references the WithStaticMethod.contains()
 * method.
 */
class WithStaticMethod {

    public static int contains(String s1, String s2) {
        return s1.indexOf(s2);
    }
}

interface IConverter {

    public int deserialize(String v1);
}

interface IConverter2 {

    // convertToInt(String v1) specifically defined parameters as String.
    // only if T is subclass of String, when calling deserialize method, it actually call convertToInt method.
    // in calling convertToInt method, you can only give the parameters of string type or subclass of
    // string type.
    public <T extends String> int deserialize(T v1);
}

interface IConverter3<T extends String> {

    public int deserialize(T v1);
}

interface IConverter4<T> {

    public int deserialize(T v1);
}

interface IConverter5<T, R> {

    public R deserialize(T v1);
}

/**
 * The convertToInt() method has the same signature as the deserialize() method of the Deserializer
 * deserialize() method. Because of that, we can create an instance of StringConverter and reference its
 * convertToInt() method from a Java lambda expression,
 */
class StringConverter {

    public int convertToInt(String v1) {
        return Integer.valueOf(v1);
    }
}

interface Factory {

    public String create(char[] val);
}