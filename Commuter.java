import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Commuter {
    private String firstName;
    private String lastName;
    private String driverLicenseNumber;
    private List<Ticket> tickets;

    public Commuter(String firstName, String lastName, String driverLicenseNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.driverLicenseNumber = driverLicenseNumber;
        this.tickets = new ArrayList<>();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void viewOutstandingTickets() {
        System.out.println("Outstanding tickets for " + getFullName() + ":");
        for (Ticket ticket : tickets) {
            if (!ticket.isPaid()) {
                System.out.println(ticket.getTicketNumber() + " - " + ticket.getOffenceDescription() + " - $" + ticket.getTotalDue());
            }
        }
    }

    public void payTicket(String ticketNumber) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketNumber().equals(ticketNumber)) {
                if (ticket.isPaid()) {
                    System.out.println("Ticket " + ticketNumber + " has already been paid.");
                } else {
                    ticket.setPaid(true);
                    System.out.println("Ticket " + ticketNumber + " has been paid.");
                    updateTicketFile(ticket);
                }
                return;
            }
        }
        System.out.println("Ticket " + ticketNumber + " not found for " + getFullName() + ".");
    }

    private void updateTicketFile(Ticket ticket) {
        String filename = "ticket.txt";
        String tempFilename = "temp.txt";
        File file = new File(filename);
        File tempFile = new File(tempFilename);
        try {
            Scanner scanner = new Scanner(file);
            FileWriter writer = new FileWriter(tempFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split("\t");
                if (columns[0].equals(ticket.getTicketNumber())) {
                    columns[13] = "Yes";
                    line = String.join("\t", columns);
                }
                writer.write(line + "\n");
            }
            scanner.close();
            writer.close();
            file.delete();
            tempFile.renameTo(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error updating ticket file: " + e.getMessage());
        }
    }

    public static void Commuter_View() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Commuter commuter = new Commuter("John", "Doe", "12345");
        commuter.addTicket(new Ticket("1", "John Doe", "01/01/1990", "123456789", "ABC1234", "01/01/2023", "02/01/2023", "03/01/2023", "123 Main St", "Speeding", 50.00, "Officer Smith", "1234", "Precinct A", false));
        commuter.addTicket(new Ticket("2", "John Doe", "01/01/1990", "123456789", "ABC1234", "01/01/2023", "02/01/2023", "03/01/2023", "456 Elm St", "Careless driving", 75.00, "Officer Johnson", "5678", "Precinct B", false));

        // Create JFrame and JPanel
        JFrame frame = new JFrame("JCF Ticket Management - Commuter View");
        JPanel panel = new JPanel(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Create JTable with ticket data
        DefaultTableModel model = new DefaultTableModel(new String[]{"Ticket Number", "Offender Name", "Date of Birth", "Driver's License Number", "Vehicle Plate#", "Date of Offence", "Payment Due Date", "Traffic Court Date", "Address/Location of Offence", "Offence Description", "Total Due", "Paid"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return Object.class;
            }
        };
        for (Ticket ticket : commuter.getTickets()) {
            model.addRow(new Object[]{ticket.getTicketNumber(), ticket.getOffenderName(), ticket.getOffenderDOB(), ticket.getOffenderDLN(), ticket.getVehiclePlate(), ticket.getDateOfOffence(), ticket.getPaymentDueDate(), ticket.getCourtDate(), ticket.getOffenceLocation(), ticket.getOffenceDescription(), ticket.getTotalDue(), ticket.isPaid() ? "Yes" : "No"});
        }
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Add sorting functionality
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

// Set column widths to fit content
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            int width = (int) table.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table, column.getHeaderValue(), false, false, 0, i).getPreferredSize().getWidth();
            for (int j = 0; j < table.getRowCount(); j++) {
                TableCellRenderer renderer = table.getCellRenderer(j, i);
                Component comp = table.prepareRenderer(renderer, j, i);
                width = Math.max(comp.getPreferredSize().width, width);
            }
            column.setPreferredWidth(119);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);



        // Create Pay button and action listener
        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a ticket to pay.");
                } else {
                    String ticketNumber = (String)table.getValueAt(selectedRow, 0);
                    commuter.payTicket(ticketNumber);
                    table.setValueAt("Yes", selectedRow, 11);
                }
            }
        });
        panel.add(payButton, BorderLayout.SOUTH);

        // Add panel to frame and display
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
