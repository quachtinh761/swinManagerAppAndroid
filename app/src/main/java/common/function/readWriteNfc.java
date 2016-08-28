package common.function;

import android.nfc.NfcAdapter;
import android.nfc.tech.MifareClassic;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
/**
 * Use for Read and write NFC Tag in Pig Project
 * It only using for 1k NFC tag
 * ---------------------------------------------------------------------------------
 * List of method in this class:
 *      CheckNfcDevice: return 0 if device hasn't NFC Adapter, 1. have nfc but don't enable, 2. have nfc and enable
 *      String[] cuttingStringToArray(String str): truyền vào str dạng $id#name#dateinport#datevaccine#datesex$ và trả về array[5]
 * ----------------------------------------------------------------------------------
 * Created by Nguyen Van Thi on 27/08/2016.
 */
public class readWriteNfc extends AppCompatActivity{
    //use in CheckNfcDevice
    private static NfcAdapter nfcAdapter;
    private byte[] passA = {(byte)0x12,(byte)0x34,(byte)0x56,(byte)0x12,(byte)0x34,(byte)0x56};
    private byte[] passB = {(byte)0x65,(byte)0x43,(byte)0x21,(byte)0x65,(byte)0x43,(byte)0x21};
    //use in read
    public boolean readSucess, writeSucess;
    final int numOfSector = 16;
    final int numOfBlockInSector = 4;
    final int BlockSize = 16;
    public byte[][][] readbuf = new byte[numOfSector][numOfBlockInSector][BlockSize];
    MifareClassic tag;
    int numSectorUsed = 3;
    //use in write
    public byte[][][] writebuf = new byte[numOfSector][numOfBlockInSector][BlockSize];
    private int numOfOject = 5; //so luong doi tuong can ghi trong the

    //----------------public method--------------------------------

    public int CheckNfcDevice() {
        /**
         * This method use for Check Nfc Adapter in device
         **/
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) return 0;
        else if (!nfcAdapter.isEnabled()) return 1;
        else return 2;
    }

    public String[] ReadNfcData() {
        /**
         * This method use for read NFC Tag for all case
         */
        String[] readTemp = new String[5];
        switch (CheckNfcDevice()) {
            case 0:
                //add method in future
                //method read if don't have Nfc Adapter
                break;
            case 1:
                //method turn on NFC Adapter
                break;
            case 2:
                //Method read normal
                ReadNfc(passA);
                readTemp = convertByteToStringArray();
                break;
        }
        return readTemp;
    }

    public void WriteNfcData(String DataTransfer) {
        /**
         * This method use for write NFC Tag for all case
         */
        switch (CheckNfcDevice()) {
            case 0:
                //add method in future
                //method write if don't have Nfc Adapter
                break;
            case 1:
                //method turn on NFC Adapter
                break;
            case 2:
                //Method write normal
                WriteNfc(passB, DataTransfer);
                break;
        }
    }
    public String[] cuttingStringToArray(String str){
        int m = 1;
        String[] buf= new String[5];
        int[] temp = new int[6];
        temp[0]=0;
        temp[5]=str.length()-1;
        if (str.startsWith("$")&&str.endsWith("$")){
            for (int i=1; i < str.length()-1; i++){
                if(str.charAt(i)=='#'){
                    temp[m] = i;
                    m++;
                }
            }
            if (m != 5) return null;
            for(int i=0;i<5;i++){
                buf[i] = str.substring(temp[i]+1,temp[i+1]);
            }
            return buf;
        }else return null;
    }
    private void changeKeyAll(){
        try {
            tag.connect();
            for (int i=1;i< numSectorUsed;i++){
                if (tag.authenticateSectorWithKeyA(i,MifareClassic.KEY_DEFAULT)||tag.authenticateSectorWithKeyB(i,MifareClassic.KEY_DEFAULT)||
                        tag.authenticateSectorWithKeyA(i,MifareClassic.KEY_MIFARE_APPLICATION_DIRECTORY)||tag.authenticateSectorWithKeyB(i,MifareClassic.KEY_MIFARE_APPLICATION_DIRECTORY)||
                        tag.authenticateSectorWithKeyA(i,MifareClassic.KEY_NFC_FORUM)||tag.authenticateSectorWithKeyB(i,MifareClassic.KEY_NFC_FORUM)){
                    byte[] keyAll = {(byte)0x12,(byte)0x34,(byte)0x56,(byte)0x12,(byte)0x34,(byte)0x56,(byte)0xff,(byte)0x07,(byte)0x80,(byte)0x69,(byte)0x65,(byte)0x43,(byte)0x21,(byte)0x65,(byte)0x43,(byte)0x21};
                    tag.writeBlock(i*numOfBlockInSector + 3, keyAll);
                }
            }
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }


    //----------------Private Method-----------------------

    //this method read normal Nfc Tag
    private void ReadNfc(byte[] passA) {
        changeKeyAll();
        try {
            if (tag.authenticateSectorWithKeyA(0,MifareClassic.KEY_DEFAULT)||tag.authenticateSectorWithKeyB(0,MifareClassic.KEY_DEFAULT)||
                    tag.authenticateSectorWithKeyA(0,MifareClassic.KEY_MIFARE_APPLICATION_DIRECTORY)||tag.authenticateSectorWithKeyB(0,MifareClassic.KEY_MIFARE_APPLICATION_DIRECTORY)||
                    tag.authenticateSectorWithKeyA(0,MifareClassic.KEY_NFC_FORUM)||tag.authenticateSectorWithKeyB(0,MifareClassic.KEY_NFC_FORUM)||
                    tag.authenticateSectorWithKeyA(0,passA)){
                    readbuf[0][0] = tag.readBlock(0);
            }
            for (int i = 1; i < numSectorUsed; i++) {
                if (tag.authenticateSectorWithKeyA(i, passA)) { //check pass A to read
                    for (int j = 0; j < numOfBlockInSector - 1; j++) {//only read 3 block in secctor
                        int index = i * numOfBlockInSector + j;
                        readbuf[i][j] = tag.readBlock(index);
                        if (!tag.isConnected()) {
                            readSucess = false;
                            return;
                        }
                    }
                }
            }
            readSucess = true;//check if read sucess
        } catch (IOException e) {
            e.printStackTrace();
            readSucess = false;
        } finally {
            if (tag != null) {
                try {
                    tag.close(); //end read
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void WriteNfc(byte[] passB, String Data) {
        String[] tempData = new String[5];
        tempData = cuttingStringToArray(Data);
        convertStringArrayToBufWrite(tempData);
        try {
            tag.connect();
            byte[] tempReadIDTag = new byte[16];
            if (tag.authenticateSectorWithKeyA(0, passA)){ //read id ig tag
                tempReadIDTag = tag.readBlock(0);
            }

            if (tempData[0] == convertByteToHexString(tempReadIDTag,4)) { //check ID before write
                for (int i = 1; i < numSectorUsed; i++) {
                    if (tag.authenticateSectorWithKeyB(i, passB)) {//check pass B to write
                        for (int j = 0; j < numOfBlockInSector - 1; j++) {
                            int index = i * numOfBlockInSector + j;
                            tag.writeBlock(index, writebuf[i][j]);
                        }
                    }
                    if (!tag.isConnected()) {
                        writeSucess = false;
                        tag.close();
                        return;
                    }
                }
            }
            else{
                writeSucess = false;
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            writeSucess = false;
        } finally {
            if (tag != null) {
                try {
                    tag.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String convertByteToHexString(byte[] temp, int numOfByteToConvert){//16byte convert
        String buf = null;
        for (int i=0;i<numOfByteToConvert;i++){
            buf += String.format("%02x", temp[i]);
        }
        return buf;
    }
    private String convertByteToString(byte[] temp,int numOfByteToConvert){
        String buf = "";
        for (int i=0; i<numOfByteToConvert;i++){
            if (temp[i]==(byte)0x00){
                break;
            }
            else{
                buf += (char)temp[i];
            }
        }
        return buf;
    }
    private String[] convertByteToStringArray(){
        String[] temp = new String[numOfOject];
        temp[0] = convertByteToHexString(readbuf[0][0],4);
        temp[1] = convertByteToString(readbuf[1][0],16);
        temp[1]+= convertByteToString(readbuf[1][1],16);
        temp[2] = convertByteToString(readbuf[2][0],16);
        temp[3] = convertByteToString(readbuf[2][1],16);
        temp[4]=convertByteToString(readbuf[2][2],16);
        return temp;
    }
    private byte[] convertStringLower16ToByte(String temp){
        byte[] buf = new byte[16];
        int s = temp.length();
        buf = temp.getBytes();
        for (int i=s;i<16;i++){
            buf[i]=0x00;
        }
        return buf;
    }
    private void convertStringArrayToBufWrite(String[] temp){
        int s = temp[1].length();
        if (s<=16){
            writebuf[1][0] = convertStringLower16ToByte(temp[1]);
        }
        else{
            String a = temp[1].substring(0,16);
            String b = temp[1].substring(16,temp[1].length());
            writebuf[1][0] = convertStringLower16ToByte(a);
            writebuf[1][0] = convertStringLower16ToByte(b);
        }
        writebuf[2][0] = convertStringLower16ToByte(temp[2]);
        writebuf[2][1] = convertStringLower16ToByte(temp[3]);
        writebuf[2][2] = convertStringLower16ToByte(temp[4]);
    }

}
