package org.example.dog;

import org.example.user.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class DogService {
    private final SessionFactory sessionFactory;
    private Session session;
    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }
    public User getUserByDog(Long dogId) {
        return session.createQuery("from Dog where id =:id", Dog.class)
                .setParameter("id",dogId).getSingleResult().getUser();
    }
}

