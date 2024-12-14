package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    // Enum 타입에는 해당 애노테이션을 붙여준다.
    // EnumType 에는 ORDINAL 와 STRING이 있는데 ORDINAL은 숫자 타입으로 들어가게 된다.
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;  // READY, COMP
}
