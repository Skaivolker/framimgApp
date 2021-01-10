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
@Table(name = "frame_profile_warehouse_balance")
public class Frame_profile_warehouse_balance {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String frame_id;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Frame_profile frameprofile;

    public Frame_profile_warehouse_balance() {
    }

    public Frame_profile_warehouse_balance(String frame_id, String description, Frame_profile frameprofile) {
        this.frame_id = frame_id;
        this.description = description;
        this.frameprofile = frameprofile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getframe_id() {
        return frame_id;
    }

    public void setframe_id(String frame_id) {
        this.frame_id = frame_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Frame_profile getAuthor() {
        return frameprofile;
    }

    public void setAuthor(Frame_profile frameprofile) {
        this.frameprofile = frameprofile;
    }
}
