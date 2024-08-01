import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import Core.Customer.Customer;
import Core.Customer.CustomerCrud;
import Core.Event.Event;
import Core.Event.EventCrud;
import Core.Menu.Menu;
import Core.Menu.MenuCrud;
import DB.ClearConsole;
import DB.Conexion;

public class MainUser {
    private static Connection connection;
    private static CustomerCrud customerCrud;
    private static EventCrud eventCrud;
    private static MenuCrud menuCrud;
    private static Scanner scanner = new Scanner(System.in);
    private static int currentCustomerId = -1;

    public static void main(String[] args) throws SQLException {
        System.out.println("Iniciando aplicación...");

        Conexion conexion = new Conexion();
        connection = conexion.conectarDB();
        if (connection == null) {
            System.out.println("Error al conectar a la base de datos.");
            return;
        }

        customerCrud = new CustomerCrud(connection);
        eventCrud = new EventCrud(connection);
        menuCrud = new MenuCrud(connection);

        while (true) {
            ClearConsole.clear();
            System.out.println("1. Iniciar sesión.");
            System.out.println("2. Registrarse.");
            System.out.println("3. Salir de la aplicación.");
            int option = getValidOption(3);

            switch (option) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    ClearConsole.clear();
                    System.out.println("Vuelve pronto.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private static int getValidOption(int maxOption) {
        while (true) {
            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                if (option >= 1 && option <= maxOption) {
                    return option;
                } else {
                    System.out.println("Selecciona una opción válida.");
                    System.out.println("1. Iniciar sesión.");
                    System.out.println("2. Registrarse.");
                    System.out.println("3. Salir de la aplicación.");
                }
            } else {
                System.out.println("Selecciona una opción válida.");
                System.out.println("1. Iniciar sesión.");
                System.out.println("2. Registrarse.");
                System.out.println("3. Salir de la aplicación.");
                scanner.next(); // Consumir la entrada no válida
            }
        }
    }

    private static void login() throws SQLException {
        ClearConsole.clear();
        System.out.println("Ingrese su correo electrónico:");
        String email = scanner.nextLine();

        ClearConsole.clear();
        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        if (customerCrud.emailAndPasswordExists(email, password)) {
            Customer customer = customerCrud.getAllCustomers().stream()
                    .filter(c -> c.getEmail().equals(email) && c.getPassword().equals(password))
                    .findFirst()
                    .orElse(null);

            if (customer != null) {
                currentCustomerId = customer.getId();
                System.out.println("Inicio de sesión exitoso.");
                mainMenu();
            } else {
                System.out.println("Correo electrónico o contraseña incorrectos.");
            }
        } else {
            System.out.println("Correo electrónico o contraseña incorrectos.");
        }
    }

    private static void register() throws SQLException {
        System.out.println("Ingrese su correo electrónico:");
        String email = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();
        System.out.println("Ingrese su número de teléfono:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Ingrese su nombre:");
        String firstName = scanner.nextLine();
        System.out.println("Ingrese su segundo nombre:");
        String middleName = scanner.nextLine();
        System.out.println("Ingrese sus apellidos:");
        String lastName = scanner.nextLine();
        System.out.println("Ingrese su colonia:");
        String neighborhood = scanner.nextLine();
        System.out.println("Ingrese su calle:");
        String street = scanner.nextLine();
        System.out.println("Ingrese su número de casa:");
        int streetNumber = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        if (customerCrud.emailOrPhoneExists(email, phoneNumber)) {
            System.out.println("El correo electrónico o el número de teléfono ya están registrados.");
            return;
        }

        Customer customer = new Customer(email, password, phoneNumber, firstName, middleName, lastName, neighborhood,
                street, streetNumber);
        customerCrud.addCustomer(customer);
        System.out.println("Registro exitoso. Ahora puede iniciar sesión.");
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("Menú.");
            System.out.println("1. Reservar nuevo evento.");
            System.out.println("2. Consultar eventos reservados.");
            System.out.println("3. Cerrar sesión.");
            int option = getValidOption(3);

            switch (option) {
                case 1:
                    reserveEvent();
                    break;
                case 2:
                    viewReservedEvents();
                    break;
                case 3:
                    currentCustomerId = -1;
                    System.out.println("Sesión cerrada.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private static void reserveEvent() {
        System.out.println("Ingrese la fecha del evento (YYYY-MM-DD):");
        String eventDate = scanner.nextLine();

        if (!isValidDate(eventDate)) {
            System.out.println("Fecha no válida. Por favor, ingrese una fecha en formato YYYY-MM-DD.");
            return;
        }

        if (!eventCrud.isDateAvailable(eventDate)) {
            System.out.println("La fecha ya está reservada. Por favor, elija otra fecha.");
            return;
        }

        List<Menu> menus = menuCrud.getAllMenus();
        System.out.println("Seleccione un menú de la lista:");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getName());
        }
        int menuIndex = getValidOption(menus.size()) - 1;
        Menu selectedMenu = menus.get(menuIndex);

        Event event = new Event(0, currentCustomerId, 0, selectedMenu.getId(), eventDate);
        eventCrud.createEvent(event);
        System.out.println("Evento reservado exitosamente.");
    }

    private static boolean isValidDate(String date) {
        // Implementa la lógica para validar la fecha en formato YYYY-MM-DD
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    private static void viewReservedEvents() {
        List<Event> events = eventCrud.getEventsByCustomerId(currentCustomerId);
        if (events.isEmpty()) {
            System.out.println("No tiene eventos reservados.");
            System.out.println("1. Regresar al menú principal.");
            getValidOption(1); // Solo opción para regresar
            return;
        }

        System.out.println("Seleccione un evento para actualizar o eliminar, o 0 para regresar al menú principal.");
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            Menu menu = menuCrud.getMenuById(event.getMenuId());
            System.out.println((i + 1) + ". Fecha: " + event.getDate() + ", Menú: " + menu.getName());
        }

        int eventOption = getValidOption(events.size() + 1) - 1;

        if (eventOption != -1) {
            Event selectedEvent = events.get(eventOption);
            eventOptionsMenu(selectedEvent);
        }
    }

    private static void eventOptionsMenu(Event event) {
        while (true) {
            System.out.println("1. Actualizar evento.");
            System.out.println("2. Eliminar evento.");
            System.out.println("3. Regresar al menú anterior.");
            int option = getValidOption(3);

            switch (option) {
                case 1:
                    updateEvent(event);
                    return;
                case 2:
                    deleteEvent(event);
                    return;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private static void updateEvent(Event event) {
        System.out.println("Ingrese la nueva fecha del evento (YYYY-MM-DD) o presione Enter para mantener la actual:");
        String eventDate = scanner.nextLine();
        if (!eventDate.isEmpty() && isValidDate(eventDate)) {
            event.setDate(eventDate);
        } else if (!eventDate.isEmpty()) {
            System.out.println("Fecha no válida. Manteniendo la fecha actual.");
        }

        List<Menu> menus = menuCrud.getAllMenus();
        System.out.println("Seleccione un nuevo menú de la lista o presione Enter para mantener el actual:");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getName());
        }
        String menuOption = scanner.nextLine();
        if (!menuOption.isEmpty()) {
            int menuIndex = Integer.parseInt(menuOption) - 1;
            if (menuIndex >= 0 && menuIndex < menus.size()) {
                Menu selectedMenu = menus.get(menuIndex);
                event.setMenuId(selectedMenu.getId());
            } else {
                System.out.println("Menú no válido. Manteniendo el menú actual.");
            }
        }

        eventCrud.updateEvent(event);
        System.out.println("Evento actualizado exitosamente.");
    }

    private static void deleteEvent(Event event) {
        eventCrud.deleteEvent(event.getId());
        System.out.println("Evento eliminado exitosamente.");
    }
}