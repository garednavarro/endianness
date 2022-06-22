package com.example.converter.model;

import android.widget.RadioButton;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class used to convert decimal number to endianness.
 * The constructor is called in the viewmodel class to create an instance and process
 * the task to get the endianness value of the decimal number.
 *
 * @author Mario Navarro
 * @since 21-06-2022
 * */
public class DecimalNumber {

    private int decimalNumber;
    private List<String> hexadecimal = new ArrayList<>();
    private StringBuilder bigEndian = new StringBuilder();
    private StringBuilder littleEndian = new StringBuilder();
    private final String PREFIX_VALUE = "0x";
    private final String SEPARATOR = " ";

    /**
     * Class constructor.
     */
    public DecimalNumber(int decimalNumber){
        this.decimalNumber = decimalNumber;
    }

    public int getDecimalNumber(){
        return decimalNumber;
    }

    /**
     * Execute the method that perform all the process to transform a decimal number into
     * hex values. This is called in the mainProcess method.
     * */
    private void decimalToHexadecimalValue(){
        convertToHexadecimalValue();
    }

    /**
     * Execute the steps required to convert the decimal number
     * into hex values.
     * */
    private void convertToHexadecimalValue(){

        buildHexadecimalNumber(getRemainderValues());

        if(lengthOfHexValueIsEven()){
            addExtraValue();
        }

        groupHexValuesInPairs();
    }

    private void cleanHexValues(){
        if(!hexadecimal.isEmpty()){
            hexadecimal.clear();
        }
    }

    private List<Integer> getRemainderValues(){

        return extractRemainderValues();
    }

    /**
     * This method is used to get the remainder value of the decimal number, the task is
     * executed while the number is greater than zero. The List of values returned are used to
     * get the corresponding hex value.
     *
     * @return A list of remainder values
     * */
    private List<Integer> extractRemainderValues(){
        int temporalDecimalNumber = getDecimalNumber();

        List<Integer> remainderValues = new ArrayList<>();

        while(temporalDecimalNumber > 0){

            int remainder = temporalDecimalNumber % 16;

            temporalDecimalNumber = temporalDecimalNumber / 16;

            remainderValues.add(remainder);
        }

        return remainderValues;
    }

    /**
     * Method in charge of storing the hex values obtained
     * from the remainders.
     * */
    private void buildHexadecimalNumber(List<Integer> remainderValues){

        for(Integer remainder : remainderValues){
            hexadecimal.add(getItsHexValue(remainder));
        }
    }

    /**
     * Pair the values to prepare the desired format
     * that will be displayed to the user.
     * */
    private void groupHexValuesInPairs(){
        List<String> mergedValues = new ArrayList<>();

        for(int i = hexadecimal.size() - 1; i >= 1; i-=2){

            mergedValues.add(hexadecimal.get(i) + hexadecimal.get(i-1));
        }

        hexadecimal = mergedValues;
    }

    private boolean lengthOfHexValueIsEven(){
        return hexadecimal.size() % 2 == 1;
    }

    private void addExtraValue(){
        hexadecimal.add("0");
    }

    /**
     * Returns a String that is the corresponding hex value of the remainder integer.
     *
     * @param currentRemainder the remainder value to get its hex value
     * @return The correspondig hex value to the remainder.
     * */
    private String getItsHexValue(Integer currentRemainder){

        switch (currentRemainder){
            case 0 : return "0";
            case 1 : return "1";
            case 2 : return "2";
            case 3 : return "3";
            case 4 : return "4";
            case 5 : return "5";
            case 6 : return "6";
            case 7 : return "7";
            case 8 : return "8";
            case 9 : return "9";
            case 10 : return "A";
            case 11 : return "B";
            case 12 : return "C";
            case 13 : return "D";
            case 14 : return "E";
            case 15 : return "F";
            default:
                return "";
        }
    }

    /**
     * Create the big endian value that will be displayed to the user.
     * */
    private void buildBigEndian(){
        for(int i = 0; i < hexadecimal.size(); i++){
            bigEndian.append(PREFIX_VALUE).append(hexadecimal.get(i)).append(SEPARATOR);
        }
    }

    /**
     * Create the little endian value that will be displayed to the user.
     * */
    private void buildLittleEndian(){
        for(int i = hexadecimal.size() - 1; i >= 0; i--){
            littleEndian.append(PREFIX_VALUE).append(hexadecimal.get(i)).append(SEPARATOR);
        }
    }

    private void cleanEndians(){
        if(bigEndian.length() != 0){
            bigEndian = new StringBuilder();
        }

        if(littleEndian.length() != 0){
            littleEndian = new StringBuilder();
        }
    }

    private void cleanUpValues(){
        cleanHexValues();
        cleanEndians();
    }

    @NonNull
    private String getBigEndian(){
        return bigEndian.toString();
    }

    @NonNull
    private String getLittleEndian(){
        return littleEndian.toString();
    }

    /**
     * Returns an String object that represent the endianness value of the decimal number
     * introduced by the user.
     *
     * This is the main method that execute the entire process to convert
     * the number into big or little endian and its called in the modelview class(MainActivity)
     * to send the value to the user.
     *
     * @param bigEndianButton RadioButton givin info to check if user has selected bigendian or not
     * @return the value of the decimal number in big endian or little endian
     * */
    public String mainPorcess(@NonNull RadioButton bigEndianButton){
        cleanUpValues();

        decimalToHexadecimalValue();

        if(bigEndianButton.isChecked()){
            buildBigEndian();
            return getBigEndian();
        } else {
            buildLittleEndian();
            return getLittleEndian();
        }
    }

}
