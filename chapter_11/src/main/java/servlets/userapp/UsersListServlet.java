package servlets.userapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import servlets.crudservlet.*;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class UsersListServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersListServlet.class);
    private final ValidateService logic = ValidateService.getInstance();

    /**
     * открывает таблицу со всеми пользователями.
     * В каждой строчке должна быть колонка с кнопками (редактировать, удалить)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, User> users = logic.findAll();
        req.setAttribute("userMap", users);
        String imgPath = getImgPath();
        req.setAttribute("imgPath", imgPath);
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletConfig().getServletContext();
        String imgPathOnServer = context.getRealPath("/images");
        String id = req.getParameter("id");
        String photoId = logic.findById(id).getPhotoId();
        if (logic.delete(id)) {
            boolean res = removeUserPhoto(imgPathOnServer, photoId);
        }
        doGet(req, resp);
    }


    /**
     * Удаляем файл юзера из папки на сервере
     */
    private boolean removeUserPhoto(String imgPath, String photoName) {
        boolean ret = false;
        File file = new File(imgPath + File.separator +  photoName);
        if (file.delete()) {
            ret = true;
        }
        return ret;
    }

    private String getImgPath() {
        try (InputStream in = UserCreateServlet.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            return config.getProperty("img-path");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
