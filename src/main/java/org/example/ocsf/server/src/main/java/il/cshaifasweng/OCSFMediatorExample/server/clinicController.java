package org.example.ocsf.server.src.main.java.il.cshaifasweng.OCSFMediatorExample.server;

import org.example.Entities.Clinic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Time;
import java.util.List;

public class clinicController {

    private static List<Clinic> getAllClinicsFromDB() {
        CriteriaBuilder builder = App.session.getCriteriaBuilder();
        CriteriaQuery<Clinic> query = builder.createQuery(Clinic.class);
        query.from(Clinic.class);
        return App.session.createQuery(query).getResultList();
    }

    public static Clinic getClinicByName (String name) {
        List<Clinic> clinics = getAllClinicsFromDB();
        for (Clinic clinic : clinics) {
            if (clinic.getName().equals(name)) {
                return clinic;
            }
        }
        return null;
    }

    public static Time getOpenningHourByClinic(Clinic clinic){
        return clinic.getOpenningHour();
    }

    public static Time getClosingHourByClinic(Clinic clinic){
        return clinic.getClosingHour();
    }
}