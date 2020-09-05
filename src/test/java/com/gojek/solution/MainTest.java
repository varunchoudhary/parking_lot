package com.gojek.solution;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainTest {
    private InputStream sysInBackup;
    private PrintStream sysOutBackup;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

}