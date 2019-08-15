package ru.job4j.io.socketbot;

import org.junit.Test;

import com.google.common.base.Joiner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    private static final String LN = System.lineSeparator();

    @Test
    public void whenServerGotExitSayBye() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Server server = new Server(socket);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        server.start();
        assertThat(out.toString(), is(String.format("Пока.%s", LN)));
    }

    @Test
    public void whenServerGotPrivetSayPrivet() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(
                Joiner.on(LN).join(
                "hello",
                "exit"
                ).getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Server server = new Server(socket);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        server.start();
        assertThat(out.toString(), is(String.format("Hello, dear friend, I'm a oracle.%s%sПока.%s", LN, LN, LN)));
    }

    @Test
    public void whenServerGotCommandSayItBack() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(
                Joiner.on(LN).join(
                        "Kolobok",
                        "exit"
                ).getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Server server = new Server(socket);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        server.start();
        assertThat(out.toString(), is(String.format("Kolobok%sПока.%s", LN, LN)));
    }
}
