package ru.job4j.matrix;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixToList {
    //flatMap принимает элемент потока и преобразовывает его в новый поток.
    public List<Integer> matrixToList(Integer[][] matrix) {
       return Stream
               .of(matrix)
               .flatMap(Stream::of)
               .collect(Collectors.toList());
    }
}
