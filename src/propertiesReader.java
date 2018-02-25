

import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyStore.PasswordProtection;
import java.util.ArrayList;
import java.util.Scanner;

public class propertiesReader {
	 private String serverIP;
	 private String serverPort;
	 private String serverUserName;
	 private String serverUserPassword;
	 private String ServerFilePath;
	 private int numOfReaders ;
	 private int numOfWriters ;
	 private int maxAcc ;
	 private int numOfClients;
	 private ArrayList<String>  clientsHosts;
	 private ArrayList<String> clientsUserNames;
	 private ArrayList<String> clientsPasswords;
	 private ArrayList<String> clientsFilePath; // including the file name 

	public propertiesReader(String file) throws FileNotFoundException {
		this.readFile(file);
	}
	
	void readFile(String file)throws FileNotFoundException {
		File propertiesFile = new File(file); 
		if(propertiesFile!= null) {
			Scanner scan = new Scanner(propertiesFile);
			String tmp;
			tmp = scan.nextLine();
			serverIP = tmp.split("=")[1].trim();
			tmp = scan.nextLine();
			setServerUserName(tmp.split("=")[1].trim());
			tmp = scan.nextLine();
			setServerUserPassword(tmp.split("=")[1].trim());
			tmp = scan.nextLine();
			setServerFilePath(tmp.split("=")[1].trim());
			tmp = scan.nextLine();
			this.setServerPort(tmp.split("=")[1].trim());
			tmp = scan.nextLine();
			this.setNumOfReaders(Integer.parseInt(tmp.split("=")[1].trim()));
			clientsHosts = new ArrayList<String>();
			clientsUserNames = new ArrayList<String>();
			clientsPasswords = new ArrayList<String>();
			clientsFilePath = new ArrayList<String>();
			// reading the reader clients properties
			for(int i = 0; i<this.numOfReaders; i++) {
				/*
				 * reading reader user properties
				 */
				tmp = scan.nextLine();
				clientsHosts.add(tmp.split("=")[1].trim());
				tmp = scan.nextLine();
				clientsUserNames.add(tmp.split("=")[1].trim());
				tmp = scan.nextLine();
				clientsPasswords.add(tmp.split("=")[1].trim());
				tmp = scan.nextLine();
				clientsFilePath.add(tmp.split("=")[1].trim());
			}
			tmp = scan.nextLine();
			this.setNumOfWriters(Integer.parseInt(tmp.split("=")[1].trim()));
			// reading the Writer clients properties
			for(int i = 0; i<this.numOfWriters; i++) {
				/*
				 * reading reader user properties
				 */
				tmp = scan.nextLine();
				clientsHosts.add(tmp.split("=")[1].trim());
				tmp = scan.nextLine();
				clientsUserNames.add(tmp.split("=")[1].trim());
				tmp = scan.nextLine();
				clientsPasswords.add(tmp.split("=")[1].trim());
				tmp = scan.nextLine();
				clientsFilePath.add(tmp.split("=")[1].trim());
							
			}
			tmp = scan.nextLine();
			this.setMaxAcc(Integer.parseInt(tmp.split("=")[1].trim()));
			scan.close();
		}
		
		
		
		
	}

	/**
	 * @return the serverIP
	 */
	public String getServerIP() {
		return serverIP;
	}

	/**
	 * @param serverIP the serverIP to set
	 */
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	/**
	 * @return the numOfReaders
	 */
	public int getNumOfReaders() {
		return numOfReaders;
	}

	/**
	 * @param numOfReaders the numOfReaders to set
	 */
	public void setNumOfReaders(int numOfReaders) {
		this.numOfReaders = numOfReaders;
	}

	/**
	 * @return the numOfWriters
	 */
	public int getNumOfWriters() {
		return numOfWriters;
	}

	/**
	 * @param numOfWriters the numOfWriters to set
	 */
	public void setNumOfWriters(int numOfWriters) {
		this.numOfWriters = numOfWriters;
	}

	/**
	 * @return the numOfClients
	 */
	public int getNumOfClients() {
		return numOfClients;
	}

	/**
	 * @param numOfClients the numOfClients to set
	 */
	public void setNumOfClients(int numOfClients) {
		this.numOfClients = numOfClients;
	}

	/**
	 * @return the serverPort
	 */
	public String getServerPort() {
		return serverPort;
	}

	/**
	 * @param serverPort the serverPort to set
	 */
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @return the maxAcc
	 */
	public int getMaxAcc() {
		return maxAcc;
	}

	/**
	 * @param maxAcc the maxAcc to set
	 */
	public void setMaxAcc(int maxAcc) {
		this.maxAcc = maxAcc;
	}

	/**
	 * @return the clientsHosts
	 */
	public ArrayList<String> getClientsHosts() {
		return clientsHosts;
	}

	/**
	 * @param clientsHosts the clientsHosts to set
	 */
	public void setClientsHosts(ArrayList<String> clientsHosts) {
		this.clientsHosts = clientsHosts;
	}

	/**
	 * @return the clientsUserNames
	 */
	public ArrayList<String> getClientsUserNames() {
		return clientsUserNames;
	}

	/**
	 * @param clientsUserNames the clientsUserNames to set
	 */
	public void setClientsUserNames(ArrayList<String> clientsUserNames) {
		this.clientsUserNames = clientsUserNames;
	}

	/**
	 * @return the clientsPasswords
	 */
	public ArrayList<String> getClientsPasswords() {
		return clientsPasswords;
	}

	/**
	 * @param clientsPasswords the clientsPasswords to set
	 */
	public void setClientsPasswords(ArrayList<String> clientsPasswords) {
		this.clientsPasswords = clientsPasswords;
	}

	/**
	 * @return the clientsFilePath
	 */
	public ArrayList<String> getClientsFilePath() {
		return clientsFilePath;
	}

	/**
	 * @param clientsFilePath the clientsFilePath to set
	 */
	public void setClientsFilePath(ArrayList<String> clientsFilePath) {
		this.clientsFilePath = clientsFilePath;
	}

	/**
	 * @return the serverUserName
	 */
	public String getServerUserName() {
		return serverUserName;
	}

	/**
	 * @param serverUserName the serverUserName to set
	 */
	public void setServerUserName(String serverUserName) {
		this.serverUserName = serverUserName;
	}

	/**
	 * @return the serverUserPassword
	 */
	public String getServerUserPassword() {
		return serverUserPassword;
	}

	/**
	 * @param serverUserPassword the serverUserPassword to set
	 */
	public void setServerUserPassword(String serverUserPassword) {
		this.serverUserPassword = serverUserPassword;
	}

	/**
	 * @return the serverFilePath
	 */
	public String getServerFilePath() {
		return ServerFilePath;
	}

	/**
	 * @param serverFilePath the serverFilePath to set
	 */
	public void setServerFilePath(String serverFilePath) {
		ServerFilePath = serverFilePath;
	}

}


