    DROP TABLE IF EXISTS Users;

    CREATE TABLE IF NOT EXISTS Users(
        user_id INTEGER PRIMARY KEY AUTOINCREMENT,
        user_name VARCHAR NOT NULL,
        user_password VARCHAR NOT NULL,
        user_age INTEGER NOT NULL,
        user_gender VARCHAR NOT NULL,
        user_role VARCHAR NOT NULL
    );

    CREATE TABLE IF NOT EXISTS Appointments(
        appointment_id INTEGER PRIMARY KEY AUTOINCREMENT,
        patient_id INTEGER,
        doctor_id INTEGER,
        appointment_date VARCHAR NOT NULL,
        appointment_start_time VARCHAR NOT NULL,
        appointment_end_time VARCHAR NOT NULL,
        appointment_location VARCHAR NOT NULL,
        appointment_status VARCHAR NOT NULL,
        FOREIGN KEY (patient_id) REFERENCES Users(user_id),
        FOREIGN KEY (doctor_id) REFERENCES Users(user_id)
    );

    CREATE TABLE IF NOT EXISTS Prescriptions(
        prescription_id INTEGER PRIMARY KEY AUTOINCREMENT,
        prescription_name VARCHAR NOT NULL,
        prescription_dosage REAL NOT NULL,
        prescription_date VARCHAR NOT NULL,
        prescription_frequency INT NOT NULL,
        prescription_condition VARCHAR NOT NULL,
        prescription_target INTEGER,
        FOREIGN KEY(prescription_target) REFERENCES Users(user_id)
    );

    INSERT INTO Users VALUES (1001, 'WAN WEI SIANG', 'test01', 21, 'Male', 'Patient');
    INSERT INTO Users VALUES (1002, 'ELSA', 'test02', 20, 'Female', 'Doctor');
    INSERT INTO Users VALUES (1003, 'SHAWN HUANG QI YANG', 'test03', 20, 'Male', 'Receptionist');
    INSERT INTO Users VALUES (1004, 'SYED ZAKI HUSAIN WAFA BIN SYED RIYAD REZA', 'test04', 25, 'Male', 'Admin');

    INSERT OR IGNORE INTO Appointments VALUES (2001, 1001, 1002, '2026-06-04', '12:58:00', '14:58:00', "Elsa's Office", 'Scheduled');   
