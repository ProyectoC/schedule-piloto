package com.schedulepiloto.core.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.schedulepiloto.core.util.dto.Validator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RolAccountDto {

    private Long id;
    private String name;

    // Validations
    public Validator validationForCreateUser(UserAccountDto userAccountDto) {
        Validator validator = new Validator();
        if (this.getName().equals("Estudiante") && userAccountDto.getCollegeCareerEntity() == null) {
            validator.setValid(false);
            validator.addError("The student must register the university program.");
            return validator;
        }
        if (this.getName().equals("Super User")) {
            validator.setValid(false);
            validator.addError("A super user cannot register using this medium.");
            return validator;
        }
        if (!this.getName().equals("Estudiante") && userAccountDto.getCollegeCareerEntity() != null) {
            validator.setValid(false);
            validator.addError("The user should not specify an academic program.");
            return validator;
        }
        validator.setValid(true);
        return validator;
    }
}
