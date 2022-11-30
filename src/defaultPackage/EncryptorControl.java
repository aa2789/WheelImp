package defaultPackage;

import EncryptorSubsystem.EncryptorFacade;
import FileInterfacePackage.BlackFileInterface;
import FileInterfacePackage.RedFileInterface;
import P2PInterfacePackage.AuditInterfaceFactory;
import P2PInterfacePackage.AuditInterfaceStubBase;
import UserInterfacePackage.UserInterface;

import java.util.ArrayList;

public class EncryptorControl
{
    EncryptorFacade eFac;
    RedFileInterface dfi;
    BlackFileInterface efi;
    UserInterface eUI;
    AuditInterfaceStubBase aISB;
    String tn;

    public EncryptorControl(AuditInterfaceStubBase aISB, String tn){
         eFac = EncryptorFacade.Instance();
         dfi = new RedFileInterface();
         efi = new BlackFileInterface();
         eUI = UserInterface.Instance();
         this.aISB= aISB;
         this.tn =tn;

    }


    private void encrypt(){
        dfi.openForRead("MyInFile.txt");
        efi.openForWrite("MyOutFile.dat");
        char c = dfi.readInputFile();
        while (dfi.EOF() == false)
        {
           // System.out.print(c);
           // System.out.print(" ");
            int outc = dfi.convert(c);
           // System.out.print(outc);
            int oute = eFac.encrypt(outc);
          //  System.out.print(" ");
            efi.writeToFile(oute);
          //  System.out.println(oute);
            c = dfi.readInputFile();
        }
        final int w1 = eFac.getWheel1Pos();
        final int w2 = eFac.getWheel2Pos();
        final int w3 = eFac.getWheel3Pos();
        String s = String.valueOf(w1)+ " "+ String.valueOf(w2)+ " "+String.valueOf(w3);
        dfi.closeInFile();
        efi.closeOutFile();
        aISB.SendEOK(tn," Wheel positions -> "+s, false);
        eUI.output("Encryption was successful");

    }

    private void decrypt(){
        efi.openForRead("MyOutFile.dat");
        final int w1 = eUI.getW1();
        eFac.setWheel1Pos(w1);
        final int w2 = eUI.getW2();
        eFac.setWheel2Pos(w2);
        final int w3 = eUI.getW3();
        eFac.setWheel3Pos(w3);
        int ine = efi.readFromFile();
        while (efi.EOF() == false)
        {
            dfi.addToBuf(ine);
            ine = efi.readFromFile();
        }
        efi.closeInFile();

        dfi.openForWrite("MyOutFile.txt");

        while (dfi.emptyBuf() == false)
        {
            int id = dfi.removeFromBuf();
            id = eFac.decrypt(id);
            dfi.addToRevBuf(id);

        }

        while (dfi.emptyRevBuf() == false)
        {
            int id = dfi.removeFromRevBuf();
           char  c = dfi.unConvert(id);
            dfi.writeToFile(c);
        }

        dfi.closeOutFile();
        aISB.SendDOK(tn,"",false);
        eUI.output("Decryption was successful");




    }

    public void runEncryptor(){
        while(true){
            int sel = eUI.getUserRequest();
            if(sel==0)encrypt();
            else decrypt();
        }
    }

}
