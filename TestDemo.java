import java.io.File;
import java.util.List;
import java.util.Map;

public class TestDemo {
    public static void main(String[] args) {
        Car c = new Car();
        c.viewAllCars();
        User u = new User();
        /*List i = u.getAllUserEmail(new File("E:\\fahim\\CarZone\\files\\user_list.txt"));
        System.out.println(i);
        List j = u.getAllUserName(new File("E:\\fahim\\CarZone\\files\\user_list.txt"));
        System.out.println(j);
        boolean is = u.checkUserExists("fahim@gmail","fahim");
        System.out.println(is);
        Admin a = new Admin();
        a.deleteUser("akib@gmail");*/
        //a.viewAllUsers();
        String userRole = u.signIn("fahim","sdsd");
        System.out.println(userRole);
        //a.deleteUser("fahim@gmail");
    }
}
