package servlets.userapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

/**
 * Сделать строгую схему MVC, когда точкой входа является сервлет.
 *  К пользователям нужно добавить поле photoId.
 *  Это поле должно содержать имя файла с картинкой.
 *  Загрузка картинки производиться на форму добавления пользователя.
 *  Отображение картинки должно быть в таблице списка пользователей.
 *  Добавьте возможность скачивать картинки отдельно.
 *  Файл должен храниться на файловой системе. в папке bin/images/
 *  При удалении пользователя нужно удалять его картинку тоже.
 *  Добавить механизм авторизации и аутентификации на фильтрах.
 *   Добавить новую модель роль. Каждый пользователь в системе имеют свою роль.
 *   Предусмотреть. Роль - администратор. Он может добавить и редактировать любого пользователя в том числе себя.
 *   В форме редактирования роли должен появиться список всех ролей
 *   Обычный пользователь может редактировать только себя. Причем он не может менять роль.
 *   для servlet api 3.0 > синхронизацию делать не надо. В задаче так же объяснить почему.
 *
 *   т.к в servlet api 3.0 реализован асинхронный процессинг для атомарных операций (чтения и записи)
 */

public class UsersListServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersListServlet.class);
    private final Logic logic = ValidateService.getInstance();

    /**
     * открывает таблицу со всеми пользователями.
     * В каждой строчке должна быть колонка с кнопками (редактировать, удалить)
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, User> users = logic.findAll();
        req.setAttribute("userMap", users);
        HttpSession session = req.getSession();
        session.setAttribute("imgPath", getImgPath());
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }

    /**
     * delete user from table
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
