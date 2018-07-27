package domain.wages.service;

import domain.wages.entity.WagesEntity;

import java.util.List;

public interface WagesQueryManagementService {
    List<WagesEntity> wagesList(Long loginId);
}
