package ch.hesge.cours634.exam;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class EventManager {

    public AccessEventEntity registerEntryEvent(int cardId) {
        // Sauvegarder un événement d'entrée
        return null;
    }

    public AccessEventEntity registerExitEvent(int cardId) {
        // Sauvegarder un événement de sortie
        return null;
    }

    public List<AccessEventEntity> getUserAccessEvents(String email, LocalDateTime start, LocalDateTime end) {
        // Retourne la liste d'accès pour l'utilisateur identifié par son email pour la période demandée
        return Collections.emptyList();
    }

}
