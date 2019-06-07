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
        for (int i = 0; i < 10; i++) {
            singleton[i] = Singleton.getInstance();
        }
        for (int i = 9; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                assertThat(singleton[j], is(singleton[i]));
            }
        }

    }

    @Test
    public void whenSingletonEnumItsOne() {
        SingletonEnum[] singleton = new SingletonEnum[10];
        for (int i = 0; i < 10; i++) {
            singleton[i] = SingletonEnum.INSTANCE;
        }
        for (int i = 9; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                assertThat(singleton[j], is(singleton[i]));
            }
        }

    }

    @Test
    public void whenSingletonEagerLoadingItsOne() {
        SingletonEager[] singleton = new SingletonEager[10];
        for (int i = 0; i < 10; i++) {
            singleton[i] = SingletonEager.getInstance();
        }
        for (int i = 9; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                assertThat(singleton[j], is(singleton[i]));
            }
        }

    }

    @Test
    public void whenSingletonItsOne2() {
        SingletonInner[] singleton = new SingletonInner[10];
        for (int i = 0; i < 10; i++) {
            singleton[i] = SingletonInner.getInstance();
        }
        for (int i = 9; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                assertThat(singleton[j], is(singleton[i]));
            }
        }

    }



}
