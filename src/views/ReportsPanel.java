package views;

import models.MyProcess;
import models.OperatingSystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReportsPanel extends JPanel {

    private static final String[] COLUMNS = {"Nombre", "Tiempo", "Bloqueo"};

    public ReportsPanel(ArrayList<MyProcess> readyProcess, ArrayList<MyProcess> dispatchedProcess,
                        ArrayList<MyProcess> executingProcess, ArrayList<MyProcess> toLockedProcess,
                        ArrayList<MyProcess> lockedProcess, ArrayList<MyProcess> wakeUpProcess,
                        ArrayList<MyProcess> expiredProcess, ArrayList<MyProcess> terminatedProcess) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FDFEFE"));
        JLabel titleLb = new JLabel("REPORTES");
        titleLb.setFont(new Font("Arial", Font.BOLD, 20));
        titleLb.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLb, BorderLayout.NORTH);
        JTabbedPane reports = new JTabbedPane();
        reports.setBackground(Color.decode("#FDFEFE"));
        TablePanel readyTable = new TablePanel(OperatingSystem.processInfo(readyProcess), COLUMNS);
        reports.add("Listos", readyTable);

        TablePanel dispatchedTable = new TablePanel(OperatingSystem.processInfo(dispatchedProcess), COLUMNS);
        reports.add("Despachados", dispatchedTable);

        TablePanel executingTable = new TablePanel(OperatingSystem.processInfo(executingProcess), COLUMNS);
        reports.add("En ejecucion", executingTable);

        TablePanel toLockedTable = new TablePanel(OperatingSystem.processInfo(toLockedProcess), COLUMNS);
        reports.add("A Bloqueados", toLockedTable);

        TablePanel lockedTable = new TablePanel(OperatingSystem.processInfo(lockedProcess), COLUMNS);
        reports.add("Bloqueados", lockedTable);

        TablePanel wakeUpTable = new TablePanel(OperatingSystem.processInfo(wakeUpProcess), COLUMNS);
        reports.add("Despertados", wakeUpTable);

        TablePanel expiredTable = new TablePanel(OperatingSystem.processInfo(expiredProcess), COLUMNS);
        reports.add("Tiempo expirado", expiredTable);

        TablePanel terminatedTable = new TablePanel(OperatingSystem.processInfo(terminatedProcess), COLUMNS);
        reports.add("terminados", terminatedTable);

        add(reports, BorderLayout.CENTER);
    }
}
