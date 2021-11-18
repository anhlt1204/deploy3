package com.esdo.bepilot.Util;

import com.esdo.bepilot.Exception.InvalidException;
import com.esdo.bepilot.Model.Entity.Employee;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class UploadAvatar {

    private static String FILE_DIRECTORY = "src/main/resources/images/";

    public static void upload(String path, Employee employee) {
        File file = new File(path);

        int index = path.trim().lastIndexOf('/');
        if (index == -1) {
            index = path.trim().lastIndexOf("\\");
        }

        int index1 = path.trim().lastIndexOf('.');
        String type = path.trim().substring(index1 + 1);

        String name = path.trim().substring(index + 1, index1);

        String pathSave = FILE_DIRECTORY + name + "." + type;

        File save = new File(pathSave);

        while (save.exists()) {
            int ramdom = (int) (Math.random() * 1000);

            name += String.valueOf(ramdom);
            pathSave = FILE_DIRECTORY + name + "." + type;

            save = new File(pathSave);
        }

        BufferedImage image = null;

        try {
            image = ImageIO.read(file);
            if (image == null) {
                throw new InvalidException("Invalid Avatar");
            }
            ImageIO.write(image, type, new File(pathSave));
            employee.setAvatar(pathSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
