package org.buysell.service.productService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.buysell.model.product.Product;
import org.buysell.model.product.ProductImage;
import org.buysell.repository.productRepo.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductImageService {
    private ProductImageRepository imageRepository;

    public List<ProductImage> getImageListByFkProductId(long productId){
        return imageRepository.findAllByProductId(productId);
    }

    public void saveProductImages(List<MultipartFile> imageFileList, Product product, List<Long> requestedDbImageIdList) throws IOException {

        if(product.getId() != 0) {
            List<ProductImage> dbImageList = getImageListByFkProductId(product.getId());
            imageRepository.deleteAll(dbImageList);
            updateProductImage(imageFileList, product, dbImageList, requestedDbImageIdList);
        }else {
            saveNewProductImage(imageFileList, product);
        }
    }

    private void saveNewProductImage(List<MultipartFile> imageFileList, Product savedProduct) throws IOException {

        for (int i = 0; i < imageFileList.size(); i++) {
            if (imageFileList.get(i).getSize() != 0) {
                saveProcessProductImage(imageFileList, savedProduct, i);
            }
        }
    }

    private void updateProductImage(List<MultipartFile> imageFileList, Product savedProduct,
                                   List<ProductImage> dbImageList, List<Long> requestedDbImageIdList) throws IOException {

        for (int i = 0; i < imageFileList.size(); i++) {

            if (imageFileList.get(i).getSize() != 0 && requestedDbImageIdList.get(i) == 0) {
                saveProcessProductImage(imageFileList, savedProduct, i);

            }else if(imageFileList.get(i).getSize() == 0 && requestedDbImageIdList.get(i) != 0){
                for (ProductImage dbProductImage : dbImageList) {

                    long dbId = dbProductImage.getImageId();
                    long requestId =  requestedDbImageIdList.get(i);

                    if(dbId == requestId){
                        ProductImage image = new ProductImage();
                        image.setName(dbProductImage.getName());
                        image.setOriginFileName(dbProductImage.getOriginFileName());
                        image.setSize(dbProductImage.getSize());
                        image.setContentType(dbProductImage.getContentType());
                        image.setImageByte(dbProductImage.getImageByte());
                        image.setProduct(savedProduct);

                        imageRepository.save(image);
                    }
                }
            }
        }
    }

    private ProductImage toProductImageEntity(MultipartFile imageFile) throws IOException {
        ProductImage productImage = new ProductImage();
        productImage.setName(imageFile.getName());
        productImage.setOriginFileName(imageFile.getOriginalFilename());
        productImage.setSize(imageFile.getSize());
        productImage.setContentType(imageFile.getContentType());
        productImage.setImageByte(imageFile.getBytes());
        return productImage;
    }

    private void saveProcessProductImage(List<MultipartFile> imageFileList, Product savedProduct, int index) throws IOException {
        ProductImage productImage = toProductImageEntity(imageFileList.get(index));
        productImage.setProduct(savedProduct);
        imageRepository.save(productImage);
    }

    public ProductImage setPreviewImageId(Product product){
        List<ProductImage> productImageList = imageRepository.findAllByProductId(product.getId());
        if(productImageList.size() != 0){
            productImageList.get(0).setPreviewImage(true);
            return productImageList.get(0);
        }
        return null;
    }
}
