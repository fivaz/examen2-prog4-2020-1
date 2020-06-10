package ch.hesge.cours634.exam;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class AccessControlTest {

    // on utilisera pour les tests les personnes marie et fred
    @Before
    @After
    public void cleanup() {
        JPAHelper.remove(PersonEntity.class, "fred@b.com");
        JPAHelper.remove(PersonEntity.class, "marie@b.com");
    }

    @Test
    public void scenario1() {
        String email = "fred@b.com";

        JPAHelper.remove(PersonEntity.class, email);

        PersonEntity p1 = new PersonEntity();
        p1.setName("Fred");
        p1.setCategory(Category.EMPLOYEE);
        p1.setEmail(email);
        p1.setAddress("rue de Saint Julien 60, 1212 Lancy");
        JPAHelper.persist(p1);

        PersonEntity p2 = JPAHelper.em().find(PersonEntity.class, email);
        Assert.assertEquals(p1, p2);
    }

    @Test
    public void scenario2() {
        // créez Marie et lui affecter une carte valable du 01.01.2020 au 01.01.2021
        String email = "marie@b.com";
        AccessManager accessManager = new AccessManager();
        PersonEntity personExpected = accessManager.addPerson(email, "Marie", "3 Rue Cavour", Category.EMPLOYEE);
        AccessCardEntity cardCreated = accessManager.addCard(LocalDate.of(2020, 1, 1), LocalDate.of(2021, 1, 1));
        accessManager.assignCardToUser(cardCreated.getCardId(), personExpected.getEmail());
        // vérifiez que la personne existe et qu'elle posséde bien la carte
        PersonEntity personGot = JPAHelper.em().find(PersonEntity.class, email);
        AccessCardEntity cardGot = JPAHelper.em().find(AccessCardEntity.class, cardCreated.getCardId());
        Assert.assertEquals(personExpected, personGot);
        Assert.assertEquals(cardGot, personGot.getCard());
    }

    @Test
    public void scenario3() {
        // Reprenez scenario 2
        String email = "marie@b.com";
        LocalDate startValidity = LocalDate.of(2020, 1, 1);
        LocalDate endValidity = LocalDate.of(2021, 1, 1);
        AccessManager accessManager = new AccessManager();
        PersonEntity personExpected = accessManager.addPerson(email, "Marie", "3 Rue Cavour", Category.EMPLOYEE);
        AccessCardEntity cardCreated = accessManager.addCard(startValidity, endValidity);
        accessManager.assignCardToUser(cardCreated.getCardId(), personExpected.getEmail());
        // La personne effectue plusieurs entrées et sorties
        accessManager.enter(cardCreated.getCardId());
        accessManager.exit(cardCreated.getCardId());
        accessManager.enter(cardCreated.getCardId());
        accessManager.exit(cardCreated.getCardId());
        accessManager.enter(cardCreated.getCardId());
        accessManager.exit(cardCreated.getCardId());
        // Vérifiez que le système a enregistré les bons événements
        List<AccessEventEntity> userAccessEvents = new EventManager().getUserAccessEvents(email, startValidity.atStartOfDay(), endValidity.atStartOfDay());
        System.out.println(userAccessEvents.size());
        Assert.assertEquals(6, userAccessEvents.size());
    }
}
