package com.ping.chen.spring.io;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class SequentialFile implements Comparable<SequentialFile> {

    private int index;

    private String fileName;

    public SequentialFile(int index, String fileName) {
        this.index = index;
        this.fileName = fileName;
    }

    @Override
    public int compareTo(@NotNull SequentialFile o) {
        return this.getIndex() - o.getIndex();
    }
}
