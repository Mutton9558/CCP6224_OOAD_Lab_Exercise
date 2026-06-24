CREATE TABLE IF NOT EXISTS Users(
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_name VARCHAR NOT NULL,
    user_password VARCHAR NOT NULL,
    user_dob VARCHAR NOT NULL,
    user_gender VARCHAR NOT NULL,
    user_role VARCHAR NOT NULL,
    office VARCHAR,
    specialization VARCHAR
);

CREATE TABLE IF NOT EXISTS Appointments(
    appointment_id INTEGER PRIMARY KEY AUTOINCREMENT,
    patient_id INTEGER,
    doctor_id INTEGER,
    appointment_date VARCHAR NOT NULL,
    appointment_time VARCHAR NOT NULL,
    appointment_location VARCHAR NOT NULL,
    appointment_status VARCHAR NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES Users(user_id),
    FOREIGN KEY (doctor_id) REFERENCES Users(user_id)
);

CREATE TABLE IF NOT EXISTS Prescriptions(
    prescription_id INTEGER PRIMARY KEY AUTOINCREMENT,
    prescription_name VARCHAR NOT NULL,
    prescription_dosage VARCHAR NOT NULL,
    prescription_date VARCHAR NOT NULL,
    prescription_frequency VARCHAR NOT NULL,
    prescription_condition VARCHAR NOT NULL,
    prescription_target INTEGER,
    FOREIGN KEY(prescription_target) REFERENCES Users(user_id)
);

INSERT OR IGNORE INTO Users VALUES (1001, 'WAN WEI SIANG', 'test01', '2006-01-01', 'Male', 'Patient', NULL, NULL);
INSERT OR IGNORE INTO Users VALUES (1002, 'ELSA', 'test02', '2006-01-01', 'Female', 'Doctor', 'CQBR1001', 'Neurologist');
INSERT OR IGNORE INTO Users VALUES (1003, 'SHAWN HUANG QI YANG', 'test03', '2006-01-01', 'Male', 'Receptionist', NULL, NULL);
INSERT OR IGNORE INTO Users VALUES (1004, 'SYED ZAKI HUSAIN WAFA BIN SYED RIYAD REZA', 'test04', '2006-01-01', 'Male', 'Admin', NULL, NULL);

INSERT OR IGNORE INTO Appointments VALUES (2001, 1001, 1002, '2026-06-04', '12:58:00', "Elsa's Office", 'Scheduled');   

INSERT OR IGNORE INTO Prescriptions VALUES (3001, 'Panadol', '100ml', '2026-06-04', 'Twice a day', 'Fever', 1001);