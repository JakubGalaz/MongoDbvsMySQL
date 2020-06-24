package com.app.repository.custom;

import com.app.helper.PersonInfoHelper;

import java.util.List;

public interface DoctorCustomRepository {
    List<PersonInfoHelper> countNumberOfVisitsByDoctors();
}
