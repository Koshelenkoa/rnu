package as.intel.util;

import java.util.ArrayList;
import java.util.HashMap;

public class AssemblerAbstract{

    protected int _IP; // Instruction Pointer
    protected ArrayList<Object> _bytecode;
    protected HashMap<String, Integer> _labels;
    protected int _binLength;

    protected <T> void _bytecode_add(T x) throws IllegalArgumentException {
        if(!((x instanceof String) || (x instanceof Number))){
            throw new IllegalArgumentException();
        }
        if(x instanceof Number){
            _bytecode.add((Byte) ((Number)x).byteValue());
        }else {
        _bytecode.add(x);
        }
        _IP++;
    }

    public static ArrayList<Object> asUnsigned(ArrayList<?> bytecode) {
        ArrayList<Object> unsignedNums = new ArrayList<>();
        for (Object signedByte : bytecode) {
            if (signedByte instanceof Byte) {
                unsignedNums.add(((Byte) signedByte).intValue() & 0xFF);
            } else {
                unsignedNums.add(signedByte);
            }
        }
        return unsignedNums;
    }

    protected byte[] _byte(int value) {
        return new byte[] { (byte) (value & 0xFF) };
    }

    protected byte[] _word(int value) {
        return new byte[] { (byte) (value & 0xFF), (byte) ((value & 0xFF00) >> 8) };
    }

    ////////////
    // Getters

    public ArrayList<Object> getByteCode() {
        return _bytecode;
    }

    public int getBinLength() {
        return _binLength;
    }


}