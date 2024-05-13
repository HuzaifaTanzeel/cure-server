package com.example.demo.CommonFramework.Enumerators;

public enum QualificationType {
    DEGREE(3501, "Degree"),
    CERTIFICATION(3502, "Certification"),
    TRAINING(3503, "Training"),
    DIPLOMA(3504, "Diploma"),
    LICENSE(3505, "License"),
    WORK_EXPERIENCE(3506, "Work Experience");

    private final int code;
    private final String displayContent;

    QualificationType(int code, String displayContent) {
        this.code = code;
        this.displayContent = displayContent;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayContent() {
        return displayContent;
    }

    public static QualificationType getByCode(int code) {
        for (QualificationType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid QualificationType code: " + code);
    }
}

