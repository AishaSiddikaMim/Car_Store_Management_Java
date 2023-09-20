import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User{
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String userRole;

    private File userFile = new File("E:\\fahim\\CarZone\\files\\user_list.txt");
    private File newUserFile = new File("E:\\fahim\\CarZone\\files\\new_user_list.txt");

    public User() {
    }

    public User(String fullname,String username, String email, String password, String userRole) {
        this.fullname=fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    //All kinds of getter setter......start

    public User getUser(){
        return this;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

    //All kinds of getter setter end....here

    public void signUp(User user){
        boolean checkUser;
        File f1,f2;
        FileWriter fw;
        BufferedWriter bw;

        checkUser = checkUserExists(user.getEmail(),user.getPassword());

        if(checkUser)
            System.out.println("Account Already exists..!");
        else {
            try {

                if (getLastUserId(newUserFile) != 0) {
                    fw = new FileWriter(newUserFile, true);
                    bw = new BufferedWriter(fw);
                    bw.append((getLastUserId(newUserFile) + 1) + "," + toString());
                } else {
                    fw = new FileWriter(newUserFile);
                    bw = new BufferedWriter(fw);
                    bw.write((getLastUserId(newUserFile) + 1) + "," + toString());
                }
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.toString();
            }
        }
    }

    public String signIn(String username, String password){
        Map<Integer, User> userList = getAllUsers();
        for (Map.Entry<Integer, User> entry : userList.entrySet()) {
            Integer id = entry.getKey();
            User user = entry.getValue();
            if ((username.compareTo(user.getUsername())==0) && password.compareTo(user.getPassword())==0) {
                return user.getUserRole();
            }else {
                return null;
            }
        }
        return null;
    }

    public void signOut(){
        System.exit(1);
    }

    public Map<Integer, User> getAllUsers(){
        String line;
        FileReader fr;
        BufferedReader br = null;
        User user;
        int id = 1;
        Map<Integer, User> userList = new HashMap<>();
        try {
            fr = new FileReader(userFile);
            br= new BufferedReader(fr);
            while((line = br.readLine())!= null) {
                String[] words =line.split(String.valueOf(','));
                user = new User(words[0],words[1],words[2],words[3],words[4]);
                userList.put(id,user);
                id++;
            }
            br.close();
            fr.close();
        }catch (Exception e){
            e.toString();
        }
        return userList;
    }

    public Map<Integer, User> getNewUsers(){
        String line;
        FileReader fr;
        BufferedReader br = null;
        User user = new User();
        int id = 1;
        Map<Integer, User> userList = new HashMap<>();
        try {
            fr = new FileReader(newUserFile);
            br= new BufferedReader(fr);
            while((line = br.readLine())!= null) {
                String[] words =line.split(String.valueOf(','));
                user = new User(words[0],words[1],words[2],words[3],words[4]);
                userList.put(id,user);
                id++;
            }
            br.close();
            fr.close();
        }catch (Exception e){
            e.toString();
        }
        return userList;
    }


    public List<String> getAllUserEmail(File file){
        String line;
        FileReader fr;
        BufferedReader br = null;
        List<String> userEmailList = new ArrayList<>();
        try {
            fr = new FileReader(file);
            br= new BufferedReader(fr);
            while((line = br.readLine())!= null) {
                String[] words =line.split(String.valueOf(','));
                userEmailList.add(words[2]);
            }
            br.close();
            fr.close();
        }catch (Exception e){
            e.toString();
        }
        return userEmailList;
    }

    public List<String> getAllUserName(File file){
        String line;
        FileReader fr;
        BufferedReader br = null;
        List<String> userNameList = new ArrayList<>();
        try {
            fr = new FileReader(file);
            br= new BufferedReader(fr);
            while((line = br.readLine())!= null) {
                String[] words =line.split(String.valueOf(','));
                userNameList.add(words[1]);
            }
            br.close();
            fr.close();
        }catch (Exception e){
            e.toString();
        }
        return userNameList;
    }

    public int getLastUserId(File file){
        String line;
        FileReader fr;
        BufferedReader br = null;
        int id=0;
        try {
            fr = new FileReader(file);
            br= new BufferedReader(fr);
            while((line = br.readLine())!= null) {
                id++;
            }
            br.close();
            fr.close();
        }catch (Exception e){
            e.toString();
        }
        System.out.println(id);
        return id;
    }

    public boolean checkUserExists(String emailNew,String usernameNew){
        Map<Integer, User> userList = getAllUsers();
        for (Map.Entry<Integer, User> entry : userList.entrySet()) {
            Integer id = entry.getKey();
            User user = entry.getValue();
            System.out.println(user.getEmail());
            if(emailNew.compareTo(user.getEmail())==0 && usernameNew.compareTo(user.getUsername())==0)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return ""+ fullname+ " "+ username +","+ email +","+ password +","+ userRole;
    }
}
