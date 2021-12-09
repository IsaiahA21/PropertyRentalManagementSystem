package database.users;

public class Landlord extends RegisteredUser{
    public Landlord(String email, String password) {
        super(email, password, 2);
    }

}
