package com.iutlan.freecovid.Bluetooth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import me.aflak.bluetooth.reader.SocketReader;

public class LineReader extends SocketReader {
    private BufferedReader reader;

    public LineReader(InputStream inputStream) {
        super(inputStream);
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public byte[] read() throws IOException {
        return reader.readLine().getBytes();
    }
}