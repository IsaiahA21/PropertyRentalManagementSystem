package database.users;

public class Manager extends RegisteredUser{
    public Manager(String email, String name, String password) {
        super(email, name, password, 3);
    }
}
