package org.example.api;

import org.example.model.View;

public interface Inflater {
    void inflate(String xml, String pathProject) throws Exception;
    void destroyWindow();
    View getElementById(String id);
}
