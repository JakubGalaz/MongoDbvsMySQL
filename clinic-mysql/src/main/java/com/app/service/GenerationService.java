package com.app.service;

import com.app.model.*;
import com.app.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class GenerationService {

    public static Integer NUMBER_OF_ADDRESSES = 16000;
    public static Integer NUMBER_OF_PATIENTS = 4000;
    public static Integer NUMBER_OF_DOCTORS = 640;
    public static Integer NUMBER_OF_MEDICINES = 1600;
    public static Integer NUMBER_OF_MEDICAL_VISITS = 16000;

    private final PharmaceuticalCompanyRepository pharmaceuticalCompanyRepository;
    private final MedicineRepository medicineRepository;
    private final AddressRepository addressRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final MedicalVisitRepository medicalVisitRepository;
    private final PrescriptionItemRepository prescriptionItemRepository;
    private final OutpatientClinicRepository outpatientClinicRepository;

    private GenerationService(MedicineRepository medicineRepository, PharmaceuticalCompanyRepository pharmaceuticalCompanyRepository, AddressRepository addressRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, MedicalVisitRepository medicalVisitRepository, PrescriptionItemRepository prescriptionItemRepository, OutpatientClinicRepository outpatientClinicRepository) {
        this.medicineRepository = medicineRepository;
        this.pharmaceuticalCompanyRepository = pharmaceuticalCompanyRepository;
        this.addressRepository = addressRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.medicalVisitRepository = medicalVisitRepository;
        this.prescriptionItemRepository = prescriptionItemRepository;
        this.outpatientClinicRepository = outpatientClinicRepository;
    }

    private void generateAddresses(){
        String[] cities = {"Kielce", "Warszawa", "Jurkowice", "Sopot", "Gdańsk", "Wąbrzeźno", "Wałbrzych", "Pajęczno"};
        String[] postCodes = {"25-324", "02-758", "28-210", "80-001", "80-007", "87-200", "58-100", "98-330"};
        String[] streets = {"Łódzka", "Warszawska", "Studencka", "Marszałkowska", "Spacerowa", "Kozia", "Jana Pawła II",
                "Chopina", "Programistyczna", "Obiektowa", "Mąchocka", "Kielecka"};
        int cityNr;

        for(int i = 0; i < NUMBER_OF_ADDRESSES; i++ ) {
            cityNr = ThreadLocalRandom.current().nextInt(0, cities.length);
            addressRepository.save(new Address(
                    cities[cityNr],
                    streets[ThreadLocalRandom.current().nextInt(0, streets.length)],
                    String.valueOf(ThreadLocalRandom.current().nextInt(0, 300)),
                    String.valueOf(ThreadLocalRandom.current().nextInt(0, 40)),
                    postCodes[cityNr],
                    cities[cityNr]
            ));
        }
    }

    private void generatePatientsAndDoctors(){
        String[] firstNames = {"Kuba", "Małgorzata", "Aleksander", "Wojciech", "Tomasz", "Kamil", "Wiktoria", "Maciek", "Krzysztof", "Grzegorz", "Marian", "Ignacy", "Andrzej", "Marcin", "Bartłomiej"};
        String[] lastNames = {"Nowak", "Kowalski", "Gałązka", "Gołąbek", "Krawczyk", "Iksinski", "Mickiewicz", "Sienkiewicz", "Lewandowski", "Błaszczykowski", "Piątek", "Nowak", "Javovski"};
        int basicPesel = 10100000;
        String[] telephones = {"+48611445123", "+48611445133", "+48611245123", "+48611445123"};
        String[] specializations = {"Dentysta", "Internista", "Dermatolog", "Ortopeda"};
        int nameRand, lastNameRand;
        List<Address> addressList = addressRepository.findAll();

        for(int i = 0; i < NUMBER_OF_PATIENTS; i++) {
            nameRand= ThreadLocalRandom.current().nextInt(0, firstNames.length);
            lastNameRand = ThreadLocalRandom.current().nextInt(0, lastNames.length);
            patientRepository.save(new Patient(
                            firstNames[nameRand],
                            lastNames[lastNameRand],
                            "900" + Integer.toString(basicPesel),
                            firstNames[nameRand].toLowerCase().replace("ł","l") + "." +
                                    lastNames[lastNameRand].toLowerCase().replace("ł","l").replace("ą","a") + "@tu.kielce.pl",
                            addressList.get(ThreadLocalRandom.current().nextInt(0, addressList.size())),
                            telephones[ThreadLocalRandom.current().nextInt(0, telephones.length)]
                    )
            );
            basicPesel++;
        }

        basicPesel = 10100000;

        for(int i = 0; i < NUMBER_OF_DOCTORS; i++) {
            nameRand= ThreadLocalRandom.current().nextInt(0, firstNames.length);
            lastNameRand = ThreadLocalRandom.current().nextInt(0, lastNames.length);
            doctorRepository.save(new Doctor(
                    firstNames[nameRand],
                    lastNames[lastNameRand],
                    "700" + Integer.toString(basicPesel),
                    firstNames[nameRand].toLowerCase().replace("ł","l") + "." +
                            lastNames[lastNameRand].toLowerCase().replace("ł","l").replace("ą","a") + "@tu.kielce.pl",
                    addressList.get(ThreadLocalRandom.current().nextInt(0, addressList.size())),
                            specializations[ThreadLocalRandom.current().nextInt(0, telephones.length)]
                    )
            );
            basicPesel++;
        }

    }

    private void generateCompanies(){
        List<Address> addressList = addressRepository.findAll();
        String[] pharmaceuticalCompanyNames = {"Bayer", "Firma C", "Pfizer", "Roche", "Novartis", "Merck", "GSK", "AbbVie", "AstraZeneca"};
        String[] pharmaceuticalCompanyNIPs = {"1111111111", "32233111111", "4444111441", "2222222222", "3333333333", "4444444444", "5555555555", "6666666666", "7777777777"};
//        String medicineNames[] = {"Apap", "Holinex", "Nospa", "Etopiryna", "Ibufen", "Metafen", "Acatar", "Katarex", "Wofex", "Bronex", "Bibex"};


        for(int i = 0; i < pharmaceuticalCompanyNames.length; i++){
            pharmaceuticalCompanyRepository.save(
                    new PharmaceuticalCompany(
                            pharmaceuticalCompanyNames[i],
                            pharmaceuticalCompanyNIPs[i],
                            addressList.get(ThreadLocalRandom.current().nextInt(0, addressList.size()))
                    )
            );
        }
    }

    private void generateMedicines() {
        String[] diseases = {"Zatwardzenie", "Biegunka", "Wymioty", "Migrena", "Zapalenie zatok", "Zapalenie żołądka", "Zapalenie dróg moczowych", "Grypa", "Przeziębienie", "Ból brzucha", "Kaszel"};
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int numberOfLetters;
        StringBuilder builder;
        List<PharmaceuticalCompany> pharmaceuticalCompanyList = pharmaceuticalCompanyRepository.findAll();

        for (int i = 0; i < NUMBER_OF_MEDICINES; i++) {
            numberOfLetters = ThreadLocalRandom.current().nextInt(3, 13);
            builder = new StringBuilder();
            while (numberOfLetters-- != 0) {
                int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            }
                medicineRepository.save(
                        new Medicine(
                                builder.toString(),
                                diseases[ThreadLocalRandom.current().nextInt(0, diseases.length)],
                                BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1d, 20d)),
                                pharmaceuticalCompanyList.get(ThreadLocalRandom.current().nextInt(0, pharmaceuticalCompanyList.size()))
                        )
                );
        }
    }


    private void generateOutpatientClinics(){
        List<Address> addressList = addressRepository.findAll();
        String[] outpatientClinicNames = {"Wesoła", "Smutna", "Kinika dr Dree", "Obiektowa"};
        String[] outpatientClinicTypes = {"Publiczna", "Prywatna"};
        for (String outpatientClinicName : outpatientClinicNames) {
            outpatientClinicRepository.save(
                    new OutpatientClinic(
                            outpatientClinicName,
                            outpatientClinicTypes[ThreadLocalRandom.current().nextInt(0, outpatientClinicTypes.length)],
                            addressList.get(ThreadLocalRandom.current().nextInt(0, addressList.size()))
                    )
            );
        }
    }

    private void generateMedicalVisits(){

        List <Patient> patients = patientRepository.findAll();
        List <Doctor> doctors = doctorRepository.findAll();
        List <OutpatientClinic> outpatientClinics = outpatientClinicRepository.findAll();
        List <Medicine> medicines = medicineRepository.findAll();
        Date randomDate;
        int numberOfMedicines;
        LocalDate startDate = LocalDate.of(2015, 1, 1);
        MedicalVisit medicalVisit;
        for(int i = 0; i < NUMBER_OF_MEDICAL_VISITS; i++){
            randomDate = new Date(ThreadLocalRandom.current().nextLong(java.util.Date.from(startDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()).getTime(), new Date().getTime()));
            numberOfMedicines = ThreadLocalRandom.current().nextInt(0, 7);

            medicalVisit = new MedicalVisit(
                    doctors.get(ThreadLocalRandom.current().nextInt(0, doctors.size())),
                    patients.get(ThreadLocalRandom.current().nextInt(0, patients.size())),
                    randomDate,
                    outpatientClinics.get(ThreadLocalRandom.current().nextInt(0, outpatientClinics.size()))
            );
            medicalVisitRepository.save(medicalVisit);
            for(int j = 0; j < numberOfMedicines; j++){
                prescriptionItemRepository.save(
                        new PrescriptionItem(
                                medicines.get(ThreadLocalRandom.current().nextInt(0, medicines.size())),
                                BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1d, 5d)),
                                medicalVisit
                        )
                );
            }
        }
    }

    public void generateAll(){
        generateAddresses();
        generatePatientsAndDoctors();
        generateOutpatientClinics();
        generateCompanies();
        generateMedicines();
        generateMedicalVisits();

    }
}
