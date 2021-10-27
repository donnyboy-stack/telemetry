package Data.Type;

import java.util.ArrayList;
import java.util.List;

public class ErrorType extends DataType{

    protected List<Integer[]> errorData;

    protected Integer[] cur;

    public ErrorType(String name, String units) {
        super(name, units);

        errorData = new ArrayList<Integer[]>();
    }

    public void putValue(double value){
        if (data.size() >= MAX_DATA_POINTS) // We only store a certain amount of data (however much fits on graph)
            data.remove(0);

        String bitString = Long.toBinaryString(Double.doubleToRawLongBits(value));
        Integer[] bits = new Integer[64];
        for (int i = 0; i < bitString.length(); i++) {
            bits[i] = Integer.parseInt(Character.toString(bitString.charAt(i)));
        }

        cur = bits;

        errorData.add(bits);
    }


}
