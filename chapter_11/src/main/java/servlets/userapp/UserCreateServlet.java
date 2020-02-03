package servlets.userapp;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.crudservlet.Logic;
import servlets.crudservlet.ValidateService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class UserCreateServlet  extends HttpServlet {
    //private static final Logger LOG = LoggerFactory.getLogger(UserCreateServlet.class);
    private final Logic logic = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("roleMap", logic.getRoles());
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = this.getServletConfig().getServletContext();
        String imgPathOnServer = context.getRealPath("/images");
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // устанавливаем временную директорию
        File repository = (File) context.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        String imgPath = getImgPath();
        String imgFilename = "";
        Map<String, String> textParams = new HashMap<>();

        try {
            //получаем  параметры
            List<FileItem> items = upload.parseRequest(req);
            File imgFolder = new File(imgPath);
            //записываем файл вне папки сервера и получаем имя файла
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    imgFilename = processUploadedFile(item, imgFolder);
                }
            }
            //получаем текстовые параметры
            items.stream().filter(FileItem::isFormField).forEach(i -> textParams.put(i.getFieldName(), i.getString()));

        } catch (FileUploadException e) {
            System.out.println(e.getMessage());
            //this.LOG.error(e.getMessage(), e);
        }

        //перемещаем файлы из внешней папки внутрь сервера
        Boolean move = moveUploadedFilesIntoServer(imgPathOnServer, imgPath);

        if (logic.add(textParams.get("name"), textParams.get("login"),
                textParams.get("pass"), textParams.get("email"), imgFilename, textParams.get("roles"))) {
            req.setAttribute("created", "yes");
        } else {
            req.setAttribute("created", "no");
        }
        req.setAttribute("userMap", logic.findAll());
        req.setAttribute("name", textParams.get("name"));
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }

    /**
     * Записываем файл в директорию вне папки сервера
     */
    private String processUploadedFile(FileItem item, File imgFolder)  throws IOException {
        String fileName = item.getName();
        if ((fileName != null) && (!fileName.isEmpty())) {
            //берем из полного пути имя файла
            fileName = FilenameUtils.getName(fileName);
            String resultPath = imgFolder + File.separator  + fileName;
            File resultFile = new File(resultPath);

            try (FileOutputStream out = new FileOutputStream(resultFile)) {
                out.write(item.getInputStream().readAllBytes());
            }
        }
        return fileName;
    }


    private String getImgPath() {
        try (InputStream in = UserCreateServlet.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            return config.getProperty("img-path-aside-of-server");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean moveUploadedFilesIntoServer(String imgPathOnServer, String imgPathOutsideServer) {
        boolean res = false;
        File dir = new File(imgPathOutsideServer);
        for (File srcFile : dir.listFiles()) {
            File destFile = new File(imgPathOnServer, srcFile.getName());
            if (srcFile.renameTo(destFile)) {
               res = true;
            }
        }
        return res;
    }

}
