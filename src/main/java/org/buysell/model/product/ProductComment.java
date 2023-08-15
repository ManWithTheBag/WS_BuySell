package org.buysell.model.product;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    @Column(name = "local_data_time")
    private LocalDateTime localDateTime;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST,}, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_product_id")
    private Product product;

    @PrePersist
    private void initLocalDataTime(){
        localDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ProductComment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
