package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchEntity extends AbstractEntity{

    private String tableId;

    private String tableName;

    private String title;

    private String details;

    private String picturePath;

    private String mainChar;

    @Override
    public String toString() {
        return "SearchEntity{" +
                "title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
