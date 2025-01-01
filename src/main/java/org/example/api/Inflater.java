package org.example.api;

public interface Inflater {
    void inflate(String xml) throws Exception;
    void destroyWindow();
}
