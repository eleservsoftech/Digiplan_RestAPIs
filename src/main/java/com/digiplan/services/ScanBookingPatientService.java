package com.digiplan.services;

import com.digiplan.entities.ScanBookingPatient;

import java.util.List;

public interface ScanBookingPatientService {
    List<ScanBookingPatient> getAllScanBookingPatients();

    ScanBookingPatient getScanBookingPatientById(String scanId);

    ScanBookingPatient createScanBookingPatient(ScanBookingPatient scanBookingPatient);

    ScanBookingPatient updateScanBookingPatient(String scanId, ScanBookingPatient updatedScanBookingPatient);

    boolean deleteScanBookingPatient(String scanId);
}
