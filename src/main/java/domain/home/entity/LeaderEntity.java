package domain.home.entity;


import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaderEntity extends AbstractEntity{

    private String leaderTitle;

    private String leaderDetails;

    private String picturePath;

    @Override
    public String toString() {
        return "LeaderEntity{" +
                "leaderTitle='" + leaderTitle + '\'' +
                ", leaderDetails='" + leaderDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
