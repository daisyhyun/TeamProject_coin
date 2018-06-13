package Tools;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ResourcesManage {
	public BufferedImage CallImage(String filename) {			//original size
		Path ResourcesPath = Paths.get("src\\resources");
		String path = ResourcesPath.toAbsolutePath().toString();
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path + "\\" + filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	
	public BufferedImage CallImage(String filename, int x, int y) {	//transformed size
		Path ResourcesPath = Paths.get("src\\resources");
		String path = ResourcesPath.toAbsolutePath().toString();
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path + "\\" + filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage resized = new BufferedImage(x,y,BufferedImage.TYPE_INT_ARGB);
		resized.getGraphics().drawImage(image.getScaledInstance(x, y, Image.SCALE_AREA_AVERAGING),0,0,x,y,null);
		return resized;
	}
}
