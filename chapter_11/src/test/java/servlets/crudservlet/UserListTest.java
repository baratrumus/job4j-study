package servlets.crudservlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import servlets.userapp.UsersListServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//позволяет  подменить работу статических методов.
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "javax.management.*"})
//нам нужно подменить класс ValidateService.
@PrepareForTest(ValidateService.class)

public class UserListTest {

    @Test
    public void whenFindAllFounfOne() throws ServletException, IOException {
        Logic validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService .class);

        when(ValidateService.getInstance()).

        thenReturn(validate);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        //mock session
        HttpSession httpSession = Mockito.mock(HttpSession.class);
        when(req.getSession()).thenReturn(httpSession);

        UsersListServlet uls = new UsersListServlet();

        //mock dispatcher
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/WEB-INF/views/list.jsp")).thenReturn(dispatcher);

        when(req.getParameter("id")).thenReturn("1");
        uls.doGet(req, resp);

        //объект validate проставляется при вызове статического метод ValidateService.getInstance();
        assertThat(validate.findAll().size(), is(1));
        String name = validate.findAll().values().iterator().next().getName();
        assertThat(name, is("root"));
    }

}
