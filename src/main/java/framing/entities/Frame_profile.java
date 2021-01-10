package framing.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "frame_profile")
public class Frame_profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long frame_id;

    @Column(name = "frame_name")
    private String frame_name;

    @Column(name = "frame_bar_std_length")
    private Integer frame_bar_std_length;

    @OneToMany(mappedBy = "author")
    private Set<Frame_profile_warehouse_balance> frameprofilewarehousebalance = new HashSet<>();

    public Frame_profile() {
    }

    public Frame_profile(String frame_name, Integer frame_bar_std_length) {
        this.frame_name = frame_name;
        this.frame_bar_std_length = frame_bar_std_length;
    }

    public Long getId() {
        return frame_id;
    }

    public void setId(Long id) {
        this.frame_id = frame_id;
    }

    public String getframe_name() {
        return frame_name;
    }

    public void setframe_name(String frame_name) {
        this.frame_name = frame_name;
    }

    public Integer getframe_bar_std_length() {
        return frame_bar_std_length;
    }

    public void setframe_bar_std_length(Integer frame_bar_std_length) {
        this.frame_bar_std_length = frame_bar_std_length;
    }

    public String getFullName() {
        return frame_name + " " + frame_bar_std_length;
    }

    public Set<Frame_profile_warehouse_balance> getFrame_profile_warehouse_balance() {
        return frameprofilewarehousebalance;
    }

    public void setFrame_profile_warehouse_balance(Set<Frame_profile_warehouse_balance> frameprofilewarehousebalance) {
        this.frameprofilewarehousebalance = frameprofilewarehousebalance;
    }
}
