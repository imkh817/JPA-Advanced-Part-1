package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// 상속 전략
// 1. SINGLE_TABLE : 상속 구조의 모든 클래스를 하나의 테이블에 저장
// 2. JOINED : 부모 클래스와 자식 클래스마다 각각의 테이블을 생성하고, 상속 관계를 조인으로 표현
// 3. TABLE_PER_CLASS : 자식 클래스마다 개별적인 테이블을 생성, 부모 테이은 만들지 않음
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // == 비즈니스 로직 == //
    // stock 증가
    public void addStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }

    // stock 감소
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
