package org.example.postoffice;

import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostOfficeService {
    private final SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }

    public List<PostOffice> getOffices() {
        return session.createQuery("select u from PostOffice u",
                PostOffice.class).getResultList();
    }

    public PostOffice getOffice(Long id){
        return session.get(PostOffice.class,id);
    }

    public PostOffice addOffice(PostOfficeOnload onload) {
        PostOffice postOffice = new PostOffice(onload.getOfficeName(),onload.getCityName());
        var transaction = session.beginTransaction();
        session.saveOrUpdate(postOffice);
        transaction.commit();
        return postOffice;
    }

    public PostOffice modifyOffice(PostOfficeOnload onload, Long id) {
        try {
            var transaction = session.beginTransaction();
            PostOffice officeFromDB = session.load(PostOffice.class, id);
            officeFromDB.setOfficeName(onload.getOfficeName());
            officeFromDB.setCityName(onload.getCityName());
            session.update(officeFromDB);
            transaction.commit();
            return officeFromDB;
        } catch (ObjectNotFoundException exception) {
            return null;
        }

    }

    public PostOffice deleteOffice(Long id) {
        try {
            var transaction = session.beginTransaction();
            PostOffice postOffice = session.load(PostOffice.class, id);
            session.delete(postOffice);
            transaction.commit();
            return postOffice;
        } catch (ObjectNotFoundException exception) {
            return null;
        }
    }
}
