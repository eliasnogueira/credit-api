package com.eliasnogueira.credit.integration.data.provider;

import com.eliasnogueira.credit.integration.data.factory.SimulationDataFactory;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static com.eliasnogueira.credit.integration.changeless.SimulationErrorData.ERRORS_AMOUNT_GREATER;
import static com.eliasnogueira.credit.integration.changeless.SimulationErrorData.ERRORS_AMOUNT_LESS;
import static com.eliasnogueira.credit.integration.changeless.SimulationErrorData.ERRORS_CPF;
import static com.eliasnogueira.credit.integration.changeless.SimulationErrorData.ERRORS_EMAIL;
import static com.eliasnogueira.credit.integration.changeless.SimulationErrorData.ERRORS_INSTALLMENTS_GREATER;
import static com.eliasnogueira.credit.integration.changeless.SimulationErrorData.ERRORS_INSTALLMENTS_LESS;
import static com.eliasnogueira.credit.integration.changeless.SimulationErrorData.ERRORS_NAME;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SimulationDataProvider {

    private SimulationDataProvider() {
    }

    public static Stream<Arguments> failedValidations() {
        var simulationDataFactory = new SimulationDataFactory();

        var simulationLessThanMinAmount = simulationDataFactory.simulationLessThanMinAmount();
        var simulationExceedAmount = simulationDataFactory.simulationExceedAmount();
        var simulationLessThanMinInstallments = simulationDataFactory.simulationLessThanMinInstallments();
        var simulationExceedInstallments = simulationDataFactory.simulationExceedInstallments();
        var simulationWithNotValidEmail = simulationDataFactory.simulationWithNotValidEmail();
        var simulationWithEmptyCPF = simulationDataFactory.simulationWithEmptyCPF();
        var simulationWithEmptyName = simulationDataFactory.simulationWithEmptyName();

        return Stream.of(
                arguments(simulationLessThanMinAmount, ERRORS_AMOUNT_GREATER.key, ERRORS_AMOUNT_GREATER.message),
                arguments(simulationExceedAmount, ERRORS_AMOUNT_LESS.key, ERRORS_AMOUNT_LESS.message),
                arguments(simulationLessThanMinInstallments, ERRORS_INSTALLMENTS_GREATER.key, ERRORS_INSTALLMENTS_GREATER.message),
                arguments(simulationExceedInstallments, ERRORS_INSTALLMENTS_LESS.key, ERRORS_INSTALLMENTS_LESS.message),
                arguments(simulationWithNotValidEmail, ERRORS_EMAIL.key, ERRORS_EMAIL.message),
                arguments(simulationWithEmptyCPF, ERRORS_CPF.key, ERRORS_CPF.message),
                arguments(simulationWithEmptyName, ERRORS_NAME.key, ERRORS_NAME.message)
        );
    }
}
