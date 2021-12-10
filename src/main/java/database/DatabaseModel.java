package database;

import com.mongodb.MongoException;
import database.properties.Property;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;


import database.properties.*;
import database.users.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseModel {
    private User user;

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> userCollection;
    private MongoCollection<Document> propertiesCollection;


    public DatabaseModel(){
        //


        connectToDatabase();

        makeReport();

        //System.out.println(test.getAccessLevel());
    }

    void connectToDatabase(){
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);

        String uri = "mongodb+srv://jacob_artuso:123pass123@prms.vrama.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
        try {
            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("PRMS");
            propertiesCollection = database.getCollection("properties");
            userCollection = database.getCollection("users");
//            addProperty("isaiTry4@gmail.com",4,2,false,"townhome",400000, "WT");
            /*addUser(1,"jacob@gmail.com","password12");
            addUser(2,"dave@gmail.com","pass12");
            addUser(3,"stuart@gmail.com","managerpass");
            
*/

        }catch (MongoException me){
            System.err.println("database error");
        }


    }

    void addProperty(String landlord, int numBedrooms, int numBathrooms, boolean furnished, String propertyType, double price, String quadrant) {

        propertiesCollection.insertOne(convertProperty(new Property(new ObjectId().toString(), landlord, numBedrooms, numBathrooms, furnished, propertyType, price,quadrant)));
    }

    public static Document convertProperty(Property prop){
        return new Document("_id", new ObjectId()).append("STATUS", prop.getStatus()).append("LANDLORD",prop.getLandlord()).append("PAYEDFOR", prop.isPayedFor()).append("OUTSTANDINGFEE", prop.getOutstandingFee()).append("NUMBEDROOMS", prop.getNumBedrooms()).append("NUMBATHROOMS", prop.getNumBathrooms()).append("FURNISHED",prop.isFurnished()).append("PROPERTYTPYE", prop.getPropertyType()).append("PRICE", prop.getPrice()).append("QUADRANT", prop.getQuadrant());

    }

    public Report makeReport() {
    	return new Report(propertiesCollection);
    }
    public boolean login(String email, String password){
        //verify with database
        Document query = new Document("$and", Arrays.asList(
                new Document("EMAIL", email),
                new Document("PASSWORD", password)
        ));
        Document user = userCollection.find(query).first();

        if (user != null) {
            switch ((int) user.get("TYPE")) {
                case 1 -> {
                    this.user = new RegisteredRenter((String) user.get("EMAIL"), (String) user.get("PASSWORD"));
                    return true;
                }
                case 2 -> {
                    this.user = new Landlord((String) user.get("EMAIL"), (String) user.get("PASSWORD"));
                    return true;
                }
                case 3 -> {
                    this.user = new Manager((String) user.get("EMAIL"), (String) user.get("PASSWORD"));
                    return true;
                }
            }
        }
        return false;
    }

    boolean userExists(String email){
        Document query = new Document("EMAIL", email);
        return userCollection.find(query).first() != null;
    }

    public void unregistedLogin(){
        user = new RegularRenter();
    }

    public ArrayList<Property> search(){
        return new ArrayList<>();
    }

    public boolean addUser(int accessLevel, String email, String password){
        if(userExists(email)){
            return false;
        }
        switch (accessLevel){
            case 0:
                return false;
            case 1:
                user = new RegisteredRenter(email,password);
                break;
            case 2:
                user = new Landlord(email,password);
                break;
            case 3:
                user = new Manager(email,password);
        }
        userCollection.insertOne(convertUser((RegisteredUser) user));
        return true;
    }

    public static Document convertUser(RegisteredUser user){
        return new Document("_id", new ObjectId()).append("TYPE",user.getAccessLevel()).append("EMAIL", user.getEmail()).append("PASSWORD",user.getPassword());
    }








}
