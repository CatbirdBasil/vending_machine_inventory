package com.petprojects.vending_machine_inventory.dao.impl;

import com.petprojects.vending_machine_inventory.dao.ExtraUserDAO;
import com.petprojects.vending_machine_inventory.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Slf4j
//@Repository
public class ExtraUserDAOImpl implements ExtraUserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String LOGIN_QUERY_PARAM = "username";
    private static final String LOGIN_QUERY_PARAM_NAME = ":" + LOGIN_QUERY_PARAM;

    private static final String GET_USER_BY_LOGIN_QUERY =
            "FROM User WHERE username = " + LOGIN_QUERY_PARAM_NAME;

    @Override
    public Optional<User> getUserByUsername(String username) {
        log.info("getUserByUsername: username = {}", username);

        TypedQuery<User> query = entityManager.createQuery(GET_USER_BY_LOGIN_QUERY, User.class);

        query.setParameter(LOGIN_QUERY_PARAM, username);

        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception ex) {
            log.debug("Something went wrong during user retrieval: ", ex);
            return Optional.empty();
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        return getUserByUsername(username).isPresent();
    }
}
