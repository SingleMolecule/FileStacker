package methods;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import create.gui.StringPanel;
import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;

public class Stacker {
		
	private StringPanel panel; 
	private File input; 
	private File output; 
	private String radioButton = "";
	private boolean runner = true;
	File currentFolder;
	
	
	public Stacker(StringPanel stringPanel, String input, String output, String radioButton) {
		this.input = new File(input);
		this.output = new File(output);
		this.radioButton = radioButton;
		this.panel = stringPanel;
		
		check();
		System.out.println(runner);
		File file = new File("");
		
		if (file.exists()){
			System.out.println("yes");
		}
		if(runner == true){
			System.out.println("Im running");
			run();
		}
		
		panel.setStatus("Done");
	
			
	
	}


	private void check() {
					
		System.out.println(runner);
	
		if(!input.exists() || !input.isDirectory()){
			panel.setStatus("Input folder not valid");
			this.runner =  false;
		}
		
//		if(!output.exists() || !output.isDirectory()){
//			
//			panel.setStatus("Output folder not valid");
//			this.runner = false;
//		}
			
		if (radioButton == null){
			panel.setStatus("Select a channel option");
			this.runner = false;
		}
		
		
	}


	public void run() {
	  File[] directoryListing = input.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	// get list for channel 1
		    	createStack(child);
		    	
		    }
		  } 
	}


	private void createStack(File child) {
		System.out.println(child.getAbsolutePath());
		
		File[] files = child.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(".tif");
		    }
		});
		
		ArrayList<File> channel1 = new ArrayList<File>();
		ArrayList<File> channel2 = new ArrayList<File>();
		ArrayList<File> channel3 = new ArrayList<File>();
		currentFolder = child;
		
		for(File file : files)
		{
			String filename = file.getName();
			if(filename.contains("C001")){
				channel1.add(file);
			}
			if(filename.contains("C002")){
				channel2.add(file);
			}
			if(filename.contains("C003")){
				channel3.add(file);
			}
		}
		
		stacker(channel1, "C001");
		stacker(channel2, "C002");
		stacker(channel3, "C003");
		
		
		
	}


	private void stacker(ArrayList<File> channel, String CH) {
		ImagePlus imp = IJ.openImage(channel.get(0).getAbsolutePath());
		ImageStack stack = imp.getStack();
		
		for(int i=1; i<channel.size(); i++){
			
			ImagePlus imp2 = IJ.openImage(channel.get(i).getAbsolutePath());
			stack.addSlice(imp2.getProcessor());	
		}
		
		imp = new ImagePlus("stack",stack);
		savefile(imp, CH);
	}


	private void savefile(ImagePlus imp, String CH) {
		if (radioButton == "Multiple Folders"){
			File OUTPUT_Multiple = new File(output.getAbsolutePath() + File.separator + currentFolder.getName());
			OUTPUT_Multiple.mkdirs();
			IJ.saveAsTiff(imp, OUTPUT_Multiple.getAbsolutePath() + File.separator + currentFolder.getName() + "-" + CH);
		}
		
		else{
			File OUTPUT_Multiple = new File(output.getAbsolutePath());
			OUTPUT_Multiple.mkdirs();
			IJ.saveAsTiff(imp, OUTPUT_Multiple.getAbsolutePath() + File.separator + currentFolder.getName() + "-" + CH);
			
		}
		
	}
	
	



}
