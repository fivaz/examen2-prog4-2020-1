package ch.hesge.cours634.exam;

import java.time.LocalDate;
import java.util.List;

public class AccessManager {

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
        List<AccessCardEntity> cards = person.getCards();
        cards.add(card);
        card.setOwner(person);
        JPAHelper.merge(person);
        return card;
    }

    public void enter(int cardId) {
        // Si la carte est valide, alors on génére un évenement d'entrée, sinon on log le refus d'entrer
    }

    public void exit(int cardId) {
        // Si la carte est valide, alors on génére un évenement de sortie, sinon on log le refus de sortir
    }

    public boolean isValide(int cardId) {
        // Verifiez que la carte n'est pas désactivée et que la date du jour
        // est bien dans la période de validité de la carte
        // retourne true si la carte est valide, false sinon
        return false;
    }

}
