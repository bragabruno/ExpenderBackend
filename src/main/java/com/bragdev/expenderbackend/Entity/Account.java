package com.bragdev.expenderbackend.Entity;

;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@Entity(name = "Account")
@Table(name = "accounts")
public class Account implements Exportable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "institution_id")
    @ManyToOne
    private Institution institution;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "access_token")
    private String accessToken;

    public int getId() {
        return id;
    }

    public Institution getInstitution() {
        return institution;
    }

    public User getUser() {
        return user;
    }

    public String getAccessToken() {
        return accessToken;
    }


    @Override
    public Map<String, Object> toApi() {
        Map<String, Object> apiData = new HashMap<>();
        apiData.put("id", this.getId());
        apiData.put("institution", this.getInstitution().toApi());
        return apiData;
    }

}
