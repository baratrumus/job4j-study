package servlets.crudservlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import servlets.userapp.UserCreateServlet;
import servlets.userapp.UserUpdateServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

//позволяет  подменить работу статических методов.
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "javax.management.*"})
//нам нужно подменить класс ValidateService.
@PrepareForTest(ValidateService.class)


public class UserUpdateTest {
    @Test
    public void whenUpdateUserThenUpdated() throws ServletException, IOException {
        Logic validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        //вариант
        //when(req.getSession().getAttribute("id")).thenReturn("1");

        //mock session
        HttpSession httpSession = Mockito.mock(HttpSession.class);
        when(req.getSession()).thenReturn(httpSession);

        //mock dispatcher
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/WEB-INF/views/list.jsp")).thenReturn(dispatcher);

        when(req.getParameter("name")).thenReturn("Anatoly");
        when(req.getParameter("roles")).thenReturn("2");
        when(req.getParameter("id")).thenReturn("1");
        new UserUpdateServlet().doPost(req, resp);

        //объект validate проставляется при вызове статического метод ValidateService.getInstance();
        String name = validate.findAll().values().iterator().next().getName();
        assertThat(name, is("Anatoly"));

        String role = validate.findAll().values().iterator().next().getRole();
        assertThat(role, is("moderator"));
    }
}
