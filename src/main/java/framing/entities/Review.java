package framing.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review", columnDefinition="TEXT")
    private String review;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Frame_profile_warehouse_balance frameprofilewarehousebalance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Frame_profile_warehouse_balance getBook() {
        return frameprofilewarehousebalance;
    }

    public void setBook(Frame_profile_warehouse_balance frameprofilewarehousebalance) {
        this.frameprofilewarehousebalance = frameprofilewarehousebalance;
    }
}
