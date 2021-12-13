package org.example.ocsf.server;

import

public class clinicController {

    private static List<Clinic> getAllClinicsFromDB() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Clinic> query = builder.createQuery(Clinic.class);
        query.from(Clinic.class);
        return session.createQuery(query).getResultList();
    }

    public static void getClinicByName (String name) {
        List<Clinic> clinics = getAllClinicsFromDB();
        for (Clinic clinic : clinics) {
            if (clinic.getName().equals(name)) {
                return clinic;
            }
        }
        return null;
    }

    public Time getOpenningHourByClinic(Clinic clinic){
        return clinic.getOpenningHour();
    }

    public Time getClosingHourByClinic(Clinic clinic){
        return clinic.getClosingHour();
    }
}