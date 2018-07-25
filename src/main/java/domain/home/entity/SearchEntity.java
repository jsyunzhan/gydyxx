package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchEntity extends AbstractEntity{

    private String tableId;

    private String url;

    private String title;


}
