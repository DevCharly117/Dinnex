package Core.Employee;

public class Employee {
    private int id; // Identificador único del empleado
    private String name; // Nombre del empleado
    private int positionId; // Identificador del puesto del empleado

    // Constructor que inicializa todos los atributos
    public Employee(int id, String name, int positionId) {
        this.id = id; // Establece el identificador del empleado
        this.name = name; // Establece el nombre del empleado
        this.positionId = positionId; // Establece el identificador del puesto del empleado
    }

    // Métodos Getter y Setter para acceder y modificar los atributos

    // Obtiene el identificador único del empleado
    public int getId() {
        return id;
    }

    // Establece el identificador único del empleado
    public void setId(int id) {
        this.id = id;
    }

    // Obtiene el nombre del empleado
    public String getName() {
        return name;
    }

    // Establece el nombre del empleado
    public void setName(String name) {
        this.name = name;
    }

    // Obtiene el identificador del puesto del empleado
    public int getPositionId() {
        return positionId;
    }

    // Establece el identificador del puesto del empleado
    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
}