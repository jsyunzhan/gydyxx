package domain.shiro.entity;

public class JsonResponseVO {

    private Boolean success;

    private String reason;

    public JsonResponseVO(){

    }

    public JsonResponseVO(Boolean success){
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}
