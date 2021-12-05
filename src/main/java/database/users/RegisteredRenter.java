package database.users;

public class RegisteredRenter extends RegisteredUser{

    public RegisteredRenter(String email, String name, String password) {
        super(email, name, password, 1);
    }
}
