
package P2PInterfacePackage;

/**
 *
 * @author billp
 */
public class P2PAuthorizationInterface extends 
                    AuthorizationInterfaceStubBase
{
   @Override
   public boolean authenticate
        (String teamName, String password) 
   {
      if (!"TestteamX".equals(teamName))  
                             return false;
      final boolean t =   
              "TestPassX".equals(password);
      return t;
   }   
}
