package com.example.demo.Model.Allergy;
import com.example.demo.CommonFramework.Enumerators.AllergyCategory;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ALLERGEN")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Allergen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALLERGEN_ID")
    private Long allergenId;

    @Column(name = "ALLERGY_NAME", length = 40)
    private String allergyName;

    @Column(name = "ALLERGY_CATEGORY")
    //@Enumerated(EnumType.ORDINAL)
    private AllergyCategory allergyCategoryCd;


    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @OneToMany(mappedBy = "allergen", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<PatientAllergy> allergyHistories=new ArrayList<>();

    // Other methods, if needed

    public void addPatientAllergies(PatientAllergy patientAllergy) {
        allergyHistories.add(patientAllergy);

        patientAllergy.setAllergen(this);
    }


}
