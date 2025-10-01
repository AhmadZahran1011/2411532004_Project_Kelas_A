package TugasPBO;

class Vehicle {
    String brand;
    double year;

    Vehicle(String brand, double year) {
        this.brand = brand;
        this.year = year;
    }
}

class Car extends Vehicle {
    int numberOfDoors;

    Car(String brand, double year, int numberOfDoors) {
        super(brand, year); // Memanggil constructor Vehicle
        this.numberOfDoors = numberOfDoors;
    }
}

class ElectricCar extends Car {
    double batteryCapacity;

    ElectricCar(String brand, double year, int numberOfDoors, double batteryCapacity) {
        super(brand, year, numberOfDoors);
        this.batteryCapacity = batteryCapacity;
    }

    void chargeBattery() {
        System.out.println("Battery charging...");
    }
}
public class latihan {

	public class Main {
	    public static void main(String[] args) {
	        ElectricCar tesla = new ElectricCar("Tesla", 2023, 4, 75.0);

	        System.out.println("Brand: " + tesla.brand);
	        System.out.println("Year: " + tesla.year);
	        System.out.println("Number of Doors: " + tesla.numberOfDoors);
	        System.out.println("Battery Capacity: " + tesla.batteryCapacity + " kWh");

	        tesla.chargeBattery();
	    }
	    
	}
}
	


