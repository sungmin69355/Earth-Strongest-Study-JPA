package hellojpa;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "MBR")
public class Member {
    @Id
    private Long id;

    @Column(name = "USERNAME", unique = true, length = 10) //DDL 제약조건은 DDL을 자동 생성할 때만 사용, JPA 실행 로직에는 영향 x
    private String name;
}
