import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        int choice;
        boolean keepGoinOn=true;
        HelperTest helperTest = new HelperTest();
        User user = null;
        while(keepGoinOn) {
            System.out.println("1.Sign Up");
            System.out.println("2.Sign in");
            System.out.print("Please choose and option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    helperTest.takeSignupInfo();
                    helperTest.passSignupInfo();
                    break;
                case 2:
                    helperTest.takeSigninInfo();
                    helperTest.passSigninInfo();
                    break;
                default:
                    break;
            }

            System.out.print("Press 1 to continue or 0 to sign out: ");
            int ch= sc.nextInt();

            switch (ch) {
                case 1:
                    keepGoinOn=true;
                    break;
                case 0:
                    keepGoinOn=false;
                    user.signOut();
                    break;
                default:
                    break;
            }
        }
    }
}
