package com.bragdev.expenderbackend.Controller;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import com.bragdev.expenderbackend.Entity.Exportable;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class BaseController {

    protected ResponseEntity<Object> renderJson(Object data) {
        if (data == null || (!(data instanceof Map) && !(data instanceof List))) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        List<Object> exportedData = new ArrayList<>();
        if (data instanceof Map) {
            Map<Object, Object> mapData = (Map<Object, Object>) data;
            for (Map.Entry<Object, Object> entry : mapData.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Exportable) {
                    exportedData.add(((Exportable) value).toApi());
                } else {
                    exportedData.add(value);
                }
            }
        } else {
            List<Object> listData = (List<Object>) data;
            for (Object value : listData) {
                if (value instanceof Exportable) {
                    exportedData.add(((Exportable) value).toApi());
                } else {
                    exportedData.add(value);
                }
            }
        }
        return new ResponseEntity<>(exportedData, HttpStatus.OK);
    }
}