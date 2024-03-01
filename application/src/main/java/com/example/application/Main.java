package com.example.application;

import com.example.cache.model.BuildInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Build Info:");
            System.out.println(new BuildInfo());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}