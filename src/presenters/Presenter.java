package presenters;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JOptionPane;

import models.MyProcess;
import models.OperatingSystem;
import models.Queue;
import views.AddProcessDialog;
import views.MainFrame;

public class Presenter implements ActionListener {

	private OperatingSystem operatingSystem;
	private MainFrame mainFrame;
	private AddProcessDialog addProcessDialog;

	public Presenter() {
		operatingSystem = new OperatingSystem();
		initFrame();
	}

	private void initFrame(){
		mainFrame = new MainFrame(this);
		mainFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())){
			case ADD:
				manageAddAction();
				break;
			case ACEPT:
				manageAcceptAction();
				break;
			case CANCEL:
				manageCancelAction();
				break;
			case INIT:
				manageInitAction();
				break;
			case NEW_SIMULATION:
				manageNewSimulationAction();
				break;
			case EXIT:
				System.exit(0);
				break;
		}
	}

	private void manageAddAction() {
		addProcessDialog = new AddProcessDialog(this, mainFrame);
		addProcessDialog.setVisible(true);
	}

	private void manageAcceptAction() {
		try {
			if(operatingSystem.verifyProcessName(addProcessDialog.getProcessName())){
				operatingSystem.addProcess(new MyProcess(addProcessDialog.getProcessName(), addProcessDialog.getProcessTime(),
						addProcessDialog.getIsBlocked()));
				addProcessDialog.dispose();
				mainFrame.updateProcesses(operatingSystem.getProcessInfo());
			}else{
				JOptionPane.showMessageDialog(mainFrame, "Nombre de proceso no disponible", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(mainFrame, "Debe ingresar unicamente numeros", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ne){
			JOptionPane.showMessageDialog(mainFrame, ne.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void manageCancelAction() {
		addProcessDialog.dispose();
	}

	private void manageInitAction() {
		operatingSystem.startSimulation();
		mainFrame.initReportsPanel(operatingSystem.getReadyProccess(), operatingSystem.getProcessDespachados(),
				operatingSystem.getExecuting(), operatingSystem.getProcessToLocked(), operatingSystem.getProcessLocked(),
				operatingSystem.getProcessWakeUp(), operatingSystem.getProcessExpired(), operatingSystem.getProcessTerminated());
	}

	private void manageNewSimulationAction() {
		operatingSystem = new OperatingSystem();
		Object[][] empty = {};
		mainFrame.updateProcesses(empty);
		mainFrame.initStartSimulationPanel(this);
	}
}
