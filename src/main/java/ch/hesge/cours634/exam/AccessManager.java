package ch.hesge.cours634.exam;

import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AccessManager {

    private Logger logger = Logger.getLogger(AccessManager.class);

    public PersonEntity addPerson(String email, String name, String address, Category category) {
        // Ajouter une personne et la retourner
        PersonEntity personEntity = new PersonEntity(email, name, address, category);
        JPAHelper.persist(personEntity);
        return personEntity;
    }

    public AccessCardEntity addCard(LocalDate startValidity, LocalDate endValidity) {
        // Créer une nouvelle carte et la retourner
        AccessCardEntity accessCardEntity = new AccessCardEntity(startValidity, endValidity, false);
        JPAHelper.persist(accessCardEntity);
        return accessCardEntity;
    }

    public AccessCardEntity assignCardToUser(int cardId, String personEmail) {
        // La carte et la personne doivent exister au préalable. On active la carte à l'affectation.
        PersonEntity person = JPAHelper.em().find(PersonEntity.class, personEmail);
        AccessCardEntity card = JPAHelper.em().find(AccessCardEntity.class, cardId);
        card.setEnabled(true);
        //TODO CHECK CASCADE
        JPAHelper.merge(card);
        person.setCard(card);
        JPAHelper.merge(person);
        return card;
    }

    public void enter(int cardId) {
        // Si la carte est valide, alors on génére un évenement d'entrée, sinon on log le refus d'entrer
        if (isValide(cardId)) {
            AccessEventEntity accessEventEntity = new EventManager().registerEntryEvent(cardId);
            System.out.println(accessEventEntity);
        } else {
            logger.error("Carte non autorisée .... pour entrer");
        }
    }

    public void exit(int cardId) {
        // Si la carte est valide, alors on génére un évenement d'entrée, sinon on log le refus d'entrer
        if (isValide(cardId)) {
            AccessEventEntity accessEventEntity = new EventManager().registerExitEvent(cardId);
            System.out.println(accessEventEntity);
        } else {
            logger.error("Carte non autorisée .... pour sortir");
        }
    }

    public boolean isValide(int cardId) {
        // Verifiez que la carte n'est pas désactivée et que la date du jour// est bien dans la période de validité de la carte
        // retourne true si la carte est valide, false sinon
        LocalDate now = LocalDate.now();
        AccessCardEntity card = JPAHelper.em().find(AccessCardEntity.class, cardId);
        return (card != null && card.isEnabled() && card.getEndValidity().isAfter(now) && card.getStartValidity().isBefore(now));
    }
}
