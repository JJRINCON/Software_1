package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		}
	}

	private void manageAddAction() {
		addProcessDialog = new AddProcessDialog(this, mainFrame);
		addProcessDialog.setVisible(true);
	}

	private void manageAcceptAction() {
		operatingSystem.addProcess(new MyProcess(addProcessDialog.getProcessName(), addProcessDialog.getProcessTime(),
									addProcessDialog.getIsBlocked()));
		addProcessDialog.dispose();
		mainFrame.updateProcesses(operatingSystem.getProcessInfo());
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
}
