import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Admin extends User{

    File userFile = new File("E:\\fahim\\CarZone\\files\\user_list.txt");
    File newUserFile = new File("E:\\fahim\\CarZone\\files\\new_user_list.txt");

    public void viewAllUsers(){
        Map<Integer,User> allUsers = getAllUsers();
        System.out.format("%-20s%-20s%-20s%-20s\n","User id","Full Name","Email","Role");

        for (int i=0;i<70;i++)
            System.out.print(".");
        System.out.println();

        for (Map.Entry<Integer, User> entry : allUsers.entrySet()) {
            Integer id = entry.getKey();
            User user = entry.getValue();
            System.out.format("%-20d%-20s%-20s%-20s\n",id,user.getFullname(),user.getEmail(),user.getUserRole());
        }
    }

    public void viewNewUsers(){
        Map<Integer,User> newUsers = getNewUsers();

        System.out.format("%-20s%-20s%-20s%-20s\n","User id","Full Name","Email","Role");

        for (int i=0;i<70;i++)
            System.out.print(".");
        System.out.println();

        for (Map.Entry<Integer, User> entry : newUsers.entrySet()) {
            Integer id = entry.getKey();
            User user = entry.getValue();
            System.out.format("%-20d%-20s%-20s%-20s\n",id,user.getFullname(),user.getEmail(),user.getUserRole());
        }
    }

    public void approveUser(){
        String userInfo;
        File f1,f2;
        FileReader fr;
        BufferedReader br;
        FileWriter fw;
        BufferedWriter bw;
        String[] words=null;

        Map<Integer,User> newUsers = getNewUsers();

        int lastId = getLastUserId(userFile);

        try {
            fr = new FileReader(newUserFile);
            br = new BufferedReader(fr);
            fw = new FileWriter(userFile,true);
            bw = new BufferedWriter(fw);
            String line;
            while((line = br.readLine()) != null) {
                String trimmedLine = line.trim();
                words = trimmedLine.split(String.valueOf(','));
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            fr.close();
            bw.close();
            fw.close();
        }catch (IOException e){
            e.toString();
        }

    }
    public void deleteUser(String emailToDelete) {
        String userEmail =null;
        File userFile,tempFile;
        FileWriter fw;
        BufferedWriter bw;
        FileReader fr;
        BufferedReader br;
        String[] words = null;

        userFile = new File("E:\\fahim\\CarZone\\files\\user_list.txt");
        tempFile = new File("E:\\fahim\\CarZone\\files\\temp.txt");

        Map<Integer, User> allUsers = getAllUsers();

        for (Map.Entry<Integer, User> entry : allUsers.entrySet()) {
            Integer id = entry.getKey();
            User user = entry.getValue();
            if (user.getEmail().equals(emailToDelete)) {
                userEmail = user.getEmail();
            }
        }

        try {
            fr = new FileReader(userFile);
            br = new BufferedReader(fr);
            fw = new FileWriter(tempFile);
            bw = new BufferedWriter(fw);
            String line;
            while((line = br.readLine()) != null) {
                String trimmedLine = line.trim();
                words = trimmedLine.split(String.valueOf(','));
                if(words[2].compareTo(emailToDelete)==0)
                    continue;
                else{
                    bw.write(line);
                    bw.newLine();
                }
            }
            bw.close();
            fr.close();
            bw.close();
            fw.close();
        }catch (IOException e){
            e.toString();
        }

        userFile.delete();

        userFile = new File("E:\\fahim\\CarZone\\files\\user_list.txt");

        try {
            fr = new FileReader(tempFile);
            br = new BufferedReader(fr);
            fw = new FileWriter(userFile,true);
            bw = new BufferedWriter(fw);
            String line;
            while((line = br.readLine()) != null) {
                String trimmedLine = line.trim();
                words = trimmedLine.split(String.valueOf(','));
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            fr.close();
            bw.close();
            fw.close();
        }catch (IOException e){
            e.toString();
        }

        tempFile.delete();

    }
}

