package domain.wages.service;

import domain.wages.entity.WagesEntity;
import domain.wages.entity.WagesQueryEntity;

import java.util.List;

public interface WagesQueryManagementService {
    List<WagesEntity> wagesList(WagesQueryEntity wagesQueryEntity);
}
