package com.elotech.avaliacao.pessoa.entity;

import java.util.Arrays;
import java.util.List;

public enum CpfValidation implements CpfValidator {

    CURRENT {
        private static final int cpfMaxLength = 11;

        private final List<String> knowInvalidCpfs = Arrays.asList("00000000000",
                "11111111111",
                "22222222222",
                "33333333333",
                "44444444444",
                "55555555555",
                "66666666666",
                "77777777777",
                "88888888888",
                "99999999999");

        @Override
        public void validate(String cpfToBeValidated) throws CpfValidationException {
            final char[] cpfCharacters = cpfToBeValidated.toCharArray();
            if (cpfCharacters.length != cpfMaxLength) {
                throw new CpfValidationException("CPF não possui 11 caracteres.");
            }
            if (knowInvalidCpfs.contains(cpfToBeValidated)) {
                throw new CpfValidationException("CPF inválido.");
            }

            int firstDigitValidationSum = 0;
            for (int count = 0; count <= 8; count++) {
                firstDigitValidationSum += Integer.parseInt(String.valueOf(cpfCharacters[count])) * (cpfMaxLength - count - 1);
            }

            int fistDigitVerificationResult = (firstDigitValidationSum * 10) % 11;
            if (fistDigitVerificationResult == 10) {
                fistDigitVerificationResult = 0;
            }

            if (fistDigitVerificationResult != Integer.parseInt(String.valueOf(cpfCharacters[9]))) {
                throw new CpfValidationException("Falha na verificação do primeiro dígito.");
            }

            int secondDigitValidationSum = 0;
            for (int count = 0; count <= 9; count++) {
                secondDigitValidationSum += Integer.parseInt(String.valueOf(cpfCharacters[count])) * (cpfMaxLength - count);
            }

            int secondDigitVerificationResult = (secondDigitValidationSum * 10) % 11;
            if (secondDigitVerificationResult == 10) {
                secondDigitVerificationResult = 0;
            }

            if (secondDigitVerificationResult != Integer.parseInt(String.valueOf(cpfCharacters[10]))) {
                throw new CpfValidationException("Falha na verificação do segundo dígito.");
            }
        }
    },
    ;


}
