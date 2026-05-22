package hashing;

import java.awt.print.PrinterGraphics;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HashTable {

    public static void main(String[] args) {
        Map<PhoneNumber, String> contact = new HashMap<>();
        contact.put(new PhoneNumber( 234,  555,  3232), "Folk");
        System.out.println(contact.get(new PhoneNumber( 234,  555,  3232)));

        Comparator<Integer> naturalOrder =
                (i, j) ->  i < j ? -1 : (i == j ? 0 : 1);

        System.out.println(naturalOrder.compare(42,42));
    }
}


/**
 * Util class for practise
 * Note : without the override hashCode method the HashTable look up
 * for the Object will mostly return null
 */
final class PhoneNumber {
    private final short areaCode, prefix, lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNumber = rangeCheck(lineNumber, 9999, "line num");

    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof  PhoneNumber)) return false;
        PhoneNumber pn = (PhoneNumber) obj;

        return  pn.lineNumber == lineNumber &&
                pn.prefix == prefix &&
                pn.areaCode == areaCode;
    }

    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31  * result + Short.hashCode(lineNumber);
        result = 31 * result + Short.hashCode(prefix);
        return result;
    }

    private short rangeCheck(int val, int max, String arg) {
        if(val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ": " +  val);
        }
        return (short) val;

    }

}
