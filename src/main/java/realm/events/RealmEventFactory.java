package realm.events;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class RealmEventFactory {

    private HashMap<String, Class<AbstractRealmEvent>> eventTypes;

    public RealmEventFactory() {
        eventTypes = new HashMap<>();

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(RealmEvent.class));
        for (BeanDefinition beanDef : provider.findCandidateComponents("realm.events")) {
            Class realmEventClass = null;
            try {
                realmEventClass = Class.forName(beanDef.getBeanClassName());
            } catch (ClassNotFoundException exception) {
                System.err.println("Class not found while scanning for RealmEvent annotation: " + beanDef.getBeanClassName());
                System.exit(-1);
            }

            if (!AbstractRealmEvent.class.isAssignableFrom(realmEventClass)) {
                System.err.println("Improper class tagged as RealmEvent: " + beanDef.getBeanClassName());
                System.exit(-1);
            }
            RealmEvent realmEvent = (RealmEvent) realmEventClass.getAnnotation(RealmEvent.class);
            eventTypes.put(realmEvent.value(), realmEventClass);
        }
    }

    public AbstractRealmEvent getRealmEvent(String gameId, String playerId, String json) {
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        String eventType = jsonObject.get("realmEventType").getAsString();

        if (eventTypes.containsKey(eventType)) {
            AbstractRealmEvent abstractRealmEvent = gson.fromJson(jsonObject.get("realmEvent"), eventTypes.get(eventType));
            abstractRealmEvent.setGameId(gameId);
            abstractRealmEvent.setPlayerId(playerId);
            return abstractRealmEvent;
        } else {
            System.err.println("Could not find matching realmEventType: " + eventType);
            return null;
        }
    }
}
