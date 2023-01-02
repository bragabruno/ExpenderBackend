package com.bragdev.expenderbackend.Entity;

import java.util.Map;

public interface Exportable {
    public Map<String, Object> toApi();
}