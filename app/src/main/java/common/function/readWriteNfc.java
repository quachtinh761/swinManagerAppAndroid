package common.function;

import android.nfc.NfcAdapter;
import android.nfc.tech.MifareClassic;
import java.io.IOException;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

/**
 * Use for Read and write NFC Tag in Pig Project
 * It only using for 1k NFC tag
 * ---------------------------------------------------------------------------------
 * List of method in this class:
 *      CheckNfcDevice: return 0 if device hasn't NFC Adapter, 1. have nfc but don't enable, 2. have nfc and enable
 *
 * ----------------------------------------------------------------------------------
 * Created by Nguyen Van Thi on 27/08/2016.
 */
public class readWriteNfc extends AppCompatActivity{
    //use in CheckNfcDevice
    private static NfcAdapter nfcAdapter;
    private byte[] passA = {(byte)0x12,(byte)0x34,(byte)0x56,(byte)0x12,(byte)0x34,(byte)0x56};
    private byte[] passB = {(byte)0x65,(byte)0x43,(byte)0x21,(byte)0x65,(byte)0x43,(byte)0x21};
    //use in read
    boolean sucess;
    final int numOfSector = 16;
    final int numOfBlockInSector = 4;
    final int BlockSize = 16;
    public byte[][][] readbuf = new byte[numOfSector][numOfBlockInSector][BlockSize];
    MifareClassic tag;
    int numSectorUsed = 4;
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

    public void ReadNfcData() {
        /**
         * This method use for read NFC Tag for all case
         */
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
                break;
        }
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

    //----------------Private Method-----------------------

    //this method read normal Nfc Tag
    private void ReadNfc(byte[] passA) {
        try {
            tag.connect();//connect to tag
            for (int i = 0; i < numSectorUsed; i++) {
                if (tag.authenticateSectorWithKeyA(i, passA)) { //check pass A to read
                    for (int j = 0; j < numOfBlockInSector - 1; j++) {//only read 3 block in secctor
                        int index = i * numOfBlockInSector + j;
                        readbuf[i][j] = tag.readBlock(index);
                        if (!tag.isConnected()) {
                            sucess = false;
                            return;
                        }
                    }
                }
                else {
                    byte[] keyAll = {(byte)0x12,(byte)0x34,(byte)0x56,(byte)0x12,(byte)0x34,(byte)0x56,(byte)0xff,(byte)0x07,(byte)0x80,(byte)0x69,(byte)0x65,(byte)0x43,(byte)0x21,(byte)0x65,(byte)0x43,(byte)0x21};
                    tag.writeBlock(i*numOfBlockInSector + 3, keyAll);

                }
            }
            sucess = true;//check if read sucess
        } catch (IOException e) {
            e.printStackTrace();
            sucess = false;
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
    private void ChangeKey(){

    }
}
