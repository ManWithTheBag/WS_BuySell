package org.buysell.model.product;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Column(length = 50)
    private String title;
    @Column(length = 1000)
    private String description;
    private float price;
    @Column(length = 50)
    private String author;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductComment> productCommentList;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> productImageList;
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
                ", author='" + author + '\'' +
                ", productCommentList=" + productCommentList +
                ", productImageList=" + productImageList +
                ", previewImageID=" + previewImageID +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
