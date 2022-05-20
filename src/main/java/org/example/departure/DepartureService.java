package org.example.departure;

import lombok.RequiredArgsConstructor;
import org.example.postoffice.PostOffice;
import org.example.postoffice.PostOfficeService;
import org.example.user.UserService;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartureService {
    private final SessionFactory sessionFactory;
    private Session session;

    private final PostOfficeService postOfficeService;

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }

    public List<Departure> getDepartures() {
        return session.createQuery("select u from Departure u",
                Departure.class).getResultList();
    }

    public Departure getDeparture(Long id){
        return session.get(Departure.class,id);
    }

    public Departure addDeparture(DepartureOnload onload) {
        Departure departure = new Departure(onload.getType(), onload.getDepartureDate());
        departure.setOffice(postOfficeService.getOffice(onload.getOfficeId()));
        var transaction = session.beginTransaction();
        session.saveOrUpdate(departure);
        transaction.commit();
        return departure;
    }

    public Departure modifyDeparture(DepartureOnload onload, Long id) {
        try {
            var transaction = session.beginTransaction();
            Departure departureFromDB = session.load(Departure.class, id);
            departureFromDB.setType(onload.getType());
            departureFromDB.setDepartureDate(onload.getDepartureDate());
            session.update(departureFromDB);
            transaction.commit();
            return departureFromDB;
        } catch (ObjectNotFoundException exception) {
            return null;
        }

    }

    public Departure deleteDeparture(Long id) {
        try {
            var transaction = session.beginTransaction();
            Departure departure = session.load(Departure.class, id);
            session.delete(departure);
            transaction.commit();
            return departure;
        } catch (ObjectNotFoundException exception) {
            return null;
        }
    }
}
