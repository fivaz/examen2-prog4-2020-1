package ch.hesge.cours634.exam;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class EventManager {

    public AccessEventEntity registerEntryEvent(int cardId) {
        // Sauvegarder un événement d'entrée
        AccessCardEntity card = JPAHelper.em().find(AccessCardEntity.class, cardId);
        AccessEventEntity accessEventEntity = new AccessEventEntity(AccessStatus.ALLOWED, LocalDateTime.now(), AccessDirection.ENTRY, card);
        JPAHelper.persist(accessEventEntity);
        return accessEventEntity;
    }

    public AccessEventEntity registerExitEvent(int cardId) {
        // Sauvegarder un événement de sortie
        AccessCardEntity card = JPAHelper.em().find(AccessCardEntity.class, cardId);
        AccessEventEntity accessEventEntity = new AccessEventEntity(AccessStatus.ALLOWED, LocalDateTime.now(), AccessDirection.EXIT, card);
        JPAHelper.persist(accessEventEntity);
        return accessEventEntity;
    }

    public List<AccessEventEntity> getUserAccessEvents(String email, LocalDateTime start, LocalDateTime end) {
        // Retourne la liste d'accès pour l'utilisateur identifié par son email pour la période demandée
        PersonEntity personEntity = JPAHelper.em().find(PersonEntity.class, email);
        AccessCardEntity card = personEntity.getCard();
        EntityManager em = JPAHelper.em();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e from AccessEventEntity e where e.card = :card");
        query.setParameter("card", card);
        List<AccessEventEntity> resultList = (List<AccessEventEntity>) query.getResultList();
        em.getTransaction().commit();
        return resultList;
    }
}
