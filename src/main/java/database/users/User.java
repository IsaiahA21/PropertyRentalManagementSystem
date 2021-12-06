package database.users;


public abstract class User {
    private final int accessLevel;// 0 regular, 1 registered , 2 landlord, 3 manager

    protected User(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getAccessLevel() {
        return accessLevel;
    }
}
