package common.function;

import android.nfc.NfcAdapter;
import android.nfc.tech.MifareClassic;
import java.io.IOException;
import android.support.v7.app.AppCompatActivity;
import android.nfc.Tag;
import org.json.JSONObject;
import java.lang.String;
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
    Tag taskTag;
    public String ID;
    int numSectorUsed = 3;
    //use in write
    public byte[][][] writebuf = new byte[numOfSector][numOfBlockInSector][BlockSize];

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

    public String ReadNfcData() {
        /**
         * This method use for read NFC Tag for all case
         */
        String readTemp = null;
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
                readTemp = ReadNfc(passA);
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
    public boolean changeKeyAll(){
        try {
            tag.connect();
            for (int i=1;i< numOfSector;i++){
                if (tag.authenticateSectorWithKeyA(i,MifareClassic.KEY_DEFAULT)||tag.authenticateSectorWithKeyB(i,MifareClassic.KEY_DEFAULT)||
                        tag.authenticateSectorWithKeyA(i,MifareClassic.KEY_MIFARE_APPLICATION_DIRECTORY)||tag.authenticateSectorWithKeyB(i,MifareClassic.KEY_MIFARE_APPLICATION_DIRECTORY)||
                        tag.authenticateSectorWithKeyA(i,MifareClassic.KEY_NFC_FORUM)||tag.authenticateSectorWithKeyB(i,MifareClassic.KEY_NFC_FORUM)){
                    byte[] keyAll = {(byte)0x12,(byte)0x34,(byte)0x56,(byte)0x12,(byte)0x34,(byte)0x56,(byte)0xff,(byte)0x07,(byte)0x80,(byte)0x69,(byte)0x65,(byte)0x43,(byte)0x21,(byte)0x65,(byte)0x43,(byte)0x21};
                    tag.writeBlock(i*numOfBlockInSector + 3, keyAll);
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //----------------Private Method-----------------------

    //this method read normal Nfc Tag
    private String ReadNfc(byte[] passA) {
        try {
            tag.connect();
            for (int i = 1; i < numSectorUsed; i++) {
                if (tag.authenticateSectorWithKeyA(i, passA)) { //check pass A to read
                    for (int j = 0; j < numOfBlockInSector - 1; j++) {//only read 3 block in secctor
                        int index = i * numOfBlockInSector + j;
                        readbuf[i][j] = tag.readBlock(index);
                        if (!tag.isConnected()) {
                            readSucess = false;
                            return null;
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
        try {
            tag.connect();
            byte[] temp = new byte[16];
            if (tag.authenticateSectorWithKeyB(0, passB)) temp = tag.readBlock(4); //read id ig tag
            if (temp == writebuf[0][0]) { //check ID before write
                for (int i = 1; i < numSectorUsed; i++) {
                    if (tag.authenticateSectorWithKeyB(i, passB)) {//check pass B to write
                        for (int j = 0; j < numOfBlockInSector - 1; j++) {
                            int index = i * numOfBlockInSector + j;
                            tag.writeBlock(index, writebuf[i][j]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
    private String convertByteToStringArray{
        String temp= "$";
    }
}
