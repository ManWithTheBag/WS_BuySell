package org.buysell.model.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;

@Entity
@Table(name = "product_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;
    private String name;
    @Column(name = "origin_file_name")
    private String originFileName;
    private Long size;
    @Column(name = "content_type")
    private String contentType;
    @Column(name = "is_preview_image")
    private boolean isPreviewImage;
    @Column(name = "image_byte", columnDefinition = "LONGBLOB")
    private byte[] imageByte;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_product_id")
    private Product product;

    public ProductImage(Long imageId, String name, String originFileName, Long size, String contentType, boolean isPreviewImage, byte[] imageByte) {
        this.imageId = imageId;
        this.name = name;
        this.originFileName = originFileName;
        this.size = size;
        this.contentType = contentType;
        this.isPreviewImage = isPreviewImage;
        this.imageByte = imageByte;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + imageId +
                ", name='" + name + '\'' +
                ", originFileName='" + originFileName + '\'' +
                ", size=" + size +
                ", contentType='" + contentType + '\'' +
                ", isPreviewImage=" + isPreviewImage +
                ", imageByte=" + Arrays.toString(imageByte) +
                '}';
    }
}
