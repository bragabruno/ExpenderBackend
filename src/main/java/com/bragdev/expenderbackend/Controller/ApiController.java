package com.bragdev.expenderbackend.Controller;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.bragdev.expenderbackend.Entity.Account;
import com.bragdev.expenderbackend.Entity.Institution;
import com.bragdev.expenderbackend.Entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final EntityManager entityManager;

    public ApiController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/institutions")
    public List<Institution> institutions() {
        String sql = "SELECT i FROM Institution i ORDER BY i.name ASC";
        TypedQuery<Institution> query = entityManager.createQuery(sql, Institution.class);
        return query.getResultList();
    }

    @GetMapping("/accounts")
    public List<Account> accounts() {
        User user = getUser();
        return user != null ? user.getAccounts() : List.of();
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<Map<String, Object>> account(@PathVariable int accountId) {
        Account account = entityManager.find(Account.class, accountId);
        User user = getUser();
        if (account == null || account.getUser().getId() != user.getId()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Map<String, Object> accountData = new HashMap<>();
        accountData.put("id", account.getId());
        accountData.put("institution", account.getInstitution().toApi());
        // TODO: add data for "accounts" key
        return new ResponseEntity<>(accountData, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody Map<String, Object> requestBody) {
        // TODO: implement authentication logic
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User getUser() {
        // TODO: implement user retrieval logic
        return null;
    }
}
