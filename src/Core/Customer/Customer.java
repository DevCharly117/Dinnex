package Core.Customer;

public class Customer {
    // Atributos de la clase Customer
    private String email; // Correo electrónico del cliente
    private String password; // Contraseña del cliente
    private String phoneNumber; // Número de teléfono del cliente
    private String firstName; // Nombre del cliente
    private String middleName; // Segundo nombre del cliente
    private String lastName; // Apellido del cliente
    private String neighborhood; // Vecindario del cliente
    private String street; // Calle del cliente
    private int streetNumber; // Número de la calle del cliente
    private int id; // Identificador único del cliente

    // Constructor con todos los parámetros
    public Customer(String email, String password, String phoneNumber, String firstName, String middleName,
            String lastName, String neighborhood, String street, int streetNumber) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.neighborhood = neighborhood;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    // Métodos Getter y Setter para acceder y modificar los atributos

    // Obtiene el identificador único del cliente
    public int getId() {
        return id;
    }

    // Establece el identificador único del cliente
    public void setId(int id) {
        this.id = id;
    }

    // Obtiene el correo electrónico del cliente
    public String getEmail() {
        return email;
    }

    // Establece el correo electrónico del cliente
    public void setEmail(String email) {
        this.email = email;
    }

    // Obtiene la contraseña del cliente
    public String getPassword() {
        return password;
    }

    // Establece la contraseña del cliente
    public void setPassword(String password) {
        this.password = password;
    }

    // Obtiene el número de teléfono del cliente
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Establece el número de teléfono del cliente
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Obtiene el nombre del cliente
    public String getFirstName() {
        return firstName;
    }

    // Establece el nombre del cliente
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Obtiene el segundo nombre del cliente
    public String getMiddleName() {
        return middleName;
    }

    // Establece el segundo nombre del cliente
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    // Obtiene el apellido del cliente
    public String getLastName() {
        return lastName;
    }

    // Establece el apellido del cliente
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Obtiene el vecindario del cliente
    public String getNeighborhood() {
        return neighborhood;
    }

    // Establece el vecindario del cliente
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    // Obtiene la calle del cliente
    public String getStreet() {
        return street;
    }

    // Establece la calle del cliente
    public void setStreet(String street) {
        this.street = street;
    }

    // Obtiene el número de la calle del cliente
    public int getStreetNumber() {
        return streetNumber;
    }

    // Establece el número de la calle del cliente
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
}