package avion;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    public static void main(String[] args) {
        AirlineModel airline = new AirlineModel();
        String[] options = {"Reservar asientos", "Cancelar reserva", "Comprar asientos", "Consultar reserva", "Consultar compra", "Salir"};
        int choice = 0;
        while (choice != options.length - 1) {
            choice = JOptionPane.showOptionDialog(null, "Bienvenido al menú de la aerolínea", "Aerolínea",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            switch (choice) {
                case 0:
                    // Lógica para reservar asientos
                    String category = JOptionPane.showInputDialog("Ingrese la categoría de asientos (Primera clase, Económica, Ejecutiva)");
                    String customerId = JOptionPane.showInputDialog("Ingrese el ID del cliente");
                    String seatsString = JOptionPane.showInputDialog("Ingrese los números de asientos separados por comas");
                    String[] seatStrings = seatsString.split(",");
                    List<Integer> seats = new ArrayList<>();
                    for (String seatString : seatStrings) {
                        seats.add(Integer.parseInt(seatString.trim()));
                    }
                    airline.reserveSeats(category, customerId, seats);
                    JOptionPane.showMessageDialog(null, "Asientos reservados exitosamente");
                    break;
                case 1:
                    // Lógica para cancelar reserva
                    customerId = JOptionPane.showInputDialog("Ingrese el ID del cliente");
                    airline.cancelReservation(customerId);
                    JOptionPane.showMessageDialog(null, "Reserva cancelada exitosamente");
                    break;
                case 2:
                    // Lógica para comprar asientos
                    category = JOptionPane.showInputDialog("Ingrese la categoría de asientos (Primera clase, Económica, Ejecutiva)");
                    customerId = JOptionPane.showInputDialog("Ingrese el ID del cliente");
                    seatsString = JOptionPane.showInputDialog("Ingrese los números de asientos separados por comas");
                    seatStrings = seatsString.split(",");
                    seats = new ArrayList<>();
                    for (String seatString : seatStrings) {
                        seats.add(Integer.parseInt(seatString.trim()));
                    }
                    airline.purchaseSeats(category, customerId, seats);
                    JOptionPane.showMessageDialog(null, "Asientos comprados exitosamente");
                    break;
                case 3:
                    // Lógica para consultar reserva
                    customerId = JOptionPane.showInputDialog("Ingrese el ID del cliente");
                    List<Integer> reservedSeats = airline.getReservedSeats(customerId);
                    if (reservedSeats != null) {
                        JOptionPane.showMessageDialog(null, "Los siguientes asientos están reservados para el cliente " + customerId + ": " + reservedSeats);
                    } else {
                        JOptionPane.showMessageDialog(null, "El cliente " + customerId + " no tiene reservas");
                    }
                    break;
                case 4:
                    // Lógica para consultar compra
                    customerId = JOptionPane.showInputDialog("Ingrese el ID del cliente");
                    List<Integer> purchasedSeats = airline.getPurchasedSeats(customerId);
                    if (purchasedSeats != null) {
                        JOptionPane.showMessageDialog(null, "Los siguientes asientos fueron comprados por el cliente " + customerId + ": " + purchasedSeats);
                    } else {
                        JOptionPane.showMessageDialog(null, "El cliente " + customerId + " no ha comprado asientos");
                    }
                    break;
                case 5:
                    // Salir del menú
                    JOptionPane.showMessageDialog(null, "Hasta pronto");
                    break;
            }
        }
    }
}
