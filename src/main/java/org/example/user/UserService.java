package org.example.user;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService {
    private final SessionFactory sessionFactory;
    private Session session;
    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }
    public List<User> getUsers() {
        return session.createQuery("select u from User u",
            User.class).getResultList();
    }

    public User addUser(UserOnload onload){
        User user = new User(onload.getFirstName(),onload.getLastName());
        var transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        return user;
    }

}
