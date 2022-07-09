package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 1. 회원은 일반회원과 관리자로 구분해야한다.
 * 2. 회원가입일과 수정일이 있어야한다.
 * 3. 회원을 설명할 수 있는 필드가 있어야한다. 이 필드는 길이 제한이 없다.
 */
@Entity
@Getter
@Setter
@Table(name = "MBR")
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 1)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) //자바의 Date 타입과 DB의 컬럼매핑(현재는 사용하지않는다. 하이버네이트에서 LocalDate 지원)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;
}
