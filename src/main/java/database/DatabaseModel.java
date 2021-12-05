package database;
import static com.mongodb.client.model.Filters.eq;

import database.properties.Properties;
import database.users.Landlord;
import database.users.User;

public class DatabaseModel {
    Properties properties;
    User user;
    public DatabaseModel(){
        User test =  new Landlord("jeff@gmail.com","jeff robertson","abc123");
        System.out.println(test.getAccessLevel());
    }
}
