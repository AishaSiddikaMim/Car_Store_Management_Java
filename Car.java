import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Car {
    private String serialNo;
    private String model;
    private String category;
    private double price;
    private int sellerId;
    private int buyerId;

    File carFile = new File("E:\\fahim\\CarZone\\files\\car_list.txt");

    public Car() {
    }

    public Car(String serialNo, String model, String category,double price,int sellerId) {
        this.serialNo = serialNo;
        this.model = model;
        this.category = category;
        this.price=price;
        this.sellerId = sellerId;
    }
    public Car(String serialNo, String model, String category, int sellerId, int buyerId) {
        this.serialNo = serialNo;
        this.model = model;
        this.category = category;
        this.sellerId =sellerId;
        this.buyerId=buyerId;

    }

    //All getter setter method here...

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }
    //Getter setter end here....

    public Map<Integer, Car> getAllCars(){
        String line;
        FileReader fr;
        BufferedReader br = null;
        int id = 1;
        Map<Integer, Car> carList = new HashMap<>();
        Car car;
        try {
            fr = new FileReader(carFile);
            br= new BufferedReader(fr);
            while((line = br.readLine())!= null) {
                String[] words =line.split(String.valueOf(','));
                double price = Double.valueOf(words[3]);
                int seller = Integer.valueOf(words[4]);
                car = new Car(words[0],words[1],words[2],price,seller);
                carList.put(id,car);
                id++;
            }
            br.close();
            fr.close();
        }catch (Exception e){
            e.toString();
        }
        return carList;
    }

    public void viewAllCars(){
        Car car = new Car();
        Map<Integer, Car> allCars = car.getAllCars();

        System.out.format("%-15s%-15s%-20s%-20s%-20s%-15s\n","Car Id","Serial No","Model","Category","Price","Seller Id");

        for (int i=0;i<105;i++)
            System.out.print(".");
        System.out.println();

        for (Map.Entry<Integer, Car> entry : allCars.entrySet()) {
            Integer id = entry.getKey();
            Car c = entry.getValue();
            System.out.format("%-15d%-15s%-20s%-20s%-20s%-15s\n",id,c.getSerialNo(),c.getModel(),c.getCategory(),
                    c.getPrice(),c.getSellerId());
        }
    }

    public List<String> getAllSerialNo(File carFile) {
        String line;
        FileReader fr;
        BufferedReader br = null;
        List<String> carSerialList = new ArrayList<>();
        try {
            fr = new FileReader(carFile);
            br= new BufferedReader(fr);
            while((line = br.readLine())!= null) {
                String[] words =line.split(String.valueOf(','));
                carSerialList.add(words[1]);
            }
            br.close();
            fr.close();
        }catch (Exception e){
            e.toString();
        }
        return carSerialList;
    }

    public int getLastCarId(File file){
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

    public boolean checkCarExists(String carSrial){
        List<String> carSerialList = getAllSerialNo(carFile);
        for(String serial:carSerialList) {
            if (carSrial.compareTo(serial) != 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "Car{}";
    }
}
