package database.properties;

public class Property {
    private int propertyID;
    private int status;//0 not shown, 1 payed for, 2 suspended?
    private String landlord;
    private boolean payedFor;
    private double outstandingFee;
    private int numBedrooms;
    private int numBathrooms;
    private boolean furnished;
    private String propertyType;
    private double price;


    //create a new property to add
    public Property(int propertyID, String landlord, int numBedrooms, int numBathrooms, boolean furnished, String propertyType, double price) {
        this.propertyID = propertyID;
        this.landlord = landlord;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.furnished = furnished;
        this.propertyType = propertyType;
        this.price = price;
        status = 0;
        payedFor = false;
        outstandingFee = 100; //should change how this works maybe stored in database


    }

    public int getNumBedrooms() {
        return numBedrooms;
    }

    public void setNumBedrooms(int numBedrooms) {
        this.numBedrooms = numBedrooms;
    }

    public int getNumBathrooms() {
        return numBathrooms;
    }

    public void setNumBathrooms(int numBathrooms) {
        this.numBathrooms = numBathrooms;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

}
