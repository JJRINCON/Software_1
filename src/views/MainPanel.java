package views;

import models.MyProcess;
import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPanel extends MyGridPanel {

    private ProcessesPanel processesPanel;
    private ReportsPanel reportsPanel;
    private  MyGridPanel startSimulationPanel;
    private ActionListener listener;

    public MainPanel(ActionListener listener) {
        this.listener = listener;
        setBackground(Color.decode("#FDFEFE"));
        processesPanel = new ProcessesPanel(listener);
        addComponent(processesPanel, 0, 2, 1, 1);
        initStartSimulationPanel(listener);
    }

    public void initStartSimulationPanel(ActionListener listener){
        hideReportsPanel();
        startSimulationPanel = new MyGridPanel();
        JButton startSimulationBtn = createBtn("Iniciar Simulacion", Color.decode("#2980B9"),
                                                listener, Events.INIT.toString());
        startSimulationPanel.setBackground(Color.decode("#FDFEFE"));
        JButton exitBtn = createBtn("Salir", Color.RED, listener, Events.EXIT.toString());
        startSimulationPanel.addComponent(exitBtn, 10, 2, 2, 0.005);
        startSimulationPanel.addComponent(new JLabel(" "), 1, 3, 12, 0.3);
        startSimulationPanel.addComponent(startSimulationBtn, 4, 4, 6, 0.05);
        startSimulationPanel.addComponent(new JLabel(" "), 1, 5, 12, 0.4);
        addComponent(startSimulationPanel, 2, 2, 9, 1);
        updateUI();
    }

    private void hideReportsPanel(){
        if(reportsPanel != null){
            this.remove(reportsPanel);
        }
    }

    public void initReportsPanel(ArrayList<MyProcess> readyProcess, ArrayList<MyProcess> dispatchedProcess,
                                 ArrayList<MyProcess> executingProcess, ArrayList<MyProcess> toLockedProcess,
                                 ArrayList<MyProcess> lockedProcess, ArrayList<MyProcess> wakeUpProcess,
                                 ArrayList<MyProcess> expiredProcess, ArrayList<MyProcess> terminatedProcess){
        this.remove(startSimulationPanel);
        reportsPanel = new ReportsPanel(readyProcess, dispatchedProcess, executingProcess, toLockedProcess,
                                        lockedProcess, wakeUpProcess, expiredProcess, terminatedProcess, listener);
        addComponent(reportsPanel, 1,2,11,0.8);
        updateUI();
    }

    private JButton createBtn(String txt, Color color, ActionListener listener, String command){
        JButton btn = new JButton(txt);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.addActionListener(listener);
        btn.setActionCommand(command);
        return btn;
    }

    public void updateProcesses(Object[][] info){
        processesPanel.updateProcesses(info);
    }
}
