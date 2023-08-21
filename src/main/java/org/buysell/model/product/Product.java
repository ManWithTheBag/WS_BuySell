package org.buysell.model.product;

import jakarta.persistence.*;
import lombok.*;
import org.buysell.model.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title", length = 50)
    private String title;
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "price")
    private float price;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductComment> productCommentList;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> productImageList;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private User user;
    @Column(name = "preview_image_id")
    private long previewImageID;

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;


    @PrePersist
    public void initLocalDataTime(){
        localDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", productCommentList=" + productCommentList +
                ", productImageList=" + productImageList +
                ", previewImageID=" + previewImageID +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
