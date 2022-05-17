package models;

import java.util.ArrayList;

public class OperatingSystem {

	private Queue<MyProcess> processQueueReady;
	private ArrayList<MyProcess> readyAndDespachado;
	private ArrayList<MyProcess> lockedAndWakeUp;
	private ArrayList<MyProcess> executing;
	private ArrayList<MyProcess> expired;
	private ArrayList<MyProcess> processTerminated;
	private MyProcess processInExecition;
	private Thread thread;

	public OperatingSystem(Queue<MyProcess> processQueue) {
		super();
		this.processQueueReady = processQueue;
		this.lockedAndWakeUp = new ArrayList<>();
		this.processTerminated = new ArrayList<>();
		executing = new ArrayList<>();
		expired = new ArrayList<>();
		readyAndDespachado = new ArrayList<>();
	}

	public boolean addProcess(MyProcess myProcess) {
		if (search(myProcess.getName()) == null) {
			readyAndDespachado.add(new MyProcess(myProcess.getName(), myProcess.getTime(), myProcess.isLocked()));
			processQueueReady.push(myProcess);
			return true;
		}
		return false;
	}

	private MyProcess search(String name) {
		Node<MyProcess> temp = processQueueReady.peek();
		while (temp != null) {
			if (temp.getData().getName().equals(name)) {
				return temp.getData();
			} else {
				temp = temp.getNext();
			}
		}
		return null;
	}

	public void startSimulation() throws InterruptedException {
		while (!processQueueReady.isEmpty()) {
			MyProcess process = processQueueReady.peek().getData();
			valideSystemTimer(process);
		}
	}

	private void valideSystemTimer(MyProcess process) {
		executing.add(new MyProcess(process.getName(), (process.getTime()-5< 0 ? 0:process.getTime()-5), process.isLocked()));
		if ((process.getTime() - 5) > 0) {
			process.setTime(5);
			valideLocked(process);
			readyAndDespachado.add(new MyProcess(process.getName(), process.getTime(), process.isLocked()));
			processQueueReady.push(processQueueReady.pop());
		} else {
			MyProcess myProcess = processQueueReady.pop();
			myProcess.setTime((int)myProcess.getTime());
			processTerminated.add(myProcess);
		}
	}

	private void valideLocked(MyProcess process) {
		if (process.isLocked()) {
			lockedAndWakeUp.add(new MyProcess(process.getName(), process.getTime(), process.isLocked()));
		} else {
			expired.add(new MyProcess(process.getName(), process.getTime(), process.isLocked()));
		}
	}


	public Queue<MyProcess> getProcessQueue() {
		return processQueueReady;
	}

	public MyProcess getProcessInExecition() {
		return processInExecition;
	}

	public ArrayList<MyProcess> getProcessQueueLocked() {
		return lockedAndWakeUp;
	}

	public void delete(String name) {
		Node<MyProcess> temp = processQueueReady.peek();
		if (temp.getData().getName().equals(name)) {

		}
	}

	/**
	 * 
	 * @return Procesos terminados
	 */
	public ArrayList<MyProcess> getProcessTerminated() {
		return processTerminated;
	}

	/**
	 * 
	 * @return Lista de los procesos listos
	 */
	public ArrayList<MyProcess> getReadyProccess() {
		return readyAndDespachado;
	}

	/**
	 * 
	 * @return Procesos despachados
	 */
	public ArrayList<MyProcess> getProcessDespachados() {
		return readyAndDespachado;
	}

	/**
	 * 
	 * @return  Processos en ejecucion
	 */
	public ArrayList<MyProcess> getExecuting() {
		return executing;
	}

	/**
	 * 
	 * @return Procesos expirados
	 */
	public ArrayList<MyProcess> getProcessExpired() {
		return expired;
	}

	/**
	 * 
	 * @return Los que pasan a bloqueado
	 */
	public ArrayList<MyProcess> getProcessToLocked() {
		return lockedAndWakeUp;
	}

	/**
	 * 
	 * @return Porcesos bloqueados
	 */
	public ArrayList<MyProcess> getProcessLocked() {
		return lockedAndWakeUp;
	}

	/**
	 * 
	 * @return Procesos despertados
	 */
	public ArrayList<MyProcess> getProcessWakeUp() {
		return lockedAndWakeUp;
	}

	public void start() {
		thread.start();
	}
	
	public void showProcess() {
		
		System.out.println("-------------Listos---------------");
		for (MyProcess myProcess : readyAndDespachado) {
			System.out.println(myProcess.toString());
		}
		
		System.out.println("-------------despachados---------------");
		for (MyProcess myProcess : readyAndDespachado) {
			System.out.println(myProcess.toString());
		}
		System.out.println("-------------Ejecucion---------------");
		for (MyProcess myProcess : executing) {
			System.out.println(myProcess.toString());
		}
		
		System.out.println("-------------Bloquear---------------");
		for (MyProcess myProcess : lockedAndWakeUp) {
			System.out.println(myProcess.toString());
		}
		System.out.println("-------------Bloqueados---------------");
		for (MyProcess myProcess : lockedAndWakeUp) {
			System.out.println(myProcess.toString());
		}		
		System.out.println("-------------despertar---------------");
		for (MyProcess myProcess : lockedAndWakeUp) {
			System.out.println(myProcess.toString());
		}
		System.out.println("-------------Expirados---------------");
		for (MyProcess myProcess : expired) {
			System.out.println(myProcess.toString());
		}
		System.out.println("-------------Terminados---------------");
		for (MyProcess myProcess : processTerminated) {
			System.out.println(myProcess.toString());
		}
	}

	
}
