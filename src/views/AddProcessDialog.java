package views;

import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddProcessDialog extends JDialog {

    private AddProcessPanel addProcessPanel;

    public AddProcessDialog(ActionListener listener, MainFrame mainFrame) {
        setSize(400, 400);
        setModal(true);
        setLayout(new BorderLayout());
        setResizable(false);
        setUndecorated(true);
        addProcessPanel = new AddProcessPanel(listener);
        add(addProcessPanel);
        setLocationRelativeTo(null);
    }

    public String getProcessName(){
        return addProcessPanel.getProcessName();
    }

    public int getProcessTime(){
        return addProcessPanel.getProcessTime();
    }

    public boolean getIsBlocked(){
        return addProcessPanel.getIsBlocked();
    }
}
