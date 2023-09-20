import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seller extends User{
    private int Id;
    private String name;
    private String address;
    private String fullname;

    File carFile = new File("E:\\fahim\\CarZone\\files\\car_list.txt");
    File orderFile = new File("E:\\fahim\\CarZone\\files\\order_list.txt");
    File tempFile = new File("E:\\fahim\\CarZone\\files\\temp.txt");

    public Seller() {
    }

    public Seller(String fullname, String username, String email, String password, String userrole, String address) {
        super(fullname,username, email, password,userrole);
        this.address = address;
    }
    public Seller(int id, String name1, String address) {
        Id = id;
        this.name = name1;
        this.address = address;
    }

    //All getter setter method ....
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //All getter setter methods ends here...

    public Map<Integer, Car> getAllOrderList(){
        int id=1;
        String line;
        FileReader fr;
        BufferedReader br = null;
        Car car = new Car();
        Map<Integer, Car> orderList = new HashMap<>();
        try {
            fr = new FileReader(orderFile);
            br= new BufferedReader(fr);
            while((line = br.readLine())!= null) {
                String[] words =line.split(String.valueOf(','));
                double price = Double.valueOf(words[3]);
                int seller = Integer.valueOf(words[4]);
                car = new Car(words[0],words[1],words[2],price,seller);
                orderList.put(id,car);
                id++;
            }
            br.close();
            fr.close();
        }catch (Exception e){
            e.toString();
        }
        return orderList;
    }

    public void addNewCar(Car car){
        boolean checkCar;
        FileWriter fw;
        BufferedWriter bw;

        checkCar = car.checkCarExists(car.getSerialNo());

        if(checkCar)
            System.out.println("Car Already exists..!");
        else {
            try {
                if (car.getLastCarId(carFile)!= 0) {
                    fw = new FileWriter(carFile, true);
                    bw = new BufferedWriter(fw);
                    bw.append((car.getSerialNo()) + "," + car);
                } else {
                    fw = new FileWriter(carFile);
                    bw = new BufferedWriter(fw);
                    bw.write((getLastUserId(carFile) + 1) + "," + car);
                }
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.toString();
            }
        }
    }

    public void removeCar(String serialNo){
        String carSerialNo =null;
        FileWriter fw;
        BufferedWriter bw;
        FileReader fr;
        BufferedReader br;
        String[] words = null;
        Car car = new Car();


        Map<Integer, Car> allCars = car.getAllCars();

        for (Map.Entry<Integer, Car> entry : allCars.entrySet()) {
            Integer id = entry.getKey();
            Car c = entry.getValue();
            if (c.getSerialNo().equals(carFile)) {
                carSerialNo = c.getSerialNo();
            }
        }

        try {
            fr = new FileReader(carFile);
            br = new BufferedReader(fr);
            fw = new FileWriter(tempFile);
            bw = new BufferedWriter(fw);
            String line;
            while((line = br.readLine()) != null) {
                String trimmedLine = line.trim();
                words = trimmedLine.split(String.valueOf(','));
                if(words[0].compareTo(serialNo)==0)
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

        carFile.delete();

        carFile = new File("E:\\fahim\\CarZone\\files\\car_list.txt");

        try {
            fr = new FileReader(tempFile);
            br = new BufferedReader(fr);
            fw = new FileWriter(carFile,true);
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

    public void approveOrder(){
        Map<Integer,Car> orderList = getAllOrderList();
        List<String> carSerialNoList=new ArrayList<>();
        FileWriter fw;
        BufferedWriter bw;
        FileReader fr;
        BufferedReader br;
        String[] words=null;
        String line;

        try{
            fr = new FileReader(orderFile);
            br = new BufferedReader(fr);
            while((line = br.readLine()) != null) {
                String trimmedLine = line.trim();
                words = trimmedLine.split(String.valueOf(','));
                carSerialNoList.add(words[0]);
            }
            br.close();
            fr.close();
        }catch (IOException e){
            e.toString();
        }

        for (Map.Entry<Integer, Car> entry : orderList.entrySet()) {
            Integer id = entry.getKey();
            Car c = entry.getValue();
            for(String serial:carSerialNoList)
                if (c.getSerialNo().equals(serial)) {
                    removeCar(serial);
                }
        }

    }

    @Override
    public String toString() {
        return "Seller{}";
    }
}
