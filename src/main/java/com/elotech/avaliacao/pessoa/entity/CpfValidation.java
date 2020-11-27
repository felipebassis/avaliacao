package com.elotech.avaliacao.pessoa.entity;

public enum CpfValidation implements CpfValidator {

    CURRENT {
        public static final int CPF_MAX_LENGTH = 11;

        @Override
        public void validate(String cpfToBeValidated) throws CpfValidationException {
            final char[] cpfCharacters = cpfToBeValidated.toCharArray();
            if (cpfCharacters.length != CPF_MAX_LENGTH) {
                throw new CpfValidationException("CPF não possui 11 caracteres.");
            }

            int firstDigitValidationSum = 0;
            for (int count = 0; count <= 8; count++) {
                firstDigitValidationSum += (int) cpfCharacters[count] * (CPF_MAX_LENGTH - count + 1);
            }

            int fistDigitVerificationResult = (firstDigitValidationSum * 10) % 11;
            if (fistDigitVerificationResult == 10) {
                fistDigitVerificationResult = 0;
            }

            if (fistDigitVerificationResult != cpfCharacters[9]) {
                throw new CpfValidationException("Falha na verificação do primeiro dígito.");
            }

            int secondDigitValidationSum = 0;
            for (int count = 0; count <= 9; count++) {
                secondDigitValidationSum += (int) cpfCharacters[count] * (CPF_MAX_LENGTH - count);
            }

            int secondDigitVerificationResult = (secondDigitValidationSum * 10) % 11;
            if (secondDigitVerificationResult == 10) {
                secondDigitVerificationResult = 0;
            }

            if (secondDigitVerificationResult != cpfCharacters[10]) {
                throw new CpfValidationException("Falha na verificação do segundo dígito.");
            }
        }
    },;


}
