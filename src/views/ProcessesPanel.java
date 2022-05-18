package views;

import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProcessesPanel extends JPanel {

    private static final String TITLE = "Lista de Procesos";
    private static final String TXT_ADD_PROCESS_BTN = "Agregar Proceso";
    private static final String[] COLUMNS = {"Nombre", "Tiempo", "Bloqueo"};
    private TablePanel processTable;

    public ProcessesPanel(ActionListener listener) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        setBackground(Color.decode("#FDFEFE"));
        setLayout(new BorderLayout());
        initComponents(listener);
    }

    private void initComponents(ActionListener listener){
        addTitle();
        addProcesses();
        initAddProcessBtn(listener);
    }

    public void addTitle(){
        JLabel titleLb = new JLabel(TITLE);
        titleLb.setFont(new Font("Arial", Font.BOLD, 20));
        titleLb.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLb, BorderLayout.NORTH);
    }

    public void addProcesses(){
        processTable = new TablePanel();
        Object[][] info = {};
        processTable.createTable(info, COLUMNS);
        add(processTable, BorderLayout.CENTER);
    }

    public void initAddProcessBtn(ActionListener listener){
        JButton addProcessBtn = new JButton(TXT_ADD_PROCESS_BTN);
        addProcessBtn.setBackground(Color.decode("#2980B9"));
        addProcessBtn.setForeground(Color.WHITE);
        addProcessBtn.setFont(new Font("Arial", Font.BOLD, 16));
        addProcessBtn.setPreferredSize(new Dimension(500, 40));
        addProcessBtn.addActionListener(listener);
        addProcessBtn.setActionCommand(Events.ADD.toString());
        add(addProcessBtn, BorderLayout.SOUTH);
    }

    public void updateProcesses(Object[][] info){
        processTable.setData(info, COLUMNS);
    }
}
