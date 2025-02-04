/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.kogito.quarkus.workflow.deployment;

import java.util.Collection;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.Index;
import org.junit.jupiter.api.BeforeAll;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.process.persistence.marshaller.AbstractMarshallerGeneratorTest;
import org.kie.kogito.codegen.process.persistence.marshaller.MarshallerGenerator;
import org.kie.kogito.codegen.process.persistence.proto.ProtoGenerator;

public class JandexMarshallerGeneratorTest extends AbstractMarshallerGeneratorTest<ClassInfo> {

    protected static Index indexWithAllClass;

    @BeforeAll
    protected static void indexOfTestClasses() {
        indexWithAllClass = JandexTestUtils.createTestIndex();
    }

    @Override
    protected MarshallerGenerator generator(KogitoBuildContext context, Collection<ClassInfo> rawDataClasses) {
        return new JandexMarshallerGenerator(context, rawDataClasses);
    }

    @Override
    protected ProtoGenerator.Builder<ClassInfo, JandexProtoGenerator> protoGeneratorBuilder() {
        return JandexProtoGenerator.builder(indexWithAllClass);
    }

    @Override
    protected ClassInfo convertToType(Class clazz) {
        return JandexTestUtils.findClassInfo(indexWithAllClass, clazz);
    }
}
