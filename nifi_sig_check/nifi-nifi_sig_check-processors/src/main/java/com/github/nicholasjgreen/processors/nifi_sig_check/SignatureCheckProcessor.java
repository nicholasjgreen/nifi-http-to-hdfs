/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.nicholasjgreen.processors.nifi_sig_check;

import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.annotation.lifecycle.OnScheduled;
import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.logging.ComponentLog;
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.ProcessorInitializationContext;
import org.apache.nifi.processor.Relationship;
import org.apache.nifi.processor.util.StandardValidators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// Useful example code here: https://www.nifi.rocks/developing-a-custom-apache-nifi-processor-json/
// Specification for Signature checking here: https://tools.ietf.org/id/draft-cavage-http-signatures-11.html

@Tags({"nicholasjgreen"})
@CapabilityDescription("Checks an HTTP signature for validity")
//@SeeAlso({})
//@ReadsAttributes({@ReadsAttribute(attribute="Authorization", description="Inspects this attribute to determine how to do auth. Other attributes are read depending on the content of the Authorisation headerde")})
//@WritesAttributes({@WritesAttribute(attribute="", description="")})
public class SignatureCheckProcessor extends AbstractProcessor {

    public static final PropertyDescriptor MY_PROPERTY = new PropertyDescriptor
            .Builder().name("MY_PROPERTY")
            .displayName("My property")
            .description("Example Property")
            .required(true)
            .addValidator(StandardValidators.NON_EMPTY_VALIDATOR)
            .build();

    public static final Relationship SUCCESS_RELATIONSHIP = new Relationship.Builder()
            .name("SUCCESS")
            .description("Success relationship")
            .build();

    private List<PropertyDescriptor> properties;

    private Set<Relationship> relationships;
    private ComponentLog logger;
    //private ComponentLog logger;

    @Override
    protected void init(final ProcessorInitializationContext context) {
        final List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
        descriptors.add(MY_PROPERTY);
        this.properties = Collections.unmodifiableList(descriptors);

        final Set<Relationship> relationships = new HashSet<Relationship>();
        relationships.add(SUCCESS_RELATIONSHIP);
        this.relationships = Collections.unmodifiableSet(relationships);
        this.logger = context.getLogger();
    }

    @Override
    public Set<Relationship> getRelationships() {
        return this.relationships;
    }

    @Override
    public final List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        return properties;
    }

    /*@OnScheduled
    public void onScheduled(final ProcessContext context) {

    }*/

    @Override
    public void onTrigger(final ProcessContext context, final ProcessSession session) throws ProcessException {
        logger.info("Triggered");
        FlowFile flowFile = session.get();
        if ( flowFile == null ) {
            logger.info("No flow :(");
            return;
        }
        logger.info("Getting auth");
        String authDescriptor = flowFile.getAttribute("Authorization");
        logger.info("Got auth: " + authDescriptor);
        session.transfer(flowFile, SUCCESS_RELATIONSHIP);
    }
}
