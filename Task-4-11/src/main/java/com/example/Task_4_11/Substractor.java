package com.example.Task_4_11;

import org.springframework.stereotype.Component;

@Component
public class Substractor implements Operation {
    @Override
    public double getResult(double a, double b) {
        return a - b;
    }
}
