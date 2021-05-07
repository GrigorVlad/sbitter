package me.grigor.sbitter.response;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ResultMessages {

    private final Map<String, String> errors;
    private final Map<String, String> warnings;
    private final Map<String, String> infos;

    public ResultMessages() {
        this.errors = new HashMap<>();
        this.warnings = new HashMap<>();
        this.infos = new HashMap<>();
    }

    public void addError(String errorKey, String errorValue) {
        errors.put(errorKey, errorValue);
    }

    public void addWarning(String warningKey, String warningValue) {
        warnings.put(warningKey, warningValue);
    }

    public void addInfos(String infoKey, String infoValue) {
        infos.put(infoKey, infoValue);
    }

    public boolean errorsExists() {
        return !errors.isEmpty();
    }

    public boolean warningsExists() {
        return !warnings.isEmpty();
    }

    public boolean infosExists() {
        return !infos.isEmpty();
    }
}
