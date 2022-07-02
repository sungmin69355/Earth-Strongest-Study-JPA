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
            //영속
            Member member = em.find(Member.class, 100L);
            member.setName("hello1111");

            //JPA가 관리x 준영속성상태
            em.detach(member);

            //완전히 초기화해서 영속성컨텍스트를 통으로 지워줌 (Test 코드짤 때 유용)
            em.clear();

            tx.commit();
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
