package UserInterfacePackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;

public class UserInterface {

    private final String prompt;
    private final String wprompt;
    private final Scanner sc;
    private final BufferedReader br;

    private static UserInterface instance = null;

    public static UserInterface Instance(){
        if(instance==null){
            instance = new UserInterface();
        }
        return instance;
    }
    private UserInterface(){
        wprompt = "Enter Wheel Position ";
        prompt ="\nSelect an option: \n(0) Encrypt \n(1) Decrypt \n(anything else) Leave\n\n>> ";
        sc = new Scanner(System.in);
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public int getwPos(int w){
        while(true) {
            System.out.print(wprompt + String.valueOf(w) + " >> ");
            try {
                int sel = sc.nextInt();
                return sel;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Invalid option, enter an integer");
            }

        }
    }

    private String getString(String pr){
        System.out.print(pr);
        String st = null;
        try{
            st = br.readLine();
            if("exit".equals(st)){
                System.exit(0);
            }

        }
        catch(IOException e){
            System.out.println(e);
        }
        return st;
    }

    public String getTeamName(){
        String s = getString("Enter Team Name (or exit) >> ");
        return s;
    }
    public String getPassword(){
        String s = getString("Enter Password  (or exit) >> ");
        return s;
    }
    public int getUserRequest(){
        System.out.print(prompt);
        while(true){
            try{
                int sel = sc.nextInt();
                if((sel==0) || (sel==1)){
                    return sel;
                }
                else{
                    System.out.println("Exiting ");
                    System.exit(0);
                }
            }
            catch(Exception e){
                sc.nextLine();
                System.out.println("Invalid option, enter an integer");
            }
        }
    }

    public int getW1(){
        final int w  = getwPos(1);
        return w;
    }

    public int getW2(){
        final int w = getwPos(2);
        return w;
    }
    public int getW3(){
        final int w = getwPos(3);
        return w;
    }

    public void output(String s){
        System.out.println();
        System.out.println("From UI: "+ "");
        System.out.println(s);
        System.out.println();
    }


}
