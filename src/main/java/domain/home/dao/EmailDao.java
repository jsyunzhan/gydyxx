package domain.home.dao;

import domain.home.entity.EmailEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailDao {
    Integer emailCount(EmailEntity emailEntity);

    List<EmailEntity> emailList(EmailEntity emailEntity);

    List<EmailEntity> emailAllList(EmailEntity emailEntity);
}
