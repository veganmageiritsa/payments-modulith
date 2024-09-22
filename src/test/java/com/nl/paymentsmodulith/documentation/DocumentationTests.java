package com.nl.paymentsmodulith.documentation;

import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

import org.junit.jupiter.api.Test;

import com.nl.paymentsmodulith.PaymentsModulithApplication;



public class DocumentationTests {
    ApplicationModules modules = ApplicationModules.of(PaymentsModulithApplication.class);

    @Test
    void writeDocumentationSnippets(){
        new Documenter(modules)
                .writeModulesAsPlantUml(Documenter.DiagramOptions.defaults()
                        .withStyle(Documenter.DiagramOptions.DiagramStyle.UML))

                .writeIndividualModulesAsPlantUml(Documenter.DiagramOptions.defaults()
                .withStyle(Documenter.DiagramOptions.DiagramStyle.UML))
                .writeModuleCanvases();



        Documenter.DiagramOptions.defaults()
                .withStyle(Documenter.DiagramOptions.DiagramStyle.UML);
    }
}
