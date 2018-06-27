package domain.shiro.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageQueryResult {

    private List rows;

    private Integer total;

    @Override
    public String toString() {
        return "PageQueryResult{" +
                "rows=" + rows +
                ", total=" + total +
                '}';
    }
}
