package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartyEntity extends AbstractEntity{

    //党建工会标题
    private String partyTitle;

    //党建工会正文
    private String partyDetails;

    @Override
    public String toString() {
        return "PartyEntity{" +
                "partyTitle='" + partyTitle + '\'' +
                ", partyDetails='" + partyDetails + '\'' +
                '}';
    }
}
