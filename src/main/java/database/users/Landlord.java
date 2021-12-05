package database.users;

public class Landlord extends RegisteredUser{
    public Landlord(String email, String name, String password) {
        super(email, name, password, 2);
    }

}
