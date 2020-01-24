package servlets.download;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * отдаем список всех файлов. В этом списке содержатся только имена.
 */
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String imgPath1 = getImgPath();
        //String imgPath2 = System.getProperty("user.dir"); //bin tomcat
        List<String> images = new ArrayList<>();
        ServletContext context = this.getServletConfig().getServletContext();
        String imgPath = context.getRealPath("/images");
        for (File name : new File(imgPath).listFiles()) {
            images.add(name.getName());
        }
        req.setAttribute("images", images);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/upload.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletConfig().getServletContext();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // Максимальный буфера данных в байтах,
        // при его привышении данные начнут записываться на диск во временную директорию
        // устанавливаем один мегабайт
        factory.setSizeThreshold(1024 * 1024);

        // устанавливаем временную директорию
        File repository = (File) context.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        //загрузчик
        ServletFileUpload upload = new ServletFileUpload(factory);
        //максимальный размер данных который разрешено загружать в байтах
        //по умолчанию -1, без ограничений. Устанавливаем 5 мегабайт.
        upload.setSizeMax(1024 * 1024 * 5);

        String imgPath = context.getRealPath("/images");
        try {
            List<FileItem> items = upload.parseRequest(req);
            File imgFolder = new File(imgPath);
            if (!imgFolder.exists()) {
                imgFolder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    processUploadedFile(item, imgFolder);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }

    private void processUploadedFile(FileItem item, File imgFolder)  throws IOException {
        Random r = new Random();
        int rnd = r.nextInt(10000);
        File resultFile;

        String fileName = item.getName();
        if (fileName != null) {
            //вырезаем из полного пути имя файла
            fileName = FilenameUtils.getName(fileName);
        }

        //добавлям к имени random и выбираем файлу имя пока не найдём свободное
        do {
            String resultPath = imgFolder + File.separator + rnd + "_" + fileName;
            resultFile = new File(resultPath);
        } while (resultFile.exists());

        //1 вар записи
        /*try (FileOutputStream out = new FileOutputStream(resultFile)) {
            out.write(item.getInputStream().readAllBytes());
        }*/

        //2 вар
        try {
            resultFile.createNewFile();
            item.write(resultFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
