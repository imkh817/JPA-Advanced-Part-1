package jpabook.jpashop.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // mappedBy = "member" : Order 테이블의 member에 의해서 맵핑됏다는 뜻, 주인이 아니라는 뜻
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
