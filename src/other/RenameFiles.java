package other;

import java.io.File;

public class RenameFiles {
    public static void main(String[] args) {
        String path = "E:\\codes\\java-data-struct-algorithm\\src\\SwordFingerOffer\\";
        File dir = new File(path);
        
        if (dir.exists() && dir.isDirectory()) {
            
            File[] files = dir.listFiles();
            
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String fileName = file.getName().toUpperCase();
                fileName = fileName.replace(".JAVA" , ".java");
                file.renameTo(new File(path + fileName));
            }
        }
    }
    
    
}
