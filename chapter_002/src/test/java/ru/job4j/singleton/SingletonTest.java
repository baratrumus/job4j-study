package ru.job4j.singleton;

import ru.job4j.tracker.*;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;
import static org.hamcrest.Matchers.is;

public class SingletonTest {


    /**
     * Проверяем эл-ты массива каждый с каждым, что это один объект
     */
    @Test
    public void whenSingletonItsOne() {
        Singleton[] singleton = new Singleton[10];
        for(int i = 0;i < 10;i++) {
            singleton[i] = Singleton.getInstance();
        }
        for(int i=9;i > 0;i--) {
            for(int j = 0;j < i;j++) {
                assertThat(singleton[j], is(singleton[i]));
            }
        }

    }

    @Test
    public void whenSingletonEnumItsOne() {
        SingletonEnum[] singleton = new SingletonEnum[10];
        for(int i = 0;i < 10;i++) {
            singleton[i] = SingletonEnum.INSTANCE;
        }
        for(int i=9;i > 0;i--) {
            for(int j = 0;j < i;j++) {
                assertThat(singleton[j], is(singleton[i]));
            }
        }

    }

    @Test
    public void whenSingletonItsOne1() {
        Singleton1[] singleton = new Singleton1[10];
        for(int i = 0;i < 10;i++) {
            singleton[i] = Singleton1.getInstance();
        }
        for(int i=9;i > 0;i--) {
            for(int j = 0;j < i;j++) {
                assertThat(singleton[j], is(singleton[i]));
            }
        }

    }

    @Test
    public void whenSingletonItsOne2() {
        Singleton2[] singleton = new Singleton2[10];
        for(int i = 0;i < 10;i++) {
            singleton[i] = Singleton2.getInstance();
        }
        for(int i=9;i > 0;i--) {
            for(int j = 0;j < i;j++) {
                assertThat(singleton[j], is(singleton[i]));
            }
        }

    }



}
