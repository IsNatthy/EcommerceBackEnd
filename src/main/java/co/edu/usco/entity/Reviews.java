package co.edu.usco.entity;

import co.edu.usco.dto.review.ReviewDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entity class representing a review.
 */
@Entity
@Data
public class Reviews {

    /**
     * Unique identifier for the review.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Rating given in the review.
     */
    private Long rating;

    /**
     * Description of the review.
     */
    @Lob
    @Column(name = "description")
    private String description;

    /**
     * Image associated with the review.
     */
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    /**
     * User who created the review.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    /**
     * Product that is being reviewed.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    /**
     * Converts the review entity to a ReviewDto.
     *
     * @return ReviewDto object containing review details.
     */
    public ReviewDto getReviewDto() {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(id);
        reviewDto.setRating(rating);
        reviewDto.setDescription(description);
        reviewDto.setReturnedImg(img);
        reviewDto.setProductId(product.getId());
        reviewDto.setUserId(user.getId());
        reviewDto.setUsername(user.getName());
        return reviewDto;
    }
}