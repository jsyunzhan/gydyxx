package domain.shiro.entity;

import java.util.Date;

//抽象公用实体类
public class AbstractEntity {



    private Long id;

    private Long createUserId;

    private Date createDate;

    private Long updateUserId;

    private Date updateDate;

    private int page ;

    private int rows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                ", createUserId=" + createUserId +
                ", createDate=" + createDate +
                ", updateUserId=" + updateUserId +
                ", updateDate=" + updateDate +
                ", page=" + page +
                ", rows=" + rows +
                '}';
    }
}
