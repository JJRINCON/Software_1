package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.MyProcess;
import models.OperatingSystem;
import models.Queue;

public class Presenter implements ActionListener {

	private OperatingSystem operatingSystem;

	public Presenter() {
		init();
	}

	private void init() {
		Queue<MyProcess> queue = new Queue<>();
		operatingSystem = new OperatingSystem(queue);
		operatingSystem.addProcess(new MyProcess("P1", 5, false));
		operatingSystem.addProcess(new MyProcess("P2", 8, false));
		operatingSystem.addProcess(new MyProcess("P3", 7, false));
		operatingSystem.addProcess(new MyProcess("P4", 6, true));
		try {
			operatingSystem.startSimulation();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		operatingSystem.showProcess();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
	}
}