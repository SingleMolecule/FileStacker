package create.gui;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Desktop;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.awt.event.ActionEvent;

import methods.Stacker;

public class StringPanel extends JPanel implements ActionListener,ItemListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton inputButtom;
	private JButton outputButtom;
	private JButton startButtom;
	private JButton cancelButtom;
	private JButton reportBug;
	private JTextField inputField;
	private JTextField outputfield;
	private JRadioButton button2;
	private JRadioButton button1;
	private File inputDir;
	private File outputDir;
	private ButtonGroup group = new ButtonGroup();	
	
	public JTextField status;
	private JLabel lblStatus;
	private JLabel lblFolderStructure;
	
		
	
	
	
	public StringPanel() {
		setupPanel();
	}
	
	private void setupPanel(){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{121, 100, 100, 100, 0};
		gridBagLayout.rowHeights = new int[]{40, 35, 0, 40, 40, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
				
	//setup buttoms
		inputButtom = new JButton("Input Folder");
		inputButtom.addActionListener(this);
		GridBagConstraints gbc_inputButtom = new GridBagConstraints();
		gbc_inputButtom.fill = GridBagConstraints.BOTH;
		gbc_inputButtom.insets = new Insets(0, 0, 5, 5);
		gbc_inputButtom.gridx = 0;
		gbc_inputButtom.gridy = 0;
		this.add(inputButtom, gbc_inputButtom);
		inputField = new JTextField("Choose an input folder");
		
		GridBagConstraints gbc_inputField = new GridBagConstraints();
		gbc_inputField.gridwidth = 3;
		gbc_inputField.fill = GridBagConstraints.BOTH;
		gbc_inputField.insets = new Insets(0, 0, 5, 0);
		gbc_inputField.gridx = 1;
		gbc_inputField.gridy = 0;
		this.add(inputField, gbc_inputField);
		
		inputField.setColumns(10);
		outputButtom= new JButton("Output Folder");
		outputButtom.addActionListener(this);
		GridBagConstraints gbc_outputButtom = new GridBagConstraints();
		gbc_outputButtom.fill = GridBagConstraints.BOTH;
		gbc_outputButtom.insets = new Insets(0, 0, 5, 5);
		gbc_outputButtom.gridx = 0;
		gbc_outputButtom.gridy = 1;
		this.add(outputButtom, gbc_outputButtom);
		outputfield = new JTextField("Choose an output folder");
		GridBagConstraints gbc_outputfield = new GridBagConstraints();
		gbc_outputfield.gridwidth = 3;
		gbc_outputfield.fill = GridBagConstraints.BOTH;
		gbc_outputfield.insets = new Insets(0, 0, 5, 0);
		gbc_outputfield.gridx = 1;
		gbc_outputfield.gridy = 1;
		this.add(outputfield, gbc_outputfield);
		
		outputfield.setColumns(10);
		
		lblFolderStructure = new JLabel("Folder Structure");
		GridBagConstraints gbc_lblFolderStructure = new GridBagConstraints();
		gbc_lblFolderStructure.insets = new Insets(0, 0, 5, 5);
		gbc_lblFolderStructure.gridx = 0;
		gbc_lblFolderStructure.gridy = 2;
		add(lblFolderStructure, gbc_lblFolderStructure);
		button2 = new JRadioButton("Multiple Folders");
		GridBagConstraints gbc_button2 = new GridBagConstraints();
		gbc_button2.insets = new Insets(0, 0, 5, 5);
		gbc_button2.gridx = 0;
		gbc_button2.gridy = 3;
		add(button2, gbc_button2);
		group.add(button2);
		reportBug = new JButton("Report bug");
		reportBug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().browse(
							new URL("https://github.com/SingleMolecule/FileStacker/issues").toURI());
				} catch (Exception e1) {
				}
			}
		});
		
		button1 = new JRadioButton("Single Folder");
		GridBagConstraints gbc_button1 = new GridBagConstraints();
		gbc_button1.anchor = GridBagConstraints.WEST;
		gbc_button1.insets = new Insets(0, 0, 5, 5);
		gbc_button1.gridx = 1;
		gbc_button1.gridy = 3;
		add(button1, gbc_button1);
		group.add(button1);
		GridBagConstraints gbc_reportBug = new GridBagConstraints();
		gbc_reportBug.fill = GridBagConstraints.BOTH;
		gbc_reportBug.insets = new Insets(0, 0, 5, 5);
		gbc_reportBug.gridx = 0;
		gbc_reportBug.gridy = 4;
		this.add(reportBug, gbc_reportBug);
		cancelButtom = new JButton("Close");
		cancelButtom.addActionListener(this);
		GridBagConstraints gbc_cancelButtom = new GridBagConstraints();
		gbc_cancelButtom.fill = GridBagConstraints.BOTH;
		gbc_cancelButtom.insets = new Insets(0, 0, 5, 5);
		gbc_cancelButtom.gridx = 2;
		gbc_cancelButtom.gridy = 4;
		this.add(cancelButtom, gbc_cancelButtom);
		startButtom = new JButton("Start");
		startButtom.addActionListener(this);
		GridBagConstraints gbc_startButtom = new GridBagConstraints();
		gbc_startButtom.insets = new Insets(0, 0, 5, 0);
		gbc_startButtom.fill = GridBagConstraints.BOTH;
		gbc_startButtom.gridx = 3;
		gbc_startButtom.gridy = 4;
		this.add(startButtom, gbc_startButtom);
		
		lblStatus = new JLabel("Status:");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 0, 5);
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 5;
		add(lblStatus, gbc_lblStatus);
		
		status = new JTextField();
		status.setText("Waiting . . . ");
		GridBagConstraints gbc_status = new GridBagConstraints();
		gbc_status.gridwidth = 3;
		gbc_status.fill = GridBagConstraints.HORIZONTAL;
		gbc_status.gridx = 1;
		gbc_status.gridy = 5;
		add(status, gbc_status);
		status.setColumns(10);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButtom) {
			System.exit(0);
		} else if (e.getSource() == inputButtom) {
			inputDir = getFile();
			writeInput(inputDir.getAbsolutePath());
			
			System.out.println(inputDir.getAbsolutePath());
		} else if (e.getSource() == outputButtom) {
			outputDir = getFile();
			writeOutput(outputDir.getAbsolutePath());
			System.out.println(outputDir.getAbsolutePath());
		}
		
		else if (e.getSource() == startButtom){
				
			Stacker stacker = new Stacker(this, inputField.getText(), outputfield.getText(), getRadioButton());
			
			
			
		}
			
		
		
	}

	private String getRadioButton() {
	  
		        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
		            AbstractButton button = buttons.nextElement();

		            if (button.isSelected()) {
		                return button.getText();
		            }
		        }

		        return null;
		
	
	}

	private File getFile() {
		File myFile = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			myFile =  fileChooser.getSelectedFile();
		}
		return myFile;
	}


	private void writeInput(String string){
		if (string == null || string.matches("")){
			inputField.setText("Choose an input folder");
		}
		else{
			inputField.setText(string);
		}
				
	}
	
	
	private void writeOutput(String string){
		if (string == null || string.matches("")){
			outputfield.setText("Choose an output folder");
		}
		else{
			outputfield.setText(string);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		 Object source = e.getItemSelectable();
	}
	
	public void setStatus(String string){
		status.setText(string);
	}
}
