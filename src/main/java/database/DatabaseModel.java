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

import java.sql.Array;
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

        user = login("jacob@gmail.com","password12");
        if(user.getAccessLevel() != 0) {
            RegisteredUser temp = (RegisteredUser) user;
            System.out.println("logged in as " + temp.getName());
        }



        //System.out.println(test.getAccessLevel());
    }

    void connectToDatabase(){
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);

        String uri = "mongodb+srv://jacob_artuso:123pass123@prms.vrama.mongodb.net/prms?retryWrites=true&w=majority";
        try {
            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("PRMS");
            propertiesCollection = database.getCollection("properties");
            userCollection = database.getCollection("users");
            /*addProperty("dave@gmail.com",2,4,false,"condo",20000);

            addProperty("dave@gmail.com",3,2,true,"condo",20000);

            addProperty("dave@gmail.com",2,4,false,"townhouse",29000);*/
            addUser(1,"jacob@gmail.com","Jacob Art","password12");
            addUser(2,"dave@gmail.com","Dave Duart","pass12");
            addUser(3,"stuart@gmail.com","Stuart Lee","managerpass");


        }catch (MongoException me){
            System.err.println("database error");
            System.exit(-1);
        }


    }


    public void addProperty(String landlord, int numBedrooms, int numBathrooms, boolean furnished, String propertyType, double price) {

        propertiesCollection.insertOne(convertProperty(new Property(new ObjectId().toString(), landlord, numBedrooms, numBathrooms, furnished, propertyType, price)));
    }

    public static Document convertProperty(Property prop){
        return new Document("_id", new ObjectId()).append("STATUS", prop.getStatus()).append("LANDLORD",prop.getLandlord()).append("PAYEDFOR", prop.isPayedFor()).append("OUTSTANDINGFEE", prop.getOutstandingFee()).append("NUMBEDROOMS", prop.getNumBedrooms()).append("NUMBATHROOMS", prop.getNumBathrooms()).append("FURNISHED",prop.isFurnished()).append("PROPERTYTPYE", prop.getPropertyType()).append("PRICE", prop.getPrice());

    }

    public void setUser(String email, String password) {
        this.user = login(email,password);
    }

    private User login(String email, String password){
        //verify with database
        Document query = new Document("$and", Arrays.asList(
                new Document("EMAIL", email),
                new Document("PASSWORD", password)
        ));
        Document user = userCollection.find(query).first();

        if (user != null) {
            switch ((int) user.get("TYPE")) {
                case 1 -> {
                    return new RegisteredRenter((String) user.get("EMAIL"), (String) user.get("NAME"), (String) user.get("PASSWORD"));
                }
                case 2 -> {
                    return new Landlord((String) user.get("EMAIL"), (String) user.get("NAME"), (String) user.get("PASSWORD"));
                }
                case 3 -> {
                    return new Manager((String) user.get("EMAIL"), (String) user.get("NAME"), (String) user.get("PASSWORD"));
                }
            }
        }
        return null;
    }

    boolean userExists(String email){
        Document query = new Document("EMAIL", email);
        return userCollection.find(query).first() != null;
    }

    public void unregistedLogin(){
        user = new RegularRenter();
    }

    public ArrayList<Property> search(ArrayList<String> criteriaStrings){
        List<Document> criteria = new ArrayList<>();
        //criteria.add(new Document(""))
        Document search = new Document("$and",criteria);
        return new ArrayList<>();
    }

    public void addUser(int accessLevel, String email, String name, String password){
        if(userExists(email)){
            return;
        }
        switch (accessLevel){
            case 0:
                return;
            case 1:
                user = new RegisteredRenter(email,name,password);
                break;
            case 2:
                user = new Landlord(email,name,password);
                break;
            case 3:
                user = new Manager(email,name,password);
        }
        userCollection.insertOne(convertUser((RegisteredUser) user));
    }

    public static Document convertUser(RegisteredUser user){
        return new Document("_id", new ObjectId()).append("TYPE",user.getAccessLevel()).append("NAME",user.getName()).append("EMAIL", user.getEmail()).append("PASSWORD",user.getPassword());
    }









}
