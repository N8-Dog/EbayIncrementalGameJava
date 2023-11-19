package IncrementalGameJava;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class imageManager {
	private HashMap<String, ImageIcon> content;
	
	public imageManager() {
		content = new HashMap<String, ImageIcon>();
		for (String fileName : listFiles()) {
			if(isPng(fileName)) {
				if(isGrided(fileName)) {
					importImage(fileName, Character.getNumericValue(fileName.charAt(fileName.length() - 7)), Character.getNumericValue(fileName.charAt(fileName.length() -5)));
				}
				else {
					importImage(fileName);
				}
			}
		}
		
		
	}
	
	
	public Icon getIcon(String key) {
		JLabel image = getLabel(key);
		return image.getIcon();
	}
	
	private String[] listFiles() {
		String[] fileList;
		
		String folderPath = "src\\IncrementalGameJava\\Assets";
		File directory = new File(folderPath);
		File[] filesList = directory.listFiles();
		//System.out.println(filesList);
		fileList = new String[filesList.length];
		int i = 0;
		for (File file : filesList) {
			fileList[i++] = file.toString();
		}
		return fileList;
	}
	
	private boolean isGrided(String fileName) {
		Pattern pattern = Pattern.compile(".*\\d{1}x\\d{1}.*$");
		Matcher matcher = pattern.matcher(fileName);
		return matcher.find();
	}
	
	private boolean isPng (String fileName) {
		String png = ".png";
		for(@SuppressWarnings("unused") String file : listFiles()) {
			int index = fileName.length() - 4;
			for (int i = 0; i < 4; i++) {
				if(fileName.charAt(index + i) != png.charAt(i)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private JLabel importImage(String source){
		ImageIcon icon = null;
		try {
			BufferedImage originalImage = ImageIO.read(new File(source));
			icon = new ImageIcon(originalImage);
			String name = source.substring(31,source.length() -4);
			content.put(name, icon);
		}catch (IOException e){
			e.printStackTrace();
		}
		return new JLabel(icon);
	}
	
	private void importImage(String source, int col, int row) {
		String nameSource = source.substring(0, source.length()-4);
		nameSource += ".names";
		FileReader fileReader;
		
		try {
			fileReader = new FileReader(nameSource);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
		
			BufferedImage originalImage = null;
			
			try {
				originalImage = ImageIO.read(new File(source));
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			int fragmentWidth = originalImage.getWidth() / col;
			int fragmentHeight = originalImage.getHeight() / row;
			
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < row;j++) {
					int x = j * fragmentWidth;
					int y = i * fragmentHeight;
					BufferedImage fragment = originalImage.getSubimage(x, y, fragmentWidth, fragmentHeight);
					ImageIcon icon = new ImageIcon(fragment);
					try {
						line = bufferedReader.readLine();
						content.put(line, icon);
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public JLabel getLabel(String key) {
		return new JLabel(content.get(key));
	}
	
}
