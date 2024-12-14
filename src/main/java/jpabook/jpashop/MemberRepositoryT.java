package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepositoryT {

    @PersistenceContext
    private EntityManager em;

    public long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findOne(long id) {
        return em.find(Member.class, id);
    }
}
