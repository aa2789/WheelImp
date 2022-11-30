package defaultPackage;

import P2PInterfacePackage.AuditInterfaceFactory;
import P2PInterfacePackage.AuditInterfaceStubBase;
import P2PInterfacePackage.AuthorizationInterfaceFactory;
import P2PInterfacePackage.AuthorizationInterfaceStubBase;
import UserInterfacePackage.UserInterface;

public class AuthControl{
    AuthorizationInterfaceFactory aF;
    AuditInterfaceFactory atF;
    UserInterface uI;
    AuthorizationInterfaceStubBase aIB;
    AuditInterfaceStubBase aISB;
    EncryptorControl ec;

    public  AuthControl() {
        aF = new AuthorizationInterfaceFactory();
        atF = new AuditInterfaceFactory();
        uI = UserInterface.Instance();
        aIB = aF.create(0);
        aISB =  atF.create(0);

}

public void authAndGo(){
    String tn = uI.getTeamName();
    String pw = uI.getPassword();
    boolean b = aIB.authenticate(tn,pw);
    if(b){
        aISB.SendLOK(tn, pw, false);
        ec = new EncryptorControl(aISB,tn);
        ec.runEncryptor();


    }
    else{
        aISB.SendLNOK(tn, pw,false);
        uI.output("Authentication Failed.");
        System.exit(0);
    }
}
}
