package com.ishwar.coronatracker.Modal;

public class VaccinationModal {
    private String vaccineCenter;
    private String vaccinationCharges;
    private String vaccineAge;
    private String vaccineTimings;
    private String vaccineName;
    private String vaccineCenterTime;
    private String vaccineCenterAddress;
    private String vaccineAvailable;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String city;

    public VaccinationModal() {
    }

    public String getVaccineCenter() {
        return vaccineCenter;
    }

    public void setVaccineCenter(String vaccineCenter) {
        this.vaccineCenter = vaccineCenter;
    }

    public String getVaccinationCharges() {
        return vaccinationCharges;
    }

    public void setVaccinationCharges(String vaccinationCharges) {
        this.vaccinationCharges = vaccinationCharges;
    }

    public String getVaccineAge() {
        return vaccineAge;
    }

    public void setVaccineAge(String vaccineAge) {
        this.vaccineAge = vaccineAge;
    }

    public String getVaccineTimings() {
        return vaccineTimings;
    }

    public void setVaccineTimings(String vaccineTimings) {
        this.vaccineTimings = vaccineTimings;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineCenterTime() {
        return vaccineCenterTime;
    }

    public void setVaccineCenterTime(String vaccineCenterTime) {
        this.vaccineCenterTime = vaccineCenterTime;
    }

    public String getVaccineCenterAddress() {
        return vaccineCenterAddress;
    }

    public void setVaccineCenterAddress(String vaccineCenterAddress) {
        this.vaccineCenterAddress = vaccineCenterAddress;
    }

    public String getVaccineAvailable() {
        return vaccineAvailable;
    }

    public void setVaccineAvailable(String vaccineAvailable) {
        this.vaccineAvailable = vaccineAvailable;
    }

    public VaccinationModal(String vaccineCenter, String vaccinationCharges, String vaccineAge, String vaccineTimings, String vaccineName, String vaccineCenterTime, String vaccineCenterAddress, String vaccineAvailable) {
        this.vaccineCenter = vaccineCenter;
        this.vaccinationCharges = vaccinationCharges;
        this.vaccineAge = vaccineAge;
        this.vaccineTimings = vaccineTimings;
        this.vaccineName = vaccineName;
        this.vaccineCenterTime = vaccineCenterTime;
        this.vaccineCenterAddress = vaccineCenterAddress;
        this.vaccineAvailable = vaccineAvailable;
    }
}
