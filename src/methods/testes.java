package methods;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;

public class testes {
	static File currentFolder;
	static File input = new File("U://RICS_MDW_Practice//");
	static File output = new File("U://out//");
	
	public static void main(String[] args) {
		
		run();
	}

	
	
	public static void run() {
		  File[] directoryListing = input.listFiles();
			  if (directoryListing != null) {
			    for (File child : directoryListing) {
			    	// get list for channel 1
			    	createStack(child);
			    	
			    }
			  } 
		}


		private static void createStack(File child) {
			System.out.println(child.getAbsolutePath());
			
			File[] files = child.listFiles(new FilenameFilter() {
			    public boolean accept(File dir, String name) {
			        return name.toLowerCase().endsWith(".tif");
			    }
			});
			
			ArrayList<File> channel1 = new ArrayList<File>();
			ArrayList<File> channel2 = new ArrayList<File>();
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
			}
			
			stacker(channel1, "C001");
			stacker(channel2, "C002");
			
			
		}


		private static void stacker(ArrayList<File> channel, String CH) {
			ImagePlus imp = IJ.openImage(channel.get(0).getAbsolutePath());
			ImageStack stack = imp.getStack();
			
			for(int i=1; i<channel.size(); i++){
				
				ImagePlus imp2 = IJ.openImage(channel.get(i).getAbsolutePath());
				stack.addSlice(imp2.getProcessor());	
			}
			
			imp = new ImagePlus("stack",stack);
			savefile(imp, CH);
		}


		private static void savefile(ImagePlus imp, String CH) {
				File OUTPUT_Multiple = new File(output.getAbsolutePath() + File.separator + currentFolder.getName());
				OUTPUT_Multiple.mkdirs();
				IJ.saveAsTiff(imp, OUTPUT_Multiple.getAbsolutePath() + File.separator + currentFolder.getName() + "-" + CH);
//			}
//			
//			else{
//				File OUTPUT_Multiple = new File(output.getAbsolutePath() + File.separator + currentFolder.getName() + File.separator + CH);
//				IJ.saveAsTiff(imp, OUTPUT_Multiple.getAbsolutePath());
//				
//			}
//			
		}
		
		
}
