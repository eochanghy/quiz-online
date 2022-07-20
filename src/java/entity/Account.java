/*
 * Copyright(C) 2021, Nguyen Thanh Dat.
 * J3.L.P0001
 * Quiz Online
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-07-07      1.0                 DatNT           First Implement
 * 2021-07-13      2.0                 DatNT           Fix Comment 
 */
package entity;

/**
 * Contains properties, constructor, getter, setter of Account object.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class Account {

    private int id;
    private String name;
    private String password;
    private String type;
    private String email;
    private int age;
    private String className;

    /**
     * Parameterless constructor used to initialize a
     * <code>entity.Account</code> object.
     */
    public Account() {
    }

    /**
     * Constructor has parameters used to initialize an object with information
     * passed in Parameters that need to be passed include id, name, password
     * type, email.
     *
     * @param id the id of Account. It is an int number.
     * @param name the user name of Account. It is a
     * <code>java.lang.String</code> object
     * @param password the password of Account. It is a
     * <code>java.lang.String</code> object
     * @param type the type of Account. It is a <code>java.lang.String</code>
     * object
     * @param email the email of Account. It is a <code>java.lang.String</code>
     * object
     */
    
    
    public Account(int id, String name, String password, String type, String email, int age, String className) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
        this.email = email;
        this.age = age;
        this.className = className;
    }

    public Account(int id, String name, String password, String type, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Constructor has parameters used to initialize an object with information
     * passed in Parameters that need to be passed include name, password type,
     * email.
     *
     * @param name the user name of Account. It is a
     * <code>java.lang.String</code> object
     * @param password the password of Account. It is a
     * <code>java.lang.String</code> object
     * @param type the type of Account. It is a <code>java.lang.String</code>
     * object
     * @param email the email of Account. It is a <code>java.lang.String</code>
     * object
     */
    public Account(String name, String password, String type, String email) {
        this.name = name;
        this.password = password;
        this.type = type;
        this.email = email;
    }

    /**
     * Get value from id attribute
     *
     * @return id of object
     */
    public int getId() {
        return id;
    }

    /**
     * Set value for id attribute
     *
     * @param id the id of Account. It is an int number.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get value from name attribute
     *
     * @return name of object
     */
    public String getName() {
        return name;
    }

    /**
     * Set value for name attribute
     *
     * @param name the name of Account. It is a <code>java.lang.String</code>
     * object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get value from password attribute
     *
     * @return password of object
     */
    public String getPassword() {
        return password;
    }
    /**
     * Set value for password attribute
     *
     * @param password  the password of Account. It is a <code>java.lang.String</code>
     * object.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get value from type attribute
     *
     * @return type of object
     */
    public String getType() {
        return type;
    }
    /**
     * Set value for type attribute
     *
     * @param type  the type of Account. It is a <code>java.lang.String</code>
     * object.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get value from email attribute
     *
     * @return email of object
     */
    public String getEmail() {
        return email;
    }
    /**
     * Set value for email attribute
     *
     * @param email  the email of Account. It is a <code>java.lang.String</code>
     * object.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", name=" + name + ", password=" + password + ", type=" + type + ", email=" + email + '}';
    }
    
}
