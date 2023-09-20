import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HelperTest {


    String fullname;
    String username;
    String email;
    String password;
    String cfPassword;
    String userRole;

    Scanner sc = new Scanner(System.in);

    public void takeSignupInfo(){
        System.out.println("\n1.Sign up as Admin");
        System.out.println("2.Sign up as Seller");
        System.out.println("3.Sign up as Buyer");
        System.out.print("Please choose and option: ");
        int opt = sc.nextInt();
        if(opt==1)
            userRole = "Admin";
        else if(opt==2)
            userRole="Seller";
        else if(opt==3)
            userRole = "Buyer";
        sc.nextLine();
        System.out.println("Please enter necessary information below: ");
        System.out.print("Full name: ");
        fullname = sc.nextLine();
        System.out.print("Username: ");
        username = sc.nextLine();
        System.out.print("Email: ");
        email = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
        System.out.print("Confirm Password: ");
        cfPassword = sc.nextLine();
        /*try {
            FileWriter fw = new FileWriter("E:\\fahim\\CarZone\\files\\new_user_list.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(username);
            bw.newLine();
        }catch (IOException e){
            e.toString();
        }*/

        int match = password.compareTo(cfPassword);

        if (match!=0) {
            System.out.println("Password mismatch Please reenter : ");
            System.out.print("Password: ");
            password = sc.nextLine();
            System.out.print("Confirm Password: ");
            cfPassword = sc.nextLine();
            match = password.compareTo(cfPassword);
        }
    }

    public void passSignupInfo(){
        User user = new User(fullname,username,email,password,userRole);
        user.setUserRole(userRole);
        if(user!=null)
            user.signUp(user);
    }

    public void takeSigninInfo(){
        System.out.println("Please enter necessary information below: ");
        System.out.print("Username: ");
        username = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
    }

    public void passSigninInfo(){
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        String userRole = u.signIn(username,password);
        if(userRole!=null){
            switch (userRole){
                case "Admin":
                    adminAction(u);
                    break;
                case "Buyer":
                    BuyerAction(u);
                    break;
                case "Seller":
                    sellerAction(u);
                    break;
                default:
                    break;
            }
        }else{
            System.out.println("\nFailed to sign in please try again.");
        }
    }
    public void adminAction(User u){
        Admin admin = new Admin();
        while(true){
            System.out.println("\n1.User List");
            System.out.println("2.New User List");
            System.out.println("3.Approve User");
            System.out.println("4.Remove User");
            System.out.println("0.Exit");
            System.out.print("Please Select an option: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 0:
                    break;
                case 1:
                    admin.viewAllUsers();
                    break;
                case 2:
                    admin.viewNewUsers();
                    break;
                case 3:
                    admin.approveUser();
                    break;
                case 4:
                    System.out.println("Enter user email u want to delete: ");
                    String email = sc.nextLine();
                    admin.deleteUser(email);
                    break;
                default:
                    break;
            }
            System.out.print("\nEnter 1 to continue 0 to exit: ");
            int ch1 = sc.nextInt();
            if(ch1!=1)
                break;
        }
    }

    public void BuyerAction(User u){
        Car car = new Car();
        Buyer buyer = new Buyer();
        while(true) {
            System.out.println("\n1.Car List");
            System.out.println("2.Order Car");
            System.out.println("3.Cancel Order");
            System.out.println("0.Exit");
            System.out.print("Please Select an option: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 0:
                    break;
                case 1:
                    car.getAllCars();
                    break;
                case 2:
                    System.out.println("Enter car id you want to purchase: ");
                    int id = sc.nextInt();
                    buyer.orderCar(id);
                    break;
                case 3:
                    buyer.cancelOrder();
                    break;
                default:
                    break;
            }
            System.out.print("Enter 1 to continue 0 to exit.");
            int ch1 = sc.nextInt();
            if (ch1 != 1)
                break;
        }
    }
    public void sellerAction(User u){
        Car car = new Car();
        Seller seller =new Seller();
        Buyer buyer = new Buyer();
        while(true) {
            System.out.println("\n1.Car List");
            System.out.println("2.Order List");
            System.out.println("3.Remove Car");
            System.out.println("4.Approve Order");
            System.out.println("5.Add new car");
            System.out.println("0.Exit");
            System.out.print("Please Select an option: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 0:
                    break;
                case 1:
                    car.getAllCars();
                    break;
                case 2:
                    seller.getAllOrderList();
                    break;
                case 3:
                    System.out.print("Enter car serial no you want to remove: ");
                    String id = sc.nextLine();
                    seller.removeCar(id);
                    break;
                case 4:
                    seller.approveOrder();
                    break;
                case 5:
                    System.out.print("Enter car information below: ");
                    System.out.println("Car Serial no: ");
                    String serialNo = sc.nextLine();
                    System.out.println("Model: ");
                    String model = sc.nextLine();
                    System.out.println("Category: ");
                    String category = sc.nextLine();
                    System.out.println("Price: ");
                    double price = sc.nextDouble();
                    Car newCar = new Car(serialNo, model, category, price, seller.getId());
                    seller.addNewCar(newCar);
                    break;
                default:
                    break;
            }
            System.out.print("Enter 1 to continue 0 to exit.");
            int ch1 = sc.nextInt();
            if (ch1 != 1)
                break;
        }
    }
}
