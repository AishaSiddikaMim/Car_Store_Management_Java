public class Buyer extends User{
    private int Id;
    private String name;
    private String address;

    public Buyer() {
    }

    public Buyer(String fullname, String username, String email, String password, String userrole, String address) {
        super(fullname,username, email, password,userrole);
        this.address = address;
    }

    public Buyer(int id, String name, String address) {
        Id = id;
        this.name = name;
        this.address = address;
    }


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

    public void orderCar(int id){
        return;
    }
    public void viewCarList(){
        Car car = new Car();
        car.viewAllCars();
    }
    public boolean cancelOrder(){
        return false;
    }

    @Override
    public String toString() {
        return "Customer{}";
    }
}
