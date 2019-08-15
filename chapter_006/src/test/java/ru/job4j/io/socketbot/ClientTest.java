package ru.job4j.io.socketbot;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    private static final String LN = System.lineSeparator();

    @Test
    public  void whenClientSendExit() throws IOException  {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(
                Joiner.on(LN).join(
                        "Hello, dear friend, I'm a oracle.",
                        "Пока."

                ).getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //подменяем вывод на консоль
        ByteArrayOutputStream consoleOutputStream = new ByteArrayOutputStream();
        PrintStream consolePrintStream = new PrintStream(consoleOutputStream);
        System.setOut(consolePrintStream);

        //подменяем ввод консоли
        ByteArrayInputStream consoleInputStream = new ByteArrayInputStream(
                Joiner.on(LN).join(
                "hello",
                "exit"
        ).getBytes());
        System.setIn(consoleInputStream);

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        Client clientOracle = new Client(socket);
        clientOracle.start();
        assertThat(consoleOutputStream.toString(), is(String.format("Введите фразу для передачи серверу: %s"
                + "Сервер ответил: Hello, dear friend, I'm a oracle.%s"
                + "Сервер ответил: Пока.%s", LN, LN, LN)));
    }

}
