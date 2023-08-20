package org.buysell.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.buysell.model.product.Product;

@Entity
@Table(name = "user_image")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserImage {

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
    @Column(name = "image_byte", columnDefinition = "LONGBLOB")
    private byte[] imageByte;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user_id")
    private User user;
}
