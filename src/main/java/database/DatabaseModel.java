package database;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import database.properties.Properties;
import database.users.Landlord;
import database.users.User;

public class DatabaseModel {
    private Properties properties;
    private User user;
    private static int id;



    private static MongoClient mongoClient;
    private static DB database;
    private static DBCollection userCollection;
    private static DBCollection propertiesCollection;
    public DatabaseModel(){
        //
        id = 0;


        connectToDatabase();




        User test =  new Landlord("jeff@gmail.com","jeff robertson","abc123");
        System.out.println(test.getAccessLevel());
    }

    void connectToDatabase(){
        mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://jacob_artuso:123pass123@prms.vrama.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"));
        database = mongoClient.getDB("PRMS");
        userCollection = database.getCollection("userCollection");
        /*ConnectionString connectionString = new ConnectionString("mongodb+srv://jacob_artuso:<password>@prms.vrama.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("test");*/
    }

    void addProperty(String landlord, int numBedrooms, int numBathrooms, boolean furnished, String propertyType, double price) {

        testCol.insertOne(convertProperty(new Property(id++, landlord, numBedrooms, numBathrooms, furnished, propertyType, price)));
    }

    public static Document convertProperty(Property prop){
        return new Document("ID", new ObjectId()).append("STATUS", prop.getStatus()).append("LANDLORD",prop.getLandlord()).append("PAYEDFOR", prop.isPayedFor()).append("OUTSTANDINGFEE", prop.getOutstandingFee()).append("NUMBEDROOMS", prop.getNumBedrooms()).append("NUMBATHROOMS", prop.getNumBathrooms()).append("FURNISHED",prop.isFurnished()).append("PROPERTYTPYE", prop.getPropertyType()).append("PRICE", prop.getPrice());

    }


}
