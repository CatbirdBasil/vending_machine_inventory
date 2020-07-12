package com.petprojects.vending_machine_inventory.dao.impl;

import com.petprojects.vending_machine_inventory.dao.ExtraProductSlotDAO;
import com.petprojects.vending_machine_inventory.dao.ExtraUserDAO;
import com.petprojects.vending_machine_inventory.model.ProductSlot;
import com.petprojects.vending_machine_inventory.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Slf4j
@Repository
public class ExtraProductSlotDAOImpl  implements ExtraProductSlotDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String SLOT_NAME_QUERY_PARAM = "slot_name";
    private static final String SLOT_NAME_QUERY_PARAM_NAME = ":" + SLOT_NAME_QUERY_PARAM;

    private static final String GET_PRODUCT_SLOT_BY_NAME_QUERY =
            "FROM ProductSlot WHERE name = " + SLOT_NAME_QUERY_PARAM_NAME;

    @Override
    public Optional<ProductSlot> getProductSlotByName(String slotName) {
        log.info("getProductSlotByName: slotName = {}", slotName);

        TypedQuery<ProductSlot> query = entityManager.createQuery(GET_PRODUCT_SLOT_BY_NAME_QUERY, ProductSlot.class);

        query.setParameter(SLOT_NAME_QUERY_PARAM, slotName);

        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception ex) {
            log.debug("Something went wrong during slot retrieval: ", ex);
            return Optional.empty();
        }
    }
}
