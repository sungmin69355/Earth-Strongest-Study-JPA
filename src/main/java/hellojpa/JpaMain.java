package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args){
        //웹서버 띄우는 시점에 한번만 올라온다.
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");

        //쓰레드간에 공유x(사용하고 버리자)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        try{
            //비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("hello");

            //영속 (이 시점에 DB에 저장 x)
            em.persist(member);

//            List<Member> findMembers = em.createQuery("select m from Member as m", Member.class)
//                            .getResultList();
//
//            for( Member member : findMembers){
//                System.out.println("member.name = " + member.getName());
//            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
