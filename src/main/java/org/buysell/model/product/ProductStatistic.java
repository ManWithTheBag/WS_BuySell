package org.buysell.model.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_statistic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "viewed_phone")
    private int viewedPhone;

    public int incrementViewedPhone(){
        return viewedPhone++;
    }

}
