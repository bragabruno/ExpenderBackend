package com.bragdev.expenderbackend.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "institutions", indexes = {
        @Index(name = "name_idx", columnList = "name")
})
public class Institution implements Exportable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "code", length = 255)
    private String code;

    public int getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public Map<String, Object> toApi() {
        Map<String, Object> apiData = new HashMap<>();
        apiData.put("id", this.getId());
        apiData.put("name", this.getName());
        apiData.put("code", this.getCode());
        return apiData;
    }

}

