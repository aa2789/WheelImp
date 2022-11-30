package P2PInterfacePackage;

//
// @author Bill Phillips
//
//
public class AuthorizationInterfaceStubBase 
{
   
   public boolean authenticate
        (String teamName, String password) 
   {
      if (!"Testteam".equals(teamName))  
                             return false;
      final boolean t =   
              "Testpass".equals(password);
      return t;
   }
        
}

