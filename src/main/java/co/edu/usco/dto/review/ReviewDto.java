package co.edu.usco.dto.review;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Data Transfer Object (DTO) for Review.
 */
@Data
public class ReviewDto {

    /**
     * The unique identifier of the review.
     */
    private Long id;

    /**
     * The rating given in the review.
     */
    private Long rating;

    /**
     * The description of the review.
     */
    private String description;

    /**
     * The image file associated with the review.
     */
    private MultipartFile img;

    /**
     * The image data returned as a byte array.
     */
    private byte[] returnedImg;

    /**
     * The unique identifier of the user who wrote the review.
     */
    private Long userId;

    /**
     * The username of the user who wrote the review.
     */
    private String username;

    /**
     * The unique identifier of the product being reviewed.
     */
    private Long productId;

}